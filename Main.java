import src.controller.FinanceController;
import src.model.FinanceManager;
import src.view.ConsoleView;
import src.repository.TransactionRepository;
import src.TransactionRepositoryImpl;

/**
 * Point d'entrée du programme
 */
public class Main {
    public static void main(String[] args) {
        // Création du repository
        TransactionRepository repository = new TransactionRepositoryImpl();

        // Création des composants du MVC
        FinanceManager financeManager = new FinanceManager(repository);
        ConsoleView consoleView = new ConsoleView();
        FinanceController controller = new FinanceController(financeManager, consoleView);

        // Lancement de l'application
        controller.demarrer();
    }
}
