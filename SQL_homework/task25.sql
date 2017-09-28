-- variant 1
SELECT 
    e.first_name, e.last_name, d.department_name 
FROM
    employees AS e
        INNER JOIN
    departments d ON (e.department_id = d.department_id
        AND hire_date > CAST('1995-1-1' AS DATE)
        AND hire_date < CAST('1999-12-31' AS DATE)
        AND d.department_name IN ('Sales' , 'Finance'));
        
-- variant 2
SELECT 
    e.first_name, e.last_name, d.department_name
FROM
    employees AS e
        INNER JOIN
    departments d ON (e.department_id = d.department_id)
WHERE
    hire_date > CAST('1995-1-1' AS DATE)
        AND hire_date < CAST('1999-12-31' AS DATE)
        AND d.department_name IN ('Sales' , 'Finance');
        
-- variant 3
SELECT 
    e.first_name, e.last_name, d.department_name
FROM
    employees AS e
        INNER JOIN
    departments d ON (e.department_id = d.department_id)
WHERE
    hire_date BETWEEN CAST('1995-1-1' AS DATE) AND CAST('1999-12-31' AS DATE)
        AND d.department_name IN ('Sales' , 'Finance');
        
 -- variant 4
SELECT 
    e.first_name, e.last_name, d.department_name
FROM
    employees AS e
        INNER JOIN
    departments d ON (e.department_id = d.department_id)
WHERE
    hire_date BETWEEN '1995-1-1' AND '1999-12-31'
        AND d.department_name IN ('Sales' , 'Finance');