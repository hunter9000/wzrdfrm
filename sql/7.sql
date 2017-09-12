USE wzrdfrm;

-- the level tables for each class's levels
CREATE TABLE `char_class_level` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `char_class_definition` BIGINT(20) NOT NULL,
    `class_level` INTEGER(11) NOT NULL,      -- the level that this describes, ie, if 4, this describes the xp required to hit level 4, and the ability you get
    `xp_required` INTEGER(11) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `char_class_level__char_class_definition` (`char_class_definition`),
    CONSTRAINT `char_class_level__char_class_definition` FOREIGN KEY (`char_class_definition`) REFERENCES `char_class_definition` (`id`)
) COLLATE='utf8_general_ci' ENGINE=InnoDB;

INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Farm Hand'), 1, 2);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Farm Hand'), 2, 3);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Farm Hand'), 3, 5);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Farm Hand'), 4, 10);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Farm Hand'), 5, 15);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Farm Hand'), 6, 15);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Farm Hand'), 7, 15);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Farm Hand'), 8, 15);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Farm Hand'), 9, 10);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Farm Hand'), 10, 10);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Fighter'), 1, 5);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Fighter'), 2, 10);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Fighter'), 3, 15);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Fighter'), 4, 15);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Fighter'), 5, 15);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Fighter'), 6, 15);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Fighter'), 7, 15);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Fighter'), 8, 15);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Fighter'), 9, 15);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Fighter'), 10, 30);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Barbarian'), 1, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Barbarian'), 2, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Barbarian'), 3, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Barbarian'), 4, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Barbarian'), 5, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Barbarian'), 6, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Barbarian'), 7, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Barbarian'), 8, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Barbarian'), 9, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Barbarian'), 10, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Monk'), 1, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Monk'), 2, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Monk'), 3, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Monk'), 4, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Monk'), 5, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Monk'), 6, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Monk'), 7, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Monk'), 8, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Monk'), 9, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Monk'), 10, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Thief'), 1, 5);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Thief'), 2, 10);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Thief'), 3, 15);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Thief'), 4, 15);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Thief'), 5, 15);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Thief'), 6, 15);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Thief'), 7, 15);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Thief'), 8, 15);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Thief'), 9, 15);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Thief'), 10, 30);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Ninja'), 1, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Ninja'), 2, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Ninja'), 3, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Ninja'), 4, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Ninja'), 5, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Ninja'), 6, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Ninja'), 7, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Ninja'), 8, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Ninja'), 9, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Ninja'), 10, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Hunter'), 1, 5);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Hunter'), 2, 10);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Hunter'), 3, 15);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Hunter'), 4, 15);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Hunter'), 5, 15);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Hunter'), 6, 15);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Hunter'), 7, 15);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Hunter'), 8, 15);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Hunter'), 9, 15);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Hunter'), 10, 30);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Ranger'), 1, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Ranger'), 2, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Ranger'), 3, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Ranger'), 4, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Ranger'), 5, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Ranger'), 6, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Ranger'), 7, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Ranger'), 8, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Ranger'), 9, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Ranger'), 10, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Hedge Mage'), 1, 5);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Hedge Mage'), 2, 10);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Hedge Mage'), 3, 15);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Hedge Mage'), 4, 15);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Hedge Mage'), 5, 15);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Hedge Mage'), 6, 15);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Hedge Mage'), 7, 15);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Hedge Mage'), 8, 15);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Hedge Mage'), 9, 15);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Hedge Mage'), 10, 30);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Alchemist'), 1, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Alchemist'), 2, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Alchemist'), 3, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Alchemist'), 4, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Alchemist'), 5, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Alchemist'), 6, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Alchemist'), 7, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Alchemist'), 8, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Alchemist'), 9, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Alchemist'), 10, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Wizard'), 1, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Wizard'), 2, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Wizard'), 3, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Wizard'), 4, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Wizard'), 5, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Wizard'), 6, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Wizard'), 7, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Wizard'), 8, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Wizard'), 9, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Wizard'), 10, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Shaman'), 1, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Shaman'), 2, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Shaman'), 3, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Shaman'), 4, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Shaman'), 5, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Shaman'), 6, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Shaman'), 7, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Shaman'), 8, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Shaman'), 9, 20);
INSERT INTO `char_class_level` (char_class_definition, class_level, xp_required) VALUES ((SELECT id FROM `char_class` where name='Shaman'), 10, 20);


INSERT INTO `sql_files` (`sql_file_name`, `run_date`) VALUES ('7.sql', NOW());
