-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema notesdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `notesdb` ;

-- -----------------------------------------------------
-- Schema notesdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `notesdb` DEFAULT CHARACTER SET utf8 ;
USE `notesdb` ;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(65) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(280) NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `role` VARCHAR(45) NULL,
  `enabled` TINYINT NOT NULL DEFAULT 1,
  `first_name` VARCHAR(80) NULL,
  `last_name` VARCHAR(80) NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `note`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `note` ;

CREATE TABLE IF NOT EXISTS `note` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(200) NOT NULL,
  `text` TEXT NULL,
  `starred` TINYINT NULL DEFAULT 0,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_notes_user_idx` (`user_id` ASC),
  CONSTRAINT `fk_notes_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `image`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `image` ;

CREATE TABLE IF NOT EXISTS `image` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `url_link` VARCHAR(245) NULL,
  `created_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `note_id` INT NULL,
  `user_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_image_note1_idx` (`note_id` ASC),
  INDEX `fk_image_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_image_note1`
    FOREIGN KEY (`note_id`)
    REFERENCES `note` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_image_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS notes@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'notes'@'localhost' IDENTIFIED BY 'notes';

GRANT SELECT, INSERT, TRIGGER ON TABLE * TO 'notes'@'localhost';
GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'notes'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `notesdb`;
INSERT INTO `user` (`id`, `username`, `email`, `password`, `created_at`, `updated_at`, `role`, `enabled`, `first_name`, `last_name`) VALUES (1, 'admin', 'admin@notes.com', '$2a$10$wLWPGAQMFBWKwSgGuaJsDO09KERzvlAMw4A2BTa.W8Jz7aI7.rj4i', DEFAULT, DEFAULT, 'admin', true, NULL, NULL);
INSERT INTO `user` (`id`, `username`, `email`, `password`, `created_at`, `updated_at`, `role`, `enabled`, `first_name`, `last_name`) VALUES (2, 'vinton', 'vinton@notes.com', '$2a$10$llS17x.w/M1mgxs7jd30HO/trXHXwNsQiC9aflQ1iWerepXpcC/He', DEFAULT, DEFAULT, 'user', true, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `note`
-- -----------------------------------------------------
START TRANSACTION;
USE `notesdb`;
INSERT INTO `note` (`id`, `title`, `text`, `starred`, `created_at`, `updated_at`, `user_id`) VALUES (1, 'jobs', 'apply to a lot of jobs', true, NULL, NULL, 1);
INSERT INTO `note` (`id`, `title`, `text`, `starred`, `created_at`, `updated_at`, `user_id`) VALUES (2, 'admin', 'check admin features', false, NULL, NULL, 2);
INSERT INTO `note` (`id`, `title`, `text`, `starred`, `created_at`, `updated_at`, `user_id`) VALUES (3, 'water', 'drink more water', false, NULL, NULL, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `image`
-- -----------------------------------------------------
START TRANSACTION;
USE `notesdb`;
INSERT INTO `image` (`id`, `url_link`, `created_at`, `updated_at`, `note_id`, `user_id`) VALUES (1, 'https://i.imgur.com/HlVLzU9.jpg', NULL, NULL, 1, 1);
INSERT INTO `image` (`id`, `url_link`, `created_at`, `updated_at`, `note_id`, `user_id`) VALUES (2, 'https://i.imgur.com/FBJi16J.jpg', NULL, NULL, NULL, 1);

COMMIT;

