package com.moralok.mall.service;

import com.moralok.mall.domain.entity.UmsDeliveryAddress;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户收货地址表 服务类
 * </p>
 *
 * @author moralok
 * @since 2020-09-16
 */
public interface IUmsDeliveryAddressService extends IService<UmsDeliveryAddress> {

    /**
     * 添加地址
     *
     * @param address 地址
     */
    void add(UmsDeliveryAddress address);

    /**
     * 查询地址列表
     *
     * @return 地址列表
     */
    List<UmsDeliveryAddress> listByUserId();

    /**
     * 查询指定ID地址
     *
     * @param id 地址ID
     * @return
     */
    UmsDeliveryAddress getByIdAndUserId(Integer id);

    /**
     * 更新指定ID的地址
     *
     * @param id 地址ID
     * @param address 地址
     * @return
     */
    void updateByIdAndUserId(Integer id, UmsDeliveryAddress address);

    /**
     * 删除指定ID的地址
     *
     * @param id 地址ID
     */
    void deleteByIdAndUserId(Integer id);
}
