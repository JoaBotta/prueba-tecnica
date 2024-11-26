# Prueba Tecnica Monitoreo de plantas

Este repositorio contiene el código fuente del Proyecto Prueba Tecnica.

##  Contenidos Front end
- Registro de usuario
- Registro del login
- Dashboard
- Editar y visualizar perfil en settings en el menu
- Registrar planta con api pais
- Calgulos de lecturas y alertas
  
##  Contenidos Back end
 Autenticación de Usuario:
  - POST /auth/login: Endpoint para la autenticación de usuarios con 
  verificación de credenciales y generación de token JWT.
  - POST /auth/register: Endpoint para registrar nuevos usuarios con 
  validación de datos y cifrado de contraseñas.
2. Obtención de Datos Dinámicos:
 - GET /api/planta/lecturas-ok: Obtención de las lecturas de sensores.
  - GET /api/planta/alertas-medias: Obtención de alertas medias.
  - GET /api/planta/alertas-rojas: Obtención de alertas rojas.
 - GET /api/planta/sensores/deshabilitados: Obtención de sensores 
  deshabilitados.
  - GET /api/planta: Obtención de información de plantas y sus datos 
  asociados.
3. Gestión de Plantas:
  - POST /api/planta: Endpoint para crear nuevas plantas.
  - PUT /api/planta/{id}: Endpoint para editar una planta existente.
  - DELETE /api/planta/{id}: Endpoint para eliminar una planta.
4. Gestion de Usuario: 
  -  GET y PUT de usuario



## Tabla de Contenidos

- [Versiones](#versiones-de-tecnologías)
- [Pre-requisitos](#pre-requisitos-instalados)
  - [Java 17](#instalación-de-java-21)
  - [Angular](#instalación-de-angular)
  - [PostgreSQL](#instalación-de-postgresql)
  - [Visual Studio Code](#instalación-visual-studio-code)



## Versiones de tecnologías

Para este proyecto trabajaremos con las siguientes versiones:
- Angular 18
- Node.js 20
- Spring Boot 3.3
- Java 17
- PostgreSQL 15



