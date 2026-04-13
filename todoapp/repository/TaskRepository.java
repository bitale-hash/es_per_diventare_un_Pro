package repository;

import model.Task;      //mi serve per utilizzare la classe Task e lavorare con gli oggetti Task 
import model.Priority; //mi serve per creare oggetti Task con priorità 

import java.util.List;
import java.util.ArrayList;
import java.io.*;       //mi serve per leggere e scrivere su file


public class TaskRepository {
    
    private final String FILE_NAME = "tasks.txt";  //final serve per indicare che questa variabile non è modificabile

    public void saveTasks(List<Task> tasks) {
        
        //non serve controllare se il file esiste, perché FileWriter lo crea automaticamente se non esiste

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {   //versione migliore (chiude automaticamente il BufferedWriter alla fine del blocco try)

        for (Task task : tasks) {       //crea una variabile temporanea "task" che prende, uno alla volta, ogni elemento della lista "tasks" 
                                        //  e ripete il ciclo per ogni elemento di "tasks"


                //crea la stringa nel formato in cui la voglio salvare
            String line = task.getDescription() + ";" +
                    task.getPriority() + ";" +
                    task.isCompleted();     

            writer.write(line);     //scrive la riga "line"
            writer.newLine();       //va a capo 
            System.out.println("SALVATAGGIO FILE...");
        }
        } catch (IOException e) {
             System.out.println("Errore salvataggio: " + e.getMessage());
        }
    }
    

    public List<Task> loadTasks() {
      
        List<Task> tasks = new ArrayList<>();
        File file = new File(FILE_NAME);
            
        if (!file.exists()) {
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {   //versione migliore (chiude automaticamente il BufferedReader alla fine del blocco try)
            String line;
                    
            

            while ( (line = reader.readLine())!=null  ) {   //legge una riga alla volta fino a quando non arriva alla fine del file (null)
                String[] parts = line.split(";");    //divide la riga in parti usando ";" come separatori

                if (parts.length == 3) {     //controlla che ci siano esattamente 3 parti (descrizione, priorità, completato)
                    String description = parts[0];

                    try{ 
                    Priority priority = Priority.valueOf(parts[1]);   //converte la stringa in un oggetto Priority
                    boolean completed = Boolean.parseBoolean(parts[2]);  //converte la stringa in un booleano

                    Task task = new Task(description, priority , completed );   //crea un nuovo oggetto Task con descrizione e priorità
                    
                    tasks.add(task);  //aggiunge il task alla lista
                    } catch (IllegalArgumentException e) {
                        System.out.println("Dati non validi, riga saltata "+ line );
                    }
                }
            }
        } catch (IOException e) {
             System.out.println("Errore caricamento: " + e.getMessage());
        }

        return tasks;  //restituisce la lista di task caricati
    }
}