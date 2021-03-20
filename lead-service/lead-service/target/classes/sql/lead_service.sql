DROP SCHEMA IF EXISTS lead_service;
CREATE SCHEMA lead_service;
USE lead_service;

CREATE TABLE `leads`(
id INT AUTO_INCREMENT NOT NULL,
`name` VARCHAR(255),
email VARCHAR(255),
company_name VARCHAR(255),
phone_number VARCHAR(255),
sales_rep_id INT,
PRIMARY KEY(id)
);

INSERT INTO `leads` (`name`, email, company_name, phone_number, sales_rep_id) VALUES
('John Nieve', 'johnstark@gmail.com', 'Invernalia', '654874598', 1),
('Baby Yoda', 'babyoda@gmail.com', 'Ajos sa', '654541257', 2);