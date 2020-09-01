

INSERT INTO VIEWER(username,password,first_name,last_name,phone_number,email,date_of_birth,active) VALUES ('pulkan','puki','Nikola','Pupovac','062889972','pulkan@gmail.com','12-01-20','true');

INSERT INTO MOVIE (movie_name,genre,description,lenght,grade) VALUES('THE NEW MUTANTS','triller','no',123,0);
INSERT INTO MOVIE (movie_name,genre,description,lenght,grade) VALUES('GUNS AKIMBO','action','no',111,0);
INSERT INTO MOVIE (movie_name,genre,description,lenght,grade) VALUES('BLACK WIDOW','action','no',132,0);
INSERT INTO MOVIE (movie_name,genre,description,lenght,grade) VALUES('A QUIET PLACE 2','triller','no',121,0);
INSERT INTO MOVIE (movie_name,genre,description,lenght,grade) VALUES('SPENSER CONFIDENTAL','action','no',155,0);
INSERT INTO MOVIE (movie_name,genre,description,lenght,grade) VALUES('VIVARIUM','action','none',134,0);
INSERT INTO CINEMA (cinema_name) VALUES ('unAttributedCinemaDataSQL');
INSERT INTO AUDITORIUM(cinema_id,capacity) VALUES (1,100);
INSERT INTO AUDITORIUM(cinema_id,capacity) VALUES (1,2);
-- ,scheduled_time,"12/12/2020, 12:30"
-- ,scheduled_time,"13/12/2020, 14:30"
-- ,scheduled_time,"12/12/2020, 12:30"
-- ,scheduled_time,"11/12/2020, 15:30"
-- ,scheduled_time,"12/12/2020, 16:30"
-- ,scheduled_time,"11/12/2020, 12:30"
-- ,scheduled_time,"10/12/2020, 17:30"
-- ,scheduled_time,"11/12/2020, 12:30"

INSERT INTO PROJECTION (movie_id,auditorium_id,price) VALUES (1,1,130);
INSERT INTO PROJECTION (movie_id,auditorium_id,price) VALUES (2,1,122);
INSERT INTO PROJECTION (movie_id,auditorium_id,price) VALUES (1,2,110);
INSERT INTO PROJECTION (movie_id,auditorium_id,price) VALUES (3,2,120);
INSERT INTO PROJECTION (movie_id,auditorium_id,price) VALUES (3,1,130);
INSERT INTO PROJECTION (movie_id,auditorium_id,price) VALUES (4,2,120);
INSERT INTO PROJECTION (movie_id,auditorium_id,price) VALUES (5,1,110);
INSERT INTO PROJECTION (movie_id,auditorium_id,price) VALUES (6,1,100);


INSERT INTO ADMINISTRATOR(username, password,active) VALUES ('admin','admin','true');
INSERT INTO MANAGER(username, password,active) VALUES ('manager','manager','true');