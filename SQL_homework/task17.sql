SELECT 
    department_name, city
FROM
    departments
        JOIN
    locations USING (location_id)