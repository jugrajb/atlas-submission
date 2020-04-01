-- Initialization Script
-- V1

CREATE TABLE GameStudio (
    sid SERIAL PRIMARY KEY,
    name CHAR(40) NOT NULL,
    city CHAR(40) NOT NULL,
    state CHAR(40) NOT NULL
);

CREATE TABLE PublishingCompany (
    pcid SERIAL PRIMARY KEY,
    name CHAR(40) NOT NULL,
    city CHAR(40) NOT NULL,
    state CHAR(40) NOT NULL
);

CREATE TABLE GamePlatform (
    gpid SERIAL PRIMARY KEY,
    name CHAR(40) NOT NULL
);

CREATE TABLE VideoGamePerson (
    pid SERIAL PRIMARY KEY,
    firstname CHAR(40) NOT NULL,
    lastname CHAR(40) NOT NULL,
    birthdate DATE,
    sid INT,
    FOREIGN KEY (sid) REFERENCES GameStudio (sid)
);

CREATE TABLE Director (
    pid INT PRIMARY KEY,
    specialization CHAR(40) NOT NULL,
    FOREIGN KEY (pid) REFERENCES VideoGamePerson(pid)
);

CREATE TABLE VoiceActor (
    pid INT PRIMARY KEY,
    primaryLanguage CHAR(40) NOT NULL,
    FOREIGN KEY (pid) REFERENCES VideoGamePerson(pid)
);

CREATE TABLE VideoGame (
    gid SERIAL PRIMARY KEY,
    title CHAR(40) UNIQUE NOT NULL,
    releaseDate DATE NOT NULL,
    genre CHAR(40) NOT NULL,
    pcid INT,
    sid INT,
    pid INT,
    FOREIGN KEY (pcid) REFERENCES PublishingCompany (pcid),
    FOREIGN KEY (sid) REFERENCES GameStudio (sid),
    FOREIGN KEY (pid) REFERENCES Director (pid)
);

CREATE TABLE PlayableOn (
    gid INT,
    gpid INT,
    PRIMARY KEY (gid, gpid),
    FOREIGN KEY (gid) REFERENCES VideoGame (gid),
    FOREIGN KEY (gpid) REFERENCES GamePlatform (gpid)
);

CREATE TABLE WorkedOn (
    gid INT,
    pid INT,
    role CHAR(100),
    PRIMARY KEY (gid, pid),
    FOREIGN KEY (gid) REFERENCES VideoGame (gid),
    FOREIGN KEY (pid) REFERENCES VideoGamePerson (pid)
);

CREATE TABLE AwardOrganization (
    oid SERIAL PRIMARY KEY,
    name CHAR(40) NOT NULL,
    websiteUrl CHAR(200)
);

CREATE TABLE VideoGameAward (
    name CHAR(40),
    oid INT,
    PRIMARY KEY (name, oid),
    FOREIGN KEY (oid) REFERENCES AwardOrganization (oid)
        ON DELETE CASCADE
);

CREATE TABLE Awarded (
    name CHAR(40),
    oid INT,
    gid INT,
    year INT NOT NULL,
    PRIMARY KEY (name, oid, gid),
    FOREIGN KEY (name, oid) REFERENCES
        VideoGameAward (name, oid),
    FOREIGN KEY (gid) REFERENCES VideoGame (gid)
);

CREATE TABLE InGameCharacter (
    cid SERIAL PRIMARY KEY,
    name CHAR(40) NOT NULL
);

CREATE TABLE VoicePerformanceBy (
    cid INT,
    pid INT,
    PRIMARY KEY (cid, pid),
    FOREIGN KEY (cid) REFERENCES InGameCharacter (cid),
    FOREIGN KEY (pid) REFERENCES VoiceActor (pid)
);

CREATE TABLE AppearsIn (
    gid INT,
    cid INT,
    PRIMARY KEY (gid, cid),
    FOREIGN KEY (gid) REFERENCES VideoGame (gid),
    FOREIGN KEY (cid) REFERENCES InGameCharacter (cid)
);

CREATE TABLE Users (
    uid SERIAL PRIMARY KEY,
    email CHAR(40) UNIQUE NOT NULL,
    password CHAR(40) NOT NULL
);

