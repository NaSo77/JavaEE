SELECT 
    department_name AS all_names
FROM
    hr.departments 
UNION ALL SELECT  -- merge all unique results
    region_name
FROM
    hr.regions 
UNION ALL SELECT 
    country_name
FROM
    hr.countries 
UNION ALL SELECT 
    city
FROM
    hr.locations
    
    
