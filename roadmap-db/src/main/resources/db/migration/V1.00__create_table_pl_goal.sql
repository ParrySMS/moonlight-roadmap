CREATE TABLE `pl_goal`
(
    `id`                    varchar(63)      NOT NULL,
    `jira_number_id`        int(11)          NOT NULL,
    `name`                  text             NOT NULL,
    `description`           text,
    `rank`                  int(11)                   DEFAULT '0' COMMENT 'It cannot be empty. If it is empty, report data error',
    `state`                 tinyint(4)       NOT NULL COMMENT '1 - Not Started, 2 - In Progress 3 - Done',
    `health`                tinyint(4)       NOT NULL COMMENT '1 - No Health,  2 - Done, 3 - At Risk, 4 - Critical/Slip, 5 - On Track, 6 - On Hold',
    `report_color`          varchar(63)               DEFAULT NULL COMMENT 'Currently, we save it, not use it on UI',
    `start_date`            date                      DEFAULT NULL,
    `target_complete_date`  date                      DEFAULT NULL,
    `portfolio_ask_date`    date                      DEFAULT NULL,
    `releases`              text,
    `release_start_date`    date                      DEFAULT NULL,
    `release_end_date`      date                      DEFAULT NULL,
    `story_points_total`    int(10) unsigned NOT NULL,
    `story_points_accepted` int(10) unsigned NOT NULL,
    `created_at`            datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_updated_at`       datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `pl_goal_jira_id_UNIQUE` (`jira_number_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4