SET SERVEROUTPUT ON;

DECLARE
    CURSOR GenerateMonthlyStatements IS
        SELECT t.TransactionID,
               t.AccountID,
               t.TransactionDate,
               t.Amount,
               t.TransactionType
        FROM Transactions t
        WHERE TRUNC(t.TransactionDate,'MM') =
              TRUNC(SYSDATE,'MM');

BEGIN
    FOR rec IN GenerateMonthlyStatements LOOP

        DBMS_OUTPUT.PUT_LINE(
            'Transaction ID: ' || rec.TransactionID ||
            ' | Account: ' || rec.AccountID ||
            ' | Date: ' || TO_CHAR(rec.TransactionDate,'DD-MON-YYYY') ||
            ' | Amount: ' || rec.Amount ||
            ' | Type: ' || rec.TransactionType
        );

    END LOOP;
END;
/

SET SERVEROUTPUT ON;

DECLARE
    CURSOR ApplyAnnualFee IS
        SELECT AccountID
        FROM Accounts;

BEGIN

    FOR rec IN ApplyAnnualFee LOOP

        UPDATE Accounts
        SET Balance = Balance - 100
        WHERE AccountID = rec.AccountID;

        DBMS_OUTPUT.PUT_LINE(
            'Annual fee deducted from Account '
            || rec.AccountID
        );

    END LOOP;

    COMMIT;

END;
/

SET SERVEROUTPUT ON;

DECLARE
    CURSOR ApplyAnnualFee IS
        SELECT AccountID
        FROM Accounts;

BEGIN

    FOR rec IN ApplyAnnualFee LOOP

        UPDATE Accounts
        SET Balance = Balance - 100
        WHERE AccountID = rec.AccountID;

        DBMS_OUTPUT.PUT_LINE(
            'Annual fee deducted from Account '
            || rec.AccountID
        );

    END LOOP;

    COMMIT;

END;
/

SELECT * FROM Accounts;

SET SERVEROUTPUT ON;

DECLARE

    CURSOR UpdateLoanInterestRates IS
        SELECT LoanID,
               InterestRate
        FROM Loans;

BEGIN

    FOR rec IN UpdateLoanInterestRates LOOP

        UPDATE Loans
        SET InterestRate = InterestRate + 0.5
        WHERE LoanID = rec.LoanID;

        DBMS_OUTPUT.PUT_LINE(
            'Loan '
            || rec.LoanID
            || ' Updated'
        );

    END LOOP;

    COMMIT;

END;
/

SELECT * FROM Loans;