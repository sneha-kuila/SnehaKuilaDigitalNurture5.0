SET SERVEROUTPUT ON;

DECLARE
    CURSOR c IS
        SELECT c.CustomerID,
               c.DOB,
               l.InterestRate
        FROM Customers c
        JOIN Loans l
        ON c.CustomerID = l.CustomerID;

    v_age NUMBER;
BEGIN
    FOR rec IN c LOOP

        v_age := FLOOR(MONTHS_BETWEEN(SYSDATE, rec.DOB)/12);

        IF v_age > 60 THEN

            UPDATE Loans
            SET InterestRate = InterestRate - 1
            WHERE CustomerID = rec.CustomerID;

            DBMS_OUTPUT.PUT_LINE('Discount applied to Customer ID '
                                 || rec.CustomerID);

        END IF;

    END LOOP;

    COMMIT;
END;
/

ALTER TABLE Customers
ADD IsVIP VARCHAR2(5);

DECLARE

    CURSOR c IS
    SELECT CustomerID,Balance
    FROM Customers;

BEGIN

    FOR rec IN c LOOP

        IF rec.Balance > 10000 THEN

            UPDATE Customers
            SET IsVIP='TRUE'
            WHERE CustomerID=rec.CustomerID;

        ELSE

            UPDATE Customers
            SET IsVIP='FALSE'
            WHERE CustomerID=rec.CustomerID;

        END IF;

    END LOOP;

    COMMIT;

END;
/

SET SERVEROUTPUT ON;

DECLARE

    CURSOR c IS

    SELECT CustomerID,
           LoanID,
           EndDate
    FROM Loans
    WHERE EndDate
    BETWEEN SYSDATE
    AND SYSDATE+30;

BEGIN

    FOR rec IN c LOOP

        DBMS_OUTPUT.PUT_LINE(
        'Reminder: Loan '
        ||rec.LoanID||
        ' of Customer '
        ||rec.CustomerID||
        ' is due on '
        ||TO_CHAR(rec.EndDate,'DD-MON-YYYY'));

    END LOOP;

END;
/

SELECT * FROM Loans;

SELECT CustomerID,Name,Balance,IsVIP
FROM Customers;