SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

CREATE DATABASE IF NOT EXISTS `project` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `project`;

CREATE TABLE `account` (
    `id` INT(10) NOT NULL,
    `name` NVARCHAR(50) NOT NULL,
    `account` CHAR(20) NOT NULL,
    `password` CHAR(16) NOT NULL,
    `gmail` CHAR(30) NOT NULL,
    `gender` CHAR(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `account` (`id`,`name`,`account`,`password`,`gmail`,`gender`) VALUES 
()