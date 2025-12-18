-- =============================================================================
-- SCRIPT 03: QUIZZES DE CAMILA (cami@mail.com)
-- Ejecutar después de 02_labels.sql
-- =============================================================================

-- =============================================================================
-- QUIZ 1: Programación Básica (PÚBLICO) - Por Cami
-- =============================================================================
INSERT INTO quiz (id, title, description, creation_date, is_private, invitation_code, user_id)
VALUES (101, 'Programación Básica', 'Conceptos fundamentales de programación para principiantes', CURDATE(), FALSE, NULL, 1);

-- Preguntas Quiz 101
INSERT INTO question (id, content, quiz_id) VALUES (1001, '¿Qué es una variable en programación?', 101);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10001, 'Un espacio en memoria para almacenar datos', TRUE, 1001);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10002, 'Un tipo de bucle', FALSE, 1001);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10003, 'Una función matemática', FALSE, 1001);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10004, 'Un error de código', FALSE, 1001);

INSERT INTO question (id, content, quiz_id) VALUES (1002, '¿Cuál es el resultado de 10 % 3 en la mayoría de lenguajes?', 101);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10005, '1', TRUE, 1002);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10006, '3', FALSE, 1002);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10007, '0', FALSE, 1002);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10008, '10', FALSE, 1002);

INSERT INTO question (id, content, quiz_id) VALUES (1003, '¿Qué estructura se usa para repetir código?', 101);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10009, 'Bucle (for, while)', TRUE, 1003);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10010, 'Condicional (if)', FALSE, 1003);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10011, 'Función', FALSE, 1003);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10012, 'Clase', FALSE, 1003);

INSERT INTO question (id, content, quiz_id) VALUES (1004, '¿Qué significa "debugging"?', 101);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10013, 'Encontrar y corregir errores', TRUE, 1004);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10014, 'Escribir código nuevo', FALSE, 1004);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10015, 'Eliminar archivos', FALSE, 1004);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10016, 'Instalar programas', FALSE, 1004);

INSERT INTO question (id, content, quiz_id) VALUES (1005, '¿Cuál NO es un tipo de dato primitivo común?', 101);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10017, 'Array', TRUE, 1005);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10018, 'Integer', FALSE, 1005);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10019, 'Boolean', FALSE, 1005);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10020, 'String', FALSE, 1005);

-- Asociar labels
INSERT IGNORE INTO label_quizzes (labels_id, quizzes_id) VALUES (14, 101); -- Programación
INSERT IGNORE INTO label_quizzes (labels_id, quizzes_id) VALUES (4, 101);  -- Educación

-- =============================================================================
-- QUIZ 2: Quiz de Películas (PÚBLICO) - Por Cami
-- =============================================================================
INSERT INTO quiz (id, title, description, creation_date, is_private, invitation_code, user_id)
VALUES (102, 'Clásicos del Cine', '¿Cuánto sabes sobre películas icónicas?', CURDATE(), FALSE, NULL, 1);

INSERT INTO question (id, content, quiz_id) VALUES (1006, '¿En qué año se estrenó "Titanic"?', 102);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10021, '1997', TRUE, 1006);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10022, '1995', FALSE, 1006);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10023, '1999', FALSE, 1006);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10024, '2000', FALSE, 1006);

INSERT INTO question (id, content, quiz_id) VALUES (1007, '¿Quién dirigió "Pulp Fiction"?', 102);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10025, 'Quentin Tarantino', TRUE, 1007);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10026, 'Martin Scorsese', FALSE, 1007);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10027, 'Steven Spielberg', FALSE, 1007);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10028, 'Christopher Nolan', FALSE, 1007);

INSERT INTO question (id, content, quiz_id) VALUES (1008, '¿Cuál es la película más taquillera de la historia?', 102);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10029, 'Avatar', TRUE, 1008);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10030, 'Avengers: Endgame', FALSE, 1008);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10031, 'Titanic', FALSE, 1008);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10032, 'Star Wars: The Force Awakens', FALSE, 1008);

INSERT INTO question (id, content, quiz_id) VALUES (1009, '¿Qué actor interpreta a Jack en Titanic?', 102);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10033, 'Leonardo DiCaprio', TRUE, 1009);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10034, 'Brad Pitt', FALSE, 1009);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10035, 'Tom Cruise', FALSE, 1009);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10036, 'Johnny Depp', FALSE, 1009);

INSERT INTO question (id, content, quiz_id) VALUES (1010, '¿De qué país es la película "Parasite" (2019)?', 102);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10037, 'Corea del Sur', TRUE, 1010);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10038, 'Japón', FALSE, 1010);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10039, 'China', FALSE, 1010);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10040, 'Tailandia', FALSE, 1010);

