SELECT 
    CONCAT(e.first_name, ' ', e.last_name) AS employee_name,
    salary
FROM
    employees e
        JOIN
    jobs AS j ON (e.job_id = j.job_id)
WHERE
    e.salary = (SELECT 
            MIN(j.min_salary)
        FROM
            jobs);