CREATE TABLE GeneralUser (
    uid INT,
    username CHAR(40) UNIQUE NOT NULL,
    profileImage BYTEA,
    PRIMARY KEY (uid),
    FOREIGN KEY (uid) REFERENCES Users (uid)
);

CREATE TABLE Administrator (
    uid INT,
    firstname CHAR(40) UNIQUE NOT NULL,
    lastname CHAR(40) UNIQUE NOT NULL,
    PRIMARY KEY (uid),
    FOREIGN KEY (uid) REFERENCES Users (uid)
);

CREATE TABLE UserReview (
    rid SERIAL PRIMARY KEY,
    uid INT,
    gid INT,
    title CHAR(40) NOT NULL,
    review CHAR(500),
    score INT NOT NULL,
    date DATE NOT NULL,
    FOREIGN KEY (uid) REFERENCES Users (uid),
    FOREIGN KEY (gid) REFERENCES VideoGame (gid)
);

CREATE TABLE CriticReview (
    rid SERIAL PRIMARY KEY,
    gid INT,
    title CHAR(200) NOT NULL,
    review CHAR(500),
    score INT NOT NULL,
    author CHAR(40) NOT NULL,
    url CHAR(200) NOT NULL,
    date DATE NOT NULL,
    FOREIGN KEY (gid) REFERENCES VideoGame (gid)
);

CREATE TABLE Series (
    gid INT,
    prevGid INT,
    PRIMARY KEY (gid, prevGid),
    FOREIGN KEY (gid) REFERENCES VideoGame (gid),
    FOREIGN KEY (prevGid) REFERENCES VideoGame (gid)
);

INSERT INTO GameStudio (name, city, state) VALUES ('Naughty Dog', 'Santa Monica', 'California');
INSERT INTO GameStudio (name, city, state) VALUES ('Santa Monica Studios', 'Santa Monica', 'California');
INSERT INTO GameStudio (name, city, state) VALUES ('Infinity Ward', 'Woodland Hills', 'California');
INSERT INTO GameStudio (name, city, state) VALUES ('Bungie', 'Bellevue', 'Washington');
INSERT INTO GameStudio (name, city, state) VALUES ('Bioware', 'Edmonton', 'Alberta');


INSERT INTO PublishingCompany (name, city, state) VALUES ('Sony Interactive Entertainment', 'San Mateo', 'California');
INSERT INTO PublishingCompany (name, city, state) VALUES ('Activision', 'Santa Monica', 'California');
INSERT INTO PublishingCompany (name, city, state) VALUES ('Xbox Game Studios', 'Redmond', 'Washington');
INSERT INTO PublishingCompany (name, city, state) VALUES ('Electronic Arts', 'San Mateo', 'California');
INSERT INTO PublishingCompany (name, city, state) VALUES ('Apple', 'Cupertino', 'California');


INSERT INTO GamePlatform (name) VALUES ('Xbox');
INSERT INTO GamePlatform (name) VALUES ('Xbox 360');
INSERT INTO GamePlatform (name) VALUES ('Playstation');
INSERT INTO GamePlatform (name) VALUES ('Playstation 2');
INSERT INTO GamePlatform (name) VALUES ('Playstation 3');
INSERT INTO GamePlatform (name) VALUES ('Playstation 4');
INSERT INTO GamePlatform (name) VALUES ('Nintendo Switch');
INSERT INTO GamePlatform (name) VALUES ('Wii');
INSERT INTO GamePlatform (name) VALUES ('PC');