INSERT IGNORE INTO label_quizzes (labels_id, quizzes_id) VALUES (12, 102); -- Películas
INSERT IGNORE INTO label_quizzes (labels_id, quizzes_id) VALUES (5, 102);  -- General

-- =============================================================================
-- QUIZ 3: Examen de Matemáticas (PRIVADO) - Por Cami
-- Código: 123456
-- =============================================================================
INSERT INTO quiz (id, title, description, creation_date, is_private, invitation_code, user_id)
VALUES (103, 'Examen Final de Matemáticas', 'Quiz privado para el grupo de estudio', CURDATE(), TRUE, '123456', 1);

INSERT INTO question (id, content, quiz_id) VALUES (1011, '¿Cuánto es la raíz cuadrada de 144?', 103);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10041, '12', TRUE, 1011);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10042, '14', FALSE, 1011);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10043, '11', FALSE, 1011);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10044, '13', FALSE, 1011);

INSERT INTO question (id, content, quiz_id) VALUES (1012, '¿Cuál es el valor de π (pi) aproximado?', 103);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10045, '3.14159', TRUE, 1012);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10046, '2.71828', FALSE, 1012);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10047, '1.41421', FALSE, 1012);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10048, '3.33333', FALSE, 1012);

INSERT INTO question (id, content, quiz_id) VALUES (1013, 'Si x + 5 = 12, ¿cuánto vale x?', 103);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10049, '7', TRUE, 1013);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10050, '5', FALSE, 1013);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10051, '17', FALSE, 1013);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10052, '12', FALSE, 1013);

INSERT INTO question (id, content, quiz_id) VALUES (1014, '¿Cuántos grados tiene un triángulo?', 103);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10053, '180 grados', TRUE, 1014);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10054, '360 grados', FALSE, 1014);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10055, '90 grados', FALSE, 1014);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10056, '270 grados', FALSE, 1014);

INSERT INTO question (id, content, quiz_id) VALUES (1015, '¿Cuál es el resultado de 2^10?', 103);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10057, '1024', TRUE, 1015);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10058, '512', FALSE, 1015);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10059, '2048', FALSE, 1015);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10060, '100', FALSE, 1015);

INSERT IGNORE INTO label_quizzes (labels_id, quizzes_id) VALUES (6, 103);  -- Matemáticas
INSERT IGNORE INTO label_quizzes (labels_id, quizzes_id) VALUES (4, 103);  -- Educación

-- =============================================================================
-- QUIZ 4: Cultura Argentina (PÚBLICO) - Por Cami
-- =============================================================================
INSERT INTO quiz (id, title, description, creation_date, is_private, invitation_code, user_id)
VALUES (104, 'Cultura Argentina', '¿Cuánto sabés de Argentina?', CURDATE(), FALSE, NULL, 1);

INSERT INTO question (id, content, quiz_id) VALUES (1016, '¿Cuál es la capital de Argentina?', 104);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10061, 'Buenos Aires', TRUE, 1016);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10062, 'Córdoba', FALSE, 1016);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10063, 'Rosario', FALSE, 1016);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10064, 'Mendoza', FALSE, 1016);

INSERT INTO question (id, content, quiz_id) VALUES (1017, '¿En qué año se independizó Argentina?', 104);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10065, '1816', TRUE, 1017);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10066, '1810', FALSE, 1017);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10067, '1820', FALSE, 1017);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10068, '1800', FALSE, 1017);

INSERT INTO question (id, content, quiz_id) VALUES (1018, '¿Quién escribió "El Aleph"?', 104);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10069, 'Jorge Luis Borges', TRUE, 1018);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10070, 'Julio Cortázar', FALSE, 1018);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10071, 'Gabriel García Márquez', FALSE, 1018);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10072, 'Pablo Neruda', FALSE, 1018);

INSERT INTO question (id, content, quiz_id) VALUES (1019, '¿Cuál es el baile típico de Argentina?', 104);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10073, 'Tango', TRUE, 1019);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10074, 'Salsa', FALSE, 1019);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10075, 'Cumbia', FALSE, 1019);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10076, 'Flamenco', FALSE, 1019);

INSERT INTO question (id, content, quiz_id) VALUES (1020, '¿Cuántas Copas del Mundo ganó Argentina en fútbol?', 104);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10077, '3 (1978, 1986, 2022)', TRUE, 1020);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10078, '2', FALSE, 1020);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10079, '4', FALSE, 1020);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (10080, '1', FALSE, 1020);

INSERT IGNORE INTO label_quizzes (labels_id, quizzes_id) VALUES (7, 104);  -- Geografía
INSERT IGNORE INTO label_quizzes (labels_id, quizzes_id) VALUES (2, 104);  -- Historia

SELECT '✅ Quizzes de Cami creados exitosamente' AS status;
SELECT COUNT(*) AS quizzes_cami FROM quiz WHERE user_id = 1;

