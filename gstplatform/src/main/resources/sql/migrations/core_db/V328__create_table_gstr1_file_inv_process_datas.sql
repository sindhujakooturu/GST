CREATE TABLE `g_gstr1_file_invoice_data` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `gstin` varchar(15) NOT NULL,
  `fp` varchar(6) NOT NULL,
  `gross_turnover` varchar(45) DEFAULT NULL,
  `file_no` varchar(25) NOT NULL,
  `version` int(2) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `assigned_to` varchar(15) DEFAULT NULL,
  `error_code` varchar(45) DEFAULT 'null',
  `error_descriptor` varchar(1000) DEFAULT 'null',
  `review_comments` varchar(1000) DEFAULT 'null',
  PRIMARY KEY (`id`),
  UNIQUE KEY `file_no_UNIQUE` (`file_no`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `g_gstr1_file_b2b_invoice` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `gstin` varchar(15) NOT NULL,
  `fp` varchar(6) DEFAULT NULL,
  `file_no` varchar(25) NOT NULL DEFAULT 'null',
  `supplier_inv_no` varchar(50) DEFAULT NULL,
  `supplier_inv_date` date DEFAULT NULL,
  `supplier_inv_value` double(15,2) DEFAULT NULL,
  `supply_place` varchar(2) DEFAULT NULL,
  `order_no` varchar(30) DEFAULT NULL,
  `order_date` varchar(6) DEFAULT NULL,
  `etin` varchar(15) DEFAULT NULL,
  `invoice_id` bigint(15) NOT NULL,
  `flag` varchar(1) DEFAULT NULL,
  `chk_sum` varchar(50) DEFAULT NULL,
  `is_reverse` tinyint(4) DEFAULT NULL,
  `is_provisional` tinyint(4) DEFAULT NULL,
  `record_type` tinyint(4) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `error_code` varchar(500) DEFAULT 'null',
  `error_descriptor` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `invoice_id_UNIQUE` (`invoice_id`),
  KEY `fk_gstr1fileb2binv_fileno_idx` (`file_no`),
  CONSTRAINT `fk_gstr1fileb2binv_fileno` FOREIGN KEY (`file_no`) REFERENCES `g_gstr1_file_invoice_data` (`file_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `g_gstr1_file_b2b_items` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `invoice_id` bigint(15) NOT NULL,
  `file_no` varchar(15) DEFAULT NULL,
  `item_type` varchar(1) DEFAULT NULL,
  `item_code` varchar(5) DEFAULT NULL,
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
  `error_code` varchar(500) DEFAULT NULL,
  `error_descriptor` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_gstr1fileb2binvitm_invoiceid_idx` (`invoice_id`),
  CONSTRAINT `fk_gstr1fileb2binvitm_invoiceid` FOREIGN KEY (`invoice_id`) REFERENCES `g_gstr1_file_b2b_invoice` (`invoice_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

INSERT IGNORE INTO `m_permission` (`grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ('organisation', 'CREATE_GSTR1FILEINVOICEDATA', 'GSTR1FILEINVOICEDATA', 'CREATE', 0);

INSERT IGNORE INTO `m_permission` (`grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ('organisation', 'READ_GSTR1FILEINVOICEDATA', 'GSTR1FILEINVOICEDATA', 'READ', 0);

INSERT IGNORE INTO `m_permission` (`grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ('organisation', 'UPDATE_GSTR1FILEINVOICEDATA', 'GSTR1FILEINVOICEDATA', 'UPDATE', 0);

INSERT IGNORE INTO `m_permission` (`grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ('organisation', 'DELETE_GSTR1FILEINVOICEDATA', 'GSTR1FILEINVOICEDATA', 'DELETE', 0);



