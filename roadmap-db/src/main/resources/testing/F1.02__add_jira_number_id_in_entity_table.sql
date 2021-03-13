ALTER TABLE `roadmap`.`epic`
CHANGE COLUMN `jira_id` `epic_link` VARCHAR(63) NULL DEFAULT NULL COMMENT 'JIRA ticket number,eg CLP-1174' ;


ALTER TABLE `roadmap`.`pl_goal`
ADD COLUMN `jira_number_id` INT NOT NULL AFTER `id`,
ADD UNIQUE INDEX `pl_goal_jira_id_UNIQUE` (`jira_number_id` ASC) VISIBLE;
;

ALTER TABLE `roadmap`.`initiative`
ADD COLUMN `jira_number_id` INT NOT NULL AFTER `id`,
ADD UNIQUE INDEX `initiative_jira_id_UNIQUE` (`jira_number_id` ASC) VISIBLE;
;

ALTER TABLE `roadmap`.`epic`
ADD COLUMN `jira_number_id` INT NOT NULL AFTER `id`,
ADD UNIQUE INDEX `epic_jira_id_UNIQUE` (`jira_number_id` ASC) VISIBLE;
;