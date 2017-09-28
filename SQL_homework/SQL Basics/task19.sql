SELECT 
    d.department_name AS 'Department name',
    l.city AS 'City',
    e.first_name AS 'Manager first name'
FROM
    departments AS d
        JOIN
    locations AS l ON (d.location_id = l.location_id)
        JOIN
    employees AS e ON (d.manager_id = e.manager_id)