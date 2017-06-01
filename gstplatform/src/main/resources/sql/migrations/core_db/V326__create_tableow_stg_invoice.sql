CREATE TABLE `m_ow_stg_invoice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
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
  `invoice_id` bigint(15) DEFAULT NULL,
  `receipt_state_code` varchar(2) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `error_code` varchar(50) DEFAULT NULL,
  `error_descr` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `m_permission` (`grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ('outwardinv', 'CREATE_OUTWARDINV', 'CREATE', 'OUTWARDINV', 0);
INSERT INTO `m_permission` (`grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ('outwardinv', 'UPDATE_OUTWARDINV', 'UPDATE', 'OUTWARDINV', 0);