INSERT INTO VideoGamePerson (firstname, lastname, birthdate, sid) VALUES ('Cory', 'Barlog', '1975-09-02', 2);
INSERT INTO VideoGamePerson (firstname, lastname, birthdate, sid) VALUES ('Amy', 'Hennig', '1964-08-19', 1);
INSERT INTO VideoGamePerson (firstname, lastname, birthdate, sid) VALUES ('Ken', 'Turner', '1964-12-25', 3);
INSERT INTO VideoGamePerson (firstname, lastname, birthdate, sid) VALUES ('Jason', 'West', '1977-03-26', 3);
INSERT INTO VideoGamePerson (firstname, lastname, birthdate, sid) VALUES ('Jason', 'Jones', '1971-06-01', 4);
INSERT INTO VideoGamePerson (firstname, lastname, birthdate, sid) VALUES ('Casey', 'Hudson', '1972-10-23', 5);
INSERT INTO VideoGamePerson (firstname, lastname, birthdate, sid) VALUES ('Nolan', 'North', '1970-10-30', 1);
INSERT INTO VideoGamePerson (firstname, lastname, birthdate, sid) VALUES ('Troy', 'Baker', '1976-04-01', NULL);
INSERT INTO VideoGamePerson (firstname, lastname, birthdate, sid) VALUES ('Steve', 'Downes', '1950-06-28', 4);
INSERT INTO VideoGamePerson (firstname, lastname, birthdate, sid) VALUES ('Mark', 'Meer', '1976-03-29', 5);
INSERT INTO VideoGamePerson (firstname, lastname, birthdate, sid) VALUES ('Christopher', 'Judge', '1964-10-13', 2);
INSERT INTO VideoGamePerson (firstname, lastname, birthdate, sid) VALUES ('Jen', 'Taylor', '1973-02-17', 4);


INSERT INTO Director VALUES (1 , 'Hack and Slash');
INSERT INTO Director VALUES (2 , 'Third-Person Shooter');
INSERT INTO Director VALUES (3 , 'FPS');
INSERT INTO Director VALUES (4 , 'FPS');
INSERT INTO Director VALUES (5 , 'FPS');
INSERT INTO Director VALUES (6 , 'Role-Playing Games');


INSERT INTO VoiceActor VALUES (7, 'English');
INSERT INTO VoiceActor VALUES (8, 'English');
INSERT INTO VoiceActor VALUES (9, 'English');
INSERT INTO VoiceActor VALUES (10, 'English');
INSERT INTO VoiceActor VALUES (11, 'English');
INSERT INTO VoiceActor VALUES (12, 'English');


INSERT INTO VideoGame (title, releaseDate, genre, pcid, sid, pid) VALUES ( 'Uncharted', '2007-11-19', 'Third-Person Shooter', 1, 1, 2);
INSERT INTO VideoGame (title, releaseDate, genre, pcid, sid, pid) VALUES ( 'Uncharted 2: Among Thieves', '2009-10-13', 'Third-Person Shooter', 1, 1, 2);
INSERT INTO VideoGame (title, releaseDate, genre, pcid, sid, pid) VALUES ( 'God of War', '2018-04-20', 'Hack and Slash', 1, 2, 1);
INSERT INTO VideoGame (title, releaseDate, genre, pcid, sid, pid) VALUES ( 'Call of Duty', '2003-10-29', 'FPS', 2, 3, 3);
INSERT INTO VideoGame (title, releaseDate, genre, pcid, sid, pid) VALUES ( 'Call of Duty: Modern Warfare', '2007-11-05', 'FPS', 2, 3, 4);
INSERT INTO VideoGame (title, releaseDate, genre, pcid, sid, pid) VALUES ( 'Halo: Combat Evolved', '2001-11-15', 'FPS', 3, 4, 5);
INSERT INTO VideoGame (title, releaseDate, genre, pcid, sid, pid) VALUES ( 'Halo 2', '2004-11-09', 'FPS', 3, 4, 5);
INSERT INTO VideoGame (title, releaseDate, genre, pcid, sid, pid) VALUES ( 'Halo 3', '2007-09-25', 'FPS', 3, 4, 5);
INSERT INTO VideoGame (title, releaseDate, genre, pcid, sid, pid) VALUES ( 'Mass Effect', '2007-11-20', 'Third-Person Shooter', 4, 5, 6);
INSERT INTO VideoGame (title, releaseDate, genre, pcid, sid, pid) VALUES ( 'Mass Effect 2', '2010-01-26', 'Third-Person Shooter', 4, 5, 6);


