package com.my.controller;

import com.my.entity.OrderRecord;
import com.my.model.CommonResult;
import com.my.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author QinHe at 2019-07-29
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/get.htm")
    @ResponseBody
    public CommonResult<OrderRecord> get(Long id) {
        return CommonResult.success(orderService.get(id));
    }

    @GetMapping("/getList.htm")
    @ResponseBody
    public CommonResult<List<OrderRecord>> getList() {
        return CommonResult.success(orderService.getList());
    }
}
