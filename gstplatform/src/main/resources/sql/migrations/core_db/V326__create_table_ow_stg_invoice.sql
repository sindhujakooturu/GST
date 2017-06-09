CREATE TABLE `g_ow_stg_invoice` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `gstin` varchar(15) NOT NULL,
  `gstin_purchaser` varchar(15) NOT NULL,
  `c_name` varchar(50) DEFAULT NULL,
  `supplier_inv_no` varchar(50) DEFAULT NULL,
  `supplier_inv_date` date DEFAULT NULL,
  `supplier_inv_value` float(15,2) DEFAULT NULL,
  `supply_state_code` varchar(2) DEFAULT NULL,
  `order_no` varchar(30) DEFAULT NULL,
  `order_date` date DEFAULT NULL,
  `etin` varchar(15) DEFAULT NULL,
  `invoice_id` bigint(15) NOT NULL DEFAULT '0',
  `receipt_state_code` varchar(2) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `error_code` varchar(50) DEFAULT NULL,
  `error_descr` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_gowstginv_invid` (`invoice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `g_ow_stg_items` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `invoice_id` bigint(10) NOT NULL,
  `item_type` varchar(1) DEFAULT NULL,
  `item_code` varchar(10) DEFAULT NULL,
  `tax_value` double(15,2) DEFAULT NULL,
  `igst_rate` double(15,2) DEFAULT NULL,
  `igst_amount` double(15,2) DEFAULT NULL,
  `cgst_rate` double(15,2) DEFAULT NULL,
  `cgst_amount` double(15,2) DEFAULT NULL,
  `sgst_rate` double(15,2) DEFAULT NULL,
  `sgst_amount` double(15,2) DEFAULT NULL,
  `cess_rate` double(15,2) DEFAULT NULL,
  `cess_amount` double(15,2) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `error_code` varchar(100) DEFAULT NULL,
  `error_descriptor` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_gowstgitem_invid_idx` (`invoice_id`),
  CONSTRAINT `fk_gowstgitems_invid` FOREIGN KEY (`invoice_id`) REFERENCES `g_ow_stg_invoice` (`invoice_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

