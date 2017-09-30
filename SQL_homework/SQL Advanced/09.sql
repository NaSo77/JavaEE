SELECT 
    CONCAT(m.first_name, ' ', m.last_name) AS manager_name,
    COUNT(e.employee_id) AS employees_in_department
FROM
    employees AS e
        JOIN
    employees AS m ON (e.employee_id = m.manager_id)
GROUP BY e.employee_id
HAVING employees_in_department = 5
    
