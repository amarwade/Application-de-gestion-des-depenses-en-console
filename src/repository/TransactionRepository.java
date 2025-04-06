package src.repository;

import src.model.Transaction;
import java.util.List;

public interface TransactionRepository {
    public void ajouterTransaction(Transaction transaction);

    public void supprimerTransaction(Transaction transaction);

    public List<Transaction> getToutesTransactions();

}