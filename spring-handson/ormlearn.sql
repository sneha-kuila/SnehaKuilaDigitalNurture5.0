DROP TABLE IF EXISTS attempt_option;
DROP TABLE IF EXISTS attempt_question;
DROP TABLE IF EXISTS attempt;
DROP TABLE IF EXISTS options;
DROP TABLE IF EXISTS question;
DROP TABLE IF EXISTS user;

CREATE TABLE user (
    us_id INT AUTO_INCREMENT PRIMARY KEY,
    us_name VARCHAR(100),
    us_email VARCHAR(100)
);

CREATE TABLE question (
    qt_id INT AUTO_INCREMENT PRIMARY KEY,
    qt_text VARCHAR(255)
);

CREATE TABLE options (
    op_id INT AUTO_INCREMENT PRIMARY KEY,
    op_text VARCHAR(255),
    op_qt_id INT,
    FOREIGN KEY (op_qt_id) REFERENCES question(qt_id)
);

CREATE TABLE attempt (
    at_id INT AUTO_INCREMENT PRIMARY KEY,
    at_date DATETIME,
    at_us_id INT,
    FOREIGN KEY (at_us_id) REFERENCES user(us_id)
);

CREATE TABLE attempt_question (
    aq_id INT AUTO_INCREMENT PRIMARY KEY,
    aq_at_id INT,
    aq_qt_id INT,
    FOREIGN KEY (aq_at_id) REFERENCES attempt(at_id),
    FOREIGN KEY (aq_qt_id) REFERENCES question(qt_id)
);

CREATE TABLE attempt_option (
    ao_id INT AUTO_INCREMENT PRIMARY KEY,
    ao_aq_id INT,
    ao_op_id INT,
    FOREIGN KEY (ao_aq_id) REFERENCES attempt_question(aq_id),
    FOREIGN KEY (ao_op_id) REFERENCES options(op_id)
);

-- Users
INSERT INTO user(us_name, us_email)
VALUES
('John', 'john@gmail.com'),
('Mary', 'mary@gmail.com');

-- Questions
INSERT INTO question(qt_text)
VALUES
('Which language is used for Spring Boot?'),
('Which database is used in this handson?');

-- Options
INSERT INTO options(op_text, op_qt_id)
VALUES
('Java',1),
('Python',1),
('C++',1),
('JavaScript',1),
('MySQL',2),
('Oracle',2),
('MongoDB',2),
('PostgreSQL',2);

-- Attempts
INSERT INTO attempt(at_date, at_us_id)
VALUES
(NOW(),1),
(NOW(),2);

-- Attempt Questions
INSERT INTO attempt_question(aq_at_id, aq_qt_id)
VALUES
(1,1),
(1,2),
(2,1);

-- Attempt Options
INSERT INTO attempt_option(ao_aq_id, ao_op_id)
VALUES
(1,1),
(2,5),
(3,2);