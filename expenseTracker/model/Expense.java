package es_per_diventare_un_Pro.expenseTracker.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;


public class Expense{
    
    private final String id;
    private final String description;
    private final BigDecimal amount;
    private final Category category;
    private final LocalDate date;
    private String note;

      
     
        //COSTRUTTORE
    private Expense(String id, String description, BigDecimal amount, Category category, LocalDate date, String note) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.note = (note == null) ? "" : note;
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
    
    //OTHER METHODS
    public Expense updateNote(String note) {
        this.note = (note == null) ? "" : note;
        return this;
    }
 
    private static BigDecimal normalize(BigDecimal amount) {
        return amount.setScale(2, RoundingMode.HALF_UP);
    }

    private static void validate(String description,BigDecimal amount, Category category, LocalDate date) {

    if (description == null || description.isBlank())
        throw new IllegalArgumentException("Description cannot be null or blank");

    if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0)
        throw new IllegalArgumentException("Amount must be >= 0");

    if (category == null)
        throw new IllegalArgumentException("Category cannot be null");

    if (date == null)
        throw new IllegalArgumentException("Date cannot be null");
}

    public static Expense load(String id, String description, BigDecimal amount, Category category, LocalDate date, String note) {

        Objects.requireNonNull(id, "Id cannot be null");
        if (id.isBlank())
             throw new IllegalArgumentException("Id cannot be blank");
        validate(description, amount, category, date);

        return new Expense(id, description, normalize(amount), category, date, note);
    }

    public static Expense create(String description, BigDecimal amount,Category category,LocalDate date,String note) {

        validate(description, amount, category, date);
        BigDecimal cleanAmount = normalize(amount);

        return new Expense(UUID.randomUUID().toString(),description,cleanAmount,category,date,note);
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

                """.formatted(id ,description, amount.toPlainString() , category, date, note);
      
    }

    //METODO EQUALS E HASHCODE
    @Override
    public boolean equals(Object o) {   //due oggetti sono uguali se hanno lo stesso id
        if (this == o) return true;
        if (!(o instanceof Expense)) return false;
        Expense expense = (Expense) o;
        return id.equals(expense.id);
    }
    @Override
    public int hashCode() {     
        return Objects.hash(id);
    }
}
