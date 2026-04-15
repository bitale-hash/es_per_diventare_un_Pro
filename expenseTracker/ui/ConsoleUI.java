package es_per_diventare_un_Pro.expenseTracker.ui;

import es_per_diventare_un_Pro.expenseTracker.model.Category;
import es_per_diventare_un_Pro.expenseTracker.model.Expense;
import es_per_diventare_un_Pro.expenseTracker.service.ExpenseService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {

    private final ExpenseService service;
    private final Scanner scanner;

    public ConsoleUI(ExpenseService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Scegli un'opzione: ");
            String input = scanner.nextLine().trim();
            switch (input) {
                case "1" -> addExpense();
                case "2" -> showAllExpenses();
                case "3" -> removeExpense();
                case "4" -> showTotalExpenses();
                case "5" -> showTotalByCategory();
                case "6" -> showMonthlyTotal();
                case "7" -> showMaxExpense();
                case "8" -> showAverageExpense();
                case "9" -> showLast7DaysTotal();
                case "10" -> showExpensesSortedByDate();
                case "11" -> showExpensesSortedByAmount();
                case "0" -> {
                    System.out.println("Arrivederci!");
                    running = false;
                }
                default -> System.out.println("Opzione non valida. Riprova.");
            }
            System.out.println();
        }
    }

    private void printMenu() {
        System.out.println("===== GESTIONE SPESE =====");
        System.out.println("1 - Aggiungi spesa");
        System.out.println("2 - Mostra tutte le spese");
        System.out.println("3 - Rimuovi spesa");
        System.out.println("4 - Totale spese");
        System.out.println("5 - Totale per categoria");
        System.out.println("6 - Totale mensile");
        System.out.println("7 - Spesa massima");
        System.out.println("8 - Spesa media");
        System.out.println("9 - Totale ultime 7 giorni");
        System.out.println("10 - Mostra spese ordinate per data");
        System.out.println("11 - Mostra spese ordinate per importo");
        System.out.println("0 - Esci");
    }

    private void addExpense() {
        try {
            System.out.print("Descrizione: ");
            String description = scanner.nextLine();
            System.out.print("Importo: ");
            BigDecimal amount = new BigDecimal(scanner.nextLine());
            System.out.print("Categoria (es. FOOD, TRAVEL, ENTERTAINMENT): ");
            Category category = Category.valueOf(scanner.nextLine().toUpperCase());
            System.out.print("Data (YYYY-MM-DD): ");
            LocalDate date = LocalDate.parse(scanner.nextLine());
            System.out.print("Nota (facoltativa): ");
            String note = scanner.nextLine();

            service.addExpense(description, amount, category, date, note);
            System.out.println("Spesa aggiunta!");
        } catch (Exception e) {
            System.out.println("Errore durante l'aggiunta della spesa: " + e.getMessage());
        }
    }

    private void showAllExpenses() {
        List<Expense> expenses = service.getExpenses();
        if (expenses.isEmpty()) {
            System.out.println("Nessuna spesa registrata.");
        } else {
            for (int i = 0; i < expenses.size(); i++) {
                System.out.println(i + " - " + expenses.get(i));
            }
        }
    }

    private void removeExpense() {
        try {
            System.out.print("Indice spesa da rimuovere: ");
            int index = Integer.parseInt(scanner.nextLine());
            service.removeExpense(index);
            System.out.println("Spesa rimossa!");
        } catch (Exception e) {
            System.out.println("Errore durante la rimozione: " + e.getMessage());
        }
    }

    private void showTotalExpenses() {
        System.out.println("Totale spese: " + service.getTotalExpenses());
    }

    private void showTotalByCategory() {
        try {
            System.out.print("Categoria: ");
            Category category = Category.valueOf(scanner.nextLine().toUpperCase());
            System.out.println("Totale per " + category + ": " + service.getTotalByCategory(category));
        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    private void showMonthlyTotal() {
        try {
            System.out.print("Mese (1-12): ");
            int month = Integer.parseInt(scanner.nextLine());
            System.out.print("Anno: ");
            int year = Integer.parseInt(scanner.nextLine());
            System.out.println("Totale mese: " + service.getMonthlyTotal(month, year));
        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    private void showMaxExpense() {
        try {
            System.out.println("Spesa massima: " + service.getMaxExpense());
        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    private void showAverageExpense() {
        System.out.println("Spesa media: " + service.getAverageExpense());
    }

    private void showLast7DaysTotal() {
        System.out.println("Totale ultime 7 giorni: " + service.getLast7DaysTotal());
    }

    private void showExpensesSortedByDate() {
        List<Expense> sorted = service.getExpensesSortedByDate();
        sorted.forEach(System.out::println);
    }

    private void showExpensesSortedByAmount() {
        List<Expense> sorted = service.getExpensesSortedByAmountDesc();
        sorted.forEach(System.out::println);
    }
}