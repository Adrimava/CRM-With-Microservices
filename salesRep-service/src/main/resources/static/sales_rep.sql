DROP SCHEMA sales_rep;
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

CREATE TABLE opportunity_id (
  opp_id INT,
  sales_rep_id INT,
  PRIMARY KEY (opp_id),
  FOREIGN KEY (sales_rep_id) REFERENCES sales_rep(id)
);

INSERT INTO sales_rep(name) VALUES
("Cruz"),
("Raya");

INSERT INTO lead_id(lead_id, sales_rep_id) VALUES
(10, 1),
(20, 2);

INSERT INTO opportunity_id(opp_id, sales_rep_id) VALUES
(30, 1),
(40, 2);

