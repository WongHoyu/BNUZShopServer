package com.wong.pay.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RefundOrderMapper {

    // 退款用
    void deleteOrder(@Param("outTradeNo") String outTradeNo, @Param("deletedTime") String deletedTime);

}
