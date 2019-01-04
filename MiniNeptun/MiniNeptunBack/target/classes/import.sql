INSERT INTO subjects (name, type, credit_value, grade_method, grade_value) VALUES ('Alkalmazasok Fejlesztese', 'TYPE_GY', 3, 'METHOD_HOMEWORK', 5);
INSERT INTO subjects (name, type, credit_value, grade_method, grade_value) VALUES ('Analizis 3.', 'TYPE_MIX', 4, 'METHOD_EXAM', 5);
INSERT INTO subjects (name, type, credit_value, grade_method, grade_value) VALUES ('Algoritmusok 1.', 'TYPE_EA', 2, 'METHOD_EXAM', 5);
INSERT INTO subjects (name, type, credit_value, grade_method, grade_value) VALUES ('Programozas', 'TYPE_GY', 7, 'METHOD_EXAM', 5);
INSERT INTO subjects (name, type, credit_value, grade_method, grade_value) VALUES ('JAVA', 'TYPE_EA', 3, 'METHOD_EXAM', 5);

INSERT INTO groups (subject_id, teacher_name, student_limit, location, timeframe) VALUES (1, 'Tanar Laszlo', 25, '1-113', 'K 10:00-12:00');
INSERT INTO groups (subject_id, teacher_name, student_limit, location, timeframe) VALUES (1, 'Tanar Gergo', 12, '1-343', 'P 14:00-16:00');
INSERT INTO groups (subject_id, teacher_name, student_limit, location, timeframe) VALUES (1, 'Tanar Gergo', 54, '1-343', 'SZ 18:00-20:00');
INSERT INTO groups (subject_id, teacher_name, student_limit, location, timeframe) VALUES (2, 'Tanar Donat', 58, 'Bolyai terem', 'SZ 08:00-10:00');
INSERT INTO groups (subject_id, teacher_name, student_limit, location, timeframe) VALUES (2, 'Tanar Zsolt', 32, '00-410', 'CS 12:00-14:00');
INSERT INTO groups (subject_id, teacher_name, student_limit, location, timeframe) VALUES (3, 'Tanar Bela', 43, '00-725', 'P 10:00-13:00');
INSERT INTO groups (subject_id, teacher_name, student_limit, location, timeframe) VALUES (4, 'Tanar Bela', 12, '1-817', 'H 12:00-14:00');
INSERT INTO groups (subject_id, teacher_name, student_limit, location, timeframe) VALUES (4, 'Tanar Roland', 33, '4-202', 'H 08:00-10:00');
INSERT INTO groups (subject_id, teacher_name, student_limit, location, timeframe) VALUES (4, 'Tanar Bertalan', 65, '1-207', 'K 10:00-12:00');
INSERT INTO groups (subject_id, teacher_name, student_limit, location, timeframe) VALUES (5, 'Tanar Kristof', 77, '6-107', 'SZ 08:00-10:00');
INSERT INTO groups (subject_id, teacher_name, student_limit, location, timeframe) VALUES (5, 'Tanar Adrian', 32, '00-114', 'P 16:00-18:00');

INSERT INTO users (name, username, password, gender, enabled, role_Id) values ('admin', 'admin', '$2a$04$YDiv9c./ytEGZQopFfExoOgGlJL6/o0er0K.hiGb5TGKHUL8Ebn..', 'GENDER_M', true, 'ROLE_ADMIN');
INSERT INTO users (name, username, password, gender, enabled, role_id) VALUES ('Tanar Bela', 'teszttanar', '$2a$04$YDiv9c./ytEGZQopFfExoOgGlJL6/o0er0K.hiGb5TGKHUL8Ebn..', 'GENDER_M', true, 'ROLE_TEACHER');
INSERT INTO users (name, username, password, gender, enabled, role_id) VALUES ('Diak Jozsi', 'tesztdiak', '$2a$04$YDiv9c./ytEGZQopFfExoOgGlJL6/o0er0K.hiGb5TGKHUL8Ebn..', 'GENDER_M', true, 'ROLE_STUDENT');
INSERT INTO users (name, username, password, gender, enabled, role_id) VALUES ('Diak Regina', 'tesztdiakk', '$2a$04$YDiv9c./ytEGZQopFfExoOgGlJL6/o0er0K.hiGb5TGKHUL8Ebn..', 'GENDER_F', true, 'ROLE_STUDENT');
INSERT INTO users (name, username, password, gender, enabled, role_id) VALUES ('Diak Elek', 'tesztdiakkk', '$2a$04$YDiv9c./ytEGZQopFfExoOgGlJL6/o0er0K.hiGb5TGKHUL8Ebn..', 'GENDER_M', true, 'ROLE_STUDENT');

INSERT INTO groups_members (groups_id, members_id) VALUES (1,2);
INSERT INTO groups_members (groups_id, members_id) VALUES (2,2);
INSERT INTO groups_members (groups_id, members_id) VALUES (1,3);
INSERT INTO groups_members (groups_id, members_id) VALUES (2,3);
INSERT INTO groups_members (groups_id, members_id) VALUES (3,3);
