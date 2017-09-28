SELECT 
    CONCAT(e.first_name, ' ', e.last_name) AS employee_name,
    jh.job_id AS position_in_past1,
    jh2.job_id AS position_in_past2,
    j.job_id AS position_now,
    j.job_title AS job_title_now
FROM
    employees AS e
        JOIN
    job_history AS jh ON (e.employee_id = jh.employee_id
        AND jh.job_id = 'AC_ACCOUNT')
        JOIN
    job_history AS jh2 ON (e.employee_id = jh2.employee_id
        AND jh2.job_id = 'AC_MGR')
        JOIN
    jobs AS j ON (e.job_id = j.job_id)