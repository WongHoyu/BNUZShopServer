package com.wong.pay.dao;

import com.wong.pay.domain.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CreateOrderMapper {

    void insertOrder(@Param("outTradeNo") String outTradeNo, @Param("totalAmount") String totalAmount, @Param("sellerId") String sellerId, @Param("body") String body, @Param("createTime") String createTime);

    Orders checkOrder(@Param("outTradeNo") String outTradeNo, @Param("totalAmount") String totalAmount, @Param("sellerId") String sellerId);

    void deleteOrder(@Param("outTradeNo") String outTradeNo, @Param("deletedTime") String deletedTime);

    void updateOrderFinishedTime(@Param("outTradeNo") String outTradeNo, @Param("finishedTime") String finishedTime);
}
