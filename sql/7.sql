USE wzrdfrm;

ALTER TABLE `seed_inventory` DROP FOREIGN KEY `seed_inventory__plant`;

INSERT INTO `sql_files` (`sql_file_name`, `run_date`) VALUES ('7.sql', NOW());