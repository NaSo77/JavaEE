SELECT 
    fn.first_name AS first_name, ln.last_name AS last_name
FROM
    employees fn
        CROSS JOIN
    employees ln