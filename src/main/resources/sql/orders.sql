CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `out_trade_no` varchar(64) NOT NULL,
  `total_amount` varchar(9) DEFAULT NULL,
  `seller_id` varchar(64) DEFAULT NULL,
  `body` varchar(100) DEFAULT NULL COMMENT '订单详情\n',
  `create_time` varchar(16) DEFAULT NULL COMMENT '订单创建时间',
  `deleted_time` varchar(16) DEFAULT NULL COMMENT '订单撤销时间',
  `finished_time` varchar(16) DEFAULT NULL COMMENT '订单结束时间',
  `deleted` varchar(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `orders_Sellers_seller_id_fk` (`seller_id`),
  CONSTRAINT `orders_Sellers_seller_id_fk` FOREIGN KEY (`seller_id`) REFERENCES `Sellers` (`seller_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8