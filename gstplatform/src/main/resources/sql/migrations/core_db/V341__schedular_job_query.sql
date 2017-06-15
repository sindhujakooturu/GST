INSERT ignore INTO `job` (`name`, `display_name`, `cron_expression`, `create_time`, `task_priority`, 
`scheduler_group`) VALUES ('Outward staging to process', 'Outward staging to process', '0 0/1 * 1/1 * ? *', now(), 4, 3);


INSERT IGNORE INTO `m_permission` (`grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ('organisation', 'UPDATE_GSTR1FILEINVOICESTATUS', 'GSTR1FILEINVOICESTATUS', 'UPDATE', 0);
