CREATE TABLE `epic_program`
(
    `id`              int(10) unsigned NOT NULL AUTO_INCREMENT,
    `epic_id`         varchar(63)      NOT NULL,
    `program_index`   int(10) unsigned NOT NULL,
    `created_at`      datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_updated_at` datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uq_epic_id_and_program_index` (`epic_id`, `program_index`),
    KEY `epic_id` (`epic_id`),
    KEY `program_index` (`program_index`),
    CONSTRAINT `epic_program_fk_epic_id` FOREIGN KEY (`epic_id`) REFERENCES `epic` (`id`),
    CONSTRAINT `epic_program_fk_program_index` FOREIGN KEY (`program_index`) REFERENCES `program` (`index`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4