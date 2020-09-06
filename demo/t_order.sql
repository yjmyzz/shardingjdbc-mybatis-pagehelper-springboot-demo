DROP TABLE IF EXISTS `t_order0`;
CREATE TABLE IF NOT EXISTS `t_order0`
(
    `order_id`   INT UNSIGNED,
    `order_name` VARCHAR(255) NOT NULL,
    `order_date` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`order_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `t_order1`;
CREATE TABLE IF NOT EXISTS `t_order1`
(
    `order_id`   INT UNSIGNED,
    `order_name` VARCHAR(255) NOT NULL,
    `order_date` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`order_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
