BEGIN
    INSERT INTO employee VALUES (1, 'Ana Silva', 'Técnica', DATE '2020-03-15');
    INSERT INTO employee VALUES (2, 'Bruno Santos', 'Engenheiro', DATE '2019-07-22');
    INSERT INTO employee VALUES (3, 'Carla Oliveira', 'Supervisora', DATE '2021-01-10');
    
    INSERT INTO maintenance_status VALUES (1, 'Pendente');
    INSERT INTO maintenance_status VALUES (2, 'Em andamento');
    INSERT INTO maintenance_status VALUES (3, 'Concluído');
    
    INSERT INTO tool VALUES (1, 'Jogo de Chaves', 'CH123', DATE '2018-06-01');
    INSERT INTO tool VALUES (2, 'Chave de Torque', 'CT456', DATE '2019-09-10');
    INSERT INTO tool VALUES (3, 'Scanner Diagnóstico', 'SD789', DATE '2020-11-20');
    
    INSERT INTO maintenance VALUES (1, 'Inspeção e diagnóstico do motor', DATE '2024-01-05', DATE '2024-01-07', 1, 3);
    INSERT INTO maintenance VALUES (2, 'Manutenção do sistema de freios', DATE '2024-02-10', DATE '2024-03-12', 2, 2);
    INSERT INTO maintenance VALUES (3, 'Troca de óleo e filtro', DATE '2024-03-12', DATE '2024-03-12', 3, 3);
    
    INSERT INTO maintenance_tool VALUES (1, 1, 1);
    INSERT INTO maintenance_tool VALUES (1, 3, 1);
    INSERT INTO maintenance_tool VALUES (2, 2, 1);
    INSERT INTO maintenance_tool VALUES (3, 1, 1);

    COMMIT;
END;
