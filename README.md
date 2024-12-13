# Microservicio - Contract-First

## Descripción

Este microservicio ha sido desarrollado siguiendo el enfoque **Contract-First**, lo que significa que su diseño está basado en el contrato definido en el archivo **OpenAPI**. En este archivo, se incluyen ejemplos claros de los endpoints disponibles, facilitando la integración y el consumo del servicio.

### Características principales

- **Gestión de migraciones con Flyway**:  
  El microservicio utiliza **Flyway** para la gestión de las migraciones de base de datos. Esto permite que los scripts SQL necesarios para la inicialización o actualización de la base de datos se ejecuten automáticamente al iniciar el servicio.

- **Colección de Postman**:  
  Se ha adjuntado una colección de **Postman** que puedes importar para probar y explorar fácilmente los endpoints del microservicio. Challenge.postman_collection.json

---

## Cómo usar

1. **Revisar el contrato OpenAPI**:  
   Asegúrate de revisar el archivo OpenAPI para comprender los endpoints disponibles y los ejemplos proporcionados.

2. **Configuración de la base de datos**:  
   Verifica que la base de datos esté configurada correctamente antes de iniciar el servicio. Flyway ejecutará automáticamente los scripts SQL necesarios.

3. **Pruebas con Postman**:  
   Importa la colección de Postman incluida en el repositorio para realizar pruebas de los endpoints.

---

## Requisitos previos

- Java 17 o superior
- Base de datos compatible con Flyway
- Postman (opcional, para pruebas)

---

## Ejecución

1. Clona el repositorio:
   ```bash
   git clone <repositorio>
   cd <directorio-del-repositorio>
