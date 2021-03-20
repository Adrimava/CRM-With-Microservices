DROP SCHEMA IF EXISTS lead_service_test;
CREATE SCHEMA lead_service_test;
USE lead_service_test;

CREATE TABLE `leads`(
id INT AUTO_INCREMENT NOT NULL,
`name` VARCHAR(255),
email VARCHAR(255),
company_name VARCHAR(255),
phone_number VARCHAR(255),
sales_rep_id INT,
PRIMARY KEY(id)
);

