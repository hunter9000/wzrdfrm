USE wzrdfrm;

CREATE TABLE `farm` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `owner` BIGINT(20) NOT NULL,
    `curr_char_class` BIGINT(20) NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `farm__user` (`owner`),
    CONSTRAINT `farm__user` FOREIGN KEY (`owner`) REFERENCES `user` (`id`)
) COLLATE='utf8_general_ci' ENGINE=InnoDB;

CREATE TABLE `farm_plot` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `farm` BIGINT(20) NOT NULL,
    `row` INTEGER(11) NOT NULL,
    `col` INTEGER(11) NOT NULL,
    `plant_date` TIMESTAMP NULL,
    `plant_type` VARCHAR(255) NULL,
    `unlocked` BIT(1) NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `farm_plot__farm` (`farm`),
    CONSTRAINT `farm_plot__farm` FOREIGN KEY (`farm`) REFERENCES `farm` (`id`)
) COLLATE='utf8_general_ci' ENGINE=InnoDB;

CREATE TABLE `char_class_definition` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `orbs_to_unlock` INTEGER(11) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `char_class_definition__name` (`name`)
) COLLATE='utf8_general_ci' ENGINE=InnoDB;

-- join table for class definition prerequisites
CREATE TABLE `char_class_prereq` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `child_class_definition` BIGINT(20) NOT NULL,
    `prereq_class_definition` BIGINT(20) NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `char_class_prereq__child_class_definition` (`child_class_definition`),
    INDEX `char_class_prereq__prereq_class_definition` (`prereq_class_definition`),
    CONSTRAINT `char_class_prereq__child_class_definition` FOREIGN KEY (`child_class_definition`) REFERENCES `char_class_definition` (`id`),
    CONSTRAINT `char_class_prereq__prereq_class_definition` FOREIGN KEY (`prereq_class_definition`) REFERENCES `char_class_definition` (`id`)
) COLLATE='utf8_general_ci' ENGINE=InnoDB;

-- character class that a farm has
CREATE TABLE `char_class` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `farm` BIGINT(20) NOT NULL,
    `char_class_definition` BIGINT(20) NOT NULL,
    `curr_xp` INTEGER(11) NOT NULL,
    `curr_level` INTEGER(11) NOT NULL,
    `unlocked` BIT(1) NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `char_class__farm` (`farm`),
    INDEX `char_class__char_class_definition` (`char_class_definition`),
    CONSTRAINT `char_class__farm` FOREIGN KEY (`farm`) REFERENCES `farm` (`id`),
    CONSTRAINT `char_class__char_class_definition` FOREIGN KEY (`char_class_definition`) REFERENCES `char_class_definition` (`id`)
) COLLATE='utf8_general_ci' ENGINE=InnoDB;

-- definition of abilities each class has
CREATE TABLE `char_class_ability` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `char_class_definition` BIGINT(20) NOT NULL,
    `ability_type` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `char_class_ability__char_class_definition` (`char_class_definition`),
    CONSTRAINT `char_class_ability__char_class_definition` FOREIGN KEY (`char_class_definition`) REFERENCES `char_class_definition` (`id`)
) COLLATE='utf8_general_ci' ENGINE=InnoDB;

-- add index and constraint for farm's char class column
ALTER TABLE `farm` ADD INDEX `farm__curr_char_class` (`curr_char_class`);
ALTER TABLE `farm` ADD CONSTRAINT `farm__curr_char_class` FOREIGN KEY (`curr_char_class`) REFERENCES `char_class` (`id`);


INSERT INTO `sql_files` (`sql_file_name`, `run_date`) VALUES ('3.sql', NOW());