INSERT INTO PlayableOn VALUES (1, 5);
INSERT INTO PlayableOn VALUES (2, 5);
INSERT INTO PlayableOn VALUES (2, 6);
INSERT INTO PlayableOn VALUES (3, 6);
INSERT INTO PlayableOn VALUES (6, 1);
INSERT INTO PlayableOn VALUES (7, 1);


INSERT INTO WorkedOn VALUES (3, 1, 'DIRECTOR');
INSERT INTO WorkedOn VALUES (1, 2, 'DIRECTOR');
INSERT INTO WorkedOn VALUES (2, 2, 'DIRECTOR');
INSERT INTO WorkedOn VALUES (3, 11);
INSERT INTO WorkedOn VALUES (6, 5, 'DIRECTOR');
INSERT INTO WorkedOn VALUES (7, 5, 'DIRECTOR');
INSERT INTO WorkedOn VALUES (8, 5, 'DIRECTOR');
INSERT INTO WorkedOn VALUES (1, 7);
INSERT INTO WorkedOn VALUES (2, 7);
INSERT INTO WorkedOn VALUES (9, 10);
INSERT INTO WorkedOn VALUES (10, 10);
INSERT INTO WorkedOn VALUES (6, 9);
INSERT INTO WorkedOn VALUES (7, 9);
INSERT INTO WorkedOn Values (4, 3, 'DIRECTOR');
INSERT INTO WorkedOn Values (5, 4);
INSERT INTO WorkedOn Values (9, 6, 'DIRECTOR');
INSERT INTO WorkedOn Values (10, 6, 'DIRECTOR');


INSERT INTO AwardOrganization (name, websiteUrl) VALUES ('Golden Joystick Awards', 'https://www.gamesradar.com/goldenjoystickawards/');
INSERT INTO AwardOrganization (name, websiteUrl) VALUES ('The Game Awards', 'https://thegameawards.com/');
INSERT INTO AwardOrganization (name, websiteUrl) VALUES ('IGN', 'https://ca.ign.com/');
INSERT INTO AwardOrganization (name, websiteUrl) VALUES ('D.I.C.E. Awards', 'https://www.interactive.org/awards/');
INSERT INTO AwardOrganization (name, websiteUrl) VALUES ('Game Informer', 'https://www.gameinformer.com/');


INSERT INTO VideoGameAward VALUES ('Best Storytelling', 1);
INSERT INTO VideoGameAward VALUES ('Best Visual Design', 1);
INSERT INTO VideoGameAward VALUES ('Game of the Year', 2);
INSERT INTO VideoGameAward VALUES ('Best Game Direction', 2);
INSERT INTO VideoGameAward VALUES ('Best Score/Music', 2);
INSERT INTO VideoGameAward VALUES ('Game of the Year', 3);
INSERT INTO VideoGameAward VALUES ('Game of the Year', 4);
INSERT INTO VideoGameAward VALUES ('Best Action Game', 5);


INSERT INTO Awarded VALUES ('Best Storytelling', 1, 3, 2018);
INSERT INTO Awarded VALUES ('Best Visual Design', 1, 3, 2018);
INSERT INTO Awarded VALUES ('Game of the Year', 3, 6, 2001);
INSERT INTO Awarded VALUES ('Game of the Year', 3, 2, 2009);
INSERT INTO Awarded VALUES ('Game of the Year', 3, 3, 2018);
INSERT INTO Awarded VALUES ('Best Score/Music', 2, 3, 2018);
INSERT INTO Awarded VALUES ('Best Action Game', 5, 3, 2018);
INSERT INTO Awarded VALUES ('Game of the Year', 4, 3, 2019);
INSERT INTO Awarded VALUES ('Game of the Year', 4, 4, 2003);


INSERT INTO InGameCharacter (name) VALUES ('Nathan Drake');
INSERT INTO InGameCharacter (name) VALUES ('Sam Drake');
INSERT INTO InGameCharacter (name) VALUES ('Kratos');
INSERT INTO InGameCharacter (name) VALUES ('Master Chief');
INSERT INTO InGameCharacter (name) VALUES ('Cortana');
INSERT INTO InGameCharacter (name) VALUES ('Commander Shepard');


