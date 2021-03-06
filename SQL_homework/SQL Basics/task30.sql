SELECT 
    j.job_title AS title,
    CONCAT(e.first_name, ' ', e.last_name) AS employee_name,
    d.department_name,
    l.city,
    c.country_name,
    r.region_name,
    CONCAT(m.first_name, ' ', m.last_name) AS manager_name
FROM
    employees AS e
        LEFT JOIN
    jobs AS j ON (e.job_id = j.job_id)
        LEFT JOIN
    departments AS d ON (d.department_id = e.department_id)
        LEFT JOIN
    locations AS l ON (d.location_id = l.location_id)
        LEFT JOIN
    countries AS c ON (c.country_id = l.country_id)
        LEFT JOIN
    regions AS r ON (c.region_id = r.region_id)
        LEFT JOIN
    employees m ON (e.manager_id = m.employee_id)