package src.controller;

import src.model.FinanceManager;
import src.model.Transaction;
import src.view.ConsoleView;
import java.util.List;

/**
 * Contrôleur qui gère les interactions entre la vue et la logique métier.
 * Respecte le DIP en ne dépendant que d'abstractions (FinanceManager).
 */
public class FinanceController {
    private final FinanceManager financeManager;
    private final ConsoleView consoleView;

    public FinanceController(FinanceManager financeManager, ConsoleView consoleView) {
        if (financeManager == null || consoleView == null) {
            throw new IllegalArgumentException("FinanceManager et ConsoleView ne peuvent pas être null.");
        }
        this.financeManager = financeManager;
        this.consoleView = consoleView;
    }

    // Démarrer l'application et afficher le menu
    public void demarrer() {
        boolean continuer = true;
        while (continuer) {
            int choix = consoleView.afficherMenu();
            switch (choix) {
                case 1 -> ajouterTransaction();
                case 2 -> afficherTransactions();
                case 3 -> afficherSolde();
                case 4 -> supprimerTransaction();
                case 5 -> continuer = false;
                default -> consoleView.afficherMessage("Choix invalide. Veuillez réessayer.");
            }

        }
    }

    // Ajouter une transaction
    private void ajouterTransaction() {
        double soldeActuel = financeManager.calculerSolde();
        Transaction transaction = consoleView.saisirTransaction(soldeActuel);
        if (transaction == null) {
            consoleView.afficherMessage("Transaction invalide. Veuillez réessayer.");
            return;
        }
        financeManager.ajouterTransaction(transaction);
        consoleView.afficherMessage("Transaction ajoutée avec succès !");
    }

    // Afficher toutes les transactions
    private void afficherTransactions() {
        List<Transaction> transactions = financeManager.getToutesTransactions();
        if (transactions == null || transactions.isEmpty()) {
            consoleView.afficherMessage("Aucune transaction à afficher.");
            return;
        }
        consoleView.afficherTransactions(transactions);
    }

    // Afficher le solde
    private void afficherSolde() {
        double solde = financeManager.calculerSolde();
        consoleView.afficherSolde(solde);
    }

    // Supprimer une transaction
    private void supprimerTransaction() {
        List<Transaction> transactions = financeManager.getToutesTransactions();
        if (transactions == null || transactions.isEmpty()) {
            consoleView.afficherMessage("Aucune transaction à supprimer.");
            return;
        }

        consoleView.afficherTransactions(transactions);
        int index = consoleView.demanderIndexTransactionASupprimer(transactions.size());
        Transaction t = transactions.get(index);
        financeManager.supprimerTransaction(t);
        consoleView.afficherMessage("Transaction supprimée avec succès !");
    }

}
