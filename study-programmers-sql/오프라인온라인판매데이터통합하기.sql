-- 코드를 입력하세요
WITH
    ONLINE_SALE2 AS (
        SELECT
            ONLINE_SALE_ID AS SALE_ID,
            USER_ID,
            PRODUCT_ID,
            SALES_AMOUNT,
            SALES_DATE
        FROM ONLINE_SALE
    ),
    OFFLINE_SALE2 AS (
        SELECT
            OFFLINE_SALE_ID AS SALE_ID,
            NULL AS USER_ID,
            PRODUCT_ID,
            SALES_AMOUNT,
            SALES_DATE
        FROM OFFLINE_SALE
    )

SELECT
    DATE_FORMAT(SALES_DATE, '%Y-%m-%d') AS SALES_DATE,
    PRODUCT_ID,
    USER_ID,
    SALES_AMOUNT
FROM (
        SELECT *
        FROM ONLINE_SALE2
        UNION ALL
        SELECT *
        FROM OFFLINE_SALE2
    ) AS S
WHERE
    '2022-03-01' <= SALES_DATE
    AND SALES_DATE < '2022-04-01'
ORDER BY
    SALES_DATE ASC,
    PRODUCT_ID ASC,
    USER_ID ASC;