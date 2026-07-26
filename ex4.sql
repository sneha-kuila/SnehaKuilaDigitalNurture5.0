CREATE OR REPLACE FUNCTION CalculateAge(
    p_dob DATE
)
RETURN NUMBER
IS
    v_age NUMBER;
BEGIN
    v_age := FLOOR(MONTHS_BETWEEN(SYSDATE, p_dob)/12);
    RETURN v_age;
END;
/

SELECT CalculateAge(
TO_DATE('1985-05-15','YYYY-MM-DD'))
AS Age
FROM dual;

CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment(
    p_loanAmount NUMBER,
    p_interestRate NUMBER,
    p_years NUMBER
)
RETURN NUMBER
IS
    v_monthly NUMBER;
BEGIN
    v_monthly :=
        (p_loanAmount +
        (p_loanAmount * p_interestRate * p_years / 100))
        /(p_years * 12);

    RETURN ROUND(v_monthly,2);
END;
/

SELECT CalculateMonthlyInstallment(
5000,
5,
5)
AS EMI
FROM dual;

CREATE OR REPLACE FUNCTION HasSufficientBalance(
    p_accountID NUMBER,
    p_amount NUMBER
)
RETURN VARCHAR2
IS
    v_balance NUMBER;
BEGIN

    SELECT Balance
    INTO v_balance
    FROM Accounts
    WHERE AccountID = p_accountID;

    IF v_balance >= p_amount THEN
        RETURN 'TRUE';
    ELSE
        RETURN 'FALSE';
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN 'ACCOUNT NOT FOUND';
END;
/

SELECT HasSufficientBalance(
1,
500)
AS Status
FROM dual;