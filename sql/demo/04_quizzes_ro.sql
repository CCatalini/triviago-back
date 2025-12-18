-- =============================================================================
-- SCRIPT 04: QUIZZES DE ROCÍO (ro@mail.com)
-- Ejecutar después de 03_quizzes_cami.sql
-- =============================================================================

-- =============================================================================
-- QUIZ 5: Ciencias Naturales (PÚBLICO) - Por Ro
-- =============================================================================
INSERT INTO quiz (id, title, description, creation_date, is_private, invitation_code, user_id)
VALUES (201, 'Ciencias Naturales Básicas', 'Explora el mundo de la ciencia', CURDATE(), FALSE, NULL, 2);

INSERT INTO question (id, content, quiz_id) VALUES (2001, '¿Cuál es el planeta más grande del sistema solar?', 201);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20001, 'Júpiter', TRUE, 2001);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20002, 'Saturno', FALSE, 2001);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20003, 'Neptuno', FALSE, 2001);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20004, 'Urano', FALSE, 2001);

INSERT INTO question (id, content, quiz_id) VALUES (2002, '¿Qué gas es esencial para la respiración humana?', 201);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20005, 'Oxígeno', TRUE, 2002);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20006, 'Nitrógeno', FALSE, 2002);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20007, 'Dióxido de carbono', FALSE, 2002);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20008, 'Hidrógeno', FALSE, 2002);

INSERT INTO question (id, content, quiz_id) VALUES (2003, '¿Cuántos huesos tiene el cuerpo humano adulto?', 201);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20009, '206', TRUE, 2003);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20010, '300', FALSE, 2003);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20011, '150', FALSE, 2003);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20012, '250', FALSE, 2003);

INSERT INTO question (id, content, quiz_id) VALUES (2004, '¿Qué es la fotosíntesis?', 201);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20013, 'Proceso por el cual las plantas producen su alimento', TRUE, 2004);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20014, 'Respiración de las plantas', FALSE, 2004);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20015, 'Movimiento de las plantas hacia la luz', FALSE, 2004);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20016, 'Reproducción de las plantas', FALSE, 2004);

INSERT INTO question (id, content, quiz_id) VALUES (2005, '¿Cuál es el órgano más grande del cuerpo humano?', 201);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20017, 'La piel', TRUE, 2005);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20018, 'El hígado', FALSE, 2005);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20019, 'El cerebro', FALSE, 2005);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20020, 'Los pulmones', FALSE, 2005);

INSERT IGNORE INTO label_quizzes (labels_id, quizzes_id) VALUES (1, 201);  -- Ciencia
INSERT IGNORE INTO label_quizzes (labels_id, quizzes_id) VALUES (4, 201);  -- Educación

-- =============================================================================
-- QUIZ 6: Música y Artistas (PÚBLICO) - Por Ro
-- =============================================================================
INSERT INTO quiz (id, title, description, creation_date, is_private, invitation_code, user_id)
VALUES (202, 'Música Pop Internacional', '¿Conocés a estos artistas?', CURDATE(), FALSE, NULL, 2);

INSERT INTO question (id, content, quiz_id) VALUES (2006, '¿De qué país es Taylor Swift?', 202);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20021, 'Estados Unidos', TRUE, 2006);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20022, 'Reino Unido', FALSE, 2006);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20023, 'Canadá', FALSE, 2006);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20024, 'Australia', FALSE, 2006);

INSERT INTO question (id, content, quiz_id) VALUES (2007, '¿Qué banda cantó "Bohemian Rhapsody"?', 202);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20025, 'Queen', TRUE, 2007);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20026, 'The Beatles', FALSE, 2007);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20027, 'Led Zeppelin', FALSE, 2007);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20028, 'Pink Floyd', FALSE, 2007);

INSERT INTO question (id, content, quiz_id) VALUES (2008, '¿Cuál es el nombre real de Bad Bunny?', 202);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20029, 'Benito Antonio Martínez', TRUE, 2008);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20030, 'Juan Carlos Pérez', FALSE, 2008);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20031, 'Roberto Sánchez', FALSE, 2008);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20032, 'Luis Miguel Torres', FALSE, 2008);

INSERT INTO question (id, content, quiz_id) VALUES (2009, '¿Qué artista argentino cantó "La Maza"?', 202);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20033, 'Mercedes Sosa', TRUE, 2009);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20034, 'Gustavo Cerati', FALSE, 2009);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20035, 'Charly García', FALSE, 2009);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20036, 'Fito Páez', FALSE, 2009);

INSERT INTO question (id, content, quiz_id) VALUES (2010, '¿Cuántos integrantes tenía One Direction?', 202);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20037, '5', TRUE, 2010);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20038, '4', FALSE, 2010);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20039, '6', FALSE, 2010);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20040, '3', FALSE, 2010);

INSERT IGNORE INTO label_quizzes (labels_id, quizzes_id) VALUES (9, 202);  -- Música
INSERT IGNORE INTO label_quizzes (labels_id, quizzes_id) VALUES (5, 202);  -- General

