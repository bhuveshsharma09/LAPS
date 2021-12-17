

USE laps;

INSERT INTO laps.department (department_id,department_name,manager_id)
Values
    ROW (3001,"Testing",1001),
    ROW (3002,"Business",1002);
-----

INSERT INTO laps.employee (employee_id ,annual_leave_count ,compensation_leave_count
                          ,job_title ,manager_id,medical_leave_count ,name)
VALUES ROW
    (1001,15,15,"Programmer",NULL,19,"Melinda"),
    ROW
        (1002,15,15,"Tester",NULL,19,"Steven"),
    ROW
        (1003,15,15,"Tester",1001,19,"bhuvesh"),
    ROW
        (1004,15,15,"Tester",1002,19,"Bowen"),
    ROW
        (1005,15,15,"Tester",1002,19,"ZX"),
    ROW
        (1006,25,25,"Tester",1001,25,"Htet");


INSERT INTO laps.employee (employee_id ,annual_leave_count ,compensation_leave_count
                          ,job_title ,manager_id,medical_leave_count ,name)
VALUES ROW
    (1007,25,25,"this is admin",NULL,25,"ADMIN");
------

INSERT INTO laps.user_credential (user_id,employee_id,password,username,employee_employee_id)
VALUES ROW
    (1,1001,"12345","melinda",1001),
    ROW
        (2,1002,"12345","steven",1002),
    ROW
        (3,1003,"12345","bhuvesh",1003),
    ROW
        (4,1004,"12345","Bowne",1004),
    ROW
        (5,1005,"12345","ZX",1005),
    ROW
        (6,1006,"12345","Htet",1006);


INSERT INTO laps.user_credential (user_id,employee_id,password,username,employee_employee_id)
VALUES ROW
    (7,1007,"12345","ADMIN",1007);

-----
INSERT INTO laps.role (role_id ,role_desc,role_title)
VALUES ROW
    (1,"ADMIN","god"),
    ROW
        (2,"MANAGER","biss"),
    ROW
        (3,"STAFF","staff");
-----

INSERT INTO laps.user_credential_roles (user_credentials_user_id,roles_role_id)
VALUES ROW
    (1,2),
    ROW
        (2,2),
    ROW
        (3,3),
    ROW
        (4,3),
    ROW
        (5,3),
    ROW
        (6,3),
    ROW
        (7,1);

-------------

INSERT INTO laps.leave_type (leave_name,granularity ,leave_code)
VALUES ROW
    ("AL",1,"Anual leave"),
    ROW
        ("CL",0,"compensation leave"),
    ROW
        ("ML",1,"Medical leave");
-----
INSERT INTO laps.leave_application (leave_id,approval_status,contact_details,covering_emp ,from_date,remarks,to_date ,employee_id ,leave_name)
VALUES ROW
    (1,1,"223345","Nobody",STR_TO_DATE('12/01/2012', '%d/%m/%Y'),"i am sick",STR_TO_DATE('14/01/2012', '%d/%m/%Y'),1001,"AL"),
    ROW
        (2,1,"223345","Nobody",STR_TO_DATE('12/01/2012', '%d/%m/%Y'),"i am sick",STR_TO_DATE('14/01/2012', '%d/%m/%Y'),1001,"AL"),
    ROW
        (3,1,"223345","Nobody",STR_TO_DATE('12/01/2012', '%d/%m/%Y'),"i am sick",STR_TO_DATE('14/01/2012', '%d/%m/%Y'),1001,"AL"),
    ROW
        (4,1,"223345","Nobody",STR_TO_DATE('12/01/2012', '%d/%m/%Y'),"i am sick",STR_TO_DATE('14/01/2012', '%d/%m/%Y'),1002,"ML");


---- SET SQL_SAFE_UPDATES = 0;
--- DELETE FROM department WHERE salary BETWEEN 13000 AND 15000;
--- SQL_SAFE_UPDATES = 1;
--- DELETE FROM user_credential_roles;


