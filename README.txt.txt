VideoGameApp

Benvenuto! Questa è la mia applicazione per la gestione di videogiochi, preferiti e wishlist.
Il progetto è composto da:
- Frontend in Angular
- Backend in Spring Boot
- Database Postgres

---

Come far partire il progetto

REQUISITI:
- Java 17 o superiore
- Node.js 16 o superiore
- PostgreSQL installato e configurato

1) Clonare il progetto:
- Apri il terminale nella cartella dove vuoi copiare il progetto e lancia i comandi:
  git clone https://github.com/tuo-username/ProgettoVideoGame.git
  cd ProgettoVideoGame

2) Ripristinare il database:
- Crea un database vuoto chiamato videoGame_db (puoi farlo con pgAdmin o terminale).
- Da terminale, nella cartella del progetto, esegui:
  psql -U postgres -d videoGame_db -f videoGame_db_dump.sql
- Inserisci la password di Postgres quando richiesta.

3) Avviare il backend:
- Vai nella cartella videoGameBackend ed esegui:
  cd videoGameBackend
  mvn spring-boot:run
- Il backend sarà disponibile su http://localhost:8080

4) Avviare il frontend:
- Apri un altro terminale, vai nella cartella videoGameFrontend ed esegui:
  cd videoGameFrontend
  npm install
  ng serve
- Il frontend sarà disponibile su http://localhost:4200

---

Login di prova:
- Puoi registrarti direttamente dall’interfaccia oppure usare le credenziali che creerai.

---

Info sul progetto:
- Framework usati:
  - Angular per il frontend
  - Spring Boot + Spring Security per il backend
  - Bootstrap + CSS per il layout responsive
- Funzionalità:
  - Registrazione e login con JWT
  - Aggiunta e rimozione di giochi nei preferiti e nella wishlist
  - Ricerca giochi in tempo reale
  - Trailer dei giochi integrati tramite YouTube