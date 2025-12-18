-- =============================================================================
-- SCRIPT 02: ETIQUETAS/LABELS
-- Ejecutar después de 01_users.sql
-- =============================================================================

-- Etiquetas de categorías principales
INSERT INTO label (id, value) VALUES (1, 'Ciencia') ON DUPLICATE KEY UPDATE value = VALUES(value);
INSERT INTO label (id, value) VALUES (2, 'Historia') ON DUPLICATE KEY UPDATE value = VALUES(value);
INSERT INTO label (id, value) VALUES (3, 'Tecnología') ON DUPLICATE KEY UPDATE value = VALUES(value);
INSERT INTO label (id, value) VALUES (4, 'Educación') ON DUPLICATE KEY UPDATE value = VALUES(value);
INSERT INTO label (id, value) VALUES (5, 'General') ON DUPLICATE KEY UPDATE value = VALUES(value);
INSERT INTO label (id, value) VALUES (6, 'Matemáticas') ON DUPLICATE KEY UPDATE value = VALUES(value);
INSERT INTO label (id, value) VALUES (7, 'Geografía') ON DUPLICATE KEY UPDATE value = VALUES(value);
INSERT INTO label (id, value) VALUES (8, 'Arte') ON DUPLICATE KEY UPDATE value = VALUES(value);
INSERT INTO label (id, value) VALUES (9, 'Música') ON DUPLICATE KEY UPDATE value = VALUES(value);
INSERT INTO label (id, value) VALUES (10, 'Deportes') ON DUPLICATE KEY UPDATE value = VALUES(value);
INSERT INTO label (id, value) VALUES (11, 'Literatura') ON DUPLICATE KEY UPDATE value = VALUES(value);
INSERT INTO label (id, value) VALUES (12, 'Películas') ON DUPLICATE KEY UPDATE value = VALUES(value);
INSERT INTO label (id, value) VALUES (13, 'Videojuegos') ON DUPLICATE KEY UPDATE value = VALUES(value);
INSERT INTO label (id, value) VALUES (14, 'Programación') ON DUPLICATE KEY UPDATE value = VALUES(value);
INSERT INTO label (id, value) VALUES (15, 'Idiomas') ON DUPLICATE KEY UPDATE value = VALUES(value);

SELECT '✅ Etiquetas creadas exitosamente' AS status;
SELECT COUNT(*) AS total_etiquetas FROM label;

