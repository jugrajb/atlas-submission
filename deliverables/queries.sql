/* SQL Queries (and other commands) Used

 DBMS: PostgreSQL
 Language: Java
 Frameworks: JDBC, Spring Boot

 Note that we used JDBC with named parameters; values preceded by a colon are placeholders
 where JDBC inserts values from our Java code. Semicolons are also omitted from the end,
 as JDBC inserts them automatically.
*/

/*
 This section is for simple queries used in the CRUD classes for each base class
 Each of these classes has simple insert, update, delete, select one, select all methods
 */

 -- Administrator
INSERT INTO administrator (uid, firstname , lastname) VALUES (:uid, :firstname, :lastname)

UPDATE administrator SET firstname = :firstname, lastname = :lastname, WHERE uid = :uid

DELETE FROM administrator WHERE uid = :uid

SELECT * FROM administrator WHERE uid = :uid

SELECT * FROM administrator

-- AppearsIn
INSERT INTO AppearsIn (gid, cid) VALUES (:gid, :cid)

UPDATE AppearsIn SET gid = :gid_new, cid = :cid_new WHERE gid = :gid_old AND cid = :cid_old

DELETE FROM AppearsIn WHERE gid = :gid AND cid = :cid

SELECT * FROM AppearsIn WHERE gid = :gid AND cid = :cid

SELECT * FROM AppearsIn

-- Awarded
INSERT INTO Awarded (name, oid, gid, year) VALUES (:name, :oid, :gid, :year)

UPDATE Awarded SET name = :name_new, oid = :oid_new, gid = :gid_new, year = :year WHERE name = :name_old AND oid = :oid_old AND gid = :gid_old

DELETE FROM Awarded WHERE name = :name AND oid = :oid AND gid = :gid

SELECT * FROM Awarded WHERE name = :name AND oid = :oid AND gid = :gid

SELECT * FROM Awarded

-- AwardOrganization
INSERT INTO AwardOrganization (name, websiteUrl) VALUES (:name, :websiteUrl)

UPDATE AwardOrganization SET name = :name, websiteUrl = :websiteUrl WHERE oid = :oid

DELETE FROM AwardOrganization WHERE oid = :oid

SELECT * FROM AwardOrganization WHERE oid = :oid

SELECT * FROM AwardOrganization

-- CriticReview
INSERT INTO criticReview (gid, title, review, score, author, url, date) VALUES ((gid, title, review, score, author, url, date))

UPDATE criticReview SET gid = :gid, title = :title, review = :review, score = :score, author = :author, url = :url, date = :date WHERE rid = :rid

DELETE FROM criticReview WHERE rid = :rid

SELECT * FROM criticReview WHERE rid = :rid

SELECT * FROM criticReview

-- Director
INSERT INTO director (pid, specialization ) VALUES (:pid, :specialization)

UPDATE director SET specialization = :specialization WHERE pid = :pid

DELETE FROM director WHERE pid = :pid

SELECT * FROM director WHERE pid = :pid

SELECT * FROM director

-- GamePlatform
INSERT INTO GamePlatform (name) VALUES (:name)

UPDATE GamePlatform SET name = :name WHERE gpid = :gpid

DELETE FROM GamePlatform WHERE gpid = :gpid

SELECT * FROM GamePlatform WHERE gpid = :gpid

SELECT * FROM GamePlatform

-- GameStudio
INSERT INTO GameStudio (name, city, state) VALUES (:name, :city, :state)

UPDATE GameStudio SET name = :name, city = :city, state = :state WHERE sid = :sid

DELETE FROM GameStudio WHERE sid = :sid

SELECT * FROM GameStudio WHERE sid = :sid

SELECT * FROM GameStudio

-- GeneralUser
INSERT INTO generalUser (uid, username, profileImage) VALUES (:uid, :username, :profileImage)

UPDATE generalUser SET username = :uername, profileImage = :profileImage, WHERE uid = :uid

DELETE FROM generalUser WHERE uid = :uid

SELECT * FROM generalUser WHERE uid = :uid

SELECT * FROM generalUser

-- InGameCharacter
INSERT INTO InGameCharacter (name) VALUES (:name)

