DROP SCHEMA account;
CREATE SCHEMA account;
USE account;

CREATE TABLE account (
  id int NOT NULL AUTO_INCREMENT,
  city varchar(255) DEFAULT NULL,
  country varchar(255) DEFAULT NULL,
  employee_count int NOT NULL,
  industry varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE contact_id (
  contact_id int NOT NULL,
  account_id int DEFAULT NULL,
  PRIMARY KEY (contact_id),
  FOREIGN KEY (account_id) REFERENCES account(id)
);

CREATE TABLE opportunity_id (
  opportunity_id int NOT NULL,
  account_id int DEFAULT NULL,
  PRIMARY KEY (opportunity_id),
  FOREIGN KEY (account_id) REFERENCES account(id)
);

INSERT INTO account (id, city, country, employee_count, industry) VALUES
('1', 'Madrid', 'Spain', 10, 'PRODUCE');

INSERT INTO contact_id (contact_id, account_id) VALUES
('1', '1');

INSERT INTO opportunity_id (opportunity_id, account_id) VALUES
('1', '1');

SELECT * FROM account;


