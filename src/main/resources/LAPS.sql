DROP SCHEMA IF EXISTS laps;

CREATE SCHEMA laps;

USE laps;

INSERT INTO laps.department
Values
    ROW (3001,"Testing",1001);

INSERT INTO laps.department
Values
    ROW (3002,"Business",1002),
    ROW (3003,"Customer Support",1003),
    ROW (3004,"Adminsrative",1004),
    ROW (3005,"Finance",1005);
-----

INSERT INTO laps.employee
VALUES
    (1001,15,15,"Programmer",19,"bhuvesh",3001);

INSERT INTO laps.employee
VALUES ROW
    (1002,15,15,"Tester",19,"Steven",3002),
    ROW
        (1003,15,15,"Tester",19,"Melinda",3003),
    ROW
        (1004,15,15,"Tester",19,"Bowen",3002),
    ROW
        (1005,15,15,"Tester",19,"ZX",3002),
    ROW
        (1006,25,25,"Tester",25,"Htet",3002);

------

INSERT INTO laps.user_credential (user_id,employee_id,password,username,employee_employee_id)
VALUES ROW
    (1,1001,"12345","bhuvesh",1001),
    ROW
        (2,1002,"12345","melinda",1002),
    ROW
        (3,1003,"12345","ZX",1003),
    ROW
        (4,1004,"12345","steven",1004);


INSERT INTO laps.user_credential (user_id,employee_id,password,username,employee_employee_id)
VALUES ROW
    (5,1005,"12345","steven1",1004);

-----
INSERT INTO laps.role
VALUES ROW
    (1,"good role","Programmer"),
    ROW
        (2,"good role","Tester"),
    ROW
        (3,"good role","advisor");
--
INSERT INTO laps.user_credential_roles
VALUES ROW
    (1,3),
    ROW
        (2,2),
    ROW
        (3,1),
    ROW
        (4,3),
    ROW
        (5,3);
---

INSERT INTO laps.leave_type
VALUES ROW
    ("AL",1,"Anual leave"),
    ROW
        ("CL",0,"compensation leave"),
    ROW
        ("ML",1,"Medical leave");
-----
INSERT INTO laps.leave_application
VALUES ROW
    (1,1,"223345","Nobody",STR_TO_DATE('12/01/2012', '%d/%m/%Y'),"i am sick",STR_TO_DATE('14/01/2012', '%d/%m/%Y'),1001,"AL");

INSERT INTO laps.leave_application
VALUES ROW
    (2,1,"223345","Nobody",STR_TO_DATE('12/01/2012', '%d/%m/%Y'),"i am sick",STR_TO_DATE('14/01/2012', '%d/%m/%Y'),1001,"AL");

INSERT INTO laps.leave_application
VALUES ROW
    (3,1,"223345","Nobody",STR_TO_DATE('12/01/2012', '%d/%m/%Y'),"i am sick",STR_TO_DATE('14/01/2012', '%d/%m/%Y'),1001,"AL");

INSERT INTO laps.leave_application
VALUES ROW
    (4,1,"223345","Nobody",STR_TO_DATE('12/01/2012', '%d/%m/%Y'),"i am sick",STR_TO_DATE('14/01/2012', '%d/%m/%Y'),1002,"ML");


--- SET SQL_SAFE_UPDATES = 0;
--- DELETE FROM instructor WHERE salary BETWEEN 13000 AND 15000;
--- SQL_SAFE_UPDATES = 1;
--- DELETE FROM user_credential_roles;


