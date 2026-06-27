# PostgreSQL Setup

The app now has a PostgreSQL profile in `src/main/resources/application-postgres.yml` and a VS Code task named `Run App with PostgreSQL`.

## What you need locally

- PostgreSQL installed on Windows
- A database named `festival_copilot`
- A user named `festival_user`
- Password `festival_password`

## Run order

1. Start PostgreSQL on your machine.
2. Make sure the database and user above exist.
3. Run the VS Code task `Run App with PostgreSQL`, or use:

   ```powershell
   .\mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=postgres
   ```

## Connection details

- Host: `localhost`
- Port: `5432`
- Database: `festival_copilot`
- Username: `festival_user`
- Password: `festival_password`