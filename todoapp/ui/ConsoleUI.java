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
            System.out.println("\n=== TODO APP ===");
            System.out.println("1. Aggiungi task");
            System.out.println("2. Mostra task");
            System.out.println("3. Completa task");
            System.out.println("4. Rimuovi task");
            System.out.println("5. Cerca task");
            System.out.println("0. Esci");
            System.out.print("Scelta: ");

            choice = scanner.nextInt();
            scanner.nextLine();

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

    private void addTask() {
        System.out.print("Descrizione: ");
        String desc = scanner.nextLine();

        System.out.print("Priorità (LOW, MEDIUM, HIGH): ");
        Priority priority = Priority.valueOf(scanner.nextLine().toUpperCase());

        service.addTask(desc, priority, false);
    }

    private void completeTask() {
        System.out.print("Indice: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        service.markAsCompleted(index);
    }

    private void removeTask() {
        System.out.print("Indice: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        service.removeTask(index);
    }

    private void searchTask() {
        System.out.print("Keyword: ");
        String keyword = scanner.nextLine();

        service.searchTasks(keyword)
                .forEach(System.out::println);
    }
}