DROP DATABASE IF EXISTS ormlearn;
CREATE DATABASE ormlearn;
USE ormlearn;

-- -----------------------------------------------------
-- USER TABLE
-- -----------------------------------------------------
CREATE TABLE user (
    us_id INT PRIMARY KEY AUTO_INCREMENT,
    us_name VARCHAR(100) NOT NULL,
    us_email VARCHAR(100) NOT NULL
);

-- -----------------------------------------------------
-- QUESTION TABLE
-- -----------------------------------------------------
CREATE TABLE question (
    qt_id INT PRIMARY KEY AUTO_INCREMENT,
    qt_text VARCHAR(500) NOT NULL
);

-- -----------------------------------------------------
-- OPTIONS TABLE
-- -----------------------------------------------------
CREATE TABLE options (
    op_id INT PRIMARY KEY AUTO_INCREMENT,
    op_qt_id INT NOT NULL,
    op_text VARCHAR(200),
    op_score DOUBLE,
    FOREIGN KEY (op_qt_id) REFERENCES question(qt_id)
);

-- -----------------------------------------------------
-- ATTEMPT TABLE
-- -----------------------------------------------------
CREATE TABLE attempt (
    at_id INT PRIMARY KEY AUTO_INCREMENT,
    at_date DATE,
    at_us_id INT,
    at_score DOUBLE,
    FOREIGN KEY (at_us_id) REFERENCES user(us_id)
);

-- -----------------------------------------------------
-- ATTEMPT QUESTION TABLE
-- -----------------------------------------------------
CREATE TABLE attempt_question (
    aq_id INT PRIMARY KEY AUTO_INCREMENT,
    aq_at_id INT,
    aq_qt_id INT,
    FOREIGN KEY (aq_at_id) REFERENCES attempt(at_id),
    FOREIGN KEY (aq_qt_id) REFERENCES question(qt_id)
);

-- -----------------------------------------------------
-- ATTEMPT OPTION TABLE
-- -----------------------------------------------------
CREATE TABLE attempt_option (
    ao_id INT PRIMARY KEY AUTO_INCREMENT,
    ao_op_id INT,
    ao_aq_id INT,
    ao_selected BOOLEAN,
    FOREIGN KEY (ao_op_id) REFERENCES options(op_id),
    FOREIGN KEY (ao_aq_id) REFERENCES attempt_question(aq_id)
);

---------------------------------------------------------
-- SAMPLE USER
---------------------------------------------------------

INSERT INTO user(us_name, us_email)
VALUES
('John','john@gmail.com');

---------------------------------------------------------
-- QUESTIONS
---------------------------------------------------------

INSERT INTO question(qt_text) VALUES
('What is the extension of the hyper text markup language file?'),
('What is the maximum level of heading tag can be used in a HTML page?'),
('The HTML document itself begins with <html> and ends </html>. State True or False');

---------------------------------------------------------
-- OPTIONS
---------------------------------------------------------

INSERT INTO options(op_qt_id,op_text,op_score) VALUES
(1,'.xhtm',0),
(1,'.ht',0),
(1,'.html',1),
(1,'.htmx',0),

(2,'5',0),
(2,'3',0),
(2,'4',0),
(2,'6',1),

(3,'false',0),
(3,'true',1);

---------------------------------------------------------
-- ATTEMPT
---------------------------------------------------------

INSERT INTO attempt(at_date,at_us_id,at_score)
VALUES
(CURDATE(),1,3);

---------------------------------------------------------
-- ATTEMPT QUESTION
---------------------------------------------------------

INSERT INTO attempt_question(aq_at_id,aq_qt_id)
VALUES
(1,1),
(1,2),
(1,3);

---------------------------------------------------------
-- ATTEMPT OPTION
---------------------------------------------------------

INSERT INTO attempt_option(ao_op_id,ao_aq_id,ao_selected)
VALUES

-- Question 1
(1,1,false),
(2,1,false),
(3,1,true),
(4,1,false),

-- Question 2
(5,2,false),
(6,2,false),
(7,2,false),
(8,2,true),

-- Question 3
(9,3,false),
(10,3,true);

---------------------------------------------------------
-- VERIFY DATA
---------------------------------------------------------

SELECT * FROM user;
SELECT * FROM question;
SELECT * FROM options;
SELECT * FROM attempt;
SELECT * FROM attempt_question;
SELECT * FROM attempt_option;