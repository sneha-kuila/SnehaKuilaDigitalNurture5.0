CREATE OR REPLACE PROCEDURE SafeTransferFunds(
    p_fromAccount NUMBER,
    p_toAccount NUMBER,
    p_amount NUMBER
)
IS
    v_balance NUMBER;
BEGIN
    SELECT Balance
    INTO v_balance
    FROM Accounts
    WHERE AccountID = p_fromAccount;

    IF v_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20001,'Insufficient Balance');
    END IF;

    UPDATE Accounts
    SET Balance = Balance - p_amount
    WHERE AccountID = p_fromAccount;

    UPDATE Accounts
    SET Balance = Balance + p_amount
    WHERE AccountID = p_toAccount;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Transfer Successful');

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE(SQLERRM);
END;
/

BEGIN
    SafeTransferFunds(1,2,200);
END;
/

SELECT * FROM Accounts;

CREATE OR REPLACE PROCEDURE UpdateSalary(
    p_empid NUMBER,
    p_percent NUMBER
)
IS
BEGIN

    UPDATE Employees
    SET Salary = Salary + (Salary*p_percent/100)
    WHERE EmployeeID = p_empid;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20002,'Employee Not Found');
    END IF;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Salary Updated');

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE(SQLERRM);
END;
/

BEGIN
    UpdateSalary(1,10);
END;
/

SELECT * FROM Employees;

CREATE OR REPLACE PROCEDURE AddNewCustomer(
    p_id NUMBER,
    p_name VARCHAR2,
    p_dob DATE,
    p_balance NUMBER
)
IS
BEGIN

    INSERT INTO Customers
    VALUES(
        p_id,
        p_name,
        p_dob,
        p_balance,
        SYSDATE,
        NULL
    );

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Customer Added');

EXCEPTION

    WHEN DUP_VAL_ON_INDEX THEN

        DBMS_OUTPUT.PUT_LINE('Customer ID Already Exists');

    WHEN OTHERS THEN

        DBMS_OUTPUT.PUT_LINE(SQLERRM);

END;
/

BEGIN
    AddNewCustomer(
        3,
        'Rahul',
        TO_DATE('1998-02-15','YYYY-MM-DD'),
        5000
    );
END;
/

SELECT * FROM Customers;