UPDATE InGameCharacter SET name = :name WHERE cid = :cid

DELETE FROM InGameCharacter WHERE cid = :cid

SELECT * FROM InGameCharacter WHERE cid = :cid

SELECT * FROM InGameCharacter

-- PlayableOn
INSERT INTO PlayableOn (gid, gpid) VALUES (:gid, :gpid)

UPDATE PlayableOn SET gid = :gid_new, gpid = :gpid_new WHERE gid = :gid_old AND gpid = :gpid_old

DELETE FROM PlayableOn WHERE gid = :gid AND gpid = :gpid

SELECT * FROM PlayableOn WHERE gid = :gid AND gpid = :gpid

SELECT * FROM PlayableOn

-- PublishingCompany
INSERT INTO PublishingCompany (name, city, state) VALUES (:name, :city, :state)

UPDATE PublishingCompany SET name = :name, city = :city, state = :city WHERE pcid = :pcid

DELETE FROM PublishingCompany WHERE pcid = :pcid

SELECT * FROM PublishingCompany WHERE pcid = :pcid

SELECT * FROM PublishingCompany

-- Series
INSERT INTO series (gid, prevGid) VALUES (:gid, :prevGid)

UPDATE series SET prevGid = :prevGid, WHERE gid = :gid

DELETE FROM series WHERE gid = :gid

SELECT * FROM series WHERE gid = :gid

SELECT * FROM series

-- UserReview
INSERT INTO userReview (uid, gid, title, review, score, date) VALUES (:uid, :gid, :title, :review, :score, :date)

UPDATE userReview SET gid = :gid, uid = :uid, title = :title, review = :review, score = :score, date = :date WHERE rid = :rid

DELETE FROM userReview WHERE rid = :rid

SELECT * FROM userReview WHERE rid = :rid

SELECT * FROM userReview

-- Users
INSERT INTO users (email , password) VALUES (:email, :password)

UPDATE users SET email = :email, password = :password WHERE uid = :uid

DELETE FROM users WHERE uid = :uid

SELECT * FROM users WHERE uid = :uid

SELECT * FROM users

-- VideoGameAward
INSERT INTO VideoGameAward (name, oid) VALUES (:name, :oid)

UPDATE VideoGameAward SET name = :name_new, oid = :oid_new WHERE name = :name_old AND oid = :oid_old

DELETE FROM VideoGameAward WHERE name = :name AND oid = :oid

SELECT * FROM VideoGameAward WHERE name = :name AND oid = :oid

SELECT * FROM VideoGameAward

-- VideoGame
INSERT INTO videogame (title, releaseDate, genre, pcid, sid, pid) VALUES (:title, :releaseDate, :genre, :pcid, :sid, :pid)

UPDATE videogame SET title = :title, releaseDate = :releaseDate, genre = :genre, pcid = :pcid, sid = :sid, pid = :pid WHERE gid = :gid

DELETE FROM videogame WHERE gid = :gid

SELECT * FROM videogame WHERE gid = :gid

SELECT * FROM videogame

-- VideoGamePerson
INSERT INTO VideoGamePerson (firstname, lastname, birthdate, sid) VALUES (:firstname, :lastname, :birthdate, :sid)

UPDATE VideoGamePerson SET firstname = :firstname, lastname = :lastname, birthdate = :birthdate, sid = :sid WHERE pid = :pid

DELETE FROM VideoGamePerson WHERE pid = :pid

SELECT * FROM VideoGamePerson WHERE pid = :pid

SELECT * FROM VideoGamePerson

-- VoiceActor
INSERT INTO voiceActor (pid, primaryLanguage ) VALUES (:pid, :primaryLanguage)

UPDATE voiceActor SET primaryLanguage = :primaryLanguage WHERE pid = :pid

DELETE FROM voiceActor WHERE pid = :pid

SELECT * FROM voiceActor WHERE pid = :pid

SELECT * FROM voiceActor

-- VoicePerformanceBy
INSERT INTO VoicePerformanceBy (cid, pid) VALUES (:cid, :pid)

UPDATE VoicePerformanceBy SET cid = :cid_new, pid = :pid_new WHERE cid = :cid_old AND pid = :pid_old

