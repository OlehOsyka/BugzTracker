CREATE DATABASE  IF NOT EXISTS `bugztracker` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `bugztracker`;
-- MySQL dump 10.13  Distrib 5.7.9, for osx10.9 (x86_64)
--
-- Host: localhost    Database: bugztracker
-- ------------------------------------------------------
-- Server version	5.7.10

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `git_project_creds`
--

DROP TABLE IF EXISTS `git_project_creds`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `git_project_creds` (
  `email` varchar(50) NOT NULL,
  `password` varchar(300) NOT NULL,
  `project_id` bigint(20) NOT NULL,
  `repository_name` varchar(300) NOT NULL,
  `nick` varchar(50) NOT NULL,
  PRIMARY KEY (`repository_name`),
  KEY `project_git_id_frk_idx` (`project_id`),
  CONSTRAINT `project_git_id_frk` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `git_project_creds`
--

LOCK TABLES `git_project_creds` WRITE;
/*!40000 ALTER TABLE `git_project_creds` DISABLE KEYS */;
/*!40000 ALTER TABLE `git_project_creds` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issue`
--

DROP TABLE IF EXISTS `issue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `issue` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(300) NOT NULL,
  `date` datetime NOT NULL,
  `date_update` datetime DEFAULT NULL,
  `priority` varchar(30) NOT NULL,
  `status` varchar(30) NOT NULL,
  `description` longtext,
  `category` varchar(30) NOT NULL,
  `version` float NOT NULL,
  `user_creator_id` bigint(20) NOT NULL,
  `user_assignee_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_cr_issue_id_frk_idx` (`user_creator_id`),
  KEY `user_ass_issue_id_frk_idx` (`user_assignee_id`),
  CONSTRAINT `user_ass_issue_id_frk` FOREIGN KEY (`user_assignee_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_cr_issue_id_frk` FOREIGN KEY (`user_creator_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue`
--

LOCK TABLES `issue` WRITE;
/*!40000 ALTER TABLE `issue` DISABLE KEYS */;
/*!40000 ALTER TABLE `issue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issue_attachment`
--

DROP TABLE IF EXISTS `issue_attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `issue_attachment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `issue_id` bigint(20) NOT NULL,
  `attachment_path` varchar(300) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `issue_att_id_frk_idx` (`issue_id`),
  CONSTRAINT `issue_att_id_frk` FOREIGN KEY (`issue_id`) REFERENCES `issue` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue_attachment`
--

LOCK TABLES `issue_attachment` WRITE;
/*!40000 ALTER TABLE `issue_attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `issue_attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issue_comment`
--

DROP TABLE IF EXISTS `issue_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `issue_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comment` varchar(300) NOT NULL,
  `issue_id` bigint(20) NOT NULL,
  `date` datetime NOT NULL,
  `user_creator_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_cr_com_id_frk_idx` (`user_creator_id`),
  KEY `issue_com_id_frk_idx` (`issue_id`),
  CONSTRAINT `issue_com_id_frk` FOREIGN KEY (`issue_id`) REFERENCES `issue` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_cr_com_id_frk` FOREIGN KEY (`user_creator_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue_comment`
--

LOCK TABLES `issue_comment` WRITE;
/*!40000 ALTER TABLE `issue_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `issue_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issue_commit`
--

DROP TABLE IF EXISTS `issue_commit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `issue_commit` (
  `issue_id` bigint(20) NOT NULL,
  `commit` varchar(50) NOT NULL,
  `date` datetime NOT NULL,
  `project_id` bigint(20) NOT NULL,
  PRIMARY KEY (`project_id`,`commit`),
  KEY `issue_commit_id_frk_idx` (`issue_id`),
  CONSTRAINT `issue_commit_id_frk` FOREIGN KEY (`issue_id`) REFERENCES `issue` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `project_commit_id_frk` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue_commit`
--

LOCK TABLES `issue_commit` WRITE;
/*!40000 ALTER TABLE `issue_commit` DISABLE KEYS */;
/*!40000 ALTER TABLE `issue_commit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participant`
--

DROP TABLE IF EXISTS `participant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `participant` (
  `user_id` bigint(20) NOT NULL,
  `project_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`project_id`),
  KEY `project_id_part_frk_idx` (`project_id`),
  CONSTRAINT `project_id_part_frk` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_id_part_frk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participant`
--

LOCK TABLES `participant` WRITE;
/*!40000 ALTER TABLE `participant` DISABLE KEYS */;
/*!40000 ALTER TABLE `participant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `date` datetime NOT NULL,
  `description` longtext,
  `is_archived` tinyint(1) DEFAULT '0',
  `user_creator_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_cr_project_id_frk_idx` (`user_creator_id`),
  CONSTRAINT `user_cr_project_id_frk` FOREIGN KEY (`user_creator_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `full_name` varchar(100) NOT NULL,
  `password` varchar(15) NOT NULL,
  `email` varchar(50) NOT NULL,
  `is_active` tinyint(1) DEFAULT '0',
  `due_register_date` datetime DEFAULT NULL,
  `date_expired` datetime DEFAULT NULL,
  `registration_token` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-12 13:34:22
