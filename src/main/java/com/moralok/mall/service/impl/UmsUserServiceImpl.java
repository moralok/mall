package com.moralok.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moralok.mall.dao.UmsUserMapper;
import com.moralok.mall.domain.CommonResult;
import com.moralok.mall.domain.constant.ResultCode;
import com.moralok.mall.domain.dto.user.UpdatePasswordParam;
import com.moralok.mall.domain.entity.UmsUser;
import com.moralok.mall.service.IUmsUserService;
import com.moralok.mall.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author moralok
 * @since 2020-08-17
 */
@Service
@Slf4j
public class UmsUserServiceImpl extends ServiceImpl<UmsUserMapper, UmsUser> implements IUmsUserService {

    @Autowired
    private RedisService redisService;

    @Value("${redis.key.prefix.verificationCode}")
    private String redisKeyPrefixVerificationCode;

    @Value("${redis.key.expire.verificationCode}")
    private Integer verificationCodeExpireSecond;

    @Override
    public UmsUser getByUsername(String username) {
        LambdaQueryWrapper<UmsUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UmsUser::getUsername, username).last("limit 1");
        return getOne(queryWrapper);
    }

    @Override
    public UmsUser getByPhoneNumber(String phoneNumber) {
        LambdaQueryWrapper<UmsUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UmsUser::getPhoneNumber, phoneNumber).last("limit 1");
        return getOne(queryWrapper);
    }

    @Override
    public UmsUser getCurrentUser() {
        Subject subject = SecurityUtils.getSubject();
        UmsUser user = (UmsUser) subject.getPrincipal();
        // 脱敏
        if (user != null) {
            user.setPassword(null);
        }
        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult register(UmsUser user) {
        // 核对验证码
        verifyCode(user.getPhoneNumber(), user.getVerificationCode());
        // 检查用户信息是否已经注册
        LambdaQueryWrapper<UmsUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UmsUser::getUsername, user.getUsername()).or().eq(UmsUser::getPhoneNumber, user.getPhoneNumber());
        List<UmsUser> existUsers = list(queryWrapper);
        if (!CollectionUtils.isEmpty(existUsers)) {
            return CommonResult.failed("用户名或手机号已注册");
        }
        // 储存用户信息
        user.setPassword(getPassword(user.getPassword(), user.getUsername()));
        user.setCreatedAt(LocalDateTime.now());
        save(user);
        return CommonResult.success(null, "注册成功");
    }

    @Override
    public CommonResult updatePassword(UpdatePasswordParam param) {
        // 核对验证码
        verifyCode(param.getPhoneNumber(), param.getVerificationCode());
        UmsUser user = getByPhoneNumber(param.getPhoneNumber());
        if (user == null) {
            throw ResultCode.USER_NOT_FOUND.generateException();
        }
        // 储存用户信息
        user.setPassword(getPassword(param.getPassword(), user.getUsername()));
        user.setUpdatedAt(LocalDateTime.now());
        updateById(user);
        return CommonResult.success(null, "修改密码成功");
    }

    @Override
    public CommonResult getVerificationCode(String phoneNumber) {
        String code = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
        log.info("手机号码 {} 的验证码为 {}", phoneNumber, code);
        redisService.set(getRedisKey(phoneNumber), code, verificationCodeExpireSecond);
        // todo: 发送验证码
        return CommonResult.success(code, "获取验证码成功");
    }

    /**
     * 获得加密后的密码
     * @param password 明文密码
     * @param username 用户名
     * @return 加密后的密码
     */
    private String getPassword(String password, String username) {
        return new SimpleHash("md5", password, username, 2).toString();
    }

    /**
     * 核对验证码
     *
     * @param phoneNumber 手机号码
     * @param code 验证码
     */
    private void verifyCode(String phoneNumber, String code) {
        if (StringUtils.isEmpty(code)) {
            throw ResultCode.VERIFY_CODE_FAILED.generateException();
        }
        String key = getRedisKey(phoneNumber);
        String theCode = (String) redisService.get(key);
        if (!Objects.equals(code, theCode)) {
            throw ResultCode.VERIFY_CODE_FAILED.generateException();
        }
        // 删除 key，避免重复使用
        redisService.remove(key);
    }

    /**
     * 获取验证码 redis key
     *
     * @param phoneNumber 手机号码
     * @return redis key
     */
    private String getRedisKey(String phoneNumber) {
        return redisKeyPrefixVerificationCode + phoneNumber;
    }
}
