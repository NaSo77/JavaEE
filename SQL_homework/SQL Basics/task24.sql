SELECT 
    d.department_name AS department_name,
    CONCAT(e.first_name, ' ', e.last_name) AS manager_name,
    l.city AS city
FROM
    employees AS e
        JOIN
    departments AS d ON (d.manager_id = e.employee_id)
        JOIN
    locations AS l ON (l.location_id = d.location_id);