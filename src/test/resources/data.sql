INSERT INTO PERSON(id,name,age,blood_type, year_of_birthday, month_of_birthday, day_of_birthday) VALUES(1,'martin',10,'A',1991,8,15);
INSERT INTO PERSON(id,name,age,blood_type, year_of_birthday, month_of_birthday, day_of_birthday) VALUES(2,'david',9,'B',1992,7,21);
INSERT INTO PERSON(id,name,age,blood_type, year_of_birthday, month_of_birthday, day_of_birthday) VALUES(3,'dennis',8,'O',1993,10,15);
INSERT INTO PERSON(id,name,age,blood_type, year_of_birthday, month_of_birthday, day_of_birthday) VALUES(4,'sophia',7,'AB',1994,8,31);
INSERT INTO PERSON(id,name,age,blood_type, year_of_birthday, month_of_birthday, day_of_birthday) VALUES(5,'benny',6,'A',1995,10,24);

INSERT INTO BLOCK(ID,NAME) VALUES(1,'dennis');
INSERT INTO BLOCK(ID,NAME) VALUES(2,'sophia');

UPDATE PERSON SET BLOCK_ID = 1 WHERE ID = 3;
UPDATE PERSON SET BLOCK_ID = 2 WHERE ID = 4;