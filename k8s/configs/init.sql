-- CREATE SCHEMA IF NOT EXISTS `krampoline` DEFAULT CHARACTER SET utf8mb4;
--
-- GRANT ALL ON *.* TO 'root'@'localhost' IDENTIFIED BY 'root' WITH GRANT OPTION;
-- GRANT ALL ON krampoline.* TO 'root'@'localhost';
-- FLUSH PRIVILEGES;
--
-- USE `workmeong`;
--
-- DROP TABLE IF EXISTS `sample_data`;
-- CREATE TABLE `sample_data` (
--                                `id` int(11) NOT NULL AUTO_INCREMENT,
--                                `detail` varchar(100) NOT NULL,
--                                PRIMARY KEY (`id`)
-- ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
--
-- INSERT INTO sample_data (`id`,`detail`) VALUES ('1', 'Hello DKOS!');

CREATE SCHEMA IF NOT EXISTS `workmeong` DEFAULT CHARACTER SET utf8mb4;
ㅕGRANT ALL ON *.* TO 'root'@'localhost' IDENTIFIED BY 'root' WITH GRANT OPTION;
GRANT ALL ON workmeong.* TO 'root'@'localhost';

-- CREATE DATABASE IF NOT EXISTS workmeong;