package com.my.dao;

import com.my.annotation.MyBatisRepository;
import com.my.entity.OrderRecord;

import java.util.List;

/**
 * @author QinHe at 2019-07-29
 */
@MyBatisRepository
public interface OrderRecordDao {
    OrderRecord get(Long id);

    List<OrderRecord> getList();
}
