SELECT 
    CONCAT(first_name, ' ', last_name) AS employees_salary_filtered
FROM
    hr.employees
WHERE
    salary BETWEEN 2000 AND 15000
        AND salary NOT BETWEEN 5000 AND 10000