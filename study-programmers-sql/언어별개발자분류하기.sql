-- 코드를 작성해주세요
WITH
    SKILLS AS (
        SELECT GROUP_CONCAT(
                S.NAME, '|', S.CATEGORY SEPARATOR '|'
            ) AS SKILLS, D.ID, D.EMAIL
        FROM DEVELOPERS AS D
            INNER JOIN SKILLCODES AS S ON (D.SKILL_CODE & S.CODE > 0)
        GROUP BY
            D.ID,
            D.EMAIL
    )

SELECT
    CASE
        WHEN SKILLS LIKE '%Front End%'
        AND SKILLS LIKE '%Python%' THEN 'A'
        WHEN SKILLS LIKE '%C#%' THEN 'B'
        WHEN SKILLS LIKE '%Front End%' THEN 'C'
        ELSE NULL
    END AS GRADE,
    ID,
    EMAIL
FROM SKILLS
GROUP BY
    GRADE,
    ID,
    EMAIL
HAVING
    GRADE IS NOT NULL
ORDER BY GRADE, ID ASC;

# SELECT
#     CASE
#         WHEN (
#             SKILL_CODE & (
#                 SELECT SUM(CODE)
#                 FROM SKILLCODES
#                 WHERE
#                     CATEGORY = 'Front End'
#             ) > 0
#         )
#         AND (
#             SKILL_CODE & (
#                 SELECT SUM(CODE)
#                 FROM SKILLCODES
#                 WHERE
#                     NAME = 'Python'
#             ) > 0
#         ) THEN 'A'
#         WHEN (
#             SKILL_CODE & (
#                 SELECT SUM(CODE)
#                 FROM SKILLCODES
#                 WHERE
#                     NAME = 'C#'
#             ) > 0
#         ) THEN 'B'
#         WHEN (
#             SKILL_CODE & (
#                 SELECT SUM(CODE)
#                 FROM SKILLCODES
#                 WHERE
#                     CATEGORY = 'Front End'
#             ) > 0
#         ) THEN 'C'
#         ELSE NULL
#     END AS GRADE,
#     ID,
#     EMAIL
# FROM DEVELOPERS
# GROUP BY
#     GRADE,
#     ID,
#     EMAIL
# HAVING
#     GRADE IS NOT NULL
# ORDER BY GRADE, ID;