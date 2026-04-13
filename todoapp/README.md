# TodoApp - Java Console Project

## 📌 Descrizione
TodoApp è un'applicazione console sviluppata in Java che permette di gestire una lista di task.

Ogni task contiene:
- una descrizione
- una priorità (LOW, MEDIUM, HIGH)
- uno stato di completamento

Il progetto è stato realizzato per esercitarmi con la programmazione orientata agli oggetti e la gestione delle collezioni in Java.

---

## 🚀 Funzionalità
- Aggiungere nuovi task
- Rimuovere task tramite indice
- Visualizzare tutti i task
- Segnare un task come completato
- Cercare task tramite parola chiave

---

## 🧱 Struttura del progetto

todoapp/
│
├── tasks.txt               → file di salvataggio dati
│
├── model/
│   ├── Task.java           → modello del task
│   └── Priority.java       → enum delle priorità
│
├── repository/
│   └── TaskRepository.java → gestione salvataggio/caricamento
│
├── service/
│   └── TaskService.java    → logica dell'app (gestione task)
│
├── ui/
│   └── ConsoleUI.java      → interfaccia utente
│
└── Main.java               → punto di ingresso del programma

---

## ⚙️ Tecnologie utilizzate
- Java
- Programmazione Orientata agli Oggetti (OOP)
- ArrayList (collezioni)
- Stream base di logica applicativa

---

## ▶️ Come eseguire il progetto
1. Aprire il progetto in un IDE 
2. Eseguire il file `Main.java`
3. Utilizzare la console per interagire con il programma

---

## 📚 Obiettivi di apprendimento
Questo progetto è stato sviluppato per esercitarmi con:
- classi e oggetti
- incapsulamento
- gestione delle liste (ArrayList)
- separazione della logica in livelli (model / service)
- metodi e organizzazione del codice

---

## 💡 Possibili miglioramenti futuri
- Salvataggio dei task su file
- Caricamento automatico all’avvio
- Aggiunta di scadenze (Date)
- Interfaccia grafica
- Persistenza su database