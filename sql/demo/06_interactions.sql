-- =============================================================================
-- SCRIPT 06: INTERACCIONES ENTRE USUARIOS
-- Ejecutar después de 05_quizzes_otros.sql
-- =============================================================================

-- =============================================================================
-- RATINGS (Calificaciones de quizzes)
-- =============================================================================

-- Cami califica los quizzes de Ro
INSERT INTO quiz_rating (id, rating, quiz_id, user_id) VALUES (1, 5, 201, 1); -- Ciencias Naturales
INSERT INTO quiz_rating (id, rating, quiz_id, user_id) VALUES (2, 4, 202, 1); -- Música Pop
INSERT INTO quiz_rating (id, rating, quiz_id, user_id) VALUES (3, 5, 204, 1); -- Historia del Arte

-- Ro califica los quizzes de Cami
INSERT INTO quiz_rating (id, rating, quiz_id, user_id) VALUES (4, 5, 101, 2); -- Programación
INSERT INTO quiz_rating (id, rating, quiz_id, user_id) VALUES (5, 4, 102, 2); -- Clásicos del Cine
INSERT INTO quiz_rating (id, rating, quiz_id, user_id) VALUES (6, 5, 104, 2); -- Cultura Argentina

-- Ana califica varios quizzes
INSERT INTO quiz_rating (id, rating, quiz_id, user_id) VALUES (7, 5, 101, 4);
INSERT INTO quiz_rating (id, rating, quiz_id, user_id) VALUES (8, 4, 201, 4);
INSERT INTO quiz_rating (id, rating, quiz_id, user_id) VALUES (9, 5, 301, 4);
INSERT INTO quiz_rating (id, rating, quiz_id, user_id) VALUES (10, 4, 401, 4);

-- Pedro califica los quizzes de videojuegos y más
INSERT INTO quiz_rating (id, rating, quiz_id, user_id) VALUES (11, 5, 102, 5);
INSERT INTO quiz_rating (id, rating, quiz_id, user_id) VALUES (12, 5, 202, 5);
INSERT INTO quiz_rating (id, rating, quiz_id, user_id) VALUES (13, 3, 301, 5);

-- =============================================================================
-- COMENTARIOS EN QUIZZES
-- =============================================================================

-- Comentarios en el quiz de Programación de Cami (ID 101)
INSERT INTO comment (id, content, creation_date_time, user_id, quiz_id, parent_comment_id) 
VALUES (1, '¡Excelente quiz para principiantes! Me sirvió mucho para repasar.', NOW(), 2, 101, NULL);

INSERT INTO comment (id, content, creation_date_time, user_id, quiz_id, parent_comment_id) 
VALUES (2, 'Gracias Ro! Me alegra que te haya servido 😊', NOW(), 1, 101, 1);

INSERT INTO comment (id, content, creation_date_time, user_id, quiz_id, parent_comment_id) 
VALUES (3, 'Muy claro y bien explicado. Las preguntas son justas.', NOW(), 4, 101, NULL);

-- Comentarios en el quiz de Ciencias de Ro (ID 201)
INSERT INTO comment (id, content, creation_date_time, user_id, quiz_id, parent_comment_id) 
VALUES (4, 'Me encantó! Las preguntas de biología están muy buenas.', NOW(), 1, 201, NULL);

INSERT INTO comment (id, content, creation_date_time, user_id, quiz_id, parent_comment_id) 
VALUES (5, 'Gracias Cami! Pronto subo más de ciencias 🔬', NOW(), 2, 201, 4);

INSERT INTO comment (id, content, creation_date_time, user_id, quiz_id, parent_comment_id) 
VALUES (6, 'Aprendí algo nuevo sobre el cuerpo humano!', NOW(), 5, 201, NULL);

-- Comentarios en el quiz de Películas de Cami (ID 102)
INSERT INTO comment (id, content, creation_date_time, user_id, quiz_id, parent_comment_id) 
VALUES (7, 'Algunas preguntas me las sabía, otras no tanto jaja', NOW(), 2, 102, NULL);

INSERT INTO comment (id, content, creation_date_time, user_id, quiz_id, parent_comment_id) 
VALUES (8, 'Deberías agregar más preguntas de cine argentino!', NOW(), 3, 102, NULL);

