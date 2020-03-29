ALTER TABLE Awarded
DROP CONSTRAINT awarded_name_oid_fkey,
ADD CONSTRAINT awarded_name_oid_fkey
  FOREIGN KEY (name, oid)
  REFERENCES VideoGameAward (name, oid)
  ON DELETE CASCADE;

INSERT INTO UserReview (uid, gid, title, review, score, date) VALUES (6, 10, 'Not impressed', 'Hopefully the next game will be better, not that great', 4, '2019-01-21');