## Database Design
### Entity Relationship Diagram

```
User (AWS Cognito user data stored locally)
├── cognito_id (PK, VARCHAR)
├── email (VARCHAR)
├── username (VARCHAR)


Journal (Journals that users write)
├── id (PK, INT AUTO_INCREMENT)
├── user_id (FK → User.cognito_id)
├── title (VARCHAR) - ex) "My Rainy Day"
├── content (text) - ex) "My first day in Paris was great."
├── created_at (timestamp) - ex) "2026-02-05 00:25:57"
├── updated_at (timestamp) - ex) "2026-02-05 00:25:57"
├── location (VARCHAR) - ex) "Paris"
├── weather (VARCHAR) - ex) "Cold"