INSERT INTO VoicePerformanceBy VALUES (1, 7);
INSERT INTO VoicePerformanceBy VALUES (2, 8);
INSERT INTO VoicePerformanceBy VALUES (3, 11);
INSERT INTO VoicePerformanceBy VALUES (4, 9);
INSERT INTO VoicePerformanceBy VALUES (5, 12);
INSERT INTO VoicePerformanceBy VALUES (6, 10);


INSERT INTO AppearsIn VALUES (1, 1);
INSERT INTO AppearsIn VALUES (2, 1);
INSERT INTO AppearsIn VALUES (3, 3);
INSERT INTO AppearsIn VALUES (6, 4);
INSERT INTO AppearsIn VALUES (7, 4);
INSERT INTO AppearsIn VALUES (8, 4);
INSERT INTO AppearsIn VALUES (6, 5);
INSERT INTO AppearsIn VALUES (7, 5);
INSERT INTO AppearsIn VALUES (8, 5);
INSERT INTO AppearsIn VALUES (9, 6);
INSERT INTO AppearsIn VALUES (10, 6);


INSERT INTO Users (email, password) VALUES ('jugraj.bath@gmail.com', '123456');
INSERT INTO Users (email, password) VALUES ('irvino.djuana@gmail.com', '123456');
INSERT INTO Users (email, password) VALUES ('admin3@gmail.com', '123456');
INSERT INTO Users (email, password) VALUES ('admin4@gmail.com', '123456');
INSERT INTO Users (email, password) VALUES ('admin5@gmail.com', '123456');
INSERT INTO Users (email, password) VALUES ('fred@gmail.com', 'MargaretThatcheris110%');
INSERT INTO Users (email, password) VALUES ('george@gmail.com', 'correcthorsebatterystaple');
INSERT INTO Users (email, password) VALUES ('ron@gmail.com', 'hunter2');
INSERT INTO Users (email, password) VALUES ('ginny@gmail.com', 'qwerty');
INSERT INTO Users (email, password) VALUES ('bill@gmail.com', 'password');


INSERT INTO GeneralUser VALUES (6, 'fred', NULL);
INSERT INTO GeneralUser VALUES (7, 'george', NULL);
INSERT INTO GeneralUser VALUES (8, 'ron', NULL);
INSERT INTO GeneralUser VALUES (9, 'ginny', NULL);
INSERT INTO GeneralUser VALUES (10, 'bill', NULL);


INSERT INTO Administrator VALUES (1, 'Jugraj', 'Bath');
INSERT INTO Administrator VALUES (2, 'Irvino', 'Djuana');
INSERT INTO Administrator VALUES (3, 'Admin3', 'Three');
INSERT INTO Administrator VALUES (4, 'Admin4', 'Four');
INSERT INTO Administrator VALUES (5, 'Admin5', 'Five');


INSERT INTO UserReview (uid, gid, title, review, score, date) VALUES (6, 3, 'Overrated', 'God of war is overrated. It doesn’t even have any guns.', 0, '2019-01-29');
INSERT INTO UserReview (uid, gid, title, review, score, date) VALUES (6, 2, 'Amazing!', 'This game is so good I bought it 5 times.', 10, '2019-01-29');
INSERT INTO UserReview (uid, gid, title, review, score, date) VALUES (7, 5, 'Sad :(', 'I’m 11 and I played this for a few days before my mom took it away from me', 7, '2019-02-06');
INSERT INTO UserReview (uid, gid, title, review, score, date) VALUES (8, 10, 'Mass Effect sucks', 'Never played it but my friend Ken won’t let me borrow it so I think it sucks now', 1, '2019-02-07');
INSERT INTO UserReview (uid, gid, title, review, score, date) VALUES (6, 5, 'VIDEO GAMES CAUSE VIOLENCE', NULL, 5, '2019-02-08');


