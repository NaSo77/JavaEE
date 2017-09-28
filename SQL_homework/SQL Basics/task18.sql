SELECT 
    d.department_name AS 'Department name', l.city AS 'City'
FROM
    departments AS d
        JOIN
    locations AS l ON (d.location_id = l.location_id)