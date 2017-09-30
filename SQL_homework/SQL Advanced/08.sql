SELECT 
    d.department_name AS department_name,
    concat(m.first_name,' ', m.last_name) AS manager_name,
    COUNT(e.employee_id) AS employees_in_department
FROM
    departments AS d
        JOIN
    employees AS e ON (d.department_id = e.department_id)
        JOIN
    employees AS m ON (e.employee_id = m.manager_id)
    GROUP BY d.department_name