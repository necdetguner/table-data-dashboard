GRANT ALL ON *.* TO dbuser;

DROP DATABASE IF EXISTS testdb;
CREATE DATABASE testdb;
USE testdb;

CREATE TABLE stock
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45) NOT NULL,
    price DOUBLE NOT NULL,
    quantity INT NOT NULL,
    daily_change_rate DOUBLE NOT NULL,
    exchange VARCHAR(10) NOT NULL
) CHARSET = utf8;

INSERT INTO `stock` (`name`, `price`, `quantity`, `daily_change_rate`, `exchange`)
VALUES ('Apple', 100, 100, 0.01, 'XNYS');

INSERT INTO `stock` (`name`, `price`, `quantity`, `daily_change_rate`, `exchange`)
VALUES ('Samsung', 200, 200, 0.02, 'XNAS');

INSERT INTO `stock` (`name`, `price`, `quantity`, `daily_change_rate`, `exchange`)
VALUES ('Huawei', 300, 300, 0.03, 'XAMS');

INSERT INTO `stock` (`name`, `price`, `quantity`, `daily_change_rate`, `exchange`)
VALUES ('Xiaomi', 400, 400, 0.04, 'XJPX');

INSERT INTO `stock` (`name`, `price`, `quantity`, `daily_change_rate`, `exchange`)
VALUES ('Google', 500, 150, 0.015, 'XNAS');

INSERT INTO `stock` (`name`, `price`, `quantity`, `daily_change_rate`, `exchange`)
VALUES ('Microsoft', 600, 250, 0.025, 'XNYS');

INSERT INTO `stock` (`name`, `price`, `quantity`, `daily_change_rate`, `exchange`)
VALUES ('Amazon', 700, 350, 0.035, 'XNYS');

INSERT INTO `stock` (`name`, `price`, `quantity`, `daily_change_rate`, `exchange`)
VALUES ('Alibaba', 800, 450, 0.045, 'XHKG');

INSERT INTO `stock` (`name`, `price`, `quantity`, `daily_change_rate`, `exchange`)
VALUES ('Facebook', 900, 550, 0.055, 'XNAS');

INSERT INTO `stock` (`name`, `price`, `quantity`, `daily_change_rate`, `exchange`)
VALUES ('Tesla', 1000, 650, 0.065, 'XNAS');

INSERT INTO `stock` (`name`, `price`, `quantity`, `daily_change_rate`, `exchange`)
VALUES ('Tencent', 1100, 750, 0.075, 'XHKG');
