SELECT 
    e.first_name, d.department_name
FROM
    employees AS e
        JOIN
    job_history AS jh ON (jh.employee_id = e.employee_id)
        JOIN
    departments AS d ON (jh.department_id = d.department_id)
WHERE
    d.department_name = 'Sales';