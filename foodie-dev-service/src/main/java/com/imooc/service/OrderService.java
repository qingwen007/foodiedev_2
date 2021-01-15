package com.imooc.service;

import com.imooc.pojo.bo.SubmitOrderBO;
import com.imooc.pojo.vo.OrderVO;

import java.util.List;

public interface OrderService {
    public OrderVO createOrder(SubmitOrderBO submitOrderBO);
}
