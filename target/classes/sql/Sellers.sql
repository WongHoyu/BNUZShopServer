CREATE TABLE `Sellers` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `seller_id` varchar(64) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Sellers_seller_id_index` (`seller_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8