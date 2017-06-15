CREATE TABLE IF NOT EXISTS `g_sac_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sac_seq_id` varchar(4) DEFAULT NULL,
  `service_name` varchar(60) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  `sac_tax_collection` varchar(10) DEFAULT NULL,
  `sac_other_reciept` varchar(10) DEFAULT NULL,
  `sac_deduct_refund` varchar(10) DEFAULT NULL,
  `sac_penalty` varchar(10) DEFAULT NULL,
  `is_deleted` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sacSeqId_UNIQUE` (`sac_seq_id`),
  UNIQUE KEY `serviceName_UNIQUE` (`service_name`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=latin1;

INSERT IGNORE INTO `m_permission` (`grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ('sacdata', 'CREATE_SACDATA', 'CREATE', 'SACDATA', 0);
INSERT IGNORE INTO `m_permission` (`grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ('sacdata', 'UPDATE_SACDATA', 'UPDATE', 'SACDATA', 0);
INSERT IGNORE INTO `m_permission` (`grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ('sacdata', 'DELETE_SACDATA', 'DELETE', 'SACDATA', 0);

