-- 코드를 입력하세요
WITH
    NOT_AVAILABLE_CAR_ID AS (
        SELECT DISTINCT
            C.CAR_ID
        FROM
            CAR_RENTAL_COMPANY_CAR AS C
            INNER JOIN CAR_RENTAL_COMPANY_RENTAL_HISTORY AS RH ON C.CAR_ID = RH.CAR_ID
        WHERE (
                CAR_TYPE = '세단'
                OR CAR_TYPE = 'SUV'
            )
            AND (
                NOT(
                    START_DATE >= '2022-12-01'
                    OR END_DATE < '2022-11-01'
                )
            )
    ),
    DISCOUNT_RATE AS (
        SELECT CAR_TYPE, DISCOUNT_RATE
        FROM
            CAR_RENTAL_COMPANY_DISCOUNT_PLAN
        WHERE (
                CAR_TYPE = '세단'
                OR CAR_TYPE = 'SUV'
            )
            AND DURATION_TYPE = '30일 이상'
    ),
    FEE AS (
        SELECT C.CAR_ID, C.CAR_TYPE, ROUND(
                (100 - DR.DISCOUNT_RATE) * 0.01 * C.DAILY_FEE * 30, 0
            ) AS FEE
        FROM
            CAR_RENTAL_COMPANY_CAR AS C
            INNER JOIN DISCOUNT_RATE AS DR ON C.CAR_TYPE = DR.CAR_TYPE
        WHERE (
                C.CAR_TYPE = '세단'
                OR C.CAR_TYPE = 'SUV'
            )
            AND C.CAR_ID NOT IN(
                SELECT CAR_ID
                FROM NOT_AVAILABLE_CAR_ID
            )
        ORDER BY FEE DESC, C.CAR_TYPE ASC, C.CAR_ID DESC
    )

SELECT CAR_ID, CAR_TYPE, FEE
FROM FEE
WHERE
    500000 <= FEE
    AND FEE < 2000000;