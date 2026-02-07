-- 코드를 작성해주세요
WITH
    GRADE AS (
        SELECT
            HE.EMP_NO,
            HE.EMP_NAME,
            HE.SAL,
            CASE
                WHEN AVG(HG.SCORE) >= 96 THEN 'S'
                WHEN AVG(HG.SCORE) >= 90 THEN 'A'
                WHEN AVG(HG.SCORE) >= 80 THEN 'B'
                ELSE 'C'
            END AS GRADE
        FROM
            HR_EMPLOYEES AS HE
            INNER JOIN HR_GRADE AS HG ON HE.EMP_NO = HG.EMP_NO
        GROUP BY
            HE.EMP_NO,
            HE.EMP_NAME,
            HE.SAL
    )

SELECT
    EMP_NO,
    EMP_NAME,
    GRADE,
    CASE GRADE
        WHEN 'S' THEN SAL * 0.20
        WHEN 'A' THEN SAL * 0.15
        WHEN 'B' THEN SAL * 0.10
        WHEN 'C' THEN SAL * 0.00
        ELSE -1
    END AS BONUS
FROM GRADE
ORDER BY EMP_NO ASC;