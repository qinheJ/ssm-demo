package com.my.service;

import com.my.entity.OrderRecord;

import java.util.List;

/**
 * @author QinHe at 2019-07-30
 */
public interface OrderService {
    OrderRecord get(Long id);

    List<OrderRecord> getList();
}
