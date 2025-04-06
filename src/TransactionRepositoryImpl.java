package src;

import src.model.Transaction;
import src.repository.TransactionRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepositoryImpl implements TransactionRepository {
    private final List<Transaction> transactions = new ArrayList<>();
    private static final String FILE_PATH = "transactions.txt";

    public TransactionRepositoryImpl() {
        chargerDepuisFichier();
    }

    @Override
    public void ajouterTransaction(Transaction transaction) {
        transactions.add(transaction);
        sauvegarderDansFichier();
    }

    @Override
    public void supprimerTransaction(Transaction transaction) {
        transactions.remove(transaction);
        sauvegarderDansFichier();
    }

    @Override
    public List<Transaction> getToutesTransactions() {
        return new ArrayList<>(transactions);
    }

    // 🔽 Sauvegarde dans le fichier
    private void sauvegarderDansFichier() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Transaction t : transactions) {
                writer.printf("%s;%f;%s;%s;%s%n",
                        t.getType(),
                        t.getMontant(),
                        (t.getCategory() != null ? t.getCategory().name() : "NULL"),
                        t.getAujourd().getTime(),
                        t.getDescription().replace(";", ",")); // éviter conflit avec le délimiteur
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde dans le fichier : " + e.getMessage());
        }
    }

    // 🔼 Chargement depuis le fichier
    private void chargerDepuisFichier() {
        File file = new File(FILE_PATH);
        if (!file.exists())
            return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] parts = ligne.split(";");
                if (parts.length >= 5) {
                    Transaction t = new Transaction();
                    t.setType(src.model.TransactionType.valueOf(parts[0]));
                    t.setMontant(Double.parseDouble(parts[1].replace(",", ".")));
                    if (!parts[2].equals("NULL")) {
                        t.setCategory(src.model.Category.valueOf(parts[2]));
                    }
                    t.setDescription(parts[4]);
                    t.setDateDepuisTimestamp(Long.parseLong(parts[3]));
                    transactions.add(t);
                }
            }
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Erreur lors du chargement des transactions : " + e.getMessage());
        }
    }
}
