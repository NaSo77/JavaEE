SELECT 
    d.department_name AS department_name,
    CONCAT(e.first_name, ' ', e.last_name) AS manager_name
FROM
    departments AS d
        JOIN
    employees AS e ON (d.manager_id = e.employee_id);