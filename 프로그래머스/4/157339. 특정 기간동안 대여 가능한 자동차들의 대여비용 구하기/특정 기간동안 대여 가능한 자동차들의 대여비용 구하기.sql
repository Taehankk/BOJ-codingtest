SELECT
    MAIN.CAR_ID,
    MAIN.CAR_TYPE,
    ROUND(MAIN.FEE)
FROM (
    SELECT
        C.CAR_ID,
        C.CAR_TYPE,
        /* 30일 요금 계산 */
        30 * C.DAILY_FEE * (100 - P.DISCOUNT_RATE) / 100 AS FEE
    FROM CAR_RENTAL_COMPANY_CAR C
    /* 할인 정책 JOIN */
    JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN P
      ON C.CAR_TYPE = P.CAR_TYPE
    WHERE
        C.CAR_TYPE IN ('세단', 'SUV')
        AND P.DURATION_TYPE = '30일 이상'
        /* 11/01~11/30 겹치는 대여가 있는 CAR_ID는 제외 */
        AND C.CAR_ID NOT IN (
            SELECT H.CAR_ID
            FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY H
            WHERE NOT (
               H.END_DATE < '2022-11-01'
               OR H.START_DATE > '2022-11-30'
            )
        )
) AS MAIN
WHERE
    MAIN.FEE >= 500000
    AND MAIN.FEE < 2000000
ORDER BY
    MAIN.FEE DESC,
    MAIN.CAR_TYPE ASC,
    MAIN.CAR_ID DESC;
