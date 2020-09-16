package com.moralok.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.moralok.mall.domain.constant.ResultCode;
import com.moralok.mall.domain.entity.UmsDeliveryAddress;
import com.moralok.mall.dao.UmsDeliveryAddressMapper;
import com.moralok.mall.domain.entity.UmsUser;
import com.moralok.mall.service.IUmsDeliveryAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moralok.mall.service.IUmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 用户收货地址表 服务实现类
 * </p>
 *
 * @author moralok
 * @since 2020-09-16
 */
@Service
public class UmsDeliveryAddressServiceImpl extends ServiceImpl<UmsDeliveryAddressMapper, UmsDeliveryAddress> implements IUmsDeliveryAddressService {

    @Autowired
    private IUmsUserService userService;

    /**
     * 最大收货地址数量
     */
    private static final int MAX_COUNT_OF_ADDRESSES = 3;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(UmsDeliveryAddress address) {
        UmsUser user = userService.getCurrentUser();
        address.setUserId(user.getId());
        List<UmsDeliveryAddress> addresses = listByUserId();
        // 地址数量校验
        if (addresses.size() >= MAX_COUNT_OF_ADDRESSES) {
            throw ResultCode.DELIVERY_ADDRESS_MAX_COUNT_LIMIT.generateException();
        }
        // 如果是第一个，设置为默认地址
        if (addresses.size() == 0) {
            address.setDefaultStatus(true);
        }
        // 如果新增地址为默认，已有地址设置为非默认
        if (address.getDefaultStatus() && addresses.size() > 0) {
            addresses.forEach(item -> item.setDefaultStatus(false));
            updateBatchById(addresses);
        }
        save(address);
    }

    @Override
    public List<UmsDeliveryAddress> listByUserId() {
        UmsUser user = userService.getCurrentUser();
        LambdaQueryWrapper<UmsDeliveryAddress> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UmsDeliveryAddress::getUserId, user.getId())
                .orderByDesc(UmsDeliveryAddress::getDefaultStatus)
                .orderByAsc(UmsDeliveryAddress::getId);
        return list(queryWrapper);
    }

    @Override
    public UmsDeliveryAddress getByIdAndUserId(Integer id) {
        UmsUser user = userService.getCurrentUser();
        LambdaQueryWrapper<UmsDeliveryAddress> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UmsDeliveryAddress::getId, id).eq(UmsDeliveryAddress::getUserId, user.getId());
        return getOne(queryWrapper);
    }

    @Override
    public void updateByIdAndUserId(Integer id, UmsDeliveryAddress address) {
        address.setId(null).setUserId(null);
        UmsUser user = userService.getCurrentUser();
        LambdaUpdateWrapper<UmsDeliveryAddress> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(UmsDeliveryAddress::getId, id).eq(UmsDeliveryAddress::getUserId, user.getId());
        update(address, updateWrapper);
    }

    @Override
    public void deleteByIdAndUserId(Integer id) {
        UmsUser user = userService.getCurrentUser();
        LambdaQueryWrapper<UmsDeliveryAddress> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UmsDeliveryAddress::getId, id).eq(UmsDeliveryAddress::getUserId, user.getId());
        remove(queryWrapper);
    }
}
