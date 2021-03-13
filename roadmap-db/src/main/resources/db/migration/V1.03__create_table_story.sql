CREATE TABLE `story`
(
    `id`               varchar(63) NOT NULL,
    `name`             text        NOT NULL,
    `description`      text,
    `rank`             int(11)     NOT NULL,
    `state`            tinyint(4)  NOT NULL COMMENT '0 - Pending, 1 - Ready To Start, 2 - In Progress, 3 - Dev Complete, 4 - Test Complete, 5 - Accepted',
    `fix_versions`     varchar(255)         DEFAULT NULL,
    `sprint`           varchar(255)         DEFAULT NULL,
    `owner`            varchar(255)         DEFAULT NULL,
    `story_points`     float       NOT NULL DEFAULT '0' COMMENT 'column LOE',
    `story_created_at` datetime    NOT NULL,
    `created_at`       datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_updated_at`  datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `jira_id`          varchar(63)          DEFAULT NULL COMMENT 'JIRA ticket number',
    `epic_id`          varchar(63)          DEFAULT NULL,
    `epic_name`        text,
    PRIMARY KEY (`id`),
    KEY `epic_id_story_id` (`epic_id`, `id`),
    KEY `epic_id` (`epic_id`),
    CONSTRAINT `story_fk_epic_id` FOREIGN KEY (`epic_id`) REFERENCES `epic` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4