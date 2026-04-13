import repository.TaskRepository;
import service.TaskService;
import ui.ConsoleUI;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        TaskRepository repository = new TaskRepository();
                
        TaskService service = new TaskService(
                repository.loadTasks(),
                repository
        );

        ConsoleUI ui = new ConsoleUI(service);

        ui.start();

        repository.saveTasks(service.getTasks());
    }
}