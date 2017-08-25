USE wzrdfrm;

CREATE TABLE `farm` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (`id`)
) COLLATE='utf8_general_ci' ENGINE=InnoDB;

CREATE TABLE `farm_plot` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `farm` BIGINT(20) NOT NULL,
    `plant_date` TIMESTAMP NULL,
    `plant_type` VARCHAR(255) NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `farm_plot__farm` (`farm`),
    CONSTRAINT `farm_plot__farm` FOREIGN KEY (`farm`) REFERENCES `farm` (`id`)
) COLLATE='utf8_general_ci' ENGINE=InnoDB;

ALTER TABLE `user` ADD COLUMN `farm` BIGINT(20);
ALTER TABLE `user` ADD UNIQUE INDEX `user__farm` (`farm`);
ALTER TABLE `user` ADD CONSTRAINT `user__farm` FOREIGN KEY (`farm`) REFERENCES `farm` (`id`);

INSERT INTO `sql_files` (`sql_file_name`, `run_date`) VALUES ('3.sql', NOW());