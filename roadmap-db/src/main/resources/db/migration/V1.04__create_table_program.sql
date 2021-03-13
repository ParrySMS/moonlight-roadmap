CREATE TABLE `program`
(
    `index`           int(10) unsigned NOT NULL AUTO_INCREMENT,
    `name`            varchar(255)     NOT NULL,
    `created_at`      datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_updated_at` datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`index`),
    UNIQUE KEY `name_unique` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4