SELECT 
    CONCAT(e.first_name, ' ', e.last_name) AS employee_name
FROM
    employees AS e
WHERE
    SUBSTRING(e.first_name, 1, 1) = SUBSTRING(e.last_name, 1, 1);