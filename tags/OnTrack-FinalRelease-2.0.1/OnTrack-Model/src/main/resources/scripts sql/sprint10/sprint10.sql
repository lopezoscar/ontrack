-- MySQL dump 10.13  Distrib 5.5.20, for Win64 (x86)
--
-- Host: localhost    Database: ontrack
-- ------------------------------------------------------
-- Server version	5.5.20

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
-- Current Database: `ontrack`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `ontrack` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ontrack`;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comments` (
  `id_comment` bigint(20) NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL,
  `text` text,
  `date` varchar(100) DEFAULT NULL,
  `id_issue` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_comment`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entry_document_file`
--

DROP TABLE IF EXISTS `entry_document_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entry_document_file` (
  `id_document_file` bigint(20) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) DEFAULT NULL,
  `file_content` blob,
  `file_length` bigint(20) DEFAULT NULL,
  `file_type` varchar(255) DEFAULT NULL,
  `id_issue_entry` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_document_file`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entry_document_file`
--

LOCK TABLES `entry_document_file` WRITE;
/*!40000 ALTER TABLE `entry_document_file` DISABLE KEYS */;
/*!40000 ALTER TABLE `entry_document_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issue`
--

DROP TABLE IF EXISTS `issue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `issue` (
  `id_issue` bigint(20) NOT NULL AUTO_INCREMENT,
  `current_status` bigint(20) DEFAULT NULL,
  `issue_type` bigint(20) DEFAULT NULL,
  `parent_issue` bigint(20) DEFAULT NULL,
  `id_project` bigint(20) DEFAULT NULL,
  `title` text,
  `description` text,
  `reporter` varchar(255) DEFAULT NULL,
  `owner` bigint(20) DEFAULT NULL,
  `code` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_issue`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue`
--

LOCK TABLES `issue` WRITE;
/*!40000 ALTER TABLE `issue` DISABLE KEYS */;
INSERT INTO `issue` VALUES (1,6,9,NULL,9,NULL,NULL,NULL,2,NULL);
/*!40000 ALTER TABLE `issue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issue_actions`
--

DROP TABLE IF EXISTS `issue_actions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `issue_actions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `action` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue_actions`
--

LOCK TABLES `issue_actions` WRITE;
/*!40000 ALTER TABLE `issue_actions` DISABLE KEYS */;
INSERT INTO `issue_actions` VALUES (1,'{0} creó el Issue {1} con el ID: {2}'),(2,'{0} se modificó el Issue con el ID: {2}');
/*!40000 ALTER TABLE `issue_actions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issue_entry`
--

DROP TABLE IF EXISTS `issue_entry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `issue_entry` (
  `id_issue_entry` bigint(20) NOT NULL AUTO_INCREMENT,
  `value` text,
  `owner` bigint(20) DEFAULT NULL,
  `id_issue` bigint(20) DEFAULT NULL,
  `id_issue_property` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_issue_entry`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue_entry`
--

LOCK TABLES `issue_entry` WRITE;
/*!40000 ALTER TABLE `issue_entry` DISABLE KEYS */;
/*!40000 ALTER TABLE `issue_entry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issue_property`
--

DROP TABLE IF EXISTS `issue_property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `issue_property` (
  `id_issue_property` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_issue_property`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue_property`
--

LOCK TABLES `issue_property` WRITE;
/*!40000 ALTER TABLE `issue_property` DISABLE KEYS */;
INSERT INTO `issue_property` VALUES (1,'Precio',1);
/*!40000 ALTER TABLE `issue_property` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issue_property_by_workflow`
--

DROP TABLE IF EXISTS `issue_property_by_workflow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `issue_property_by_workflow` (
  `id_workflow` bigint(20) NOT NULL,
  `id_issue_property` bigint(20) NOT NULL,
  PRIMARY KEY (`id_workflow`,`id_issue_property`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue_property_by_workflow`
--

LOCK TABLES `issue_property_by_workflow` WRITE;
/*!40000 ALTER TABLE `issue_property_by_workflow` DISABLE KEYS */;
INSERT INTO `issue_property_by_workflow` VALUES (1,1);
/*!40000 ALTER TABLE `issue_property_by_workflow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issue_property_type`
--

DROP TABLE IF EXISTS `issue_property_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `issue_property_type` (
  `id_property` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_property`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue_property_type`
--

LOCK TABLES `issue_property_type` WRITE;
/*!40000 ALTER TABLE `issue_property_type` DISABLE KEYS */;
INSERT INTO `issue_property_type` VALUES (1,'Texto'),(2,'Numerico'),(3,'Fecha'),(4,'Moneda');
/*!40000 ALTER TABLE `issue_property_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issue_status`
--

DROP TABLE IF EXISTS `issue_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `issue_status` (
  `id_issue_status` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` text,
  `id_workflow` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_issue_status`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue_status`
--

LOCK TABLES `issue_status` WRITE;
/*!40000 ALTER TABLE `issue_status` DISABLE KEYS */;
INSERT INTO `issue_status` VALUES (1,'Inicio',NULL),(2,'Fin',NULL),(3,'TODO',NULL),(4,'DONE',NULL),(5,'Estado',NULL),(6,'DOING',NULL),(7,'TEST',NULL),(8,'INIT',NULL),(9,'RELEASE',NULL);
/*!40000 ALTER TABLE `issue_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issue_status_by_workflow`
--

DROP TABLE IF EXISTS `issue_status_by_workflow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `issue_status_by_workflow` (
  `id_workflow` bigint(20) NOT NULL,
  `id_issue_status` bigint(20) NOT NULL,
  `position` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_workflow`,`id_issue_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue_status_by_workflow`
--

LOCK TABLES `issue_status_by_workflow` WRITE;
/*!40000 ALTER TABLE `issue_status_by_workflow` DISABLE KEYS */;
INSERT INTO `issue_status_by_workflow` VALUES (1,1,1),(1,2,2),(2,3,1),(2,4,2),(6,5,1),(7,1,1),(9,3,1),(9,4,3),(9,6,2),(10,3,1),(10,6,2),(10,7,3),(11,6,2),(11,8,1),(11,9,3);
/*!40000 ALTER TABLE `issue_status_by_workflow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issue_type`
--

DROP TABLE IF EXISTS `issue_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `issue_type` (
  `id_issue_type` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_project` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_issue_type`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue_type`
--

LOCK TABLES `issue_type` WRITE;
/*!40000 ALTER TABLE `issue_type` DISABLE KEYS */;
INSERT INTO `issue_type` VALUES (1,NULL,NULL),(2,NULL,NULL),(3,NULL,NULL),(4,NULL,NULL),(5,NULL,NULL),(6,NULL,NULL),(7,NULL,NULL),(8,NULL,NULL),(9,NULL,'Issue'),(10,NULL,'Bug');
/*!40000 ALTER TABLE `issue_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issue_type_by_workflow`
--

DROP TABLE IF EXISTS `issue_type_by_workflow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `issue_type_by_workflow` (
  `id_workflow` int(11) NOT NULL,
  `id_issue_type` varchar(45) NOT NULL,
  PRIMARY KEY (`id_workflow`,`id_issue_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue_type_by_workflow`
--

LOCK TABLES `issue_type_by_workflow` WRITE;
/*!40000 ALTER TABLE `issue_type_by_workflow` DISABLE KEYS */;
/*!40000 ALTER TABLE `issue_type_by_workflow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issue_types_and_status`
--

DROP TABLE IF EXISTS `issue_types_and_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `issue_types_and_status` (
  `id_issue_type` bigint(20) NOT NULL,
  `id_issue_status` bigint(20) NOT NULL,
  `id_workflow` bigint(20) NOT NULL,
  PRIMARY KEY (`id_issue_type`,`id_issue_status`,`id_workflow`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue_types_and_status`
--

LOCK TABLES `issue_types_and_status` WRITE;
/*!40000 ALTER TABLE `issue_types_and_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `issue_types_and_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_issues`
--

DROP TABLE IF EXISTS `log_issues`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_issues` (
  `id_log` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` text,
  `date` varchar(100) DEFAULT NULL,
  `action` int(11) DEFAULT NULL,
  `id_issue` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_log`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_issues`
--

LOCK TABLES `log_issues` WRITE;
/*!40000 ALTER TABLE `log_issues` DISABLE KEYS */;
INSERT INTO `log_issues` VALUES (1,'{0} creó el Issue {1} con el ID: {2}','Sat Nov 09 19:43:04 GMT-03:00 2013',1,1);
/*!40000 ALTER TABLE `log_issues` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modules`
--

DROP TABLE IF EXISTS `modules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `modules` (
  `id_module` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_module`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modules`
--

LOCK TABLES `modules` WRITE;
/*!40000 ALTER TABLE `modules` DISABLE KEYS */;
INSERT INTO `modules` VALUES (1,'Project'),(2,'Issue');
/*!40000 ALTER TABLE `modules` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissions`
--

DROP TABLE IF EXISTS `permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permissions` (
  `id_permission` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(45) DEFAULT NULL,
  `acronym` varchar(45) DEFAULT NULL,
  `module` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_permission`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissions`
--

LOCK TABLES `permissions` WRITE;
/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;
INSERT INTO `permissions` VALUES (1,'Leer Issues','READ_ISSUE',1);
/*!40000 ALTER TABLE `permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persons`
--

DROP TABLE IF EXISTS `persons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persons` (
  `id_person` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_person`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persons`
--

LOCK TABLES `persons` WRITE;
/*!40000 ALTER TABLE `persons` DISABLE KEYS */;
INSERT INTO `persons` VALUES (1,NULL,NULL,'santiago.lohigorri@gmail.com'),(2,'Oscar','López','lopezoscar.job@gmail.com'),(3,NULL,NULL,'mariamedina.69@gmail.com'),(4,NULL,NULL,'joselarocathomas@gmail.com'),(5,NULL,NULL,'roseguzmant@gmail.com'),(6,NULL,NULL,'norberto.bech@gmail.com'),(7,NULL,NULL,'rdarias@gmail.com');
/*!40000 ALTER TABLE `persons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `process_history`
--

DROP TABLE IF EXISTS `process_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `process_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `status` varchar(255) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  `id_issue` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `process_history`
--

LOCK TABLES `process_history` WRITE;
/*!40000 ALTER TABLE `process_history` DISABLE KEYS */;
INSERT INTO `process_history` VALUES (1,'Abierto','Wed Aug 14 19:14:37 ART 2013',25),(2,'Abierto','Wed Aug 14 20:46:07 ART 2013',25),(3,'IssueStatus 1','Sat Sep 14 17:14:04 ART 2013',7),(4,'IssueStatus 1','Sat Sep 14 17:14:39 ART 2013',1),(5,'IssueStatus 1','Sat Sep 14 17:15:10 ART 2013',8),(6,'DOING','Sat Nov 09 19:43:04 GMT-03:00 2013',1);
/*!40000 ALTER TABLE `process_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `id_project` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `admin` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_project`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (1,'Project',1),(2,'Projecto',1),(3,'Example',1),(4,'ItP',1),(5,'UNPRO',1),(6,'Proyectooooo',1),(7,'fff',1),(8,'Projectoooo',1),(9,'OnTrack',1),(10,'AdminPrueba',1),(11,'AdminPro',1);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projects_roles`
--

DROP TABLE IF EXISTS `projects_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `projects_roles` (
  `id_project` bigint(20) NOT NULL,
  `id_role` bigint(20) NOT NULL,
  PRIMARY KEY (`id_project`,`id_role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projects_roles`
--

LOCK TABLES `projects_roles` WRITE;
/*!40000 ALTER TABLE `projects_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `projects_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_permission` (
  `id_role` bigint(20) NOT NULL,
  `id_permission` bigint(20) NOT NULL,
  PRIMARY KEY (`id_role`,`id_permission`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
INSERT INTO `role_permission` VALUES (1,1);
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id_role` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  `acronym` varchar(45) DEFAULT NULL,
  `id_project` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'Usuario','ROLE_USER',NULL),(2,'Administrador','ROLE_ADMIN',NULL),(3,'Desarrollador','DEV',1);
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `services`
--

DROP TABLE IF EXISTS `services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `services` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `path` text,
  `method` varchar(45) DEFAULT NULL,
  `class` varchar(255) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `services`
--

LOCK TABLES `services` WRITE;
/*!40000 ALTER TABLE `services` DISABLE KEYS */;
INSERT INTO `services` VALUES (1,'listServices','http://localhost:8080/OnTrack-SOA/consolesrv/listservices/','GET','com.sappe.ontrack.soa.resources.ConsoleService',NULL),(2,'getAllIssuePropertyTypes','http://localhost:8080/OnTrack-SOA/issuepropertysrv/allissuepropertytypes/','GET','com.sappe.ontrack.soa.resources.IssuePropertyService',NULL),(3,'getAllIssuePropertyTypes','http://localhost:8080/OnTrack-SOA/issuepropertytypesrv/getallissuepropertytypes/','POST','com.sappe.ontrack.soa.resources.IssuePropertyTypeService',NULL),(4,'createIssuePropertyType','http://localhost:8080/OnTrack-SOA/issuepropertytypesrv/createissuepropertytype/','POST','com.sappe.ontrack.soa.resources.IssuePropertyTypeService',NULL),(5,'getIssuePropertyTypeByID','http://localhost:8080/OnTrack-SOA/issuepropertytypesrv/getissuepropertytypebyid/{pk}/','GET','com.sappe.ontrack.soa.resources.IssuePropertyTypeService',NULL),(6,'updateIssuePropertyType','http://localhost:8080/OnTrack-SOA/issuepropertytypesrv/updateissuepropertytype/','POST','com.sappe.ontrack.soa.resources.IssuePropertyTypeService',NULL),(7,'deleteIssuePropertyType','http://localhost:8080/OnTrack-SOA/issuepropertytypesrv/deleteissuepropertytype/','POST','com.sappe.ontrack.soa.resources.IssuePropertyTypeService',NULL),(8,'getIssuesByOwnerId','http://localhost:8080/OnTrack-SOA/issuesrv/getissuesbyownerid/{id}/','GET','com.sappe.ontrack.soa.resources.IssueService',NULL),(9,'getIssuesByReporter','http://localhost:8080/OnTrack-SOA/issuesrv/getissusbyreporter/{reporter}/','GET','com.sappe.ontrack.soa.resources.IssueService',NULL),(10,'getIssuesByStatus','http://localhost:8080/OnTrack-SOA/issuesrv/getissusbystatus/','POST','com.sappe.ontrack.soa.resources.IssueService',NULL),(11,'getIssuesByType','http://localhost:8080/OnTrack-SOA/issuesrv/getissusbytype/','POST','com.sappe.ontrack.soa.resources.IssueService',NULL),(12,'getIssueById','http://localhost:8080/OnTrack-SOA/issuesrv/getissuebyid/{pk}/','GET','com.sappe.ontrack.soa.resources.IssueService',NULL),(13,'createIssue','http://localhost:8080/OnTrack-SOA/issuesrv/saveissue/','POST','com.sappe.ontrack.soa.resources.IssueService',NULL),(14,'mergeIssue','http://localhost:8080/OnTrack-SOA/issuesrv/mergeissue/','POST','com.sappe.ontrack.soa.resources.IssueService',NULL),(15,'getEntriesByIssueId','http://localhost:8080/OnTrack-SOA/issuesrv/getentriesbyissueid/{issueid}/','GET','com.sappe.ontrack.soa.resources.IssueService',NULL),(16,'getIssuesByCodee','http://localhost:8080/OnTrack-SOA/issuesrv/getissuesbycode/{code}/','GET','com.sappe.ontrack.soa.resources.IssueService',NULL),(17,'createIssueStatus','http://localhost:8080/OnTrack-SOA/issuestatussrv/createissuestatus/','POST','com.sappe.ontrack.soa.resources.IssueStatusService',NULL),(18,'getIssueStatusById','http://localhost:8080/OnTrack-SOA/issuestatussrv/getissuestatusbyid/{pk}/','GET','com.sappe.ontrack.soa.resources.IssueStatusService',NULL),(19,'getAllIssueStatus','http://localhost:8080/OnTrack-SOA/issuestatussrv/getallissuestatus/','GET','com.sappe.ontrack.soa.resources.IssueStatusService',NULL),(20,'updateIssueStatus','http://localhost:8080/OnTrack-SOA/issuestatussrv/updateissuestatus/','POST','com.sappe.ontrack.soa.resources.IssueStatusService',NULL),(21,'deleteIssueStatus','http://localhost:8080/OnTrack-SOA/issuestatussrv/deleteissuestatus/','POST','com.sappe.ontrack.soa.resources.IssueStatusService',NULL),(22,'getIssueStatusByIssueType','http://localhost:8080/OnTrack-SOA/issuestatussrv/getissuestatusbyissuetype/','POST','com.sappe.ontrack.soa.resources.IssueStatusService',NULL),(23,'createIssueType','http://localhost:8080/OnTrack-SOA/issuetypesrv/createissuetype/','POST','com.sappe.ontrack.soa.resources.IssueTypeService',NULL),(24,'getIssueTypeById','http://localhost:8080/OnTrack-SOA/issuetypesrv/getissuetypebyid/{pk}/','GET','com.sappe.ontrack.soa.resources.IssueTypeService',NULL),(25,'getAllIssueType','http://localhost:8080/OnTrack-SOA/issuetypesrv/getallissuetype/','POST','com.sappe.ontrack.soa.resources.IssueTypeService',NULL),(26,'updateIssueType','http://localhost:8080/OnTrack-SOA/issuetypesrv/updateissuetype/','POST','com.sappe.ontrack.soa.resources.IssueTypeService',NULL),(27,'deleteIssueType','http://localhost:8080/OnTrack-SOA/issuetypesrv/deleteissuestatus/','POST','com.sappe.ontrack.soa.resources.IssueTypeService',NULL),(28,'getIssueTypeByProject','http://localhost:8080/OnTrack-SOA/issuetypesrv/getissuetypesbyprojectid/{projectid}/','GET','com.sappe.ontrack.soa.resources.IssueTypeService',NULL),(29,'sendEmail','http://localhost:8080/OnTrack-SOA/notificationsrv/sendEmail/','POST','com.sappe.ontrack.soa.resources.NotificationService',NULL),(30,'getAllProjects','http://localhost:8080/OnTrack-SOA/projectsrv/getallprojects/','GET','com.sappe.ontrack.soa.resources.ProjectService',NULL),(31,'saveProject','http://localhost:8080/OnTrack-SOA/projectsrv/saveproject/','POST','com.sappe.ontrack.soa.resources.ProjectService',NULL),(32,'getIssuesByProject','http://localhost:8080/OnTrack-SOA/projectsrv/getissuesbyprojectid/{id}/','GET','com.sappe.ontrack.soa.resources.ProjectService',NULL),(33,'getRoleById','http://localhost:8080/OnTrack-SOA/rolesrv/getrolebyid/{pk}/','GET','com.sappe.ontrack.soa.resources.RoleService',NULL),(34,'createRole','http://localhost:8080/OnTrack-SOA/rolesrv/createrole/','POST','com.sappe.ontrack.soa.resources.RoleService',NULL),(35,'updateRole','http://localhost:8080/OnTrack-SOA/rolesrv/updaterole/','POST','com.sappe.ontrack.soa.resources.RoleService',NULL),(36,'deleteRole','http://localhost:8080/OnTrack-SOA/rolesrv/deleterole/','POST','com.sappe.ontrack.soa.resources.RoleService',NULL),(37,'getAllRoles','http://localhost:8080/OnTrack-SOA/rolesrv/getallroles/','GET','com.sappe.ontrack.soa.resources.RoleService',NULL),(38,'userByUserName','http://localhost:8080/OnTrack-SOA/usersrv/userbyusername/','POST','com.sappe.ontrack.soa.resources.UserService',NULL),(39,'getUserById','http://localhost:8080/OnTrack-SOA/usersrv/getuserbyid/{id}/','GET','com.sappe.ontrack.soa.resources.UserService',NULL),(40,'getAllUsers','http://localhost:8080/OnTrack-SOA/usersrv/getallusers/','GET','com.sappe.ontrack.soa.resources.UserService',NULL),(41,'contactsByUserName','http://localhost:8080/OnTrack-SOA/usersrv/contacts/','POST','com.sappe.ontrack.soa.resources.UserService',NULL);
/*!40000 ALTER TABLE `services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `services_parameters`
--

DROP TABLE IF EXISTS `services_parameters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `services_parameters` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` text,
  `id_service` int(11) DEFAULT NULL,
  `body` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `services_parameters`
--

LOCK TABLES `services_parameters` WRITE;
/*!40000 ALTER TABLE `services_parameters` DISABLE KEYS */;
INSERT INTO `services_parameters` VALUES (1,'com.sappe.ontrack.model.issues.IssuePropertyType',NULL,4,'{\"id\":null,\"name\":null}'),(2,'java.lang.Long',NULL,5,NULL),(3,'com.sappe.ontrack.model.issues.IssuePropertyType',NULL,6,'{\"id\":null,\"name\":null}'),(4,'com.sappe.ontrack.model.issues.IssuePropertyType',NULL,7,'{\"id\":null,\"name\":null}'),(5,'java.lang.Long',NULL,8,NULL),(6,'java.lang.String',NULL,9,NULL),(7,'com.sappe.ontrack.model.issues.IssueStatus',NULL,10,'{\"id\":null,\"description\":null}'),(8,'com.sappe.ontrack.model.issues.IssueType',NULL,11,'{\"id\":null,\"description\":null}'),(9,'java.lang.Long',NULL,12,NULL),(10,'com.sappe.ontrack.model.issues.Issue',NULL,13,'{\"id\":null,\"title\":null,\"description\":null,\"reporter\":null,\"owner\":null,\"currentStatus\":null,\"issueType\":null,\"parent\":null,\"project\":null,\"entries\":null}'),(11,'com.sappe.ontrack.model.issues.Issue',NULL,14,'{\"id\":null,\"title\":null,\"description\":null,\"reporter\":null,\"owner\":null,\"currentStatus\":null,\"issueType\":null,\"parent\":null,\"project\":null,\"entries\":null}'),(12,'java.lang.Long',NULL,15,NULL),(13,'java.lang.String',NULL,16,NULL),(14,'com.sappe.ontrack.model.issues.IssueStatus',NULL,17,'{\"id\":null,\"description\":null}'),(15,'java.lang.Long',NULL,18,NULL),(16,'com.sappe.ontrack.model.issues.IssueStatus',NULL,20,'{\"id\":null,\"description\":null}'),(17,'com.sappe.ontrack.model.issues.IssueStatus',NULL,21,'{\"id\":null,\"description\":null}'),(18,'com.sappe.ontrack.model.issues.IssueType',NULL,22,'{\"id\":null,\"description\":null}'),(19,'com.sappe.ontrack.model.issues.IssueType',NULL,23,'{\"id\":null,\"description\":null}'),(20,'java.lang.Long',NULL,24,NULL),(21,'com.sappe.ontrack.model.issues.IssueType',NULL,26,'{\"id\":null,\"description\":null}'),(22,'com.sappe.ontrack.model.issues.IssueType',NULL,27,'{\"id\":null,\"description\":null}'),(23,'long',NULL,28,NULL),(24,'com.sappe.ontrack.model.notifications.NotificationDTO',NULL,29,'{\"from\":null,\"to\":null,\"cc\":null,\"cco\":null,\"subject\":null,\"body\":null,\"priority\":null}'),(25,'com.sappe.ontrack.model.issues.Project',NULL,31,'{\"id\":null,\"name\":null,\"roles\":null,\"users\":null}'),(26,'java.lang.Long',NULL,32,NULL),(27,'java.lang.Integer',NULL,33,NULL),(28,'com.sappe.ontrack.model.users.Role',NULL,34,'{\"id\":null,\"roleName\":null,\"acronym\":null}'),(29,'com.sappe.ontrack.model.users.Role',NULL,35,'{\"id\":null,\"roleName\":null,\"acronym\":null}'),(30,'com.sappe.ontrack.model.users.Role',NULL,36,'{\"id\":null,\"roleName\":null,\"acronym\":null}'),(31,'java.lang.String',NULL,38,'\"\"'),(32,'java.lang.Long',NULL,39,NULL),(33,'com.sappe.ontrack.model.users.User',NULL,41,'{\"id\":null,\"firstName\":null,\"lastName\":null,\"mail\":null,\"userName\":null,\"password\":null,\"roles\":null,\"projects\":null}');
/*!40000 ALTER TABLE `services_parameters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `id_person` bigint(20) NOT NULL,
  `id_role` int(11) NOT NULL,
  PRIMARY KEY (`id_person`,`id_role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1),(1,2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id_person` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_person`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'UserName',NULL),(2,'Java Developer',NULL),(3,'',NULL),(4,'',NULL),(5,'',NULL),(6,'',NULL),(7,'',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_by_project`
--

DROP TABLE IF EXISTS `users_by_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_by_project` (
  `id_user` bigint(20) NOT NULL,
  `id_project` bigint(20) NOT NULL,
  PRIMARY KEY (`id_user`,`id_project`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_by_project`
--

LOCK TABLES `users_by_project` WRITE;
/*!40000 ALTER TABLE `users_by_project` DISABLE KEYS */;
INSERT INTO `users_by_project` VALUES (1,1),(1,2),(1,3),(1,8),(2,9),(2,10),(2,11),(3,3),(3,6),(4,3),(5,5),(6,8),(7,9);
/*!40000 ALTER TABLE `users_by_project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workflow`
--

DROP TABLE IF EXISTS `workflow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `workflow` (
  `id_workflow` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_project` bigint(20) DEFAULT NULL,
  `id_issue_type` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_workflow`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workflow`
--

LOCK TABLES `workflow` WRITE;
/*!40000 ALTER TABLE `workflow` DISABLE KEYS */;
INSERT INTO `workflow` VALUES (1,3,1),(2,3,2),(3,4,3),(4,4,4),(5,4,5),(6,5,6),(7,6,7),(8,7,8),(9,8,9),(10,9,9),(11,9,10);
/*!40000 ALTER TABLE `workflow` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-11-23 11:30:23