INSERT INTO CriticReview (gid, title, review, score, author, url, date) VALUES (4, 'Call of Duty 4: Modern Warfare Review', 'The single-player campaign is over in a flash, but the high quality of that campaign and its terrific multiplayer options make Call of Duty 4 a fantastic package.', 9, 'Jeff Gerstmann', 'https://www.gamespot.com/reviews/call-of-duty-4-modern-warfare-review/1900-6182426/', '2015-05-12');
INSERT INTO CriticReview (gid, title, review, score, author, url, date) VALUES (3, 'God of War Review', 'Kratos makes an epic comeback as The Nine Realms greatest dad.', 10, 'Jonathon Dornbush', 'https://ca.ign.com/articles/2018/04/12/god-of-war-review', '2018-04-12');
INSERT INTO CriticReview (gid, title, review, score, author, url, date) VALUES (8, 'Halo 3 Review', 'Halo 3 builds upon the concepts of Halo 2 in ways that you would expect, but there are also new modes and options that send the series in exciting new directions.', 9, 'Jeff Gerstmann', 'https://www.gamespot.com/reviews/halo-3-review/1900-6179646/', '2007-09-23');
INSERT INTO CriticReview (gid, title, review, score, author, url, date) VALUES (3, 'God of War Review', 'One of PlayStation’s finest moments, Kratos has been reimagined for a new audience while keeping the best bits of what originally made him great.', 10, 'Leon Hurley', 'https://www.gamesradar.com/god-of-war-review', '2018-04-12');
INSERT INTO CriticReview (gid, title, review, score, author, url, date) VALUES (2, 'Uncharted 2: Among Thieves Review', 'With an exhilarating campaign, intense cooperative mode, and addictive multiplayer competition, Uncharted 2 is a complete game that is completely awesome.', 9, 'Tom McShea', 'https://www.gamespot.com/reviews/uncharted-2-among-thieves-review/1900-6231311/', '2009-10-07');


INSERT INTO Series VALUES (2, 1);
INSERT INTO Series VALUES (5, 4);
INSERT INTO Series VALUES (7, 6);
INSERT INTO Series VALUES (8, 7);
INSERT INTO Series VALUES (10, 9);

-- V2 - update user review 5

UPDATE userReview SET review = '' WHERE rid = 5

-- V3 - alter awarded insert userreview

ALTER TABLE Awarded
DROP CONSTRAINT awarded_name_oid_fkey,
ADD CONSTRAINT awarded_name_oid_fkey
  FOREIGN KEY (name, oid)
  REFERENCES VideoGameAward (name, oid)
  ON DELETE CASCADE;

INSERT INTO UserReview (uid, gid, title, review, score, date) VALUES (6, 10, 'Not impressed', 'Hopefully the next game will be better, not that great', 4, '2019-01-21');

-- V4 - triggers

-- Function and trigger for auto-adding workedon relationship when inserting new videogame
CREATE OR REPLACE FUNCTION add_director_from_vg()
    RETURNS trigger AS
$BODY$
BEGIN
    IF NEW.pid IS NOT NULL THEN
        INSERT INTO WorkedOn (gid, pid, role) VALUES (NEW.gid, NEW.pid, 'DIRECTOR');
    END IF;

    RETURN NEW;
END;
$BODY$ LANGUAGE plpgsql;

CREATE TRIGGER director_from_vg
    AFTER INSERT
    ON VideoGame
    FOR EACH ROW
    EXECUTE PROCEDURE add_director_from_vg();


-- Table, function, trigger for logging changes to passwords
CREATE TABLE PasswordChange (
    id SERIAL PRIMARY KEY,
    uid INT NOT NULL,
    oldPassword CHAR(40) NOT NULL,
    newPassword CHAR(40) NOT NULL,
    changedOn TIMESTAMP(6),
    FOREIGN KEY (uid) REFERENCES Users (uid)
        ON DELETE CASCADE
);

CREATE OR REPLACE FUNCTION log_password_changes()
    RETURNS TRIGGER AS
$BODY$
BEGIN
    IF NEW.password <> OLD.password THEN
        INSERT INTO PasswordChange (uid, oldPassword, newPassword, changedOn) VALUES (NEW.uid, OLD.password, NEW.password, now());
    END IF;
    RETURN NEW;
END;
$BODY$ LANGUAGE plpgsql;

CREATE TRIGGER password_changes
    AFTER UPDATE
    ON Users
    FOR EACH ROW
    EXECUTE PROCEDURE log_password_changes();
