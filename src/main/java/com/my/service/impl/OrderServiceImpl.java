package com.my.service.impl;

import com.github.pagehelper.PageHelper;
import com.my.dao.OrderRecordDao;
import com.my.entity.OrderRecord;
import com.my.service.OrderService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author QinHe at 2019-07-30
 */
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRecordDao orderRecordDao;

    public OrderServiceImpl(OrderRecordDao orderRecordDao) {
        this.orderRecordDao = orderRecordDao;
    }

    @Override
    public OrderRecord get(Long id) {
        return orderRecordDao.get(id);
    }

    @Override
    @RequiresPermissions("order")
    public List<OrderRecord> getList() {
        PageHelper.startPage(1, 10);
        return orderRecordDao.getList();
    }
}
