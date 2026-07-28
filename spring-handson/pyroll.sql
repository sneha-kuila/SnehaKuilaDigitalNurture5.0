CREATE TABLE department (
    dp_id INT PRIMARY KEY AUTO_INCREMENT,
    dp_name VARCHAR(50) NOT NULL
);
CREATE TABLE employee (
    em_id INT PRIMARY KEY AUTO_INCREMENT,
    em_name VARCHAR(50) NOT NULL,
    em_salary DECIMAL(10,2),
    em_permanent BOOLEAN,
    em_date_of_birth DATE,
    em_dp_id INT,
    FOREIGN KEY (em_dp_id) REFERENCES department(dp_id)
);
CREATE TABLE skill (
    sk_id INT PRIMARY KEY AUTO_INCREMENT,
    sk_name VARCHAR(50) NOT NULL
);
CREATE TABLE employee_skill (
    es_em_id INT,
    es_sk_id INT,
    PRIMARY KEY (es_em_id, es_sk_id),
    FOREIGN KEY (es_em_id) REFERENCES employee(em_id),
    FOREIGN KEY (es_sk_id) REFERENCES skill(sk_id)
);
INSERT INTO department(dp_name) VALUES
('Human Resources'),
('Finance'),
('Information Technology'),
('Sales');
INSERT INTO employee
(em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id)
VALUES
('John',50000,true,'1998-05-20',3),
('Sneha',65000,true,'1999-08-15',3),
('Rahul',45000,false,'2000-03-10',2),
('Priya',70000,true,'1997-11-25',1);
INSERT INTO skill(sk_name) VALUES
('Java'),
('Spring Boot'),
('SQL'),
('Python'),
('Angular');
INSERT INTO employee_skill VALUES
(1,1),
(1,2),
(1,3),
(2,1),
(2,4),
(3,3),
(4,5);
SELECT * FROM department;
SELECT * FROM employee;
SELECT * FROM skill;
SELECT * FROM employee_skill;
