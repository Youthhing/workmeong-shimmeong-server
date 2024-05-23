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

-- member 테이블 생성
CREATE TABLE IF NOT EXISTS `workmeong`.`member` (
                                                    `member_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                    `member_email` VARCHAR(50) NOT NULL,
    `member_name` VARCHAR(255),
    `member_type` ENUM('TYPE1', 'TYPE2') NOT NULL,
    `member_phone` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`member_id`),
    UNIQUE INDEX `member_email_UNIQUE` (`member_email` ASC) VISIBLE
    ) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4;

-- program 테이블 생성
CREATE TABLE IF NOT EXISTS `workmeong`.`program` (
                                                     `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                     `program_name` VARCHAR(255) NOT NULL,
    `program_description` TEXT NOT NULL,
    `program_road_name_address` VARCHAR(255) NOT NULL,
    `program_start_time` VARCHAR(255) NOT NULL,
    `program_time` BIGINT NOT NULL,
    `program_chat_link` VARCHAR(255),
    `space_type` ENUM('TYPE1', 'TYPE2') NOT NULL,
    `space_status` ENUM('AVAILABLE', 'UNAVAILABLE') NOT NULL DEFAULT 'AVAILABLE',
    `program_money` BIGINT NOT NULL,
    `space_number` CHAR(36) NOT NULL,
    `host_id` BIGINT,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `space_number_UNIQUE` (`space_number` ASC) VISIBLE,
    INDEX `fk_program_member_idx` (`host_id` ASC) VISIBLE,
    CONSTRAINT `fk_program_member`
    FOREIGN KEY (`host_id`)
    REFERENCES `workmeong`.`member` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    ) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4;

-- image 테이블 생성
CREATE TABLE IF NOT EXISTS `workmeong`.`image` (
                                                   `image_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                   `image_url` VARCHAR(500) NOT NULL,
    `image_order` INT NOT NULL,
    `program_id` BIGINT,
    PRIMARY KEY (`image_id`),
    INDEX `fk_image_program_idx` (`program_id` ASC) VISIBLE,
    CONSTRAINT `fk_image_program`
    FOREIGN KEY (`program_id`)
    REFERENCES `workmeong`.`program` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    ) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4;

-- reservation 테이블 생성
CREATE TABLE IF NOT EXISTS `workmeong`.`reservation` (
                                                         `reservation_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                         `reservation_status` ENUM('WAITING', 'CONFIRMED') NOT NULL DEFAULT 'WAITING',
    `space_id` BIGINT,
    `guest_id` BIGINT NOT NULL,
    `reservation_request` TEXT,
    PRIMARY KEY (`reservation_id`),
    INDEX `fk_reservation_program_idx` (`space_id` ASC) VISIBLE,
    CONSTRAINT `fk_reservation_program`
    FOREIGN KEY (`space_id`)
    REFERENCES `workmeong`.`program` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    ) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4;
