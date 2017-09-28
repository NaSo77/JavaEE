SELECT 
    l.city, d.department_name
FROM
    locations AS l,
    departments AS d
WHERE
    l.location_id = d.location_id 
UNION SELECT 
    l.city, NULL
FROM
    locations l
WHERE
    l.location_id NOT IN (SELECT 
            d.location_id
        FROM
            departments d);