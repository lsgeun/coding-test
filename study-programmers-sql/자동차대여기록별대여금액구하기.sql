-- 코드를 입력하세요
WITH
    TRUCK AS (
        SELECT
            C.CAR_TYPE,
            C.DAILY_FEE,
            RH.HISTORY_ID,
            DATEDIFF(END_DATE, START_DATE) + 1 AS DAYS,
            CASE
                WHEN DATEDIFF(END_DATE, START_DATE) + 1 >= 90 THEN '90일 이상'
                WHEN DATEDIFF(END_DATE, START_DATE) + 1 >= 30 THEN '30일 이상'
                WHEN DATEDIFF(END_DATE, START_DATE) + 1 >= 7 THEN '7일 이상'
                ELSE NULL
            END AS DURATION_TYPE
        FROM
            CAR_RENTAL_COMPANY_CAR AS C
            INNER JOIN CAR_RENTAL_COMPANY_RENTAL_HISTORY AS RH ON C.CAR_ID = RH.CAR_ID
        WHERE
            C.CAR_TYPE = '트럭'
    ),
    FEE AS (
        SELECT T.HISTORY_ID, ROUND(
                (
                    100 - COALESCE(DP.DISCOUNT_RATE, 0)
                ) * 0.01 * T.DAILY_FEE * T.DAYS, 0
            ) AS FEE
        FROM
            TRUCK AS T
            LEFT OUTER JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN AS DP ON T.CAR_TYPE = DP.CAR_TYPE
            AND T.DURATION_TYPE = DP.DURATION_TYPE
    )

SELECT HISTORY_ID, FEE
FROM FEE
ORDER BY FEE DESC, HISTORY_ID DESC;