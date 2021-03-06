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
CREATE SCHEMA IF NOT EXISTS `stockblogger` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
USE `stockblogger` ;

-- -----------------------------------------------------
-- Table `stockblogger`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `stockblogger`.`users` ;

CREATE TABLE IF NOT EXISTS `stockblogger`.`users` (
  `user_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(45) NOT NULL UNIQUE,
  `date_user_joined` DATE NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_general_ci;


-- -----------------------------------------------------
-- Table `stockblogger`.`post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `stockblogger`.`posts` ;

CREATE TABLE IF NOT EXISTS `stockblogger`.`posts` (
  `post_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `title` VARCHAR(75) NOT NULL,
  `body` TEXT NOT NULL,
  `post_date` DATE NOT NULL,
  PRIMARY KEY (`post_id`),
  CONSTRAINT `post to user`
    FOREIGN KEY (`user_id`)
    REFERENCES `stockblogger`.`users` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_general_ci;
CREATE INDEX `idx_post_to_user` ON `stockblogger`.`posts`(`user_id` ASC);

-- -----------------------------------------------------
-- Table `stockblogger`.`comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `stockblogger`.`comments` ;

CREATE TABLE IF NOT EXISTS `stockblogger`.`comments` (
  `comment_id` INT(11) NOT NULL AUTO_INCREMENT,
  `post_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  `body` TEXT NOT NULL,
  `comment_date` DATE NOT NULL,
  PRIMARY KEY (`comment_id`),
  CONSTRAINT `comment to post`
    FOREIGN KEY (`post_id`)
    REFERENCES `stockblogger`.`posts` (`post_id`),
  CONSTRAINT `comment to user`
    FOREIGN KEY (`user_id`)
    REFERENCES `stockblogger`.`users` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_general_ci;
CREATE INDEX `idx_comment_to_post` ON `stockblogger`.`comments`(`post_id` ASC);
CREATE INDEX `idx_comment_to_user` ON `stockblogger`.`comments`(`user_id` ASC);

INSERT INTO `stockblogger`.`users`
(`user_name`,
`date_user_joined`,
`password`)
VALUES
('testuser',
DATE '2015-12-17',
'password');

INSERT INTO `stockblogger`.`users`
(`user_name`,
`date_user_joined`,
`password`)
VALUES
('testuser2',
DATE '2016-01-21',
'password');

INSERT INTO `stockblogger`.`posts`
(`user_id`,
`title`,
`body`,
`post_date`)
VALUES
(1,
'some title',
'some text',
DATE '2015-12-17');

INSERT INTO `stockblogger`.`posts`
(`user_id`,
`title`,
`body`,
`post_date`)
VALUES
(1,
'some title',
'some text',
DATE '2015-12-22');

INSERT INTO `stockblogger`.`posts`
(`user_id`,
`title`,
`body`,
`post_date`)
VALUES
(2,
'some title',
'some text',
DATE '2016-01-21');

INSERT INTO `stockblogger`.`posts`
(`user_id`,
`title`,
`body`,
`post_date`)
VALUES
(2,
'some title',
'some text',
DATE '2016-01-27');

INSERT INTO `stockblogger`.`posts`
(`user_id`,
`title`,
`body`,
`post_date`)
VALUES
(1,
'some title',
'some text',
DATE '2016-01-27');

INSERT INTO `stockblogger`.`comments`
(`post_id`,
`user_id`,
`body`,
`comment_date`)
VALUES
(1,
2,
'some text',
DATE '2016-01-21');

INSERT INTO `stockblogger`.`comments`
(`post_id`,
`user_id`,
`body`,
`comment_date`)
VALUES
(1,
1,
'some text',
DATE '2016-01-21');

INSERT INTO `stockblogger`.`comments`
(`post_id`,
`user_id`,
`body`,
`comment_date`)
VALUES
(2,
2,
'some text',
DATE '2016-01-21');

INSERT INTO `stockblogger`.`comments`
(`post_id`,
`user_id`,
`body`,
`comment_date`)
VALUES
(3,
1,
'some text',
DATE '2016-01-25');

USE `stockblogger`;
DROP procedure IF EXISTS `add_user`;

DELIMITER $$
USE `stockblogger`$$
CREATE PROCEDURE `add_user` (IN USER_NAME_INPUT VARCHAR(45),IN DATE_JOINED_INPUT DATE,IN PASSWORD_INPUT VARCHAR(45),OUT USER_ID INT)
BEGIN

INSERT INTO `stockblogger`.`users`
(`user_name`,
`date_user_joined`,
`password`)
VALUES
(USER_NAME_INPUT,
DATE_JOINED_INPUT,
PASSWORD_INPUT);

SET USER_ID = LAST_INSERT_ID();


END$$

DELIMITER ;

USE `stockblogger`;
DROP procedure IF EXISTS `add_post`;

DELIMITER $$
USE `stockblogger`$$
CREATE PROCEDURE `add_post` (IN USER_ID_INPUT INT,IN TITLE_INPUT VARCHAR(75),IN POST_BODY_INPUT TEXT,IN POST_DATE_INPUT DATE)
BEGIN

INSERT INTO `stockblogger`.`posts`
(`user_id`,
`title`,
`body`,
`post_date`)
VALUES
(USER_ID_INPUT,
TITLE_INPUT,
POST_BODY_INPUT,
POST_DATE_INPUT);

END$$

DELIMITER ;

USE `stockblogger`;
DROP procedure IF EXISTS `add_comment`;

DELIMITER $$
USE `stockblogger`$$
CREATE PROCEDURE `add_comment`(IN POST_ID_INPUT INT,IN USER_ID_INPUT INT,IN COMMENT_BODY_INPUT TEXT,IN COMMENT_DATE_INPUT DATE)
BEGIN

INSERT INTO `stockblogger`.`comments`
(`post_id`,
`user_id`,
`body`,
`comment_date`)
VALUES
(POST_ID_INPUT,
USER_ID_INPUT,
COMMENT_BODY_INPUT,
COMMENT_DATE_INPUT);

END$$

DELIMITER ;

USE `stockblogger`;
DROP procedure IF EXISTS `edit_user`;

DELIMITER $$
USE `stockblogger`$$
CREATE PROCEDURE `edit_user` (IN USER_ID_INPUT INT,IN USER_NAME_INPUT VARCHAR(45),IN DATE_JOINED_INPUT DATE,IN PASSWORD_INPUT VARCHAR(45))
BEGIN

UPDATE `stockblogger`.`users`
SET user_name = USER_NAME_INPUT,
	date_user_joined = DATE_JOINED_INPUT,
    password = PASSWORD_INPUT
WHERE user_id = USER_ID_INPUT;

END$$

DELIMITER ;

USE `stockblogger`;
DROP procedure IF EXISTS `edit_post`;

DELIMITER $$
USE `stockblogger`$$
CREATE PROCEDURE `edit_post` (IN POST_ID_INPUT INT,IN USER_ID_INPUT INT,IN TITLE_INPUT VARCHAR(75),IN POST_BODY_INPUT TEXT,IN POST_DATE_INPUT DATE)
BEGIN

UPDATE `stockblogger`.`posts`
SET user_id = USER_ID_INPUT,
title = TITLE_INPUT,
body = POST_BODY_INPUT,
post_date = POST_DATE_INPUT
WHERE post_id = POST_ID_INPUT;

END$$

DELIMITER ;

USE `stockblogger`;
DROP procedure IF EXISTS `edit_comment`;

DELIMITER $$
USE `stockblogger`$$
CREATE PROCEDURE `edit_comment`(IN COMMENT_ID_INPUT INT,IN POST_ID_INPUT INT,IN USER_ID_INPUT INT,IN COMMENT_BODY_INPUT TEXT,IN COMMENT_DATE_INPUT DATE)
BEGIN

UPDATE `stockblogger`.`comments`
SET post_id = POST_ID_INPUT,
user_id = USER_ID_INPUT,
body = COMMENT_BODY_INPUT,
comment_date = COMMENT_DATE_INPUT
WHERE comment_id = COMMENT_ID_INPUT ;

END$$

DELIMITER ;

USE `stockblogger`;
DROP procedure IF EXISTS `select_posts_from_user`;

DELIMITER $$
USE `stockblogger`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `select_posts_from_user`(IN USER_ID_INPUT INT)
BEGIN

SELECT *
FROM posts
WHERE user_id = USER_ID_INPUT;

END$$

DELIMITER ;

USE `stockblogger`;
DROP procedure IF EXISTS `select_comments_from_post`;

DELIMITER $$
USE `stockblogger`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `select_comments_from_post`(IN POST_ID_INPUT INT)
BEGIN

SELECT *
FROM comments
WHERE post_id = POST_ID_INPUT;

END$$

DELIMITER ;


ALTER TABLE posts
ADD FULLTEXT (title, body);

USE `stockblogger`;
DROP procedure IF EXISTS `search_posts`;

DELIMITER $$
USE `stockblogger`$$
CREATE procedure search_posts (IN keywords VARCHAR(30) )
BEGIN

SELECT * FROM posts
WHERE MATCH(title, body) AGAINST(keywords);

END $$

DELIMITER ;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- create a user and grant privileges to that user
GRANT SELECT, INSERT, DELETE, UPDATE
ON stockblogger.*
TO dbuser@localhost
IDENTIFIED BY 'user';