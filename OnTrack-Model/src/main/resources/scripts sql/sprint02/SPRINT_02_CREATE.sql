SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;

SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';



CREATE SCHEMA IF NOT EXISTS `ontrack` DEFAULT CHARACTER SET utf8 ;

USE `ontrack` ;



-- -----------------------------------------------------

-- Table `ontrack`.`entry_document_file`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `ontrack`.`entry_document_file` (

  `id_document_file` BIGINT(20) NOT NULL AUTO_INCREMENT ,

  `file_name` VARCHAR(255) NULL DEFAULT NULL ,

  `file_content` BLOB NULL DEFAULT NULL ,

  `file_length` BIGINT(20) NULL DEFAULT NULL ,

  `file_type` VARCHAR(255) NULL DEFAULT NULL ,

  `issue_entry` BIGINT(20) NULL DEFAULT NULL ,

  PRIMARY KEY (`id_document_file`) )

ENGINE = InnoDB

DEFAULT CHARACTER SET = utf8;





-- -----------------------------------------------------

-- Table `ontrack`.`issue`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `ontrack`.`issue` (

  `id_issue` BIGINT(20) NOT NULL AUTO_INCREMENT ,

  `current_status` BIGINT(20) NULL DEFAULT NULL ,

  `issue_type` BIGINT(20) NULL DEFAULT NULL ,

  `parent_issue` BIGINT(20) NULL DEFAULT NULL ,

  `id_project` BIGINT(20) NULL DEFAULT NULL ,

  PRIMARY KEY (`id_issue`) )

ENGINE = InnoDB

AUTO_INCREMENT = 8

DEFAULT CHARACTER SET = utf8;





-- -----------------------------------------------------

-- Table `ontrack`.`issue_entry`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `ontrack`.`issue_entry` (

  `id_issue_entry` BIGINT(20) NOT NULL AUTO_INCREMENT ,

  `comment` TEXT NULL DEFAULT NULL ,

  `owner` BIGINT(20) NULL DEFAULT NULL ,

  PRIMARY KEY (`id_issue_entry`) )

ENGINE = InnoDB

AUTO_INCREMENT = 3

DEFAULT CHARACTER SET = utf8;





-- -----------------------------------------------------

-- Table `ontrack`.`issue_property`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `ontrack`.`issue_property` (

  `id_issue_property` BIGINT(20) NOT NULL AUTO_INCREMENT ,

  `description` VARCHAR(255) NULL DEFAULT NULL ,

  `type` BIGINT(20) NULL DEFAULT NULL ,

  PRIMARY KEY (`id_issue_property`) )

ENGINE = InnoDB

AUTO_INCREMENT = 2

DEFAULT CHARACTER SET = utf8;





-- -----------------------------------------------------

-- Table `ontrack`.`issue_property_type`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `ontrack`.`issue_property_type` (

  `id_property` BIGINT(20) NOT NULL AUTO_INCREMENT ,

  `name` VARCHAR(255) NULL DEFAULT NULL ,

  PRIMARY KEY (`id_property`) )

ENGINE = InnoDB

AUTO_INCREMENT = 3

DEFAULT CHARACTER SET = utf8;





-- -----------------------------------------------------

-- Table `ontrack`.`issue_status`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `ontrack`.`issue_status` (

  `id_issue_status` BIGINT(20) NOT NULL AUTO_INCREMENT ,

  `description` TEXT NULL DEFAULT NULL ,

  PRIMARY KEY (`id_issue_status`) )

ENGINE = InnoDB

AUTO_INCREMENT = 2

DEFAULT CHARACTER SET = utf8;





-- -----------------------------------------------------

-- Table `ontrack`.`issue_type`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `ontrack`.`issue_type` (

  `id_issue_type` BIGINT(20) NOT NULL AUTO_INCREMENT ,

  `id_project` BIGINT(20) NULL DEFAULT NULL ,

  `description` VARCHAR(255) NULL DEFAULT NULL ,

  PRIMARY KEY (`id_issue_type`) )

ENGINE = InnoDB

DEFAULT CHARACTER SET = utf8;





-- -----------------------------------------------------

-- Table `ontrack`.`persons`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `ontrack`.`persons` (

  `id_person` BIGINT(20) NOT NULL AUTO_INCREMENT ,

  `first_name` VARCHAR(255) NULL DEFAULT NULL ,

  `last_name` VARCHAR(255) NULL DEFAULT NULL ,

  `email` VARCHAR(255) NULL DEFAULT NULL ,

  PRIMARY KEY (`id_person`) )

ENGINE = InnoDB

DEFAULT CHARACTER SET = utf8;





-- -----------------------------------------------------

-- Table `ontrack`.`project`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `ontrack`.`project` (

  `id_project` INT(11) NOT NULL AUTO_INCREMENT ,

  `name` VARCHAR(255) NULL DEFAULT NULL ,

  PRIMARY KEY (`id_project`) )

ENGINE = InnoDB

AUTO_INCREMENT = 2

DEFAULT CHARACTER SET = utf8;





-- -----------------------------------------------------

-- Table `ontrack`.`roles`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `ontrack`.`roles` (

  `id_role` INT(11) NOT NULL ,

  `role_name` VARCHAR(255) NULL DEFAULT NULL ,

  PRIMARY KEY (`id_role`) )

ENGINE = InnoDB

DEFAULT CHARACTER SET = utf8;





-- -----------------------------------------------------

-- Table `ontrack`.`user_role`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `ontrack`.`user_role` (

  `id_person` BIGINT(20) NOT NULL ,

  `id_role` INT(11) NOT NULL ,

  PRIMARY KEY (`id_person`, `id_role`) )

ENGINE = InnoDB

DEFAULT CHARACTER SET = utf8;





-- -----------------------------------------------------

-- Table `ontrack`.`users`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `ontrack`.`users` (

  `id_person` BIGINT(20) NOT NULL AUTO_INCREMENT ,

  `user_name` VARCHAR(255) NULL DEFAULT NULL ,

  `password` VARCHAR(255) NULL DEFAULT NULL ,

  PRIMARY KEY (`id_person`) )

ENGINE = InnoDB

DEFAULT CHARACTER SET = utf8;


CREATE  TABLE `ontrack`.`workflow` (

  `id_workflow` BIGINT NOT NULL AUTO_INCREMENT ,

  `id_issue_type` BIGINT NULL ,

  `id_project` BIGINT NULL ,

  PRIMARY KEY (`id_workflow`) );


CREATE  TABLE `ontrack`.`issue_status_by_workflow` (

  `id_workflow` BIGINT NOT NULL ,

  `id_issue_type` BIGINT NOT NULL ,

  PRIMARY KEY (`id_workflow`, `id_issue_type`) );





SET SQL_MODE=@OLD_SQL_MODE;

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;

SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;