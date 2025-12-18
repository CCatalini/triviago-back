# 📦 Scripts SQL de Demo - Triviago

Scripts para cargar datos de demostración en la base de datos de Triviago.

## 🚀 Cómo usar

### Opción 1: Ejecutar desde MySQL Workbench o terminal

```bash
mysql -u root -p triviago < 01_users.sql
mysql -u root -p triviago < 02_labels.sql
mysql -u root -p triviago < 03_quizzes_cami.sql
mysql -u root -p triviago < 04_quizzes_ro.sql
mysql -u root -p triviago < 05_quizzes_otros.sql
mysql -u root -p triviago < 06_interactions.sql
```

### Opción 2: Desde MySQL Workbench
1. Abrir cada archivo en orden
2. Ejecutar con `Ctrl+Shift+Enter`

## 📁 Archivos

| Archivo | Descripción |
|---------|-------------|
| `01_users.sql` | Usuarios y relaciones de seguimiento |
| `02_labels.sql` | Etiquetas/categorías de quizzes |
| `03_quizzes_cami.sql` | Quizzes de Camila (cami@mail.com) |
| `04_quizzes_ro.sql` | Quizzes de Rocío (ro@mail.com) |
| `05_quizzes_otros.sql` | Quizzes de otros usuarios |
| `06_interactions.sql` | Comentarios, ratings, likes, favoritos |

## 👥 Usuarios de Demo

| Email | Password | Nombre | Rol |
|-------|----------|--------|-----|
| `cami@mail.com` | `test12345` | Camila Catalini | Usuario principal |
| `ro@mail.com` | `test12345` | Rocío García | Usuario principal |
| `profesor@edu.com` | `prof123` | Juan Martínez | Profesor |
| `ana@estudiante.com` | `ana123` | Ana López | Estudiante |
| `pedro@gamer.com` | `pedro123` | Pedro Gamer | Gamer |
| `maria@ciencia.com` | `maria123` | María Científica | Científica |
| `carlos@historia.com` | `carlos123` | Carlos Historiador | Historiador |

## 🔒 Quizzes Privados (Códigos de Invitación)

| Quiz | Código | Creador |
|------|--------|---------|
| Examen Final de Matemáticas | `123456` | cami@mail.com |
| Reto de Geografía Mundial | `789012` | ro@mail.com |
| Física para Principiantes | `456789` | maria@ciencia.com |

## 📊 Datos incluidos

- **7 usuarios** con relaciones de seguimiento
- **15 etiquetas** de categorías
- **13 quizzes** (10 públicos, 3 privados)
- **65+ preguntas** con 4 opciones cada una
- **Comentarios y respuestas** entre usuarios
- **Ratings** de quizzes
- **Quizzes guardados** como favoritos
- **Resoluciones** de quizzes

## 🎭 Escenario de Demo

**Historia:**
- Camila y Rocío son amigas que usan Triviago para estudiar
- Se siguen mutuamente y comentan en los quizzes del otro
- Han creado quizzes públicos para compartir conocimiento
- También tienen quizzes privados para sus grupos de estudio
- Otros usuarios (profesor, estudiantes) también participan

**Interacciones destacadas:**
- Cami y Ro se han dado likes mutuos en comentarios
- Ambas han guardado quizzes del otro como favoritos
- Han calificado los quizzes entre ellas con 4-5 estrellas
- Mantienen conversaciones en los comentarios

