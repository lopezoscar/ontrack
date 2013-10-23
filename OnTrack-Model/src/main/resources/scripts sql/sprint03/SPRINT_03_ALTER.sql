ALTER TABLE `ontrack`.`issue` ADD COLUMN `title` TEXT NULL  AFTER `id_project` , ADD COLUMN `description` TEXT NULL  AFTER `title` , ADD COLUMN `reporter` VARCHAR(255) NULL  AFTER `description` , ADD COLUMN `owner` BIGINT NULL  AFTER `reporter` ;

