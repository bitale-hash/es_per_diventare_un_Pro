package es_per_diventare_un_Pro.expenseTracker.service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import es_per_diventare_un_Pro.expenseTracker.model.Category;  
import es_per_diventare_un_Pro.expenseTracker.model.Expense; 
import es_per_diventare_un_Pro.expenseTracker.repository.ExpenseRepository;
 
 

public class ExpenseService {

    private final List<Expense> expenses;
    private final ExpenseRepository repository;
        
        //COSTRUTTORE
    public ExpenseService(List<Expense> expenses, ExpenseRepository repository) {
        this.expenses = new ArrayList<>(Objects.requireNonNullElse(expenses, new ArrayList<>()));
        this.repository = Objects.requireNonNull(repository, "Repository cannot be null");
    }
        //CRUD
    public void addExpense(String description, BigDecimal amount, Category category, LocalDate date, String note) {
        Expense expense = Expense.create(description, amount, category, date, note);
        if (expenses.contains(expense)) 
            throw new IllegalArgumentException("Expense already exists");
        expenses.add(expense);
        save();
    }
    public void removeExpense(int index) {
        if (index < 0 || index >= expenses.size()) 
            throw new IllegalArgumentException("Invalid index");
        
        expenses.remove(index);
        save();
    }
    

            //GET TOTALS BY
    public BigDecimal getTotalExpenses() {
            return sum(expenses);
    }
    
    public BigDecimal getTotalByCategory(Category category) {
        Objects.requireNonNull(category, "Category cannot be null");
         return sum(expenses.stream()
                    .filter(e -> category.equals(e.getCategory()))
                    .toList());
            
    }
    
    public BigDecimal getMonthlyTotal(int month, int year) {

        if (month < 1 || month > 12) 
            throw new IllegalArgumentException("Month must be between 1 and 12");
        int currentYear = LocalDate.now().getYear();
        if (year < 2000 || year > currentYear) 
            throw new IllegalArgumentException("Year must be between 2000 and current year");
         
        return sum(expenses.stream()
            .filter(e -> e.getDate() != null &&
                         e.getDate().getMonthValue() == month &&
                         e.getDate().getYear() == year).toList());
    }
    
    public Map<Category, BigDecimal> getTotalGroupedByCategory() {
    return expenses.stream()
        .filter(e -> e.getCategory() != null)
        .collect(Collectors.groupingBy(
            Expense::getCategory,
            Collectors.mapping(
                Expense::getAmount,
                Collectors.reducing(BigDecimal.ZERO, BigDecimal::add)
            )
        ));
    }
            //FIND LIST BY
    public List<Expense> findByCategory(Category category) {
        Objects.requireNonNull(category, "Category cannot be null");
        return expenses.stream()
            .filter(e -> category.equals(e.getCategory())) //seleziona solo cio che rispetta la condizione
            .toList() ;                                //colleziona i risultati in una lista
    }
    
    public List<Expense> findByDate(LocalDate date) {
        Objects.requireNonNull(date, "Date cannot be null");
        return expenses.stream()
            .filter(e -> e.getDate() != null &&
                         e.getDate().equals(date))
            .toList();
    }
    
   
        
    
    // METODI PUBBLICI 

    public List<Expense> getExpenses() {
        return List.copyOf(expenses);   //restituisce una copia della lista originale per evitare modifiche esterne               
    }
    public Expense getMaxExpense() {
        return expenses.stream()
        .max(Comparator.comparing(Expense::getAmount))
        .orElseThrow(() -> new IllegalStateException("No expenses available"));
    }
    
    public BigDecimal getAverageExpense() {     //SPESA MEDIA
        if (expenses.isEmpty()) 
            return BigDecimal.ZERO;
        BigDecimal total = getTotalExpenses();
        return total.divide(BigDecimal.valueOf(expenses.size()), 2, RoundingMode.HALF_UP);
    }
        
    
    
    //METODI DI ORDINAMENTO
    public List<Expense> getExpensesSortedByDate() {
        return expenses.stream()
            .sorted(Comparator.comparing(Expense::getDate))
            .toList();
    }
    public List<Expense> getExpensesSortedByAmountDesc() {
        return expenses.stream()
            .sorted(Comparator.comparing(Expense::getAmount).reversed())
            .toList();
    }
    public BigDecimal getLast7DaysTotal() {
        LocalDate now = LocalDate.now();

        return sum(expenses.stream()
            .filter(e -> !e.getDate().isBefore(now.minusDays(7))) 
            .toList());
    }
        
    
    
    //METODI PRIVATI
    
    private BigDecimal sum(List<Expense> list) {
    return list.stream()
            .map(Expense::getAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    private void save() {

    repository.saveExpenses(expenses);
}
}
