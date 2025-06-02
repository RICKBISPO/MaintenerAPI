CREATE TABLE user_auth (
    user_id NUMBER PRIMARY KEY,
    login VARCHAR2(50) UNIQUE NOT NULL,
    password VARCHAR2(255) NOT NULL,
    role VARCHAR2(20) NOT NULL
);

CREATE TABLE employee (
    employee_id NUMBER PRIMARY KEY,
    name VARCHAR2(100) NOT NULL,
    position VARCHAR2(50) NOT NULL,
    hire_date DATE NOT NULL
);

CREATE TABLE maintenance_status (
    status_id NUMBER PRIMARY KEY,
    description VARCHAR2(50) NOT NULL
);

CREATE TABLE maintenance (
    maintenance_id NUMBER PRIMARY KEY,
    description VARCHAR2(200) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    employee_id NUMBER NOT NULL,
    status_id NUMBER NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id),
    FOREIGN KEY (status_id) REFERENCES maintenance_status(status_id)
);

CREATE TABLE tool (
    tool_id NUMBER PRIMARY KEY,
    name VARCHAR2(100) NOT NULL,
    serial_code VARCHAR2(50) NOT NULL,
    purchase_date DATE NOT NULL
);

CREATE TABLE maintenance_tool (
    maintenance_id NUMBER NOT NULL,
    tool_id NUMBER NOT NULL,
    quantity_used NUMBER NOT NULL,
    PRIMARY KEY (maintenance_id, tool_id),
    FOREIGN KEY (maintenance_id) REFERENCES maintenance(maintenance_id),
    FOREIGN KEY (tool_id) REFERENCES tool(tool_id)
);

CREATE SEQUENCE seq_user_id
    START WITH 20
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;


CREATE SEQUENCE seq_employee_id
    START WITH 20
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE SEQUENCE seq_maintenance_status_id
    START WITH 20
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE SEQUENCE seq_maintenance_id
    START WITH 20
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE SEQUENCE seq_tool_id
    START WITH 20
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;