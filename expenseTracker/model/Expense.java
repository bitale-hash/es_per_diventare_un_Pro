package es_per_diventare_un_Pro.expenseTracker.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.UUID;


public class Expense{
    
    private String id;
    private String description;
    private BigDecimal amount;
    private Category category;
    private LocalDate date;
    private String note;

        //COSTRUTTORE1
    public Expense(String description, BigDecimal amount, Category category, LocalDate date, String note) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        validateAmount(amount);
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.note = note;
    }
        //COSTRUTTORE2  
    public Expense(String id, String description, BigDecimal amount, Category category, LocalDate date, String note) {
        this.id = id;
        this.description = description;
        validateAmount(amount);
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.note = note;
    }
   

        //GET
    public String getId(){
        return id;
    }
    public String getDescription(){
        return description;
    }
    public BigDecimal getAmount(){
        return amount;
    }
    public Category getCategory(){
        return category;
    }
    public LocalDate getDate(){
        return date;
    }
    public String getNote(){
        return note;
    }
     
    
        //SET
    public void setDescription(String d){
        if (d == null || d.isBlank())
            return;
        this.description=d;
    }
    public void setAmount(BigDecimal a){
        validateAmount(a);
        this.amount=a;
    }
    public void setCategory(Category c){
        this.category=c;
    }
    public void setDate(LocalDate d){
        this.date=d;
    }
    public void setNote(String n){
        this.note=n;
    }
    //OTHER METHODS
    private String formatAmount() {
        return amount.setScale(2, RoundingMode.HALF_UP).toPlainString();
    }

    private void validateAmount(BigDecimal amount) {
    if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) 
        throw new IllegalArgumentException("Amount must be >= 0 and not null");
    }



    @Override
    public String toString(){
        return """
        
                id: %s
                Description: %s
                Amount: %s
                Category: %s
                Date: %s
                Note: %s

                """.formatted(id ,description, formatAmount(), category, date, note);
      
    }
    
}
