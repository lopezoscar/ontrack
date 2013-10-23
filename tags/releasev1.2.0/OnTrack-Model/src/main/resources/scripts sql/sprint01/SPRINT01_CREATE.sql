CREATE DATABASE ontrack;


CREATE  TABLE `ontrack`.`persons` (

  `id_person` BIGINT NOT NULL AUTO_INCREMENT ,

  `first_name` VARCHAR(255) NULL ,

  `last_name` VARCHAR(255) NULL ,

  `email` VARCHAR(255) NULL ,

  PRIMARY KEY (`id_person`) );

  
CREATE  TABLE `ontrack`.`users` (

  `id_person` BIGINT NOT NULL AUTO_INCREMENT ,

  `user_name` VARCHAR(255) NULL ,

  `password` VARCHAR(255) NULL ,

  PRIMARY KEY (`id_person`) );


CREATE TABLE `roles` (

  `id_role` int(11) NOT NULL,
  
  `role_name` varchar(255) DEFAULT NULL,
  
  PRIMARY KEY (`id_role`)
) 


-- Tablas para issues

CREATE  TABLE `ontrack`.`issue_type` (

  `id_issue_type` BIGINT NOT NULL AUTO_INCREMENT ,

  `id_project` BIGINT NULL ,

  `description` VARCHAR(255) NULL ,

  PRIMARY KEY (`id_issue_type`) );
  

CREATE  TABLE `ontrack`.`issue_status` (

  `id_issue_status` BIGINT NOT NULL AUTO_INCREMENT ,

  `description` TEXT NULL ,

  PRIMARY KEY (`id_issue_status`) );



CREATE  TABLE `ontrack`.`user_role` (

  `id_person` BIGINT NOT NULL ,

  `id_role` INT NOT NULL ,

  PRIMARY KEY (`id_person`, `id_role`) );

/*
CREATE  TABLE `ontrack`.`document_file` (

  `id_document_file` BIGINT NOT NULL AUTO_INCREMENT ,

  `file_name` VARCHAR(255) NULL ,

  `file_content` VARCHAR(255) NULL ,

  `file_length` BIGINT NULL ,

  `file_type` VARCHAR(255) NULL ,

  PRIMARY KEY (`id_document_file`) );
*/  
  
CREATE  TABLE `ontrack`.`issue_entry` (

  `id_issue_entry` BIGINT NOT NULL AUTO_INCREMENT ,

  `comment` TEXT NULL ,

  `owner` BIGINT NULL ,

  PRIMARY KEY (`id_issue_entry`) );


CREATE  TABLE `ontrack`.`issue_property` (

  `id_issue_property` BIGINT NOT NULL AUTO_INCREMENT ,

  `description` TEXT NULL ,

  `id_property` INT NULL ,

  PRIMARY KEY (`id_issue_property`) );

CREATE  TABLE `ontrack`.`issue_property_type` (

  `id_property` BIGINT NOT NULL AUTO_INCREMENT ,

  `name` TEXT NULL ,

  PRIMARY KEY (`id_property`) );
  
  
  
  CREATE  TABLE `ontrack`.`issue_status` (

  `id_issue_status` BIGINT NOT NULL AUTO_INCREMENT ,

  `description` VARCHAR(255) NULL ,

  PRIMARY KEY (`id_issue_status`) );

CREATE  TABLE `ontrack`.`issue_type` (

  `id_issue_type` BIGINT NOT NULL AUTO_INCREMENT ,

  `id_project` INT NULL ,

  `description` TEXT NULL ,

  PRIMARY KEY (`id_issue_type`) );
  

CREATE  TABLE `ontrack`.`project` (

  `id_project` BIGINT NOT NULL AUTO_INCREMENT ,

  `name` VARCHAR(255) NULL ,

  PRIMARY KEY (`id_project`) );
  
  -- Se actualiza modelo issue entry y document file
 /* 
  CREATE  TABLE `ontrack`.`issue_entry` (

  `id_issue_entry` BIGINT NOT NULL AUTO_INCREMENT ,

  `comment` TEXT NULL ,

  `owner` BIGINT NULL ,

  `issue_entry` BIGINT NULL ,

  PRIMARY KEY (`id_issue_entry`) );
  
  */

  CREATE  TABLE `ontrack`.`issue_entry` (

  `id_issue_entry` BIGINT NOT NULL AUTO_INCREMENT ,

  `comment` TEXT NULL ,

  `owner` BIGINT NULL ,

  PRIMARY KEY (`id_issue_entry`) );
  
  
  CREATE  TABLE `ontrack`.`entry_document_file` (

  `id_document_file` BIGINT NOT NULL AUTO_INCREMENT ,

  `file_name` VARCHAR(255) NULL ,

  `file_content` BLOB NULL ,

  `file_length` BIGINT NULL ,

  `file_type` VARCHAR(255) NULL ,

  `issue_entry` BIGINT NULL ,

  PRIMARY KEY (`id_document_file`) );

CREATE  TABLE `ontrack`.`issue` (

  `id_issue` BIGINT NOT NULL AUTO_INCREMENT ,

  `current_status` BIGINT NULL ,

  `issue_type` BIGINT NULL ,

  `parent_issue` BIGINT NULL ,

  `id_project` BIGINT NULL ,

  PRIMARY KEY (`id_issue`) );

CREATE  TABLE `ontrack`.`project` (

  `id_project` INT NOT NULL ,

  `name` VARCHAR(255) NULL ,

  PRIMARY KEY (`id_project`) );





