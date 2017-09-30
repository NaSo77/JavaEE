SELECT 
    COUNT(e.employee_id) AS employee_count, d.department_name, j.job_title
FROM
    employees AS e
        JOIN
    departments AS d ON (e.department_id = d.department_id)
        JOIN
    locations AS l ON (d.location_id = l.location_id)
    join jobs as j on (e.job_id = j.job_id)
GROUP BY d.department_id, e.job_id