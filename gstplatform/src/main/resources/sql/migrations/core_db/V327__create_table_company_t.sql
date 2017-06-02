CREATE TABLE IF NOT EXISTS `company_t` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `gstin` VARCHAR(15) NULL,
  `company_name` VARCHAR(256) NULL,
  `contact_name` VARCHAR(60) NULL,
  `office_phone` VARCHAR(60) NULL,
  `home_phone` VARCHAR(60) NULL,
  `mobile` VARCHAR(60) NULL,
  `fax` VARCHAR(60) NULL,
  `email` VARCHAR(60) NULL,
  `gstn_reg_no` VARCHAR(10) NULL,
  `pan_no` VARCHAR(10) NULL,
  `address_line1` VARCHAR(256) NULL,
  `address_line2` VARCHAR(256) NULL,
  `city` VARCHAR(60) NULL,
  `state` VARCHAR(60) NULL,
  `country` VARCHAR(60) NULL,
  `pin` VARCHAR(10) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `gstin_UNIQUE` (`gstin` ASC));

INSERT IGNORE INTO `m_permission` (`grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ('company', 'CREATE_COMPANY', 'CREATE', 'COMPANY', 0);
INSERT IGNORE INTO `m_permission` (`grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ('company', 'UPDATE_COMPANY', 'UPDATE', 'COMPANY', 0);

