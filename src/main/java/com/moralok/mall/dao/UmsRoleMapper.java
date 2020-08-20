package com.moralok.mall.dao;

import com.moralok.mall.domain.entity.UmsRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author moralok
 * @since 2020-08-20
 */
public interface UmsRoleMapper extends BaseMapper<UmsRole> {

    /**
     * 根据用户ID查询角色
     *
     * @param userId
     * @return
     */
    List<UmsRole> getRoles(@Param("userId") Integer userId);
}
