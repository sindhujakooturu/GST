UPDATE `m_office` SET `name`='Trigital' WHERE `id`='1';
INSERT IGNORE INTO `m_role` (`name`, `description`, `is_disabled`) VALUES ('CA', 'ca role', '0');

insert ignore into m_role_permission values(
(select id from m_role mr where mr.name='CA'),
(select id from m_permission mp where mp.code = 'READ_GSTR1FILEINVOICEDATA'));

insert ignore into m_role_permission values(
(select id from m_role mr where mr.name='CA'),
(select id from m_permission mp where mp.code = 'UPDATE_GSTR1FILEINVOICEDATASTATUS'));


INSERT IGNORE INTO `m_office`(`parent_id`,`name`,`opening_date`)
VALUES(1,'Basic',now());

INSERT IGNORE INTO `m_office`(`parent_id`,`name`,`opening_date`)
VALUES(1,'Advance',now());
