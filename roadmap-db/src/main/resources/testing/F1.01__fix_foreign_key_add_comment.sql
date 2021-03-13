ALTER TABLE `roadmap`.`pl_goal`
    CHANGE COLUMN `rank` `rank` INT DEFAULT 0 COMMENT 'It cannot be empty. If it is empty, report data error',
    CHANGE COLUMN `report_color` `report_color` VARCHAR(63) DEFAULT NULL COMMENT 'Currently, we save it, not use it on UI'
;

--
-- the foreign key constraint need the keyword FOREIGN KEY.
-- refer: https://dev.mysql.com/doc/refman/8.0/en/create-table-foreign-keys.html
--

ALTER TABLE `roadmap`.`initiative`
    ADD INDEX `pl_goal_id_initiative_id` (`pl_goal_id` ASC, `id` ASC) VISIBLE,
    ADD INDEX `pl_goal_id` (`pl_goal_id` ASC) VISIBLE,
    ADD CONSTRAINT `initiative_fk_pl_goal_id`
        FOREIGN KEY (`pl_goal_id`)
            REFERENCES `roadmap`.`pl_goal` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
;

ALTER TABLE `roadmap`.`epic`
    ADD INDEX `initiative_id_epic_id` (`initiative_id` ASC, `id` ASC) VISIBLE,
    ADD INDEX `initiative_id` (`initiative_id` ASC) VISIBLE,
    ADD CONSTRAINT `epic_fk_initiative_id`
        FOREIGN KEY (`initiative_id`)
            REFERENCES `roadmap`.`initiative` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
;

ALTER TABLE `roadmap`.`story`
    ADD INDEX `epic_id_story_id` (`epic_id` ASC, `id` ASC) VISIBLE,
    ADD INDEX `epic_id` (`epic_id` ASC) VISIBLE,
    ADD CONSTRAINT `story_fk_epic_id`
        FOREIGN KEY (`epic_id`)
            REFERENCES `roadmap`.`epic` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
;

--
-- program with number index, other FK linked to name
--

ALTER TABLE `roadmap`.`program`
    ADD COLUMN `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP AFTER `name`,
    ADD COLUMN `last_updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP AFTER `created_at`,
    ADD COLUMN `id` INT UNSIGNED NOT NULL AUTO_INCREMENT FIRST,
    DROP PRIMARY KEY,
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE INDEX `name_unique` (`name` ASC) VISIBLE
;

