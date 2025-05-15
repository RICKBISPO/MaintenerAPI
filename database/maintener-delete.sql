BEGIN
    DELETE FROM maintenance_tool;

    DELETE FROM maintenance;
    
    DELETE FROM tool;
    
    DELETE FROM maintenance_status;
    
    DELETE FROM employee;

    COMMIT;
END;