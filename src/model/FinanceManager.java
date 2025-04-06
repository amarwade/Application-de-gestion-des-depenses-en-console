package src.model;

import java.util.List;
import src.repository.TransactionRepository;

public class FinanceManager {
    private final TransactionRepository repository;

    public FinanceManager(TransactionRepository repository) {
        this.repository = repository;
    }

    // Ajout d'une transaction
    public void ajouterTransaction(Transaction transaction) {
        repository.ajouterTransaction(transaction);
    }

    // Suppression d'une transaction
    public void supprimerTransaction(Transaction transaction) {
        repository.supprimerTransaction(transaction);
    }

    // Récupération de toutes les transactions
    public List<Transaction> getToutesTransactions() {
        return repository.getToutesTransactions();
    }

    // Calcul du solde actuel
    public double calculerSolde() {
        double solde = 0;
        for (Transaction t : repository.getToutesTransactions()) {
            if (t.getType() == TransactionType.REVENU) {
                solde += t.getMontant();
            } else {
                solde -= t.getMontant();
            }
        }
        return solde;
    }

}