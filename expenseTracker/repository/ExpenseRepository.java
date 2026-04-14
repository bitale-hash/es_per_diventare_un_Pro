package es_per_diventare_un_Pro.expenseTracker.repository;

import es_per_diventare_un_Pro.expenseTracker.model.Expense;      
import es_per_diventare_un_Pro.expenseTracker.model.Category; 
import java.math.BigDecimal;
import java.time.LocalDate;

//questi import qua giù controlla se servono
import java.util.List;
import java.util.ArrayList;
import java.io.*;       

public class ExpenseRepository {
     
    private static final String FILE_NAME = "expenses.txt";  //final serve per indicare che questa variabile non è modificabile

    public void saveExpenses(List<Expense> expenses) {
        
        //non serve controllare se il file esiste, perché FileWriter lo crea automaticamente se non esiste

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {   //versione migliore (chiude automaticamente il BufferedWriter alla fine del blocco try)

        for (Expense expense : expenses) {       //crea una variabile temporanea "expense" che prende, uno alla volta, ogni elemento della lista "expenses" 
                                        //  e ripete il ciclo per ogni elemento di "expenses"


                //crea la stringa nel formato in cui la voglio salvare
            String line = 
                    expense.getId() + ";" +  
                    expense.getDescription() + ";" +
                    expense.getAmount() + ";" +
                    expense.getCategory() + ";" +
                    expense.getDate() + ";" +
                    expense.getNote();     

            writer.write(line);     //scrive la riga "line"
            writer.newLine();       //va a capo 
             
        }
        } catch (IOException e) {
             System.out.println("Errore salvataggio: " + e.getMessage());
        }
    }
    

    public List<Expense> loadExpenses() {
      
        List<Expense> expenses = new ArrayList<>();
        File file = new File(FILE_NAME);
            
        if (!file.exists()) {
            return expenses;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {   //versione migliore (chiude automaticamente il BufferedReader alla fine del blocco try)
            String line;
                    
            

            while ( (line = reader.readLine())!=null  ) {   //legge una riga alla volta fino a quando non arriva alla fine del file (null)
                String[] parts = line.split(";");    //divide la riga in parti usando ";" come separatori

                if (parts.length != 6) 
                    continue;
                String id = parts[0];
                String description = parts[1];
                
                if (description == null || description.isBlank()) 
                        continue;
                   try{ 

                        BigDecimal amount = new BigDecimal(parts[2]).setScale(2, BigDecimal.ROUND_HALF_UP);  
                        Category category = Category.valueOf(parts[3]); 
                        LocalDate date = LocalDate.parse(parts[4]);   //controlla
                        String note = parts[5];

                        Expense expense = new Expense(id, description, amount, category, date, note);
                        expenses.add(expense);

                    } catch (IllegalArgumentException e) {
                        //System.out.println("Riga corrotta o non valida: " + line);
                        continue;  
                    }
                
            }
        } catch (IOException e) {
             System.out.println("Errore caricamento: " + e.getMessage());
        }

        return expenses;  //restituisce la lista di expense caricati
    }
}