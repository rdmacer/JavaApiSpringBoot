DROP TABLE IF EXISTS Exchange;
CREATE TABLE Exchange(id INT PRIMARY KEY, source_currency VARCHAR(10), target_currency VARCHAR(10), rate DOUBLE(6));