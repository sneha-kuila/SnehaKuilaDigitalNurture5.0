------------------------------------------------------------
-- EXERCISE 7 - SCENARIO 1
-- CUSTOMERMANAGEMENT PACKAGE
------------------------------------------------------------

CREATE OR REPLACE PACKAGE CustomerManagement AS

    PROCEDURE AddCustomer(
        p_id NUMBER,
        p_name VARCHAR2,
        p_dob DATE,
        p_balance NUMBER
    );

    PROCEDURE UpdateCustomer(
        p_id NUMBER,
        p_name VARCHAR2
    );

    FUNCTION GetCustomerBalance(
        p_id NUMBER
    ) RETURN NUMBER;

END CustomerManagement;
/

CREATE OR REPLACE PACKAGE BODY CustomerManagement AS

    PROCEDURE AddCustomer(
        p_id NUMBER,
        p_name VARCHAR2,
        p_dob DATE,
        p_balance NUMBER
    )
    IS
    BEGIN

        INSERT INTO Customers
        (
            CustomerID,
            Name,
            DOB,
            Balance,
            LastModified,
            IsVIP
        )
        VALUES
        (
            p_id,
            p_name,
            p_dob,
            p_balance,
            SYSDATE,
            NULL
        );

    END AddCustomer;

    PROCEDURE UpdateCustomer(
        p_id NUMBER,
        p_name VARCHAR2
    )
    IS
    BEGIN

        UPDATE Customers
        SET Name = p_name
        WHERE CustomerID = p_id;

    END UpdateCustomer;

    FUNCTION GetCustomerBalance(
        p_id NUMBER
    )
    RETURN NUMBER
    IS
        v_balance NUMBER;
    BEGIN

        SELECT Balance
        INTO v_balance
        FROM Customers
        WHERE CustomerID = p_id;

        RETURN v_balance;

    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN 0;
    END GetCustomerBalance;

END CustomerManagement;
/

BEGIN
    CustomerManagement.AddCustomer(
        5,
        'Rahul',
        TO_DATE('1999-05-12','YYYY-MM-DD'),
        7000
    );
END;
/

SELECT CustomerManagement.GetCustomerBalance(5)
FROM dual;

------------------------------------------------------------
-- EXERCISE 7 - SCENARIO 2
-- EMPLOYEEMANAGEMENT PACKAGE
------------------------------------------------------------

CREATE OR REPLACE PACKAGE EmployeeManagement AS

    PROCEDURE HireEmployee(
        p_id NUMBER,
        p_name VARCHAR2,
        p_position VARCHAR2,
        p_salary NUMBER,
        p_department VARCHAR2,
        p_hiredate DATE
    );

    PROCEDURE UpdateEmployee(
        p_id NUMBER,
        p_salary NUMBER
    );

    FUNCTION AnnualSalary(
        p_id NUMBER
    ) RETURN NUMBER;

END EmployeeManagement;
/

CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS

    PROCEDURE HireEmployee(
        p_id NUMBER,
        p_name VARCHAR2,
        p_position VARCHAR2,
        p_salary NUMBER,
        p_department VARCHAR2,
        p_hiredate DATE
    )
    IS
    BEGIN

        INSERT INTO Employees
        (
            EmployeeID,
            Name,
            Position,
            Salary,
            Department,
            HireDate
        )
        VALUES
        (
            p_id,
            p_name,
            p_position,
            p_salary,
            p_department,
            p_hiredate
        );

    END HireEmployee;

    PROCEDURE UpdateEmployee(
        p_id NUMBER,
        p_salary NUMBER
    )
    IS
    BEGIN

        UPDATE Employees
        SET Salary = p_salary
        WHERE EmployeeID = p_id;

    END UpdateEmployee;

    FUNCTION AnnualSalary(
        p_id NUMBER
    )
    RETURN NUMBER
    IS
        v_salary NUMBER;
    BEGIN

        SELECT Salary
        INTO v_salary
        FROM Employees
        WHERE EmployeeID = p_id;

        RETURN v_salary * 12;

    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN 0;
    END AnnualSalary;

END EmployeeManagement;
/

SELECT EmployeeManagement.AnnualSalary(1)
FROM dual;

------------------------------------------------------------
-- EXERCISE 7 - SCENARIO 3
-- ACCOUNTOPERATIONS PACKAGE
------------------------------------------------------------

CREATE OR REPLACE PACKAGE AccountOperations AS

    PROCEDURE OpenAccount(
        p_accountid NUMBER,
        p_customerid NUMBER,
        p_type VARCHAR2,
        p_balance NUMBER
    );

    PROCEDURE CloseAccount(
        p_accountid NUMBER
    );

    FUNCTION TotalBalance(
        p_customerid NUMBER
    ) RETURN NUMBER;

END AccountOperations;
/

CREATE OR REPLACE PACKAGE BODY AccountOperations AS

    PROCEDURE OpenAccount(
        p_accountid NUMBER,
        p_customerid NUMBER,
        p_type VARCHAR2,
        p_balance NUMBER
    )
    IS
    BEGIN

        INSERT INTO Accounts
        (
            AccountID,
            CustomerID,
            AccountType,
            Balance,
            LastModified
        )
        VALUES
        (
            p_accountid,
            p_customerid,
            p_type,
            p_balance,
            SYSDATE
        );

    END OpenAccount;

    PROCEDURE CloseAccount(
        p_accountid NUMBER
    )
    IS
    BEGIN

        DELETE FROM Accounts
        WHERE AccountID = p_accountid;

    END CloseAccount;

    FUNCTION TotalBalance(
        p_customerid NUMBER
    )
    RETURN NUMBER
    IS
        v_total NUMBER;
    BEGIN

        SELECT NVL(SUM(Balance),0)
        INTO v_total
        FROM Accounts
        WHERE CustomerID = p_customerid;

        RETURN v_total;

    END TotalBalance;

END AccountOperations;
/

SELECT AccountOperations.TotalBalance(1)
FROM dual;