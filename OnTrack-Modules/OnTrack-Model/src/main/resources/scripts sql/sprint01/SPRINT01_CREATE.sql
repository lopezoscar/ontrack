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