DELETE FROM VoicePerformanceBy WHERE cid = :cid AND pid = :pid

SELECT * FROM VoicePerformanceBy WHERE cid = :cid AND pid = :pid

SELECT * FROM VoicePerformanceBy

-- WorkedOn
INSERT INTO WorkedOn (gid, pid, role) VALUES (:gid, :pid, :role)

UPDATE WorkedOn SET gid = :gid_new, pid = :pid_new, role = :role WHERE gid = :gid_old AND pid = :pid_old

DELETE FROM WorkedOn WHERE gid = :gid AND pid = :pid

SELECT * FROM WorkedOn WHERE gid = :gid AND pid = :pid

SELECT * FROM WorkedOn

/*
This section is for complex queries used in our application, and a description
for each as a comment above the SQL query

Note that some of our queries her have string formatters %s, as we used string
manipulation in Java to accomodate for custom conditions and custom fields
(selection and projection)
*/

-- (BestUsers)
-- find users that left reviews on every game that has reviews
SELECT * FROM GeneralUser u
WHERE NOT EXISTS
(SELECT DISTINCT r1.gid FROM UserReview r1) EXCEPT
(SELECT DISTINCT r2.gid FROM UserReview r2
WHERE r2.uid = u.uid))

-- (CharacterVa)
-- find voice actor - character pairs for a particular game
SELECT vg.gid, c.cid, p.pid, vg.title,c.name AS character, p.firstname, p.lastname
FROM VideoGame vg, InGameCharacter c, AppearsIn a,
VoicePerformanceBy vp, VoiceActor va, VideoGamePerson p
WHERE vg.gid = a.gid AND c.cid = a.cid AND vp.pid = va.pid AND
vp.cid = c.cid AND va.pid = p.pid AND vg.gid = :gid
ORDER BY c.name ASC

-- find voice actor - character pairs for all games
SELECT vg.gid, c.cid, p.pid, vg.title,c.name AS character, p.firstname, p.lastname
FROM VideoGame vg, InGameCharacter c, AppearsIn a,
VoicePerformanceBy vp, VoiceActor va, VideoGamePerson p
WHERE vg.gid = a.gid AND c.cid = a.cid AND vp.pid = va.pid AND
vp.cid = c.cid AND va.pid = p.pid
ORDER BY vg.title, c.name ASC

-- (GameCard)
-- get particular game card
SELECT gid, title FROM VideoGame WHERE gid = :gid

-- get all game cards
SELECT gid, title FROM VideoGame ORDER BY title ASC

-- get all game cards with given condition
SELECT gid, title FROM VideoGame
WHERE %s %s :value
ORDER BY title ASC


-- (PersonCard)
-- get particular person card
SELECT pid, firstname, lastname FROM VideoGamePerson WHERE pid = :pid

-- get all person cards
SELECT pid, firstname, lastname FROM VideoGamePerson ORDER BY firstname, lastname ASC

-- get all person cards with given condition
SELECT pid, firstname, lastname FROM VideoGamePerson
WHERE %s %s :value
ORDER BY firstname, lastname ASC

-- (PersonGame)
-- get all games that a person worked on
SELECT vg.gid, vg.title, vg.releaseDate
FROM VideoGame vg
WHERE vg.gid IN
(SELECT w.gid FROM WorkedOn w WHERE w.pid = :pid)

-- get all people that worked on a game
SELECT p.pid, p.firstname, p.lastname, w.role
FROM VideoGamePerson p, WorkedOn w, VideoGame vg
WHERE w.pid = p.pid AND w.gid = vg.gid AND vg.gid = :gid

-- (RelatedGames)
-- get all games that are involved in a series with a given game
SELECT vg.gid, vg.title
FROM VideoGame vg, Series s WHERE s.gid = vg.gid AND s.prevGid = :gid
UNION
SELECT vg.gid, vg.title
FROM VideoGame vg, Series s WHERE s.prevGid = vg.gid AND s.gid = :gid

-- (SummaryScore)
-- get aggregated summary of user scores for a particular game
SELECT vg.gid, vg.title, AVG(r.score) avg, MAX(r.score) max,
MIN(r.score) min, COUNT(r.rid) count
FROM VideoGame vg LEFT OUTER JOIN UserReview r
ON r.gid = vg.gid
WHERE vg.gid = :gid
GROUP BY vg.gid, vg.title

