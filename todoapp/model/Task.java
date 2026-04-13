package model;
import java.util.UUID;
//import model.Priority;

public class Task{
    private String description;
    private Priority priority;
    private boolean completed;
    private String id;  //non lo utilizzo per ora, ma potrebbe essere utile in futuro per implementare nuove funzionalità 
    
    public Task(String description, Priority priority, boolean completed) {
    this.id= UUID.randomUUID().toString();  //genera un id univoco per ogni task
    this(description, priority, completed);    /*this.description = description;
                                                this.priority = priority;
                                                this.completed = completed;*/
    
    }

        //GET
    public String getDescription(){
        return description;
    }
    public Priority getPriority(){
        return priority;
    }
    public boolean isCompleted(){
        return completed;
    }
    public String getId(){
        return id;
    }
        //SET
    public void setDescription(String d){
        this.description=d;
    }
    public void setPriority(Priority p){
        this.priority=p;
    }
    public void setCompleted(boolean b){
        this.completed=b;
    }
    
    @Override
    public String toString(){
        return """
                id: %s
                Task : %s
                Priority: %s
                state:   %s

                """.formatted(id,description, priority ,completed);
      
    }
    
}