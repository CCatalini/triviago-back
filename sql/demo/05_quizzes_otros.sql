-- =============================================================================
-- SCRIPT 05: QUIZZES DE OTROS USUARIOS
-- Ejecutar después de 04_quizzes_ro.sql
-- =============================================================================

-- =============================================================================
-- QUIZ 9: Historia Universal (PÚBLICO) - Por Profesor Juan
-- =============================================================================
INSERT INTO quiz (id, title, description, creation_date, is_private, invitation_code, user_id)
VALUES (301, 'Historia Universal', 'Recorre los momentos más importantes de la humanidad', CURDATE(), FALSE, NULL, 3);

INSERT INTO question (id, content, quiz_id) VALUES (3001, '¿En qué año cayó el Muro de Berlín?', 301);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (30001, '1989', TRUE, 3001);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (30002, '1991', FALSE, 3001);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (30003, '1985', FALSE, 3001);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (30004, '1995', FALSE, 3001);

INSERT INTO question (id, content, quiz_id) VALUES (3002, '¿Quién fue el primer presidente de Estados Unidos?', 301);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (30005, 'George Washington', TRUE, 3002);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (30006, 'Abraham Lincoln', FALSE, 3002);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (30007, 'Thomas Jefferson', FALSE, 3002);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (30008, 'John Adams', FALSE, 3002);

INSERT INTO question (id, content, quiz_id) VALUES (3003, '¿En qué año comenzó la Primera Guerra Mundial?', 301);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (30009, '1914', TRUE, 3003);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (30010, '1918', FALSE, 3003);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (30011, '1912', FALSE, 3003);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (30012, '1920', FALSE, 3003);

INSERT INTO question (id, content, quiz_id) VALUES (3004, '¿Qué imperio construyó las pirámides de Giza?', 301);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (30013, 'Egipcio', TRUE, 3004);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (30014, 'Romano', FALSE, 3004);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (30015, 'Griego', FALSE, 3004);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (30016, 'Persa', FALSE, 3004);

INSERT INTO question (id, content, quiz_id) VALUES (3005, '¿Quién descubrió América?', 301);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (30017, 'Cristóbal Colón', TRUE, 3005);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (30018, 'Américo Vespucio', FALSE, 3005);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (30019, 'Fernando de Magallanes', FALSE, 3005);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (30020, 'Hernán Cortés', FALSE, 3005);

INSERT IGNORE INTO label_quizzes (labels_id, quizzes_id) VALUES (2, 301);  -- Historia
INSERT IGNORE INTO label_quizzes (labels_id, quizzes_id) VALUES (4, 301);  -- Educación

-- =============================================================================
-- QUIZ 10: Videojuegos Clásicos (PÚBLICO) - Por Pedro Gamer
-- =============================================================================
INSERT INTO quiz (id, title, description, creation_date, is_private, invitation_code, user_id)
VALUES (401, 'Videojuegos Clásicos', '¿Sos un verdadero gamer? Demostralo!', CURDATE(), FALSE, NULL, 5);

INSERT INTO question (id, content, quiz_id) VALUES (4001, '¿En qué año se lanzó el primer Mario Bros?', 401);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (40001, '1985', TRUE, 4001);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (40002, '1983', FALSE, 4001);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (40003, '1987', FALSE, 4001);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (40004, '1990', FALSE, 4001);

INSERT INTO question (id, content, quiz_id) VALUES (4002, '¿Cuál es el personaje principal de Zelda?', 401);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (40005, 'Link', TRUE, 4002);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (40006, 'Zelda', FALSE, 4002);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (40007, 'Ganondorf', FALSE, 4002);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (40008, 'Epona', FALSE, 4002);

INSERT INTO question (id, content, quiz_id) VALUES (4003, '¿Qué empresa creó PlayStation?', 401);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (40009, 'Sony', TRUE, 4003);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (40010, 'Microsoft', FALSE, 4003);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (40011, 'Nintendo', FALSE, 4003);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (40012, 'Sega', FALSE, 4003);

