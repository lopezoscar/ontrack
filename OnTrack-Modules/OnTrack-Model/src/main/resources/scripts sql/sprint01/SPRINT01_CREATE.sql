CREATE DATABASE ontrack;


CREATE  TABLE `ontrack`.`persons` (

  `id_person` INT NOT NULL AUTO_INCREMENT ,

  `first_name` VARCHAR(255) NULL ,

  `last_name` VARCHAR(255) NULL ,

  `email` VARCHAR(255) NULL ,

  PRIMARY KEY (`id_person`) );

  
CREATE  TABLE `ontrack`.`users` (

  `id_person` INT NOT NULL AUTO_INCREMENT ,

  `user_name` VARCHAR(255) NULL ,

  `password` VARCHAR(255) NULL ,

  PRIMARY KEY (`id_person`) );


CREATE TABLE `roles` (

  `id_role` int(11) NOT NULL,
  
  `role_name` varchar(255) DEFAULT NULL,
  
  PRIMARY KEY (`id_role`)
) 
ENGINE=InnoDB DEFAULT CHARSET=utf8$$


