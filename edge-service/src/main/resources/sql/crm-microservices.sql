DROP SCHEMA IF EXISTS opportunity;
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

drop schema if exists contact;
create schema contact;
use contact;
create table contact (
   id int auto_increment not null,
    name varchar(255),
    phone_number varchar(255),
    email varchar(255),
    company_name varchar(255),
    primary key (id)
);
insert into contact (name, phone_number, email, company_name) values
("Pedro", "684253247", "pedro@pica.piedra", "Canteras Rodriguez"),
("Jaime", "628242447", "jaimelbueno@bond.es", "Royal British Crown"),
("Pepe", "897425631", "pepebotellas@bot.es", "Embotelladora Pepito");

DROP SCHEMA IF EXISTS sales_rep;
CREATE SCHEMA sales_rep;
USE sales_rep;
CREATE TABLE sales_rep (
  id INT AUTO_INCREMENT NOT NULL,
  name VARCHAR(255),
  PRIMARY KEY (id)
);
CREATE TABLE lead_id (
  lead_id INT,
  sales_rep_id INT,
  PRIMARY KEY (lead_id),
  FOREIGN KEY (sales_rep_id) REFERENCES sales_rep(id)
);
CREATE TABLE opportunity_sales_rep_id (
  opp_id INT,
  sales_rep_id INT,
  PRIMARY KEY (opp_id),
  FOREIGN KEY (sales_rep_id) REFERENCES sales_rep(id)
);
INSERT INTO sales_rep(name) VALUES
("Cruz"),
("Raya");
INSERT INTO lead_id(lead_id, sales_rep_id) VALUES
(1, 1),
(2, 2);
INSERT INTO opportunity_sales_rep_id(opp_id, sales_rep_id) VALUES
(1, 1),
(2, 2);


DROP SCHEMA IF EXISTS account;
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
CREATE TABLE opportunity_account_id (
  opportunity_id int NOT NULL,
  account_id int DEFAULT NULL,
  PRIMARY KEY (opportunity_id),
  FOREIGN KEY (account_id) REFERENCES account(id)
);
INSERT INTO account (id, city, country, employee_count, industry) VALUES
('1', 'Madrid', 'Spain', 10, 'PRODUCE');
INSERT INTO contact_id (contact_id, account_id) VALUES
('1', '1');
INSERT INTO opportunity_account_id (opportunity_id, account_id) VALUES
('1', '1');

DROP SCHEMA IF EXISTS lead_service;
CREATE SCHEMA lead_service;
USE lead_service;
CREATE TABLE leads(
id INT AUTO_INCREMENT NOT NULL,
name VARCHAR(255),
email VARCHAR(255),
company_name VARCHAR(255),
phone_number VARCHAR(255),
sales_rep_id INT,
PRIMARY KEY(id)
);
INSERT INTO leads (name, email, company_name, phone_number, sales_rep_id) VALUES
('John Nieve', 'johnstark@gmail.com', 'Invernalia', '654874598', 1),
('Baby Yoda', 'babyoda@gmail.com', 'Ajos sa', '654541257', 2);
SELECT * FROM opportunity.opportunity;
SELECT * FROM lead_service.leads;
SELECT * FROM account.account;
SELECT * FROM account.opportunity_account_id;
SELECT * FROM account.contact_id;
SELECT * FROM contact.contact;
SELECT * FROM sales_rep.sales_rep;
SELECT * FROM sales_rep.lead_id;
SELECT * FROM sales_rep.opportunity_sales_rep_id;
