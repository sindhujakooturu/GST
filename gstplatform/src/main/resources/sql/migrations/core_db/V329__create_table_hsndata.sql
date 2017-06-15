CREATE TABLE IF NOT EXISTS `g_hsn_data` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `hsn_code` varchar(8) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  `is_deleted` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `hsn_code_UNIQUE` (`hsn_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT IGNORE INTO `m_permission` (`grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ('hsndata', 'CREATE_HSNDATA', 'CREATE', 'HSNDATA', 0);
INSERT IGNORE INTO `m_permission` (`grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ('hsndata', 'UPDATE_HSNDATA', 'UPDATE', 'HSNDATA', 0);
INSERT IGNORE INTO `m_permission` (`grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ('hsndata', 'DELETE_HSNDATA', 'DELETE', 'HSNDATA', 0);

