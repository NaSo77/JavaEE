SELECT 
    d.department_name, COALESCE(e.first_name, '(No manager)')
FROM
    hr.departments AS d
        LEFT JOIN
    employees AS e ON (d.manager_id = e.employee_id)
GROUP BY d.department_name;

