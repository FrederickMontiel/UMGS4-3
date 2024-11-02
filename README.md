# Recomendaciones
Antes de correr el proyecto porfavor obtener la versión mas reciente con el comando `git pull origin main` en la carpeta donde descomprimar el proyecto.

# Proyecto de Calificaciones

Este proyecto es una aplicación de gestión de calificaciones que permite manejar información sobre alumnos, grados y materias. La aplicación se conecta a una base de datos PostgreSQL para almacenar y recuperar datos.

## Estructura del Proyecto

La estructura del proyecto es la siguiente:

- **models**: Contiene la lógica de acceso a datos y modelos de negocio.
- **entities**: Define las entidades del sistema, como `Alumno`, `Grado`, y `Materia`.
- **bin**: Contiene las clases compiladas.
- **parsers**: Encargado de convertir los datos de la base de datos a objetos.

### Descripción de Clases

- **ConnectionPgSql**: Esta clase maneja la conexión a la base de datos PostgreSQL utilizando el patrón de diseño Singleton para asegurar que solo exista una conexión por sesión.

- **Alumno**: Esta entidad representa un alumno con los campos `cui`, `nombres` y `apellidos`.

- **Grado**: Esta entidad representa un grado académico con los campos `id`, `nombre`, `seccion` y `anio`.

- **Materia**: Esta entidad representa una materia con los campos `id` y `nombre`.

- **MateriasModel**: Esta clase maneja las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para las materias en la base de datos.

- **CallbackInterface**: Esta interfaz define un método de callback que recibe un objeto `JSONObject`.

### Dependencias

- Java 8 o superior.
- PostgreSQL JDBC Driver.

### Configuración de la Base de Datos

1. Asegúrate de tener PostgreSQL instalado y en ejecución.
2. Crea una base de datos llamada `Calificaciones`.
3. Ejecuta el siguiente script SQL para crear la tabla `Materias`:

```sql
    drop database if exists calificaciones;
    create database calificaciones;
    use calificaciones;
    CREATE TABLE Alumnos (
        cui BIGINT PRIMARY KEY,
        nombres VARCHAR(50),
        apellidos VARCHAR(50)
    );
    
    CREATE TABLE Grados (
        id SERIAL PRIMARY KEY,
        nombre VARCHAR(200) NOT NULL,
        seccion CHAR(1) DEFAULT 'A',
        anio INT
    );
    
    CREATE TABLE Materias (
        id SERIAL PRIMARY KEY,
        nombre VARCHAR(200) NOT NULL
    );
    
    CREATE TABLE AsignacionAlumno (
        id SERIAL PRIMARY KEY,
        cui BIGINT NOT NULL,
        grado INT NOT NULL,
        materia INT NOT NULL,
        FOREIGN KEY (cui) REFERENCES Alumnos(cui),
        FOREIGN KEY (grado) REFERENCES Grados(id),
        FOREIGN KEY (materia) REFERENCES Materias(id)
    );
    
    CREATE TABLE Notas (
        id SERIAL PRIMARY KEY,
        unidad INT NOT NULL,
        nota FLOAT NOT NULL,
        asignacion INT NOT NULL,
        FOREIGN KEY (asignacion) REFERENCES AsignacionAlumno(id)
    );
    
    DELIMITER //
    CREATE TRIGGER set_anio_default
    BEFORE INSERT ON Grados
    FOR EACH ROW
    BEGIN
        IF NEW.anio IS NULL THEN
            SET NEW.anio = YEAR(NOW());
        END IF;
    END; //
    DELIMITER ;
    
    -- Insertar Alumnos
    INSERT INTO Alumnos (cui, nombres, apellidos) VALUES
    (1234567890123, 'Juan', 'Perez'),
    (9876543210123, 'Maria', 'Lopez'),
    (4567891230123, 'Carlos', 'Sanchez');
    
    -- Insertar Grados
    INSERT INTO Grados (nombre, seccion, anio) VALUES
    ('Primero Primaria', 'A', NULL),
    ('Segundo Primaria', 'B', NULL),
    ('Tercero Primaria', 'A', NULL);
    
    -- Insertar Materias
    INSERT INTO Materias (nombre) VALUES
    ('Matemáticas'),
    ('Ciencias Naturales'),
    ('Lenguaje'),
    ('Estudios Sociales');
    
    -- Insertar AsignacionAlumno
    INSERT INTO AsignacionAlumno (cui, grado, materia) VALUES
    (1234567890123, 1, 1), -- Juan - Primero Primaria - Matemáticas
    (9876543210123, 2, 2), -- Maria - Segundo Primaria - Ciencias Naturales
    (4567891230123, 3, 3), -- Carlos - Tercero Primaria - Lenguaje
    (1234567890123, 1, 3), -- Juan - Primero Primaria - Lenguaje
    (9876543210123, 2, 4); -- Maria - Segundo Primaria - Estudios Sociales
    
    -- Insertar Notas
    INSERT INTO Notas (unidad, nota, asignacion) VALUES
    (1, 85.5, 1), -- Nota de Juan en Matemáticas (Unidad 1)
    (2, 90.0, 1), -- Nota de Juan en Matemáticas (Unidad 2)
    (1, 78.0, 2), -- Nota de Maria en Ciencias Naturales (Unidad 1)
    (1, 92.0, 3), -- Nota de Carlos en Lenguaje (Unidad 1)
    (1, 88.0, 4), -- Nota de Juan en Lenguaje (Unidad 1)
    (1, 91.0, 5); -- Nota de Maria en Estudios Sociales (Unidad 1)
    
    -- Seleccionar todos los alumnos
    SELECT * FROM Alumnos;
    
    -- Seleccionar todos los grados
    SELECT * FROM Grados;
    
    -- Seleccionar todas las materias
    SELECT * FROM Materias;
    
    -- Seleccionar todas las asignaciones de alumnos
    SELECT * FROM AsignacionAlumno;
    
    -- Seleccionar todas las notas
    SELECT * FROM Notas;
```

### Cómo Ejecutar el Proyecto

1. Clona el repositorio en tu máquina local:

```bash
git clone https://github.com/tu_usuario/calificaciones.git
```
2. Accede a la carpeta donde se clonó el proyecto
```bash
cd calificaciones
```
3. Compilar la solución
```bash
javac -d bin src/com/fmontiel/calificaciones/*.java
```
4. Ejecutar la solución.
```bash
java -cp bin com.fmontiel.calificaciones.Main
```
