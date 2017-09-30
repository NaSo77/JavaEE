SELECT 
    COUNT(d.department_id) AS employees_in_department, d.department_name
FROM
    employees AS e
        JOIN
    departments AS d ON (e.department_id = d.department_id)
WHERE
    d.department_id IS NOT NULL
GROUP BY d.department_id;
    
    
SELECT 
    CONCAT(e.first_name, ' ', e.last_name) AS employee_name,
    l.city,
    COUNT(*) AS employees_in_department,
    d.department_name
FROM
    employees AS e
        JOIN
    departments AS d ON (e.department_id = d.department_id)
        JOIN
    locations AS l ON (d.location_id = l.location_id)
WHERE
    d.department_id IS NOT NULL
GROUP BY d.department_id , employee_id;