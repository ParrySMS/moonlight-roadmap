CREATE TABLE `pl_goal_program`
(
    `index`           int(10) unsigned NOT NULL AUTO_INCREMENT,
    `pl_goal_id`      varchar(63)      NOT NULL,
    `program_index`   int(10) unsigned NOT NULL,
    `created_at`      datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_updated_at` datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`index`),
    UNIQUE KEY `uq_pl_goal_id_and_program_index` (`pl_goal_id`, `program_index`),
    KEY `pl_goal_id` (`pl_goal_id`),
    KEY `program_index` (`program_index`),
    CONSTRAINT `pl_goal_program_fk_pl_goal_id` FOREIGN KEY (`pl_goal_id`) REFERENCES `pl_goal` (`id`),
    CONSTRAINT `pl_goal_program_fk_program_index` FOREIGN KEY (`program_index`) REFERENCES `program` (`index`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4