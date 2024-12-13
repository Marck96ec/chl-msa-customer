# Microservicio - Contract-First

## Descripción

Este microservicio ha sido desarrollado siguiendo el enfoque **Contract-First**, lo que significa que su diseño está basado en el contrato definido en el archivo **OpenAPI**. En este archivo, se incluyen ejemplos claros de los endpoints disponibles, facilitando la integración y el consumo del servicio.

### Características principales

- **Gestión de migraciones con Flyway**:  
  El microservicio utiliza **Flyway** para la gestión de las migraciones de base de datos. Esto permite que los scripts SQL necesarios para la inicialización o actualización de la base de datos se ejecuten automáticamente al iniciar el servicio.

- **Colección de Postman**:  
  Se ha adjuntado una colección de **Postman** que puedes importar para probar y explorar fácilmente los endpoints del microservicio.

- **Puerto de ejecución**:  
  El microservicio está configurado para ejecutarse en el puerto `8080`.

---

## Cómo usar

1. **Revisar el contrato OpenAPI**:  
   Asegúrate de revisar el archivo OpenAPI para comprender los endpoints disponibles y los ejemplos proporcionados.

2. **Configuración de la conexión a la base de datos**:  
   Configura la conexión a tu base de datos en el archivo de propiedades o variables de entorno, especificando:
   - **URL de la base de datos**
   - **Usuario**
   - **Contraseña**

   Ejemplo en `application.properties` o `application.yml`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/nombre_base_datos
   spring.datasource.username=usuario
   spring.datasource.password=contraseña
