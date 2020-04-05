CREATE TABLE GamePhotos (
    gphid INT PRIMARY KEY,
    cover VARCHAR,
    banner VARCHAR,
    FOREIGN KEY (gphid) REFERENCES VideoGame (gid)
);

INSERT INTO GamePhotos (gphid, cover, banner) VALUES (1, 'uncharted_1.jpg', 'uncharted_1.jpg');
INSERT INTO GamePhotos (gphid, cover, banner) VALUES (2, 'uncharted_2.jpg', 'uncharted_2.png');
INSERT INTO GamePhotos (gphid, cover, banner) VALUES (3, 'god_of_war.jpg', 'god_of_war.png');
INSERT INTO GamePhotos (gphid, cover, banner) VALUES (4, 'cod_1.jpg', 'cod_1.jpg');
INSERT INTO GamePhotos (gphid, cover, banner) VALUES (5, 'cod_4.jpg', 'cod_4.jpg');
INSERT INTO GamePhotos (gphid, cover, banner) VALUES (6, 'halo_1.jpg', 'halo_1.jpg');
INSERT INTO GamePhotos (gphid, cover, banner) VALUES (7, 'halo_2.png', 'halo_2.jpg');
INSERT INTO GamePhotos (gphid, cover, banner) VALUES (8, 'halo_3.jpg', 'halo_3.jpg');
INSERT INTO GamePhotos (gphid, cover, banner) VALUES (9, 'mass_effect_1.jpg', 'mass_effect_1.jpg');
INSERT INTO GamePhotos (gphid, cover, banner) VALUES (10, 'mass_effect_2.png', 'mass_effect_2.jpg');