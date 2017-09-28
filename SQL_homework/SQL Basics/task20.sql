SELECT 
    l.city, d.department_name
FROM
    departments AS d
        RIGHT JOIN
    locations AS l ON (d.location_id = l.location_id)