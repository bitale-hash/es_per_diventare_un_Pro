package ui;

import model.Priority;
//import repository.TaskRepository;
import service.TaskService;

import java.util.Scanner;

public class ConsoleUI {

    private final TaskService service;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleUI(TaskService service) {
        this.service = service;
    }

    public void start() {

        int choice;

        do {
            printMenu();

            choice = readInt();

            switch (choice) {

                case 1 -> addTask();
                case 2 -> service.printTasks();
                case 3 -> completeTask();
                case 4 -> removeTask();
                case 5 -> searchTask();
                case 0 -> System.out.println("Uscita...");
                default -> System.out.println("Scelta non valida");
            }

        } while (choice != 0);
    }

    private void printMenu() {
    System.out.println("\n=== TODO APP ===");
    System.out.println("1. Aggiungi task");
    System.out.println("2. Mostra task");
    System.out.println("3. Completa task");
    System.out.println("4. Rimuovi task");
    System.out.println("5. Cerca task");
    System.out.println("0. Esci");
    }

    private void addTask() {
        String desc = readLine("Descrizione: ");
        Priority priority = readPriority();
        service.addTask(desc, priority, false);
    }

    private void completeTask() {
        
        int index = readInt();
        boolean success = service.markAsCompleted(index);

        if (success) 
            System.out.println("✔ Task completato");
        else 
            System.out.println("✘ Indice non valido");
    
        
    }

    private void removeTask() {
        
        int index = readInt();
        boolean success = service.removeTask(index);

        if (success) 
            System.out.println("✔ Task rimosso");
        else 
            System.out.println("✘ Indice non valido");
    
    }

    private void searchTask() {

        String keyword = readLine("Keyword: ");
        service.searchTasks(keyword)
                .forEach(System.out::println);
    }

    private int readInt() {
    while (true) {
        try {
            System.out.print("Scelta: ");
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Inserisci un numero valido.");
        }
    }
}

    private Priority readPriority() {
        while (true) {
            System.out.print("Priorità (LOW, MEDIUM, HIGH): ");
            String input = scanner.nextLine().trim().toUpperCase();

            try {
                return Priority.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Valore non valido, riprova.");
            }
        }
    }

    private String readLine(String label) {
    System.out.print(label);
    return scanner.nextLine().trim();
}
}