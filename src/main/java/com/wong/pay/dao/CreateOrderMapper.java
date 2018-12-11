package com.wong.pay.dao;

import com.wong.pay.domain.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CreateOrderMapper {

    // 持久化订单
    void insertOrder(@Param("outTradeNo") String outTradeNo, @Param("totalAmount") String totalAmount, @Param("sellerId") String sellerId, @Param("body") String body, @Param("createTime") String createTime);

}
