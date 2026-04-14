package service;

import java.util.ArrayList;
import java.util.List;

import model.Priority;  //per assengnare la priorità al task
import model.Task;      //per creare una lista di task 
import repository.TaskRepository;

public class TaskService {

    private List<Task> tasks;
    private TaskRepository repository;
        
        //COSTRUTTORE
    public TaskService(List<Task> tasks, TaskRepository repository) {
    this.tasks = tasks;
    this.repository = repository;
}
        
    public void addTask(String description, Priority priority, boolean completed) {
    Task task = new Task(description, priority, completed);
    tasks.add(task);
    repository.saveTasks(tasks);
    }
    
    
    public void printTasks() {
        if (tasks.isEmpty()) {
        System.out.println("Nessun task presente.");
        return;
        }

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + ": " + tasks.get(i));
            System.out.println("-------------------");
        }

    }   

    public boolean markAsCompleted(int index) {

        if (index < 0 || index >= tasks.size()) {
            return false;
        }

        tasks.get(index).setCompleted(true);
        repository.saveTasks(tasks);
        return true;
    }

        //DOVREBBE ESSERE RISTRUTTURATO PER POTER RIMUOVERE UN TASK IN ALTRI MODI, NON SOLO PER INDICE
    public boolean removeTask(int index) {

        if (index < 0 || index >= tasks.size()) {
            return false;
        }

        tasks.remove(index);
        repository.saveTasks(tasks);
        return true;
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
