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
