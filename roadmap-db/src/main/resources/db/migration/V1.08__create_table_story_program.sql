CREATE TABLE `story_program`
(
    `id`              int(10) unsigned NOT NULL AUTO_INCREMENT,
    `story_id`        varchar(63)      NOT NULL,
    `program_index`   int(10) unsigned NOT NULL,
    `created_at`      datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_updated_at` datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uq_story_id_and_program_index` (`story_id`, `program_index`),
    KEY `story_id` (`story_id`),
    KEY `program_index` (`program_index`),
    CONSTRAINT `story_program_fk_story_id` FOREIGN KEY (`story_id`) REFERENCES `story` (`id`),
    CONSTRAINT `tory_program_fk_program_index` FOREIGN KEY (`program_index`) REFERENCES `program` (`index`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4