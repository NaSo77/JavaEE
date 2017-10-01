SELECT 
    CONCAT(e.first_name, ' ', e.last_name) AS employee_name,
    IF(e.manager_id IS NULL,
        'Just and employee',
        CONCAT(m.first_name, ' ', m.last_name)) AS manager_name
FROM
    hr.employees AS e
        LEFT JOIN
    employees AS m ON (e.manager_id = m.employee_id) 
UNION ALL SELECT 
    CONCAT(e.first_name, ' ', e.last_name) AS employee_name,
    CONCAT(m.first_name, ' ', m.last_name) AS manager_name
FROM
    hr.employees AS e
        RIGHT JOIN
    employees AS m ON (e.manager_id = m.employee_id);