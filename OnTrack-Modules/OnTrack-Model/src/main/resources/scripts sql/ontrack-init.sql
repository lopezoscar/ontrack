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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue`
--

LOCK TABLES `issue` WRITE;
/*!40000 ALTER TABLE `issue` DISABLE KEYS */;
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
INSERT INTO `issue_actions` VALUES (1,'%s creó el Issue %s con el ID: %s'),(2,'%s se modificó el Issue con el ID: %s');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue_property`
--

LOCK TABLES `issue_property` WRITE;
/*!40000 ALTER TABLE `issue_property` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue_status`
--

LOCK TABLES `issue_status` WRITE;
/*!40000 ALTER TABLE `issue_status` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue_type`
--

LOCK TABLES `issue_type` WRITE;
/*!40000 ALTER TABLE `issue_type` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_issues`
--

LOCK TABLES `log_issues` WRITE;
/*!40000 ALTER TABLE `log_issues` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persons`
--

LOCK TABLES `persons` WRITE;
/*!40000 ALTER TABLE `persons` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `process_history`
--

LOCK TABLES `process_history` WRITE;
/*!40000 ALTER TABLE `process_history` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `services`
--

LOCK TABLES `services` WRITE;
/*!40000 ALTER TABLE `services` DISABLE KEYS */;
INSERT INTO `services` VALUES (1,'listServices','consolesrv/listservices/','GET','com.sappe.ontrack.soa.resources.ConsoleService',NULL),(2,'getAllIssuePropertyTypes','/issuepropertysrv/allissuepropertytypes/','GET','com.sappe.ontrack.soa.resources.IssuePropertyService',NULL),(3,'getAllIssuePropertyTypes','issuepropertytypesrv/getallissuepropertytypes/','POST','com.sappe.ontrack.soa.resources.IssuePropertyTypeService',NULL),(4,'createIssuePropertyType','issuepropertytypesrv/createissuepropertytype/','POST','com.sappe.ontrack.soa.resources.IssuePropertyTypeService',NULL),(5,'getIssuePropertyTypeByID','issuepropertytypesrv/getissuepropertytypebyid/{pk}/','GET','com.sappe.ontrack.soa.resources.IssuePropertyTypeService',NULL),(6,'updateIssuePropertyType','issuepropertytypesrv/updateissuepropertytype/','POST','com.sappe.ontrack.soa.resources.IssuePropertyTypeService',NULL),(7,'deleteIssuePropertyType','issuepropertytypesrv/deleteissuepropertytype/','POST','com.sappe.ontrack.soa.resources.IssuePropertyTypeService',NULL),(8,'getIssuesByOwnerId','issuesrv/getissuesbyownerid/{id}/','GET','com.sappe.ontrack.soa.resources.IssueService',NULL),(9,'getIssuesByReporter','issuesrv/getissusbyreporter/{reporter}/','GET','com.sappe.ontrack.soa.resources.IssueService',NULL),(10,'getIssuesByStatus','issuesrv/getissusbystatus/','POST','com.sappe.ontrack.soa.resources.IssueService',NULL),(11,'getIssuesByType','issuesrv/getissusbytype/','POST','com.sappe.ontrack.soa.resources.IssueService',NULL),(12,'saveIssue','issuesrv/saveissue/','POST','com.sappe.ontrack.soa.resources.IssueService',NULL),(13,'getIssueById','issuesrv/getissuebyid/{pk}/','GET','com.sappe.ontrack.soa.resources.IssueService',NULL),(14,'mergeIssue','issuesrv/mergeissue/','POST','com.sappe.ontrack.soa.resources.IssueService',NULL),(15,'reassign','issuesrv/reassignOwner/{currentOwner}/{newOwner}/','GET','com.sappe.ontrack.soa.resources.IssueService',NULL),(16,'listIssuesByUser','issuesrv/listIssuesByUser/','POST','com.sappe.ontrack.soa.resources.IssueService',NULL),(17,'listIssuesByUserFromProject','issuesrv/listIssuesByUserFromProject/','POST','com.sappe.ontrack.soa.resources.IssueService',NULL),(18,'getEntriesByIssueId','issuesrv/getentriesbyissueid/{issueid}/','GET','com.sappe.ontrack.soa.resources.IssueService',NULL),(19,'getIssuesByCodee','issuesrv/getissuesbycode/{code}/','GET','com.sappe.ontrack.soa.resources.IssueService',NULL),(20,'addCommentToIssue','issuesrv/addcommenttoissue/','POST','com.sappe.ontrack.soa.resources.IssueService',NULL),(21,'createIssueStatus','issuestatussrv/createissuestatus/','POST','com.sappe.ontrack.soa.resources.IssueStatusService',NULL),(22,'getIssueStatusById','issuestatussrv/getissuestatusbyid/{pk}/','GET','com.sappe.ontrack.soa.resources.IssueStatusService',NULL),(23,'getAllIssueStatus','issuestatussrv/getallissuestatus/','GET','com.sappe.ontrack.soa.resources.IssueStatusService',NULL),(24,'updateIssueStatus','issuestatussrv/updateissuestatus/','POST','com.sappe.ontrack.soa.resources.IssueStatusService',NULL),(25,'deleteIssueStatus','issuestatussrv/deleteissuestatus/','POST','com.sappe.ontrack.soa.resources.IssueStatusService',NULL),(26,'getIssueStatusByIssueType','issuestatussrv/getissuestatusbyissuetype/{issueTypeId}/{projectId}/','GET','com.sappe.ontrack.soa.resources.IssueStatusService',NULL),(27,'sendEmail','notificationsrv/sendEmail/','POST','com.sappe.ontrack.soa.resources.NotificationService',NULL),(28,'getAllPlaces','places/getAllPlaces/','GET','com.sappe.ontrack.soa.resources.PlacesService',NULL),(29,'getAllProjects','/projectsrv/getallprojects/','GET','com.sappe.ontrack.soa.resources.ProjectService',NULL),(30,'projectById','/projectsrv/projectbyid/{id}/','GET','com.sappe.ontrack.soa.resources.ProjectService',NULL),(31,'saveProject','/projectsrv/saveproject/','POST','com.sappe.ontrack.soa.resources.ProjectService',NULL),(32,'getIssuesByProject','/projectsrv/getissuesbyprojectid/{id}/','GET','com.sappe.ontrack.soa.resources.ProjectService',NULL),(33,'getProjectsByUser','/projectsrv/projectsbyuser/','POST','com.sappe.ontrack.soa.resources.ProjectService',NULL),(34,'getProjectsByAdmin','/projectsrv/projectsbyadmin/','POST','com.sappe.ontrack.soa.resources.ProjectService',NULL),(35,'getAllRoles','rolesrv/getallroles/','GET','com.sappe.ontrack.soa.resources.RoleService',NULL),(36,'deleteRole','rolesrv/deleterole/','POST','com.sappe.ontrack.soa.resources.RoleService',NULL),(37,'updateRole','rolesrv/updaterole/','POST','com.sappe.ontrack.soa.resources.RoleService',NULL),(38,'createRole','rolesrv/createrole/','POST','com.sappe.ontrack.soa.resources.RoleService',NULL),(39,'getRoleById','rolesrv/getrolebyid/{pk}/','GET','com.sappe.ontrack.soa.resources.RoleService',NULL),(40,'roleById','rolesrv/rolebyid/{id}/','GET','com.sappe.ontrack.soa.resources.RoleService',NULL),(41,'userByUserName','usersrv/userbyusername/','POST','com.sappe.ontrack.soa.resources.UserService',NULL),(42,'getAllUsers','usersrv/getallusers/','GET','com.sappe.ontrack.soa.resources.UserService',NULL),(43,'getUserById','usersrv/getuserbyid/{id}/','GET','com.sappe.ontrack.soa.resources.UserService',NULL),(44,'createUser','usersrv/createuser/','POST','com.sappe.ontrack.soa.resources.UserService',NULL),(45,'updateUser','usersrv/updateuser/','POST','com.sappe.ontrack.soa.resources.UserService',NULL),(46,'userByUserEmail','usersrv/userbyemail/','POST','com.sappe.ontrack.soa.resources.UserService',NULL),(47,'contactsByUserName','usersrv/contacts/','POST','com.sappe.ontrack.soa.resources.UserService',NULL),(48,'createWorkflow','workflowsrv/createworkflow/','POST','com.sappe.ontrack.soa.resources.WorkflowService',NULL),(49,'createWorkflow','workflowsrv/createworkflowbylist/','POST','com.sappe.ontrack.soa.resources.WorkflowService',NULL),(50,'listWorkflowsByUser','workflowsrv/listworkflowsbyuser/','POST','com.sappe.ontrack.soa.resources.WorkflowService',NULL),(51,'listWorkflowsByProject','workflowsrv/listworkflowsbyproject/','POST','com.sappe.ontrack.soa.resources.WorkflowService',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `services_parameters`
--

LOCK TABLES `services_parameters` WRITE;
/*!40000 ALTER TABLE `services_parameters` DISABLE KEYS */;
INSERT INTO `services_parameters` VALUES (1,'com.sappe.ontrack.model.issues.IssuePropertyType',NULL,4,'{\"id\":null,\"name\":null}'),(2,'java.lang.Long',NULL,5,NULL),(3,'com.sappe.ontrack.model.issues.IssuePropertyType',NULL,6,'{\"id\":null,\"name\":null}'),(4,'com.sappe.ontrack.model.issues.IssuePropertyType',NULL,7,'{\"id\":null,\"name\":null}'),(5,'java.lang.Long',NULL,8,NULL),(6,'java.lang.String',NULL,9,NULL),(7,'com.sappe.ontrack.model.issues.IssueStatus',NULL,10,'{\"id\":null,\"description\":null}'),(8,'com.sappe.ontrack.model.issues.IssueType',NULL,11,'{\"id\":null,\"description\":null}'),(9,'java.lang.Long',NULL,12,NULL),(10,'com.sappe.ontrack.model.issues.Issue',NULL,13,'{\"id\":null,\"title\":null,\"description\":null,\"reporter\":null,\"owner\":null,\"currentStatus\":null,\"issueType\":null,\"parent\":null,\"project\":null,\"entries\":null}'),(11,'com.sappe.ontrack.model.issues.Issue',NULL,14,'{\"id\":null,\"title\":null,\"description\":null,\"reporter\":null,\"owner\":null,\"currentStatus\":null,\"issueType\":null,\"parent\":null,\"project\":null,\"entries\":null}'),(12,'java.lang.Long',NULL,15,NULL),(13,'java.lang.String',NULL,16,NULL),(14,'com.sappe.ontrack.model.issues.IssueStatus',NULL,17,'{\"id\":null,\"description\":null}'),(15,'java.lang.Long',NULL,18,NULL),(16,'com.sappe.ontrack.model.issues.IssueStatus',NULL,20,'{\"id\":null,\"description\":null}'),(17,'com.sappe.ontrack.model.issues.IssueStatus',NULL,21,'{\"id\":null,\"description\":null}'),(18,'com.sappe.ontrack.model.issues.IssueType',NULL,22,'{\"id\":null,\"description\":null}'),(19,'com.sappe.ontrack.model.issues.IssueType',NULL,23,'{\"id\":null,\"description\":null}'),(20,'java.lang.Long',NULL,24,NULL),(21,'com.sappe.ontrack.model.issues.IssueType',NULL,26,'{\"id\":null,\"description\":null}'),(22,'com.sappe.ontrack.model.issues.IssueType',NULL,27,'{\"id\":null,\"description\":null}'),(23,'long',NULL,28,NULL),(24,'com.sappe.ontrack.model.notifications.NotificationDTO',NULL,29,'{\"from\":null,\"to\":null,\"cc\":null,\"cco\":null,\"subject\":null,\"body\":null,\"priority\":null}'),(25,'com.sappe.ontrack.model.issues.Project',NULL,31,'{\"id\":null,\"name\":null,\"roles\":null,\"users\":null}'),(26,'java.lang.Long',NULL,32,NULL),(27,'java.lang.Integer',NULL,33,NULL),(28,'com.sappe.ontrack.model.users.Role',NULL,34,'{\"id\":null,\"roleName\":null,\"acronym\":null}'),(29,'com.sappe.ontrack.model.users.Role',NULL,35,'{\"id\":null,\"roleName\":null,\"acronym\":null}'),(30,'com.sappe.ontrack.model.users.Role',NULL,36,'{\"id\":null,\"roleName\":null,\"acronym\":null}'),(31,'java.lang.String',NULL,38,'\"\"'),(32,'java.lang.Long',NULL,39,NULL),(33,'com.sappe.ontrack.model.users.User',NULL,41,'{\"id\":null,\"firstName\":null,\"lastName\":null,\"mail\":null,\"userName\":null,\"password\":null,\"roles\":null,\"projects\":null}'),(34,'com.sappe.ontrack.model.issues.IssuePropertyType',NULL,4,'{\"id\":null,\"name\":null}'),(35,'java.lang.Long',NULL,5,NULL),(36,'com.sappe.ontrack.model.issues.IssuePropertyType',NULL,6,'{\"id\":null,\"name\":null}'),(37,'com.sappe.ontrack.model.issues.IssuePropertyType',NULL,7,'{\"id\":null,\"name\":null}'),(38,'java.lang.Long',NULL,8,NULL),(39,'java.lang.String',NULL,9,NULL),(40,'com.sappe.ontrack.model.issues.IssueStatus',NULL,10,'{\"id\":null,\"description\":null,\"position\":0}'),(41,'com.sappe.ontrack.model.issues.IssueType',NULL,11,'{\"id\":null,\"description\":null}'),(42,'com.sappe.ontrack.model.issues.Issue',NULL,12,'{\"id\":null,\"code\":null,\"title\":null,\"description\":null,\"reporter\":null,\"owner\":null,\"currentStatus\":null,\"issueType\":null,\"parent\":null,\"project\":null,\"entries\":null,\"comments\":null,\"logs\":null,\"history\":null}'),(43,'java.lang.Long',NULL,13,NULL),(44,'com.sappe.ontrack.model.issues.Issue',NULL,14,'{\"id\":null,\"code\":null,\"title\":null,\"description\":null,\"reporter\":null,\"owner\":null,\"currentStatus\":null,\"issueType\":null,\"parent\":null,\"project\":null,\"entries\":null,\"comments\":null,\"logs\":null,\"history\":null}'),(45,'java.lang.Long',NULL,15,NULL),(46,'java.lang.Long',NULL,15,NULL),(47,'com.sappe.ontrack.model.users.User',NULL,16,'{\"id\":null,\"firstName\":null,\"lastName\":null,\"mail\":null,\"token\":null,\"userName\":null,\"password\":null,\"roles\":null}'),(48,'com.sappe.ontrack.model.users.User',NULL,17,'{\"id\":null,\"firstName\":null,\"lastName\":null,\"mail\":null,\"token\":null,\"userName\":null,\"password\":null,\"roles\":null}'),(49,'java.lang.Long',NULL,18,NULL),(50,'java.lang.String',NULL,19,NULL),(51,'com.sappe.ontrack.model.issues.IssueComment',NULL,20,'{\"id\":null,\"author\":null,\"text\":null,\"date\":null,\"issueID\":null}'),(52,'com.sappe.ontrack.model.issues.IssueStatus',NULL,21,'{\"id\":null,\"description\":null,\"position\":0}'),(53,'java.lang.Long',NULL,22,NULL),(54,'com.sappe.ontrack.model.issues.IssueStatus',NULL,24,'{\"id\":null,\"description\":null,\"position\":0}'),(55,'com.sappe.ontrack.model.issues.IssueStatus',NULL,25,'{\"id\":null,\"description\":null,\"position\":0}'),(56,'java.lang.Long',NULL,26,NULL),(57,'java.lang.Long',NULL,26,NULL),(58,'com.sappe.ontrack.model.notifications.NotificationDTO',NULL,27,'{\"from\":null,\"to\":[],\"cc\":[],\"cco\":[],\"subject\":null,\"body\":null,\"priority\":null}'),(59,'java.lang.Long',NULL,30,NULL),(60,'com.sappe.ontrack.model.issues.Project',NULL,31,'{\"id\":null,\"name\":null,\"roles\":null,\"users\":null,\"admin\":null}'),(61,'java.lang.Long',NULL,32,NULL),(62,'com.sappe.ontrack.model.users.User',NULL,33,'{\"id\":null,\"firstName\":null,\"lastName\":null,\"mail\":null,\"token\":null,\"userName\":null,\"password\":null,\"roles\":null}'),(63,'com.sappe.ontrack.model.users.User',NULL,34,'{\"id\":null,\"firstName\":null,\"lastName\":null,\"mail\":null,\"token\":null,\"userName\":null,\"password\":null,\"roles\":null}'),(64,'com.sappe.ontrack.model.users.Role',NULL,36,'{\"id\":null,\"roleName\":null,\"acronym\":null}'),(65,'com.sappe.ontrack.model.users.Role',NULL,37,'{\"id\":null,\"roleName\":null,\"acronym\":null}'),(66,'com.sappe.ontrack.model.users.Role',NULL,38,'{\"id\":null,\"roleName\":null,\"acronym\":null}'),(67,'java.lang.Integer',NULL,39,NULL),(68,'java.lang.Long',NULL,40,NULL),(69,'java.lang.String',NULL,41,'\"\"'),(70,'java.lang.Long',NULL,43,NULL),(71,'com.sappe.ontrack.model.users.User',NULL,44,'{\"id\":null,\"firstName\":null,\"lastName\":null,\"mail\":null,\"token\":null,\"userName\":null,\"password\":null,\"roles\":null}'),(72,'com.sappe.ontrack.model.users.User',NULL,45,'{\"id\":null,\"firstName\":null,\"lastName\":null,\"mail\":null,\"token\":null,\"userName\":null,\"password\":null,\"roles\":null}'),(73,'java.lang.String',NULL,46,'\"\"'),(74,'com.sappe.ontrack.model.users.User',NULL,47,'{\"id\":null,\"firstName\":null,\"lastName\":null,\"mail\":null,\"token\":null,\"userName\":null,\"password\":null,\"roles\":null}'),(75,'com.sappe.ontrack.model.issues.Workflow',NULL,48,'{\"id\":null,\"issueType\":null,\"issueStatusByWorkflow\":null,\"issueStatus\":null,\"issueProperties\":null,\"project\":null}'),(76,'java.lang.String',NULL,49,'\"\"'),(77,'com.sappe.ontrack.model.users.User',NULL,50,'{\"id\":null,\"firstName\":null,\"lastName\":null,\"mail\":null,\"token\":null,\"userName\":null,\"password\":null,\"roles\":null}'),(78,'com.sappe.ontrack.model.issues.Project',NULL,51,'{\"id\":null,\"name\":null,\"roles\":null,\"users\":null,\"admin\":null}');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workflow`
--

LOCK TABLES `workflow` WRITE;
/*!40000 ALTER TABLE `workflow` DISABLE KEYS */;
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

-- Dump completed on 2014-02-22 11:45:27
