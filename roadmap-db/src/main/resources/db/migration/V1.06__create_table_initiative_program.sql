CREATE TABLE `initiative_program`
(
    `index`           int(10) unsigned NOT NULL AUTO_INCREMENT,
    `initiative_id`   varchar(63)      NOT NULL,
    `program_index`   int(10) unsigned NOT NULL,
    `created_at`      datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_updated_at` datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`index`),
    UNIQUE KEY `uq_initiative_id_and_program_index` (`initiative_id`, `program_index`),
    KEY `initiative_id` (`initiative_id`),
    KEY `program_index` (`program_index`),
    CONSTRAINT `initiative_program_fk_initiative_id` FOREIGN KEY (`initiative_id`) REFERENCES `initiative` (`id`),
    CONSTRAINT `initiative_program_fk_program_index` FOREIGN KEY (`program_index`) REFERENCES `program` (`index`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4