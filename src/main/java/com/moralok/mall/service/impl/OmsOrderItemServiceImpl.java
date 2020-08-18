package com.moralok.mall.service.impl;

import com.moralok.mall.domain.entity.OmsOrderItem;
import com.moralok.mall.dao.OmsOrderItemMapper;
import com.moralok.mall.service.IOmsOrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单中包含的商品 服务实现类
 * </p>
 *
 * @author moralok
 * @since 2020-08-18
 */
@Service
public class OmsOrderItemServiceImpl extends ServiceImpl<OmsOrderItemMapper, OmsOrderItem> implements IOmsOrderItemService {

}
