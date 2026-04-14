📌 Descrizione

ExpenseTracker è un'applicazione console sviluppata in Java che permette di gestire le proprie spese personali.

Ogni spesa contiene:

una descrizione
un importo
una categoria (FOOD, TRANSPORT, ENTERTAINMENT, BILLS, SHOPPING, HEALTH, OTHER)
una data

Il progetto è stato realizzato per esercitarmi con la programmazione orientata agli oggetti, la gestione delle collezioni e la manipolazione dei dati in Java.

▶️ Come eseguire il progetto
Aprire il progetto in un IDE
Eseguire il file Main.java
Utilizzare la console per interagire con il programma


🚀 Funzionalità
Aggiungere nuove spese
Rimuovere spese tramite indice
Visualizzare tutte le spese
Calcolare il totale delle spese
Filtrare le spese per categoria
Visualizzare il totale per categoria
(Opzionale) Gestione delle spese per data
🧱 Struttura del progetto
expense-tracker/
│
├── expenses.txt                 → file di salvataggio dati
│
├── model/
│   ├── Expense.java             → modello della spesa
│   └── Category.java            → enum delle categorie
│
├── repository/
│   └── ExpenseRepository.java   → gestione salvataggio/caricamento
│
├── service/
│   └── ExpenseService.java     → logica dell'app (calcoli e gestione dati)
│
├── ui/
│   └── ConsoleUI.java          → interfaccia utente
│
└── Main.java                   → punto di ingresso del programma


⚙️ Tecnologie utilizzate
Java
Programmazione Orientata agli Oggetti (OOP)
ArrayList (collezioni)
LocalDate (gestione date)
Logica di aggregazione dati (somma, filtri)
📚 Obiettivi di apprendimento

Questo progetto è stato sviluppato per esercitarmi con:

classi e oggetti
incapsulamento
gestione delle collezioni (ArrayList)
separazione della logica in livelli (model / service / repository / ui)
manipolazione di numeri e date
filtri e calcoli su liste di dati