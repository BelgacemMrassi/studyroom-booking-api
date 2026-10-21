# StudyRoom Booking API

Eine REST-API zur Verwaltung und Buchung von Lernräumen, gebaut mit **Spring Boot 3.3**, **Spring Data JPA** und einer **H2 In-Memory-Datenbank**.

## Tech-Stack

| Komponente | Version / Details |
|------------|-------------------|
| Java | 21 |
| Spring Boot | 3.3.5 (Web, Data JPA) |
| Datenbank | H2 (In-Memory) |
| Lombok | Boilerplate-Reduktion |
| springdoc-openapi | 2.6.0 (Swagger UI) |
| Build | Maven (Wrapper enthalten) |

## Starten

Voraussetzung: JDK 21 und eine gesetzte `JAVA_HOME`-Variable.

```bash
# Windows
.\mvnw.cmd spring-boot:run

# Linux / macOS
./mvnw spring-boot:run
```

Die Anwendung läuft danach auf **http://localhost:8081**.

## Nützliche URLs

| Was | URL |
|-----|-----|
| Swagger UI | http://localhost:8081/swagger-ui.html |
| OpenAPI JSON | http://localhost:8081/v3/api-docs |
| H2-Konsole | http://localhost:8081/h2-console |

H2-Login: JDBC-URL `jdbc:h2:mem:roombookingdb`, User `sa`, Passwort leer.

## Datenmodell `StudyRoom`

| Feld | Typ | Beschreibung |
|------|-----|--------------|
| `id` | Long | Wird automatisch vergeben |
| `roomName` | String | Name des Raums |
| `capacity` | int | Anzahl Plätze |
| `hasWhiteboard` | boolean | Whiteboard vorhanden? |
| `booked` | boolean | Gebucht? (neue Räume sind immer ungebucht) |

## Endpunkte

| Methode | Pfad | Beschreibung |
|---------|------|--------------|
| `GET` | `/api/rooms` | Alle Räume auflisten |
| `GET` | `/api/rooms/available` | Nur freie Räume auflisten |
| `POST` | `/api/rooms` | Neuen Raum anlegen |
| `PUT` | `/api/rooms/{id}/book` | Raum buchen (404, falls nicht vorhanden) |

### Beispiele

```bash
# Raum anlegen
curl -X POST http://localhost:8081/api/rooms \
  -H "Content-Type: application/json" \
  -d '{"roomName":"Raum 101","capacity":6,"hasWhiteboard":true}'

# Alle Räume
curl http://localhost:8081/api/rooms

# Freie Räume
curl http://localhost:8081/api/rooms/available

# Raum mit ID 1 buchen
curl -X PUT http://localhost:8081/api/rooms/1/book
```

> **Hinweis:** Da H2 im Speicher läuft, gehen alle Daten beim Neustart verloren.

## Tests

```bash
.\mvnw.cmd test
```