INSERT INTO question (id, content, quiz_id) VALUES (4004, '¿Cuál es el juego más vendido de la historia?', 401);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (40013, 'Minecraft', TRUE, 4004);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (40014, 'GTA V', FALSE, 4004);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (40015, 'Tetris', FALSE, 4004);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (40016, 'Wii Sports', FALSE, 4004);

INSERT INTO question (id, content, quiz_id) VALUES (4005, '¿De qué color es Sonic?', 401);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (40017, 'Azul', TRUE, 4005);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (40018, 'Rojo', FALSE, 4005);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (40019, 'Verde', FALSE, 4005);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (40020, 'Amarillo', FALSE, 4005);

INSERT IGNORE INTO label_quizzes (labels_id, quizzes_id) VALUES (13, 401); -- Videojuegos
INSERT IGNORE INTO label_quizzes (labels_id, quizzes_id) VALUES (3, 401);  -- Tecnología

-- =============================================================================
-- QUIZ 11: Deportes (PÚBLICO) - Por Ana Estudiante
-- =============================================================================
INSERT INTO quiz (id, title, description, creation_date, is_private, invitation_code, user_id)
VALUES (402, 'Deportes Mundiales', 'Trivia de deportes para fanáticos', CURDATE(), FALSE, NULL, 4);

INSERT INTO question (id, content, quiz_id) VALUES (4006, '¿Cada cuántos años se realizan los Juegos Olímpicos?', 402);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (40021, '4 años', TRUE, 4006);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (40022, '2 años', FALSE, 4006);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (40023, '5 años', FALSE, 4006);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (40024, '3 años', FALSE, 4006);

INSERT INTO question (id, content, quiz_id) VALUES (4007, '¿Cuántos jugadores tiene un equipo de fútbol en cancha?', 402);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (40025, '11', TRUE, 4007);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (40026, '10', FALSE, 4007);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (40027, '12', FALSE, 4007);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (40028, '9', FALSE, 4007);

INSERT INTO question (id, content, quiz_id) VALUES (4008, '¿Qué deporte se juega en Wimbledon?', 402);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (40029, 'Tenis', TRUE, 4008);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (40030, 'Golf', FALSE, 4008);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (40031, 'Cricket', FALSE, 4008);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (40032, 'Polo', FALSE, 4008);

INSERT INTO question (id, content, quiz_id) VALUES (4009, '¿De qué país es Lionel Messi?', 402);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (40033, 'Argentina', TRUE, 4009);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (40034, 'Brasil', FALSE, 4009);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (40035, 'España', FALSE, 4009);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (40036, 'Portugal', FALSE, 4009);

INSERT INTO question (id, content, quiz_id) VALUES (4010, '¿Cuántos sets se necesitan para ganar un partido de voley?', 402);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (40037, '3', TRUE, 4010);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (40038, '2', FALSE, 4010);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (40039, '4', FALSE, 4010);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (40040, '5', FALSE, 4010);

INSERT IGNORE INTO label_quizzes (labels_id, quizzes_id) VALUES (10, 402); -- Deportes
INSERT IGNORE INTO label_quizzes (labels_id, quizzes_id) VALUES (5, 402);  -- General

-- =============================================================================
-- QUIZ 12: Física Básica (PRIVADO) - Por María Científica
-- Código: 456789
-- =============================================================================
INSERT INTO quiz (id, title, description, creation_date, is_private, invitation_code, user_id)
VALUES (501, 'Física para Principiantes', 'Quiz privado de estudio', CURDATE(), TRUE, '456789', 6);

INSERT INTO question (id, content, quiz_id) VALUES (5001, '¿Cuál es la velocidad de la luz?', 501);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (50001, '299,792 km/s', TRUE, 5001);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (50002, '150,000 km/s', FALSE, 5001);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (50003, '340 m/s', FALSE, 5001);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (50004, '1,000,000 km/s', FALSE, 5001);

INSERT INTO question (id, content, quiz_id) VALUES (5002, '¿Qué es la gravedad?', 501);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (50005, 'Fuerza de atracción entre masas', TRUE, 5002);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (50006, 'Fuerza magnética', FALSE, 5002);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (50007, 'Energía cinética', FALSE, 5002);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (50008, 'Presión atmosférica', FALSE, 5002);

