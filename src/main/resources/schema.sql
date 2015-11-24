CREATE SCHEMA IF NOT EXISTS `bugztracker`;
SET SCHEMA `bugztracker`;
DROP TABLE IF EXISTS `issue_attachment`;
CREATE TABLE `issue_attachment` (`id` bigint(20) NOT NULL,`issue_id` bigint(20) NOT NULL,`attachment_path` mediumtext NOT NULL,PRIMARY KEY (`id`));
DROP TABLE IF EXISTS `issue_comment`;
CREATE TABLE `issue_comment` (`id` bigint(20) NOT NULL,`user_id_sender` bigint(20) NOT NULL,`comment` varchar(500) NOT NULL,`date` date NOT NULL,`issue_id` bigint(20) NOT NULL,PRIMARY KEY (`id`));
DROP TABLE IF EXISTS `issue`;
CREATE TABLE `issue` (`id` bigint(20) NOT NULL,`name` varchar(300) NOT NULL,`date` date NOT NULL,`priority` VARCHAR(200) NOT NULL,`user_id_cr` bigint(20) NOT NULL,`user_id_as` bigint(20) NOT NULL,`status` VARCHAR(200) NOT NULL,`description` longtext,`project_id` bigint(20) NOT NULL,`category` VARCHAR(200) NOT NULL,`version` decimal(2,1) NOT NULL, PRIMARY KEY ( `id`));
DROP TABLE IF EXISTS `participant`;
CREATE TABLE `participant` (`id` bigint(20) NOT NULL,`user_id` bigint(20) NOT NULL,`project_id` bigint(20) NOT NULL,PRIMARY KEY (`id`));
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (`id` bigint(20) NOT NULL,`name` varchar(300) NOT NULL,`date` date NOT NULL,`description` longtext,PRIMARY KEY (`id`));
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (`id` bigint(20) NOT NULL,`full_name` varchar(100) NOT NULL,`password` varchar(10) NOT NULL,`email` varchar(50) NOT NULL,`date_expired` datetime DEFAULT NULL,PRIMARY KEY (`id`),UNIQUE KEY EMAIL_UNIQUE_0 (`email`));

ALTER TABLE `issue` ADD CONSTRAINT `project_id_is_frk` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`);
ALTER TABLE `issue` ADD CONSTRAINT `user_id_as_frk` FOREIGN KEY (`user_id_as`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE NO ACTION;
ALTER TABLE `issue` ADD CONSTRAINT `user_id_cr_frk` FOREIGN KEY (`user_id_cr`) REFERENCES `user` (`id`);
ALTER TABLE `issue_attachment` ADD CONSTRAINT `issue_id_att_frk` FOREIGN KEY (`issue_id`) REFERENCES `issue` (`id`);
ALTER TABLE `issue_comment` ADD CONSTRAINT `issue_id_frk` FOREIGN KEY (`issue_id`) REFERENCES `issue` (`id`);
ALTER TABLE `issue_comment` ADD CONSTRAINT `user_id_sender_frk` FOREIGN KEY (`user_id_sender`) REFERENCES `user` (`id`);
ALTER TABLE `participant` ADD CONSTRAINT `project_id_frk` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`);
ALTER TABLE `participant` ADD CONSTRAINT `user_id_frk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

INSERT INTO `user` VALUES (1,'Yuliia Vovk','827ccb0eea','juliia.vovk@gmail.com',NULL);
