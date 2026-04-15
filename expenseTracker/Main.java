

package es_per_diventare_un_Pro.expenseTracker;

import es_per_diventare_un_Pro.expenseTracker.repository.ExpenseRepository;
import es_per_diventare_un_Pro.expenseTracker.service.ExpenseService;
import es_per_diventare_un_Pro.expenseTracker.ui.ConsoleUI;

public class Main {

    public static void main(String[] args) {

        ExpenseRepository repository = new ExpenseRepository();
        ExpenseService service = new ExpenseService(repository.loadExpenses(), repository);

        ConsoleUI ui = new ConsoleUI(service);
        ui.start();
    }
}