INSERT INTO comment (id, content, creation_date_time, user_id, quiz_id, parent_comment_id) 
VALUES (9, 'Buena idea profe! Lo voy a considerar para el próximo.', NOW(), 1, 102, 8);

-- Comentarios en el quiz de Música de Ro (ID 202)
INSERT INTO comment (id, content, creation_date_time, user_id, quiz_id, parent_comment_id) 
VALUES (10, 'Taylor Swift 💕 Sabía todas las de ella', NOW(), 4, 202, NULL);

INSERT INTO comment (id, content, creation_date_time, user_id, quiz_id, parent_comment_id) 
VALUES (11, 'Queen forever! Bohemian Rhapsody es un temazo.', NOW(), 1, 202, NULL);

INSERT INTO comment (id, content, creation_date_time, user_id, quiz_id, parent_comment_id) 
VALUES (12, 'Jaja totalmente de acuerdo! Es una obra maestra 🎸', NOW(), 2, 202, 11);

-- Comentarios en Historia Universal del Profesor (ID 301)
INSERT INTO comment (id, content, creation_date_time, user_id, quiz_id, parent_comment_id) 
VALUES (13, 'Perfecto para estudiar para el parcial!', NOW(), 4, 301, NULL);

INSERT INTO comment (id, content, creation_date_time, user_id, quiz_id, parent_comment_id) 
VALUES (14, 'Me alegra Ana! Para eso lo armé.', NOW(), 3, 301, 13);

INSERT INTO comment (id, content, creation_date_time, user_id, quiz_id, parent_comment_id) 
VALUES (15, 'Muy completo profe, gracias!', NOW(), 1, 301, NULL);

-- Comentarios en Videojuegos de Pedro (ID 401)
INSERT INTO comment (id, content, creation_date_time, user_id, quiz_id, parent_comment_id) 
VALUES (16, 'Jajaja Zelda no es el protagonista! Buen quiz.', NOW(), 1, 401, NULL);

INSERT INTO comment (id, content, creation_date_time, user_id, quiz_id, parent_comment_id) 
VALUES (17, 'Es un error muy común! 😅', NOW(), 5, 401, 16);

INSERT INTO comment (id, content, creation_date_time, user_id, quiz_id, parent_comment_id) 
VALUES (18, 'Minecraft número 1! 🎮', NOW(), 2, 401, NULL);

-- =============================================================================
-- LIKES EN COMENTARIOS
-- =============================================================================

-- Cami da like a comentarios
INSERT INTO comment_like (id, is_like, comment_id, user_id) VALUES (1, TRUE, 1, 1);  -- Like al comentario de Ro
INSERT INTO comment_like (id, is_like, comment_id, user_id) VALUES (2, TRUE, 4, 1);  -- Like a su propio comentario
INSERT INTO comment_like (id, is_like, comment_id, user_id) VALUES (3, TRUE, 13, 1); -- Like al de Ana

-- Ro da like a comentarios
INSERT INTO comment_like (id, is_like, comment_id, user_id) VALUES (4, TRUE, 2, 2);  -- Like a respuesta de Cami
INSERT INTO comment_like (id, is_like, comment_id, user_id) VALUES (5, TRUE, 11, 2); -- Like al comentario de Queen
INSERT INTO comment_like (id, is_like, comment_id, user_id) VALUES (6, TRUE, 16, 2); -- Like al de Zelda

-- Ana da likes
INSERT INTO comment_like (id, is_like, comment_id, user_id) VALUES (7, TRUE, 1, 4);
INSERT INTO comment_like (id, is_like, comment_id, user_id) VALUES (8, TRUE, 5, 4);
INSERT INTO comment_like (id, is_like, comment_id, user_id) VALUES (9, TRUE, 14, 4);

-- Pedro da likes
INSERT INTO comment_like (id, is_like, comment_id, user_id) VALUES (10, TRUE, 6, 5);
INSERT INTO comment_like (id, is_like, comment_id, user_id) VALUES (11, TRUE, 18, 5);

-- =============================================================================
-- QUIZZES GUARDADOS (Favoritos)
-- =============================================================================

