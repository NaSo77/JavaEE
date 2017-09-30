SELECT 
    d.department_name,
    AVG(e.salary) AS avarage_salary
FROM
    hr.departments AS d
        JOIN
    employees AS e ON (d.department_id = e.department_id)
GROUP BY d.department_name