INSERT INTO question (id, content, quiz_id) VALUES (5003, '¿Quién formuló la teoría de la relatividad?', 501);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (50009, 'Albert Einstein', TRUE, 5003);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (50010, 'Isaac Newton', FALSE, 5003);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (50011, 'Stephen Hawking', FALSE, 5003);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (50012, 'Galileo Galilei', FALSE, 5003);

INSERT INTO question (id, content, quiz_id) VALUES (5004, '¿Cuál es la unidad de fuerza en el SI?', 501);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (50013, 'Newton', TRUE, 5004);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (50014, 'Joule', FALSE, 5004);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (50015, 'Watt', FALSE, 5004);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (50016, 'Pascal', FALSE, 5004);

INSERT INTO question (id, content, quiz_id) VALUES (5005, '¿Qué partícula tiene carga negativa?', 501);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (50017, 'Electrón', TRUE, 5005);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (50018, 'Protón', FALSE, 5005);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (50019, 'Neutrón', FALSE, 5005);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (50020, 'Fotón', FALSE, 5005);

INSERT IGNORE INTO label_quizzes (labels_id, quizzes_id) VALUES (1, 501);  -- Ciencia
INSERT IGNORE INTO label_quizzes (labels_id, quizzes_id) VALUES (4, 501);  -- Educación

-- =============================================================================
-- QUIZ 13: Literatura Clásica (PÚBLICO) - Por Carlos Historiador
-- =============================================================================
INSERT INTO quiz (id, title, description, creation_date, is_private, invitation_code, user_id)
VALUES (601, 'Literatura Clásica', 'Grandes obras de la literatura universal', CURDATE(), FALSE, NULL, 7);

INSERT INTO question (id, content, quiz_id) VALUES (6001, '¿Quién escribió "Don Quijote de la Mancha"?', 601);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (60001, 'Miguel de Cervantes', TRUE, 6001);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (60002, 'Federico García Lorca', FALSE, 6001);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (60003, 'Gabriel García Márquez', FALSE, 6001);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (60004, 'Pablo Neruda', FALSE, 6001);

INSERT INTO question (id, content, quiz_id) VALUES (6002, '¿De qué país era Shakespeare?', 601);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (60005, 'Inglaterra', TRUE, 6002);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (60006, 'Francia', FALSE, 6002);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (60007, 'Alemania', FALSE, 6002);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (60008, 'Italia', FALSE, 6002);

INSERT INTO question (id, content, quiz_id) VALUES (6003, '¿Quién escribió "Cien años de soledad"?', 601);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (60009, 'Gabriel García Márquez', TRUE, 6003);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (60010, 'Mario Vargas Llosa', FALSE, 6003);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (60011, 'Jorge Luis Borges', FALSE, 6003);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (60012, 'Julio Cortázar', FALSE, 6003);

INSERT INTO question (id, content, quiz_id) VALUES (6004, '¿Cuál es la obra más famosa de Dante?', 601);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (60013, 'La Divina Comedia', TRUE, 6004);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (60014, 'El Decamerón', FALSE, 6004);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (60015, 'Los Cuentos de Canterbury', FALSE, 6004);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (60016, 'La Odisea', FALSE, 6004);

INSERT INTO question (id, content, quiz_id) VALUES (6005, '¿Quién escribió "1984"?', 601);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (60017, 'George Orwell', TRUE, 6005);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (60018, 'Aldous Huxley', FALSE, 6005);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (60019, 'Ray Bradbury', FALSE, 6005);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (60020, 'Isaac Asimov', FALSE, 6005);

INSERT IGNORE INTO label_quizzes (labels_id, quizzes_id) VALUES (11, 601); -- Literatura
INSERT IGNORE INTO label_quizzes (labels_id, quizzes_id) VALUES (2, 601);  -- Historia

SELECT '✅ Quizzes de otros usuarios creados exitosamente' AS status;
SELECT user_id, COUNT(*) AS total FROM quiz GROUP BY user_id;

