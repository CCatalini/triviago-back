-- =============================================================================
-- SCRIPT 01: USUARIOS DE DEMO
-- Ejecutar primero para crear los usuarios base
-- =============================================================================

-- Limpiar usuarios existentes (CUIDADO en producción)
-- DELETE FROM user_following;
-- DELETE FROM user_saved_quizzes;
-- DELETE FROM quiz_rating;
-- DELETE FROM comment_like;
-- DELETE FROM comment;
-- DELETE FROM quiz_resolution;
-- DELETE FROM answer;
-- DELETE FROM question;
-- DELETE FROM label_quizzes;
-- DELETE FROM quiz;
-- DELETE FROM user;

-- =============================================================================
-- USUARIOS PRINCIPALES (para la demo)
-- =============================================================================

-- Usuario: Camila (cami@mail.com) - password: test12345
INSERT INTO user (id, first_name, last_name, email, password, birth_date, creation_date) 
VALUES (1, 'Camila', 'Catalini', 'cami@mail.com', 'test12345', '1998-03-15', CURDATE())
ON DUPLICATE KEY UPDATE first_name = VALUES(first_name);

-- Usuario: Rocío (ro@mail.com) - password: test12345
INSERT INTO user (id, first_name, last_name, email, password, birth_date, creation_date) 
VALUES (2, 'Rocío', 'García', 'ro@mail.com', 'test12345', '1999-07-22', CURDATE())
ON DUPLICATE KEY UPDATE first_name = VALUES(first_name);

-- =============================================================================
-- USUARIOS ADICIONALES
-- =============================================================================

-- Profesor Juan (profesor@edu.com) - password: prof123
INSERT INTO user (id, first_name, last_name, email, password, birth_date, creation_date) 
VALUES (3, 'Juan', 'Martínez', 'profesor@edu.com', 'prof123', '1975-02-10', CURDATE())
ON DUPLICATE KEY UPDATE first_name = VALUES(first_name);

-- Estudiante Ana (ana@estudiante.com) - password: ana123
INSERT INTO user (id, first_name, last_name, email, password, birth_date, creation_date) 
VALUES (4, 'Ana', 'López', 'ana@estudiante.com', 'ana123', '2002-09-05', CURDATE())
ON DUPLICATE KEY UPDATE first_name = VALUES(first_name);

-- Pedro Gamer (pedro@gamer.com) - password: pedro123
INSERT INTO user (id, first_name, last_name, email, password, birth_date, creation_date) 
VALUES (5, 'Pedro', 'Gamer', 'pedro@gamer.com', 'pedro123', '2000-11-20', CURDATE())
ON DUPLICATE KEY UPDATE first_name = VALUES(first_name);

-- María Ciencia (maria@ciencia.com) - password: maria123
INSERT INTO user (id, first_name, last_name, email, password, birth_date, creation_date) 
VALUES (6, 'María', 'Científica', 'maria@ciencia.com', 'maria123', '1995-04-18', CURDATE())
ON DUPLICATE KEY UPDATE first_name = VALUES(first_name);

-- Carlos Historia (carlos@historia.com) - password: carlos123
INSERT INTO user (id, first_name, last_name, email, password, birth_date, creation_date) 
VALUES (7, 'Carlos', 'Historiador', 'carlos@historia.com', 'carlos123', '1988-12-03', CURDATE())
ON DUPLICATE KEY UPDATE first_name = VALUES(first_name);

-- =============================================================================
-- RELACIONES DE SEGUIMIENTO
-- =============================================================================

-- Cami sigue a Ro y al Profesor
INSERT IGNORE INTO user_following (user_id, following_id) VALUES (1, 2);
INSERT IGNORE INTO user_following (user_id, following_id) VALUES (1, 3);

-- Ro sigue a Cami y a Ana
INSERT IGNORE INTO user_following (user_id, following_id) VALUES (2, 1);
INSERT IGNORE INTO user_following (user_id, following_id) VALUES (2, 4);

-- Ana sigue a Cami, Ro y Profesor
INSERT IGNORE INTO user_following (user_id, following_id) VALUES (4, 1);
INSERT IGNORE INTO user_following (user_id, following_id) VALUES (4, 2);
INSERT IGNORE INTO user_following (user_id, following_id) VALUES (4, 3);

-- Pedro sigue a todos
INSERT IGNORE INTO user_following (user_id, following_id) VALUES (5, 1);
INSERT IGNORE INTO user_following (user_id, following_id) VALUES (5, 2);
INSERT IGNORE INTO user_following (user_id, following_id) VALUES (5, 3);
INSERT IGNORE INTO user_following (user_id, following_id) VALUES (5, 4);

SELECT '✅ Usuarios creados exitosamente' AS status;
SELECT COUNT(*) AS total_usuarios FROM user;

