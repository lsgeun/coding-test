-- 코드를 입력하세요
WITH
    NOT_DUPLICATED AS (
        SELECT DISTINCT
            NAME
        FROM ANIMAL_INS
        WHERE
            NAME IS NOT NULL
    )

SELECT COUNT(*) FROM NOT_DUPLICATED;