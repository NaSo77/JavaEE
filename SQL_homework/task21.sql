SELECT 
    l.city, d.department_name
FROM
    locations AS l
        LEFT JOIN
    departments AS d ON (d.location_id = l.location_id)