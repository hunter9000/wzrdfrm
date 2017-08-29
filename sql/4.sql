USE wzrdfrm;

INSERT INTO `char_class_definition` (name, orbs_to_unlock) VALUES ('Farm Hand', 0);
INSERT INTO `char_class_definition` (name, orbs_to_unlock) VALUES ('Fighter', 5);
INSERT INTO `char_class_definition` (name, orbs_to_unlock) VALUES ('Barbarian', 10);
INSERT INTO `char_class_definition` (name, orbs_to_unlock) VALUES ('Monk', 10);
INSERT INTO `char_class_definition` (name, orbs_to_unlock) VALUES ('Thief', 5);
INSERT INTO `char_class_definition` (name, orbs_to_unlock) VALUES ('Ninja', 10);
INSERT INTO `char_class_definition` (name, orbs_to_unlock) VALUES ('Hunter', 5);
INSERT INTO `char_class_definition` (name, orbs_to_unlock) VALUES ('Ranger', 10);
INSERT INTO `char_class_definition` (name, orbs_to_unlock) VALUES ('Hedge Mage', 5);
INSERT INTO `char_class_definition` (name, orbs_to_unlock) VALUES ('Alchemist', 10);
INSERT INTO `char_class_definition` (name, orbs_to_unlock) VALUES ('Wizard', 10);
INSERT INTO `char_class_definition` (name, orbs_to_unlock) VALUES ('Shaman', 15);

INSERT INTO `char_class_prereq` (child_class_definition, prereq_class_definition) VALUES ((SELECT id FROM char_class_definition WHERE name = 'Fighter'), (SELECT id FROM char_class_definition WHERE name = 'Farm Hand'));
INSERT INTO `char_class_prereq` (child_class_definition, prereq_class_definition) VALUES ((SELECT id FROM char_class_definition WHERE name = 'Thief'), (SELECT id FROM char_class_definition WHERE name = 'Farm Hand'));
INSERT INTO `char_class_prereq` (child_class_definition, prereq_class_definition) VALUES ((SELECT id FROM char_class_definition WHERE name = 'Hunter'), (SELECT id FROM char_class_definition WHERE name = 'Farm Hand'));
INSERT INTO `char_class_prereq` (child_class_definition, prereq_class_definition) VALUES ((SELECT id FROM char_class_definition WHERE name = 'Hedge Mage'), (SELECT id FROM char_class_definition WHERE name = 'Farm Hand'));
INSERT INTO `char_class_prereq` (child_class_definition, prereq_class_definition) VALUES ((SELECT id FROM char_class_definition WHERE name = 'Barbarian'), (SELECT id FROM char_class_definition WHERE name = 'Fighter'));
INSERT INTO `char_class_prereq` (child_class_definition, prereq_class_definition) VALUES ((SELECT id FROM char_class_definition WHERE name = 'Monk'), (SELECT id FROM char_class_definition WHERE name = 'Fighter'));
INSERT INTO `char_class_prereq` (child_class_definition, prereq_class_definition) VALUES ((SELECT id FROM char_class_definition WHERE name = 'Ninja'), (SELECT id FROM char_class_definition WHERE name = 'Thief'));
INSERT INTO `char_class_prereq` (child_class_definition, prereq_class_definition) VALUES ((SELECT id FROM char_class_definition WHERE name = 'Ranger'), (SELECT id FROM char_class_definition WHERE name = 'Hunter'));
INSERT INTO `char_class_prereq` (child_class_definition, prereq_class_definition) VALUES ((SELECT id FROM char_class_definition WHERE name = 'Alchemist'), (SELECT id FROM char_class_definition WHERE name = 'Hedge Mage'));
INSERT INTO `char_class_prereq` (child_class_definition, prereq_class_definition) VALUES ((SELECT id FROM char_class_definition WHERE name = 'Wizard'), (SELECT id FROM char_class_definition WHERE name = 'Hedge Mage'));
INSERT INTO `char_class_prereq` (child_class_definition, prereq_class_definition) VALUES ((SELECT id FROM char_class_definition WHERE name = 'Shaman'), (SELECT id FROM char_class_definition WHERE name = 'Hedge Mage'));
INSERT INTO `char_class_prereq` (child_class_definition, prereq_class_definition) VALUES ((SELECT id FROM char_class_definition WHERE name = 'Shaman'), (SELECT id FROM char_class_definition WHERE name = 'Hunter'));


INSERT INTO `sql_files` (`sql_file_name`, `run_date`) VALUES ('4.sql', NOW());