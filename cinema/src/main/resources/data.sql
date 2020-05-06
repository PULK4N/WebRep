INSERT INTO MOVIE (movie_name) VALUES ('FTN');
INSERT INTO CINEMA (cinema_name) VALUES ('bioskop');
INSERT INTO AUDITORIUM(cinema_id,capacity) VALUES (1,100);
INSERT INTO PROJECTION (movie_id,auditorium_id,price) VALUES (1,1,100);
INSERT INTO SCORE (value) VALUES (10);
INSERT INTO VIEWER(first_name,date_of_birth) VALUES ('pulkan','12-01-20');
INSERT INTO RESERVING (viewer_id,projection_id) VALUES (1,1);
INSERT INTO ADMINISTRATOR(username) VALUES ('Pupi');