SELECT 
    CONCAT(e.first_name, ' ', e.last_name) AS employee_name,
    e.salary
FROM
    employees AS e
        JOIN
    jobs AS j ON (e.job_id = j.job_id)
WHERE
    e.salary < (select min(salary) FROM employees)*1.1
