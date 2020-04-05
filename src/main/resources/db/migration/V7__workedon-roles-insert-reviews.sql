UPDATE WorkedOn SET role = 'n/a' WHERE role IS NULL;

INSERT INTO UserReview (uid, gid, title, review, score, date) VALUES (7,3,'Meh','God of War is okay, nothing great',5,'2020-04-04');

INSERT INTO UserReview (uid, gid, title, review, score, date) VALUES (7,10,'Absolute garbage','complete waste of money. dont bother playing it even if they PAY you to do it',1,'2020-04-04');
