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

CREATE USER IF NOT EXISTS 'root'@'localhost' IDENTIFIED BY 'root';
GRANT ALL PRIVILEGES ON `workmeong`.* TO 'root'@'localhost' WITH GRANT OPTION;
FLUSH PRIVILEGES;


CREATE TABLE IF NOT EXISTS `image` (
                                       `image_id` BIGINT NOT NULL AUTO_INCREMENT,
                                       `image_url` VARCHAR(500) NOT NULL,
    `image_order` INT NOT NULL,
    `program_id` BIGINT,
    PRIMARY KEY (`image_id`),
    CONSTRAINT `fk_image_program`
    FOREIGN KEY (`program_id`)
    REFERENCES `program` (`id`)
    ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `member` (
                                        `member_id` BIGINT NOT NULL AUTO_INCREMENT,
                                        `member_email` VARCHAR(50) NOT NULL,
    `member_name` VARCHAR(255),
    `member_type` VARCHAR(255) NOT NULL,
    `member_phone` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`member_id`),
    UNIQUE INDEX `idx_member_email` (`member_email` ASC)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `program` (
                                         `id` BIGINT NOT NULL AUTO_INCREMENT,
                                         `program_name` VARCHAR(255) NOT NULL,
    `program_description` TEXT NOT NULL,
    `program_road_name_address` VARCHAR(255) NOT NULL,
    `program_start_time` VARCHAR(255) NOT NULL,
    `program_time` BIGINT NOT NULL,
    `program_chat_link` VARCHAR(255),
    `space_type` VARCHAR(255) NOT NULL,
    `space_status` VARCHAR(255) NOT NULL DEFAULT 'AVAILABLE',
    `program_money` BIGINT NOT NULL,
    `space_number` BINARY(16) NOT NULL UNIQUE,
    `host_id` BIGINT,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_program_member`
    FOREIGN KEY (`host_id`)
    REFERENCES `member` (`member_id`)
    ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `reservation` (
                                             `reservation_id` BIGINT NOT NULL AUTO_INCREMENT,
                                             `reservation_status` VARCHAR(255) NOT NULL DEFAULT 'WAITING',
    `space_id` BIGINT,
    `guest_id` BIGINT NOT NULL,
    `reservation_request` TEXT,
    PRIMARY KEY (`reservation_id`),
    CONSTRAINT `fk_reservation_program`
    FOREIGN KEY (`space_id`)
    REFERENCES `program` (`id`)
    ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



-- CREATE DATABASE IF NOT EXISTS workmeong;