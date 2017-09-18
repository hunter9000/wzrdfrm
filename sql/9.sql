USE wzrdfrm;

ALTER TABLE `farm` ADD COLUMN `num_unlock_orbs` INTEGER(11) NOT NULL;

DROP TABLE `char_class_ability`;

ALTER TABLE `char_class_level` ADD COLUMN `ability_type` VARCHAR(255) NULL;

UPDATE `char_class_level`
SET ability_type = 'UNLOCK_PLOT_2'
WHERE char_class_definition = (SELECT id FROM char_class_definition WHERE name = 'Farm Hand')
AND class_level = 1;

UPDATE `char_class_level`
SET ability_type = 'UNLOCK_PLOT_3'
WHERE char_class_definition = (SELECT id FROM char_class_definition WHERE name = 'Farm Hand')
AND class_level = 2;

UPDATE `char_class_level`
SET ability_type = 'UNLOCK_PLOT_4'
WHERE char_class_definition = (SELECT id FROM char_class_definition WHERE name = 'Farm Hand')
AND class_level = 3;

INSERT INTO `sql_files` (`sql_file_name`, `run_date`) VALUES ('9.sql', NOW());