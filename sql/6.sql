USE wzrdfrm;

CREATE TABLE `usable_item` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
) COLLATE='utf8_general_ci' ENGINE=InnoDB;

CREATE TABLE `consumable_inventory` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `farm` BIGINT(20) NOT NULL,
    `usable_item` BIGINT(20) NOT NULL,
    `quantity` INTEGER(11) NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `consumable_inventory__farm` (`farm`),
    INDEX `consumable_inventory__usable_item` (`usable_item`),
    CONSTRAINT `consumable_inventory__farm` FOREIGN KEY (`farm`) REFERENCES `farm` (`id`),
    CONSTRAINT `consumable_inventory__usable_item` FOREIGN KEY (`usable_item`) REFERENCES `usable_item` (`id`)
) COLLATE='utf8_general_ci' ENGINE=InnoDB;

INSERT INTO `usable_item` (name) VALUES ('Small Fertilizer');
INSERT INTO `usable_item` (name) VALUES ('Large Fertilizer');
INSERT INTO `usable_item` (name) VALUES ('Growth Potion');

INSERT INTO `sql_files` (`sql_file_name`, `run_date`) VALUES ('6.sql', NOW());