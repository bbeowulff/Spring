-- CREATE TABLE IF NOT EXISTS purchase (
--                                         id INT AUTO_INCREMENT PRIMARY KEY,
--                                         product VARCHAR(50) NOT NULL,
--     price DOUBLE NOT NULL
--     );
-- CREATE TABLE account (
--                          id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
--                          name VARCHAR(50) NOT NULL,
--                          amount DOUBLE NOT NULL
-- );

CREATE TABLE purchase (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          product VARCHAR(50) NOT NULL,
                          price DOUBLE NOT NULL
);

