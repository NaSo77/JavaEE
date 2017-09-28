SELECT 
    first_name,
    last_name,
    salary AS year_salary,
    salary / 12 AS monthly_salary,
    salary / 12 / 20 AS dayly_salary,
    salary / 12 / 20 / 8 AS hourly_salary
FROM
    hr.employees