-- =============================================================================
-- QUIZ 7: Reto de Geografía (PRIVADO) - Por Ro
-- Código: 789012
-- =============================================================================
INSERT INTO quiz (id, title, description, creation_date, is_private, invitation_code, user_id)
VALUES (203, 'Reto de Geografía Mundial', 'Quiz privado para amigos', CURDATE(), TRUE, '789012', 2);

INSERT INTO question (id, content, quiz_id) VALUES (2011, '¿Cuál es el río más largo del mundo?', 203);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20041, 'Nilo', TRUE, 2011);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20042, 'Amazonas', FALSE, 2011);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20043, 'Yangtsé', FALSE, 2011);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20044, 'Misisipi', FALSE, 2011);

INSERT INTO question (id, content, quiz_id) VALUES (2012, '¿Cuál es el país más pequeño del mundo?', 203);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20045, 'Vaticano', TRUE, 2012);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20046, 'Mónaco', FALSE, 2012);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20047, 'San Marino', FALSE, 2012);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20048, 'Liechtenstein', FALSE, 2012);

INSERT INTO question (id, content, quiz_id) VALUES (2013, '¿En qué continente está Egipto?', 203);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20049, 'África', TRUE, 2013);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20050, 'Asia', FALSE, 2013);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20051, 'Europa', FALSE, 2013);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20052, 'Oceanía', FALSE, 2013);

INSERT INTO question (id, content, quiz_id) VALUES (2014, '¿Cuál es la montaña más alta del mundo?', 203);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20053, 'Monte Everest', TRUE, 2014);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20054, 'K2', FALSE, 2014);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20055, 'Aconcagua', FALSE, 2014);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20056, 'Kilimanjaro', FALSE, 2014);

INSERT INTO question (id, content, quiz_id) VALUES (2015, '¿Cuántos países hay en Sudamérica?', 203);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20057, '12', TRUE, 2015);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20058, '10', FALSE, 2015);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20059, '14', FALSE, 2015);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20060, '15', FALSE, 2015);

INSERT IGNORE INTO label_quizzes (labels_id, quizzes_id) VALUES (7, 203);  -- Geografía
INSERT IGNORE INTO label_quizzes (labels_id, quizzes_id) VALUES (4, 203);  -- Educación

-- =============================================================================
-- QUIZ 8: Arte y Pintores (PÚBLICO) - Por Ro
-- =============================================================================
INSERT INTO quiz (id, title, description, creation_date, is_private, invitation_code, user_id)
VALUES (204, 'Historia del Arte', 'Conoce a los grandes maestros del arte', CURDATE(), FALSE, NULL, 2);

INSERT INTO question (id, content, quiz_id) VALUES (2016, '¿Quién pintó "La Mona Lisa"?', 204);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20061, 'Leonardo da Vinci', TRUE, 2016);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20062, 'Miguel Ángel', FALSE, 2016);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20063, 'Rafael', FALSE, 2016);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20064, 'Botticelli', FALSE, 2016);

INSERT INTO question (id, content, quiz_id) VALUES (2017, '¿De qué nacionalidad era Picasso?', 204);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20065, 'Española', TRUE, 2017);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20066, 'Francesa', FALSE, 2017);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20067, 'Italiana', FALSE, 2017);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20068, 'Portuguesa', FALSE, 2017);

INSERT INTO question (id, content, quiz_id) VALUES (2018, '¿Qué artista se cortó una oreja?', 204);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20069, 'Vincent van Gogh', TRUE, 2018);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20070, 'Claude Monet', FALSE, 2018);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20071, 'Paul Cézanne', FALSE, 2018);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20072, 'Edvard Munch', FALSE, 2018);

INSERT INTO question (id, content, quiz_id) VALUES (2019, '¿Qué movimiento artístico fundó Salvador Dalí?', 204);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20073, 'Surrealismo', TRUE, 2019);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20074, 'Impresionismo', FALSE, 2019);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20075, 'Cubismo', FALSE, 2019);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20076, 'Expresionismo', FALSE, 2019);

INSERT INTO question (id, content, quiz_id) VALUES (2020, '¿Dónde se encuentra "La Noche Estrellada"?', 204);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20077, 'MoMA, Nueva York', TRUE, 2020);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20078, 'Louvre, París', FALSE, 2020);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20079, 'Museo del Prado, Madrid', FALSE, 2020);
INSERT INTO answer (id, content, is_correct, question_id) VALUES (20080, 'Uffizi, Florencia', FALSE, 2020);

INSERT IGNORE INTO label_quizzes (labels_id, quizzes_id) VALUES (8, 204);  -- Arte
INSERT IGNORE INTO label_quizzes (labels_id, quizzes_id) VALUES (2, 204);  -- Historia

SELECT '✅ Quizzes de Ro creados exitosamente' AS status;
SELECT COUNT(*) AS quizzes_ro FROM quiz WHERE user_id = 2;

