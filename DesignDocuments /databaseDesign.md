## Database Design
### Entity Relationship Diagram

```
User (AWS Cognito user data stored locally)
├── id (PK, INT AUTO_INCREMENT)
├── first_name (VARCHAR)
├── last_name (VARCHAR)
├── user_name (VARCHAR)
├── password (VARCHAR)
└── cognito_id (VARCHAR)

Location (Geographic locations for journals)
├── id (PK, INT AUTO_INCREMENT)
└── name (VARCHAR)

Journal (Journals that users write)
├── id (PK, INT AUTO_INCREMENT)
├── user_id (FK → User.id)
├── location_id (FK → Location.id)
├── title (VARCHAR)
├── content (TEXT) 
├── weather (VARCHAR)
├── created_at (TIMESTAMP)
└── updated_at (TIMESTAMP)