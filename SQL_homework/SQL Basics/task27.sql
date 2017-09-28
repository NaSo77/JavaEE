SELECT 
    CONCAT(e.first_name, ' ', e.last_name) AS employee_name,
    ' is managed by ',
    CONCAT(m.first_name, ' ', m.last_name) AS manager_name
FROM
    employees e
        JOIN
    employees m ON (e.manager_id = m.employee_id)