-- Cami guarda quizzes de Ro y otros
INSERT IGNORE INTO user_saved_quizzes (user_id, saved_quizzes_id) VALUES (1, 201); -- Ciencias de Ro
INSERT IGNORE INTO user_saved_quizzes (user_id, saved_quizzes_id) VALUES (1, 202); -- Música de Ro
INSERT IGNORE INTO user_saved_quizzes (user_id, saved_quizzes_id) VALUES (1, 301); -- Historia del Profesor
INSERT IGNORE INTO user_saved_quizzes (user_id, saved_quizzes_id) VALUES (1, 401); -- Videojuegos de Pedro

-- Ro guarda quizzes de Cami y otros
INSERT IGNORE INTO user_saved_quizzes (user_id, saved_quizzes_id) VALUES (2, 101); -- Programación de Cami
INSERT IGNORE INTO user_saved_quizzes (user_id, saved_quizzes_id) VALUES (2, 102); -- Películas de Cami
INSERT IGNORE INTO user_saved_quizzes (user_id, saved_quizzes_id) VALUES (2, 104); -- Cultura Argentina de Cami
INSERT IGNORE INTO user_saved_quizzes (user_id, saved_quizzes_id) VALUES (2, 601); -- Literatura de Carlos

-- Ana guarda varios
INSERT IGNORE INTO user_saved_quizzes (user_id, saved_quizzes_id) VALUES (4, 101);
INSERT IGNORE INTO user_saved_quizzes (user_id, saved_quizzes_id) VALUES (4, 201);
INSERT IGNORE INTO user_saved_quizzes (user_id, saved_quizzes_id) VALUES (4, 301);

-- Pedro guarda varios
INSERT IGNORE INTO user_saved_quizzes (user_id, saved_quizzes_id) VALUES (5, 102);
INSERT IGNORE INTO user_saved_quizzes (user_id, saved_quizzes_id) VALUES (5, 202);

-- =============================================================================
-- RESOLUCIONES DE QUIZZES
-- =============================================================================

-- Cami resuelve quizzes de Ro
INSERT INTO quiz_resolution (id, correct_answers, quiz_id, user_id) VALUES (1, 4, 201, 1); -- 4/5 en Ciencias
INSERT INTO quiz_resolution (id, correct_answers, quiz_id, user_id) VALUES (2, 5, 202, 1); -- 5/5 en Música

-- Ro resuelve quizzes de Cami
INSERT INTO quiz_resolution (id, correct_answers, quiz_id, user_id) VALUES (3, 5, 101, 2); -- 5/5 en Programación
INSERT INTO quiz_resolution (id, correct_answers, quiz_id, user_id) VALUES (4, 3, 102, 2); -- 3/5 en Películas
INSERT INTO quiz_resolution (id, correct_answers, quiz_id, user_id) VALUES (5, 5, 104, 2); -- 5/5 en Argentina

-- Ana resuelve varios
INSERT INTO quiz_resolution (id, correct_answers, quiz_id, user_id) VALUES (6, 4, 101, 4);
INSERT INTO quiz_resolution (id, correct_answers, quiz_id, user_id) VALUES (7, 5, 201, 4);
INSERT INTO quiz_resolution (id, correct_answers, quiz_id, user_id) VALUES (8, 5, 301, 4);

-- Pedro resuelve varios
INSERT INTO quiz_resolution (id, correct_answers, quiz_id, user_id) VALUES (9, 5, 401, 5); -- Su propio quiz
INSERT INTO quiz_resolution (id, correct_answers, quiz_id, user_id) VALUES (10, 4, 102, 5);
INSERT INTO quiz_resolution (id, correct_answers, quiz_id, user_id) VALUES (11, 3, 202, 5);

SELECT '✅ Interacciones creadas exitosamente' AS status;
SELECT 'Ratings' AS tipo, COUNT(*) AS total FROM quiz_rating
UNION ALL
SELECT 'Comentarios', COUNT(*) FROM comment
UNION ALL
SELECT 'Likes', COUNT(*) FROM comment_like
UNION ALL
SELECT 'Guardados', COUNT(*) FROM user_saved_quizzes
UNION ALL
SELECT 'Resoluciones', COUNT(*) FROM quiz_resolution;

