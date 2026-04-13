package model;
//import model.Priority;

public class Task{
    private String description;
    private Priority priority;
    private boolean completed;
    
    public Task(String description, Priority priority, boolean completed) {
    this.description = description;
    this.priority = priority;
    this.completed = completed;
    }

    public Task(String description, Priority priority) {
    this.description = description;
    this.priority = priority;
    this.completed = false;
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
        
                Task : %s
                Priority: %s
                state:   %s

                """.formatted(description, priority ,completed);
      
    }
    
}