-- get aggregated summary of critic scores for a particular game
SELECT vg.gid, vg.title, AVG(r.score) avg, MAX(r.score) max,
MIN(r.score) min, COUNT(r.rid) count
FROM VideoGame vg LEFT OUTER JOIN CriticReview r
ON r.gid = vg.gid
WHERE vg.gid = :gid
GROUP BY vg.gid, vg.title

-- get aggregated summary of user scores for all games
SELECT vg.gid, vg.title, AVG(r.score) avg, MAX(r.score) max,
MIN(r.score) min, COUNT(r.rid) count
FROM VideoGame vg LEFT OUTER JOIN UserReview r
ON r.gid = vg.gid
GROUP BY vg.gid, vg.title
ORDER BY vg.gid

-- get aggregated summary of critic scores for all games
SELECT vg.gid, vg.title, AVG(r.score) avg, MAX(r.score) max,
MIN(r.score) min, COUNT(r.rid) count
FROM VideoGame vg LEFT OUTER JOIN CriticReview r
ON r.gid = vg.gid
GROUP BY vg.gid, vg.title
ORDER BY vg.gid

-- get aggregated summary for highest average user-scored game
SELECT vg.gid, vg.title, AVG(r.score) avg, MAX(r.score) max,
MIN(r.score) min, COUNT(r.rid) count
FROM VideoGame vg, UserReview r
WHERE r.gid = vg.gid
GROUP BY vg.gid, vg.title
HAVING AVG(r.score) >= ALL
(SELECT AVG(r.score)
 FROM VideoGame vg, UserReview r
 WHERE r.gid = vg.gid
 GROUP BY vg.gid)

-- get aggregated summary for highest average critic-scored game
SELECT vg.gid, vg.title, AVG(r.score) avg, MAX(r.score) max,
MIN(r.score) min, COUNT(r.rid) count
FROM VideoGame vg, CriticReview r
WHERE r.gid = vg.gid
GROUP BY vg.gid, vg.title
HAVING AVG(r.score) >= ALL
(SELECT AVG(r.score)
 FROM VideoGame vg, CriticReview r
 WHERE r.gid = vg.gid
 GROUP BY vg.gid)

-- (UserFilter)
-- get user-specified fields for particular general user
SELECT %s FROM Users NATURAL JOIN GeneralUser WHERE uid = :uid

-- get user-specified fields for all general users
SELECT %s FROM Users NATURAL JOIN GeneralUser

-- (NaturalJoin)
-- get results from two user-specified relations natural joined together
SELECT * FROM :r1 NATURAL JOIN :r2


/*
This section is for queries related to using triggers in our database.
*/

SELECT * FROM PasswordChange WHERE uid = :uid ORDER BY changedon

SELECT * FROM PasswordChange ORDER BY uid, changedon

--- login (auth)
SELECT u.uid, g.username, u.email, u.password FROM users u
LEFT JOIN administrator a ON u.uid = a.uid
LEFT JOIN generaluser g ON u.uid = g.uid
WHERE u.email = :email AND u.password = :password


--- Game Photos
INSERT INTO gamephotos (gphid, cover, banner) VALUES (:gphid, :cover, :banner)

UPDATE gamephotos SET cover = :cover, banner = :banner WHERE gphid = :gphid

DELETE FROM gamephotos WHERE gphid = :gphid

SELECT * FROM gamephotos WHERE gphid = :gphid

SELECT * FROM gamephotos


--- General User set profileimage
UPDATE generalUser SET username = :username, profileImage = :profileImage WHERE uid = :uid

--- User get by email
SELECT * FROM users WHERE email = :email

--- Additional Queries for FE
SELECT * FROM criticReview WHERE gid = :gid

SELECT gp.gpid, gp.name FROM PlayableOn p
LEFT JOIN GamePlatform gp ON gp.gpid = p.gpid
WHERE p.gid = :gid

SELECT * FROM userReview u LEFT JOIN generaluser gu ON gu.uid = u.uid WHERE u.gid = :gid










