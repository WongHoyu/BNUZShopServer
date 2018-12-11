package com.wong.pay.dao;

import com.wong.pay.domain.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface NotificationMapper {
    // 当同步通知到达时，更新订单结束时间
    void updateOrderFinishedTime(@Param("outTradeNo") String outTradeNo, @Param("finishedTime") String finishedTime);

    // 异步更新所用
    Orders checkOrder(@Param("outTradeNo") String outTradeNo, @Param("totalAmount") String totalAmount, @Param("sellerId") String sellerId);

}
