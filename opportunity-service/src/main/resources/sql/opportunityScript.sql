GRANT ALL PRIVILEGES ON opportunity.* TO 'adrialopezbou'@'localhost';
DROP SCHEMA opportunity;
CREATE SCHEMA opportunity;
USE opportunity;


CREATE TABLE opportunity (
	id INT NOT NULL AUTO_INCREMENT,
    product VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    contact_id INT NOT NULL,
    status VARCHAR(255) NOT NULL,
    sales_rep_id INT NOT NULL,
    PRIMARY KEY(id)
);

INSERT INTO opportunity(product, quantity, contact_id, status, sales_rep_id) VALUES
	('HYBRID', 20, 1, 'OPEN', 1),
    ('BOX', 30, 2, 'OPEN', 1)
;

SELECT * FROM opportunity;
