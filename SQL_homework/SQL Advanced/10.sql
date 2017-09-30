SELECT 
    COUNT(e.employee_id) AS employee_count, l.city AS city
FROM
    employees AS e
        JOIN
    departments AS d ON (e.department_id = d.department_id)
        JOIN
    locations AS l ON (d.location_id = l.location_id)
GROUP BY l.location_id