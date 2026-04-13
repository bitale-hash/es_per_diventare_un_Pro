package service;

import java.util.ArrayList;
import java.util.List;

import model.Priority;  //per assengnare la priorità al task
import model.Task;      //per creare una lista di task 
import repository.TaskRepository;

public class TaskService {

    private List<Task> tasks = new ArrayList<>();
    private TaskRepository repository;

    public TaskService(List<Task> tasks, TaskRepository repository) {
    this.tasks = tasks;
    this.repository = repository;
}

    public void addTask(String description, Priority priority) {
    Task task = new Task(description, priority);
    tasks.add(task);
    }

    public void printTasks() {
        if (tasks.isEmpty()) {
        System.out.println("Nessun task presente.");
        return;
        }

        for (Task task : tasks) {
            System.out.println(task);
            System.out.println("-------------------");
        }

    }   

    public void markAsCompleted(int index) {
    if (index < 0 || index >= tasks.size()) {
        System.out.println("Indice non valido");
        return;
    }

    tasks.get(index).setCompleted(true);
    }
    
    public void removeTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            System.out.println("Indice non valido");
            return;
        }   
    Task removed = tasks.remove(index);
    System.out.println("Rimosso: " + removed);
    }

    public List<Task> searchTasks(String keyword){
        if (keyword == null || keyword.isBlank()) 
             return new ArrayList<>();
        

        List<Task> result= new ArrayList<>();
        for(Task task: tasks){
            if(task.getDescription().toLowerCase().contains(keyword.toLowerCase())){
                result.add(task);
            }       
        }
        return result; 
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
