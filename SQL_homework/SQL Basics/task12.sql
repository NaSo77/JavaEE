SELECT 
    CONCAT(first_name, ' ', last_name) AS full_name
FROM
    hr.employees
WHERE
    salary IN (2500 , 4000, 5000)