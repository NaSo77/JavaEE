SELECT 
    d.department_name, MAX(e.salary) AS max_salary
FROM
    employees AS e
        JOIN
    departments AS d USING (department_id)
GROUP BY d.department_name