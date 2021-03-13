CREATE TABLE `initiative`
(
    `id`                    varchar(63)      NOT NULL,
    `jira_number_id`        int(11)          NOT NULL,
    `name`                  text             NOT NULL,
    `description`           text,
    `rank`                  int(11)          NOT NULL,
    `state`                 tinyint(4)       NOT NULL COMMENT '1 - Not Started, 2 - In Progress, 3 - Accepted',
    `health`                tinyint(4)       NOT NULL COMMENT '1 - No Health, 2 - Done, 3 - At Risk, 4 - Critical/Slip, 5 - On Track, 6 - On Hold',
    `report_color`          varchar(63)               DEFAULT NULL,
    `start_date`            datetime                  DEFAULT NULL,
    `target_complete_date`  datetime                  DEFAULT NULL,
    `portfolio_ask_date`    datetime                  DEFAULT NULL,
    `releases`              text,
    `release_start_date`    date                      DEFAULT NULL,
    `release_end_date`      date                      DEFAULT NULL,
    `story_points_total`    int(10) unsigned NOT NULL,
    `story_points_accepted` int(10) unsigned NOT NULL,
    `created_at`            datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_updated_at`       datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `pl_goal_id`            varchar(63)               DEFAULT NULL,
    `pl_goal_name`          text,
    PRIMARY KEY (`id`),
    UNIQUE KEY `initiative_jira_id_UNIQUE` (`jira_number_id`),
    KEY `pl_goal_id_initiative_id` (`pl_goal_id`, `id`),
    KEY `pl_goal_id` (`pl_goal_id`),
    CONSTRAINT `initiative_fk_pl_goal_id` FOREIGN KEY (`pl_goal_id`) REFERENCES `pl_goal` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4