## Estado Actual del Proyecto

![Logo del proyecto](assets/Animation.gif)

---

## Tecnologías, Frameworks y APIs

- **Lenguajes:** Java, JavaScript
- **Frameworks:** Spring Boot, Thymeleaf
- **Bases de Datos:** MySQL
- **Estilos y Diseño:** TailwindCSS, PostCSS
- **Otras herramientas:** Lombok, REST APIs

---

## Recursos Adicionales

- **Control de versiones:** Git, GitHub
- **Gestor de dependencias:** Maven
- **Testing y pruebas:** Postman

---

### Configuración de TailwindCSS:

1. **Inicializar el proyecto (si no existe un `package.json`)**:

   ```bash
   npm init -y
   ```

2. **Instalar las dependencias de TailwindCSS, PostCSS y Autoprefixer**:

   ```bash
   npm install tailwindcss postcss autoprefixer
   ```

3. **Generar los archivos de configuración de Tailwind**:

   ```bash
   npx tailwindcss init
   ```

4. **Instalar PostCSS CLI**:

   ```bash
   npm install postcss-cli
   ```

### Compilación de TailwindCSS:

1. **Generar `output.css` (Tener Node.js instalado)**:

   ```bash
   npm run watch
   ```

### Script `package.json` para simplificar:

```json
"scripts": {
  "build": "npx tailwindcss -i ./src/styles.css -o ./dist/output.css",
  "watch": "npx tailwindcss -i ./src/styles.css -o ./dist/output.css --watch"
}
```

### Ejecución del Proyecto:

```bash
mvn spring-boot:run
```
