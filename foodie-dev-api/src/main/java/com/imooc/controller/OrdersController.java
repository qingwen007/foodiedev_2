package com.imooc.controller;

import com.imooc.enums.PayMethod;
import com.imooc.pojo.bo.SubmitOrderBO;
import com.imooc.pojo.vo.OrderVO;
import com.imooc.service.OrderService;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "订单相关", tags = {"订单相关的api接口"})
@RequestMapping("orders")
@RestController
public class OrdersController {
    final static Logger logger = LoggerFactory.getLogger(OrdersController.class);
    @Autowired
    OrderService    orderService;

    @ApiOperation(value = "用户下单", notes = "用户下单", httpMethod = "POST")
    @PostMapping("/create")
    public IMOOCJSONResult create(
            @RequestBody SubmitOrderBO submitOrderBO,
            HttpServletRequest request,
            HttpServletResponse response){
        if(submitOrderBO.getPayMethod() != PayMethod.WEINXIN.type
                && submitOrderBO.getPayMethod() != PayMethod.ALIPAY.type)
        {
            return  IMOOCJSONResult.errorMsg("支付方式不支持！");
        }
        System.out.println(submitOrderBO.toString());
        // 1. 创建订单
        OrderVO orderVO = orderService.createOrder(submitOrderBO);

        return IMOOCJSONResult.ok();
    }
}
