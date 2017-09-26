SELECT 
    *
FROM
    hr.locations
WHERE
    postal_code IS NOT NULL
        AND state_province IS NOT NULL