ALTER TABLE `roadmap`.`pl_goal_program`
    ADD COLUMN `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP AFTER `program_name`,
    ADD COLUMN `last_updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP AFTER `created_at`,
    ADD COLUMN `id` INT UNSIGNED NOT NULL AUTO_INCREMENT FIRST,
    DROP PRIMARY KEY,
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE INDEX `uq_id_name` (`pl_goal_id` ASC, `program_name` ASC) VISIBLE,
    ADD INDEX `pl_goal_id` (`pl_goal_id` ASC) VISIBLE,
    ADD CONSTRAINT `pl_goal_program_fk_pl_goal_id`
        FOREIGN KEY (`pl_goal_id`)
            REFERENCES `roadmap`.`pl_goal` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    ADD INDEX `program_name` (`program_name` ASC) VISIBLE,
    ADD CONSTRAINT `pl_goal_program_fk_program_name`
        FOREIGN KEY (`program_name`)
            REFERENCES `roadmap`.`program` (`name`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
;


ALTER TABLE `roadmap`.`initiative_program`
    ADD COLUMN `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP AFTER `program_name`,
    ADD COLUMN `last_updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP AFTER `created_at`,
    ADD COLUMN `id` INT UNSIGNED NOT NULL AUTO_INCREMENT FIRST,
    DROP PRIMARY KEY,
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE INDEX `uq_id_name` (`initiative_id` ASC, `program_name` ASC) VISIBLE,
    ADD INDEX `initiative_id` (`initiative_id` ASC) VISIBLE,
    ADD CONSTRAINT `initiative_program_fk_initiative_id`
        FOREIGN KEY (`initiative_id`)
            REFERENCES `roadmap`.`initiative` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    ADD INDEX `program_name` (`program_name` ASC) VISIBLE,
    ADD CONSTRAINT `initiative_program_fk_program_name`
        FOREIGN KEY (`program_name`)
            REFERENCES `roadmap`.`program` (`name`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
;

ALTER TABLE `roadmap`.`epic_program`
    ADD COLUMN `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP AFTER `program_name`,
    ADD COLUMN `last_updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP AFTER `created_at`,
    ADD COLUMN `id` INT UNSIGNED NOT NULL AUTO_INCREMENT FIRST,
    DROP PRIMARY KEY,
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE INDEX `uq_id_name` (`epic_id` ASC, `program_name` ASC) VISIBLE,
    ADD INDEX `epic_id` (`epic_id` ASC) VISIBLE,
    ADD CONSTRAINT `epic_program_fk_epic_id`
        FOREIGN KEY (`epic_id`)
            REFERENCES `roadmap`.`epic` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    ADD INDEX `program_name` (`program_name` ASC) VISIBLE,
    ADD CONSTRAINT `epic_program_fk_program_name`
        FOREIGN KEY (`program_name`)
            REFERENCES `roadmap`.`program` (`name`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
;

ALTER TABLE `roadmap`.`story_program`
    ADD COLUMN `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP AFTER `program_name`,
    ADD COLUMN `last_updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP AFTER `created_at`,
    ADD COLUMN `id` INT UNSIGNED NOT NULL AUTO_INCREMENT FIRST,
    DROP PRIMARY KEY,
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE INDEX `uq_id_name` (`story_id` ASC, `program_name` ASC) VISIBLE,
    ADD INDEX `story_id` (`story_id` ASC) VISIBLE,
    ADD CONSTRAINT `story_program_fk_story_id`
        FOREIGN KEY (`story_id`)
            REFERENCES `roadmap`.`story` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    ADD INDEX `program_name` (`program_name` ASC) VISIBLE,
    ADD CONSTRAINT `tory_program_fk_program_name`
        FOREIGN KEY (`program_name`)
            REFERENCES `roadmap`.`program` (`name`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
;

--
-- program_increment with number index,  other FK linked to id for the DATE
--

ALTER TABLE `roadmap`.`program_increment`
    ADD COLUMN `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP AFTER `name`,
    ADD COLUMN `last_updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP AFTER `created_at`,
    ADD COLUMN `start_date` DATE NULL AFTER `name`,
    ADD COLUMN `end_date` DATE NULL AFTER `start_date`,
    ADD COLUMN `id` INT UNSIGNED NOT NULL AUTO_INCREMENT FIRST,
    DROP PRIMARY KEY,
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE INDEX `name_unique` (`name` ASC) VISIBLE
;

ALTER TABLE `roadmap`.`pl_goal_pi`
    CHANGE COLUMN `pi_name` `pi_id` INT UNSIGNED NOT NULL , -- linked to id for the DATE
    ADD COLUMN `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP AFTER `pi_id`,
    ADD COLUMN `last_updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP AFTER `created_at`,
    ADD COLUMN `id` INT UNSIGNED NOT NULL AUTO_INCREMENT FIRST,
    DROP PRIMARY KEY,
    ADD PRIMARY KEY (`id`),
    ADD INDEX `uq_id_name` (`pl_goal_id` ASC, `pi_id` ASC) INVISIBLE,
    ADD INDEX `pl_goal_id` (`pl_goal_id` ASC) VISIBLE,
    ADD CONSTRAINT `pl_goal_pi_fk_pl_goal_id`
        FOREIGN KEY (`pl_goal_id`)
            REFERENCES `roadmap`.`pl_goal` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    ADD INDEX `pi_id` (`pi_id` ASC) VISIBLE,
    ADD CONSTRAINT `pl_goal_pi_fk_pl_id`
        FOREIGN KEY (`pi_id`)
            REFERENCES `roadmap`.`program_increment` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
;

ALTER TABLE `roadmap`.`initiative_pi`
    ADD COLUMN `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP AFTER `pi_id`,
    ADD COLUMN `last_updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP AFTER `created_at`,
    CHANGE COLUMN `pi_name` `pi_id` INT UNSIGNED NOT NULL , -- linked to id for the DATE
    ADD COLUMN `id` INT UNSIGNED NOT NULL AUTO_INCREMENT FIRST,
    DROP PRIMARY KEY,
    ADD PRIMARY KEY (`id`),
    ADD INDEX `uq_id_name` (`initiative_id` ASC, `pi_id` ASC) INVISIBLE,
    ADD INDEX `initiative_id` (`initiative_id` ASC) VISIBLE,
    ADD CONSTRAINT `initiative_pi_fk_initiative_id`
        FOREIGN KEY (`initiative_id`)
            REFERENCES `roadmap`.`initiative` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    ADD INDEX `pi_id` (`pi_id` ASC) VISIBLE,
    ADD CONSTRAINT `initiative_pi_fk_pl_id`
        FOREIGN KEY (`pi_id`)
            REFERENCES `roadmap`.`program_increment` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
;

ALTER TABLE `roadmap`.`epic_pi`
    ADD COLUMN `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP AFTER `pi_id`,
    ADD COLUMN `last_updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP AFTER `created_at`,
    CHANGE COLUMN `pi_name` `pi_id` INT UNSIGNED NOT NULL , -- linked to id for the DATE
    ADD COLUMN `id` INT UNSIGNED NOT NULL AUTO_INCREMENT FIRST,
    DROP PRIMARY KEY,
    ADD PRIMARY KEY (`id`),
    ADD INDEX `uq_id_name` (`epic_id` ASC, `pi_id` ASC) INVISIBLE,
    ADD INDEX `epic_id` (`epic_id` ASC) VISIBLE,
    ADD CONSTRAINT `epic_pi_fk_epic_id`
        FOREIGN KEY (`epic_id`)
            REFERENCES `roadmap`.`epic` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    ADD INDEX `pi_id` (`pi_id` ASC) VISIBLE,
    ADD CONSTRAINT `epic_pi_fk_pl_id`
        FOREIGN KEY (`pi_id`)
            REFERENCES `roadmap`.`program_increment` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
;

ALTER TABLE `roadmap`.`story_pi`
    ADD COLUMN `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP AFTER `pi_id`,
    ADD COLUMN `last_updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP AFTER `created_at`,
    CHANGE COLUMN `pi_name` `pi_id` INT UNSIGNED NOT NULL , -- linked to id for the DATE
    ADD COLUMN `id` INT UNSIGNED NOT NULL AUTO_INCREMENT FIRST,
    DROP PRIMARY KEY,
    ADD PRIMARY KEY (`id`),
    ADD INDEX `uq_id_name` (`story_id` ASC, `pi_id` ASC) INVISIBLE,
    ADD INDEX `story_id` (`story_id` ASC) VISIBLE,
    ADD CONSTRAINT `story_pi_fk_story_id`
        FOREIGN KEY (`story_id`)
            REFERENCES `roadmap`.`story` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    ADD INDEX `pi_id` (`pi_id` ASC) VISIBLE,
    ADD CONSTRAINT `story_pi_fk_pl_id`
        FOREIGN KEY (`pi_id`)
            REFERENCES `roadmap`.`program_increment` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
;

--
-- team with number index, other FK linked to name
--

ALTER TABLE `roadmap`.`team`
    ADD COLUMN `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP AFTER `name`,
    ADD COLUMN `last_updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP AFTER `created_at`,
    ADD COLUMN `id` INT UNSIGNED NOT NULL AUTO_INCREMENT FIRST,
    DROP PRIMARY KEY,
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE INDEX `name_unique` (`name` ASC) VISIBLE;
;

ALTER TABLE `roadmap`.`pl_goal_team`
    ADD COLUMN `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP AFTER `team_name`,
    ADD COLUMN `last_updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP AFTER `created_at`,
    ADD COLUMN `id` INT UNSIGNED NOT NULL AUTO_INCREMENT FIRST,
    DROP PRIMARY KEY,
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE INDEX `uq_id_team` (`pl_goal_id` ASC, `team_name` ASC) VISIBLE,
    ADD INDEX `pl_goal_id` (`pl_goal_id` ASC) VISIBLE,
    ADD CONSTRAINT `pl_goal_team_fk_pl_goal_id`
        FOREIGN KEY (`pl_goal_id`)
            REFERENCES `roadmap`.`pl_goal` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    ADD INDEX `team_name` (`team_name` ASC) VISIBLE,
    ADD CONSTRAINT`pl_goal_team_fk_team_name`
        FOREIGN KEY (`team_name`)
            REFERENCES `roadmap`.`team` (`name`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
;

ALTER TABLE `roadmap`.`initiative_team`
    ADD COLUMN `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP AFTER `team_name`,
    ADD COLUMN `last_updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP AFTER `created_at`,
    ADD COLUMN `id` INT UNSIGNED NOT NULL AUTO_INCREMENT FIRST,
    DROP PRIMARY KEY,
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE INDEX `uq_id_team` (`initiative_id` ASC, `team_name` ASC) VISIBLE,
    ADD INDEX `initiative_id` (`initiative_id` ASC) VISIBLE,
    ADD CONSTRAINT `initiative_team_fk_initiative_id`
        FOREIGN KEY (`initiative_id`)
            REFERENCES `roadmap`.`initiative` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    ADD INDEX `team_name` (`team_name` ASC) VISIBLE,
    ADD CONSTRAINT `initiative_team_fk_team_name`
        FOREIGN KEY (`team_name`)
            REFERENCES `roadmap`.`team` (`name`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
;

ALTER TABLE `roadmap`.`epic_team`
    ADD COLUMN `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP AFTER `team_name`,
    ADD COLUMN `last_updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP AFTER `created_at`,
    ADD COLUMN `id` INT UNSIGNED NOT NULL AUTO_INCREMENT FIRST,
    DROP PRIMARY KEY,
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE INDEX `uq_id_team` (`epic_id` ASC, `team_name` ASC) VISIBLE,
    ADD INDEX `epic_id` (`epic_id` ASC) VISIBLE,
    ADD CONSTRAINT `epic_team_fk_epic_id`
        FOREIGN KEY (`epic_id`)
            REFERENCES `roadmap`.`epic` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    ADD INDEX `team_name` (`team_name` ASC) VISIBLE,
    ADD CONSTRAINT `epic_team_fk_team_name`
        FOREIGN KEY (`team_name`)
            REFERENCES `roadmap`.`team` (`name`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
;

ALTER TABLE `roadmap`.`story_team`
    ADD COLUMN `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP AFTER `team_name`,
    ADD COLUMN `last_updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP AFTER `created_at`,
    ADD COLUMN `id` INT UNSIGNED NOT NULL AUTO_INCREMENT FIRST,
    DROP PRIMARY KEY,
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE INDEX `uq_id_team` (`story_id` ASC, `team_name` ASC) VISIBLE,
    ADD INDEX `story_id` (`story_id` ASC) VISIBLE,
    ADD CONSTRAINT `story_team_fk_story_id`
        FOREIGN KEY (`story_id`)
            REFERENCES `roadmap`.`story` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    ADD INDEX `team_name` (`team_name` ASC) VISIBLE,
    ADD CONSTRAINT `story_team_fk_team_name`
        FOREIGN KEY (`team_name`)
            REFERENCES `roadmap`.`team` (`name`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
;