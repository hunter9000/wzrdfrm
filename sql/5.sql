USE wzrdfrm;

CREATE TABLE `plant` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `grow_time_milliseconds` BIGINT(20) NOT NULL,
    PRIMARY KEY (`id`)
) COLLATE='utf8_general_ci' ENGINE=InnoDB;

ALTER TABLE `farm_plot` DROP COLUMN `plant_type`;
ALTER TABLE `farm_plot` ADD COLUMN `plant` BIGINT(20) NULL;
ALTER TABLE `farm_plot` ADD INDEX `farm_plot__plant` (`plant`);
ALTER TABLE `farm_plot` ADD CONSTRAINT `farm_plot__plant` FOREIGN KEY (`plant`) REFERENCES `plant` (`id`);

INSERT INTO `plant` (name, grow_time_milliseconds) VALUES ('Weeds', 10000);
INSERT INTO `plant` (name, grow_time_milliseconds) VALUES ('Grass', 10000);
INSERT INTO `plant` (name, grow_time_milliseconds) VALUES ('Fern', 10000);
INSERT INTO `plant` (name, grow_time_milliseconds) VALUES ('Potato', 10000);
INSERT INTO `plant` (name, grow_time_milliseconds) VALUES ('Strawberry', 10000);
INSERT INTO `plant` (name, grow_time_milliseconds) VALUES ('Lily', 10000);
INSERT INTO `plant` (name, grow_time_milliseconds) VALUES ('Cattail', 10000);

CREATE TABLE `seed_inventory` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `farm` BIGINT(20) NOT NULL,
    `plant` BIGINT(20) NOT NULL,
    `quantity` INTEGER(11) NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `seed_inventory__farm` (`farm`),
    INDEX `seed_inventory__plant` (`plant`),
    CONSTRAINT `seed_inventory__farm` FOREIGN KEY (`farm`) REFERENCES `farm` (`id`),
    CONSTRAINT `seed_inventory__plant` FOREIGN KEY (`plant`) REFERENCES `plant` (`id`)
) COLLATE='utf8_general_ci' ENGINE=InnoDB;


INSERT INTO `sql_files` (`sql_file_name`, `run_date`) VALUES ('5.sql', NOW());