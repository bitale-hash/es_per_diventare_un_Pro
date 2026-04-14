package es_per_diventare_un_Pro.expenseTracker.service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import es_per_diventare_un_Pro.expenseTracker.model.Category;  
import es_per_diventare_un_Pro.expenseTracker.model.Expense; 
import es_per_diventare_un_Pro.expenseTracker.repository.ExpenseRepository;
 
 

public class ExpenseService {

    private List<Expense> expenses;
    private ExpenseRepository repository;
        
        //COSTRUTTORE
    
    public ExpenseService(List<Expense> expenses, ExpenseRepository repository) {
    this.expenses = (expenses != null) ? expenses : new ArrayList<>(); //questo evita che expenses sia null, se lo è viene inizializzato come una nuova lista vuota
    this.repository = Objects.requireNonNull(repository, "Repository cannot be null");
    }
    
    public void addExpense(String description, BigDecimal amount, Category category, LocalDate date, String note) {
        Expense expense = new Expense(description, amount, category, date, note);
        expenses.add(expense);
        repository.saveExpenses(expenses);
    }
    
    public BigDecimal getTotalExpenses() {
    return expenses.stream()
            .map(Expense::getAmount)  //map: estrae da ogni Expense il suo importo
            .reduce(BigDecimal.ZERO, BigDecimal::add);  //reduce: somma tutti gli importi partendo da BigDecimal.ZERO
    }
    
    public BigDecimal getTotalByCategory(Category category) {
        return expenses.stream()
            .filter(e -> e.getCategory() == category)
            .map(Expense::getAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    public BigDecimal getMonthlyTotal(int month, int year) {
        return expenses.stream()
            .filter(e -> e.getDate().getMonthValue() == month &&
                         e.getDate().getYear() == year)
            .map(Expense::getAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
}
    
    public List<Expense> getByCategory(Category category) {
        return expenses.stream()
            .filter(e -> e.getCategory() == category) //seleziona solo cio che rispetta la condizione
            .toList();                                //colleziona i risultati in una lista
    }
    
    public List<Expense> getByDate(LocalDate date) {
        return expenses.stream()
            .filter(e -> e.getDate().equals(date))
            .toList();
    }
    
    public List<Expense> getExpenses() {
        return List.copyOf(expenses);   //restituisce una copia della lista originale per evitare modifiche esterne               
    }
}
