-- MySQL Script generated by MySQL Workbench
-- Thu Mar 14 14:42:40 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema stockblogger
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema stockblogger
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `stockblogger` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `stockblogger` ;

-- -----------------------------------------------------
-- Table `stockblogger`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `stockblogger`.`user` ;

CREATE TABLE IF NOT EXISTS `stockblogger`.`user` (
  `user_id` INT(11) NOT NULL,
  `user_name` VARCHAR(45) NOT NULL,
  `date_user_joined` DATE NULL DEFAULT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `stockblogger`.`post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `stockblogger`.`post` ;

CREATE TABLE IF NOT EXISTS `stockblogger`.`post` (
  `post_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  `title` VARCHAR(75) NOT NULL,
  `body` VARCHAR(5000) NOT NULL,
  `post_date` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`post_id`),
  INDEX `post to user_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `post to user`
    FOREIGN KEY (`user_id`)
    REFERENCES `stockblogger`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `stockblogger`.`comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `stockblogger`.`comment` ;

CREATE TABLE IF NOT EXISTS `stockblogger`.`comment` (
  `comment_id` INT(11) NOT NULL,
  `post_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  `body` VARCHAR(1000) NULL DEFAULT NULL,
  `comment_date` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  INDEX `comment to post_idx` (`post_id` ASC) VISIBLE,
  INDEX `comment to user_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `comment to post`
    FOREIGN KEY (`post_id`)
    REFERENCES `stockblogger`.`post` (`post_id`),
  CONSTRAINT `comment to user`
    FOREIGN KEY (`user_id`)
    REFERENCES `stockblogger`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO `stockblogger`.`user`
(`user_id`,
`user_name`,
`date_user_joined`,
`password`)
VALUES
(1,
'testuser',
DATE '2015-12-17',
'password');

INSERT INTO `stockblogger`.`user`
(`user_id`,
`user_name`,
`date_user_joined`,
`password`)
VALUES
(2,
'testuser2',
DATE '2016-01-21',
'password');

INSERT INTO `stockblogger`.`post`
(`post_id`,
`user_id`,
`title`,
`body`,
`post_date`)
VALUES
(1,
1,
'some title',
'some text',
DATE '2015-12-17');

INSERT INTO `stockblogger`.`post`
(`post_id`,
`user_id`,
`title`,
`body`,
`post_date`)
VALUES
(2,
1,
'some title',
'some text',
DATE '2015-12-22');

INSERT INTO `stockblogger`.`post`
(`post_id`,
`user_id`,
`title`,
`body`,
`post_date`)
VALUES
(3,
2,
'some title',
'some text',
DATE '2016-01-21');

INSERT INTO `stockblogger`.`post`
(`post_id`,
`user_id`,
`title`,
`body`,
`post_date`)
VALUES
(4,
2,
'some title',
'some text',
DATE '2016-01-27');

INSERT INTO `stockblogger`.`post`
(`post_id`,
`user_id`,
`title`,
`body`,
`post_date`)
VALUES
(5,
1,
'some title',
'some text',
DATE '2016-01-27');

INSERT INTO `stockblogger`.`comment`
(`comment_id`,
`post_id`,
`user_id`,
`body`,
`comment_date`)
VALUES
(1,
1,
2,
'some text',
DATE '2016-01-21');

INSERT INTO `stockblogger`.`comment`
(`comment_id`,
`post_id`,
`user_id`,
`body`,
`comment_date`)
VALUES
(2,
1,
1,
'some text',
DATE '2016-01-21');

INSERT INTO `stockblogger`.`comment`
(`comment_id`,
`post_id`,
`user_id`,
`body`,
`comment_date`)
VALUES
(3,
2,
2,
'some text',
DATE '2016-01-21');

INSERT INTO `stockblogger`.`comment`
(`comment_id`,
`post_id`,
`user_id`,
`body`,
`comment_date`)
VALUES
(4,
3,
1,
'some text',
DATE '2016-01-25');


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;