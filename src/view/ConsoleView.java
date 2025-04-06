package src.view;

import src.model.Transaction;
import src.model.TransactionType;
import java.util.List;
import java.util.Scanner;
import src.model.Category;

/**
 * Gère l'affichage et les interactions avec l'utilisateur via la console.
 */
public class ConsoleView {
    private final Scanner scanner;

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }

    // Afficher le menu principal et obtenir le choix de l'utilisateur
    public int afficherMenu() {
        System.out.println("\n====== Gestion de depense ======");
        System.out.println("1. Ajouter une transaction");
        System.out.println("2. Afficher toutes les transactions");
        System.out.println("3. Afficher le solde actuel");
        System.out.println("4. Supprimer une transaction");
        System.out.println("5. Quitter");

        int option;
        do {
            System.out.print("Choisissez une option (Nombre): ");
            option = scanner.nextInt();
        } while (option < 1 || option > 5);
        return option;
    }

    public Transaction saisirTransaction(double soldeActuel) {
        System.out.print("Type de transaction (REVENU/DEPENSE) : ");
        TransactionType type = null;
        while (type == null) {
            try {
                String typeStr = scanner.next().toUpperCase();
                type = TransactionType.valueOf(typeStr);
            } catch (IllegalArgumentException e) {
                System.out.println("Type invalide. Entrez soit REVENU, soit DEPENSE.");
            }
        }

        // Demander le montant de la transaction
        double montant;
        do {
            System.out.print("Montant : ");
            montant = scanner.nextDouble();

            if (montant <= 0) {
                System.out.println("Le montant doit être supérieur à zéro.");
                continue;
            }

            if (type == TransactionType.DEPENSE && montant > soldeActuel) {
                System.out.println(
                        "Solde insuffisant pour effectuer cette dépense. Votre solde actuel est : " + soldeActuel);
                montant = -1; // Reboucle
            }
        } while (montant <= 0);

        // demander la catégorie si c'est une dépense
        Category cat = null;
        if (type == TransactionType.DEPENSE) {
            System.out.println("Choisissez une catégorie :");
            Category[] categories = Category.values();
            for (int i = 0; i < categories.length; i++) {
                System.out.printf("%d. %s%n", i + 1, categories[i]);
            }

            int choixCat = -1;
            while (choixCat < 1 || choixCat > categories.length) {
                System.out.print("Votre choix : ");
                if (scanner.hasNextInt()) {
                    choixCat = scanner.nextInt();
                    if (choixCat >= 1 && choixCat <= categories.length) {
                        cat = categories[choixCat - 1];
                    } else {
                        System.out.println("Numéro invalide. Veuillez choisir parmi les options affichées.");
                    }
                } else {
                    System.out.println("Veuillez entrer un nombre.");
                    scanner.next(); // consommer la mauvaise saisie
                }
            }
        }

        scanner.nextLine();
        String description = ""; // Consomme le retour à la ligne
        if (type == TransactionType.DEPENSE) {
            System.out.print("Description : ");
            description = scanner.nextLine();
        }
        if (type == TransactionType.REVENU) {
            System.out.print("PROVENANCE DU REVENU : ");
            description = scanner.nextLine();

        }

        return new Transaction(montant, cat, type, description);
    }

    // Afficher la liste des transactions
    public void afficherTransactions(List<Transaction> transactions) {
        System.out.println("\n=== Liste des transactions ===");
        if (transactions.isEmpty()) {
            System.out.println("Aucune transaction enregistrée.");
        } else {
            int i = 1;
            for (Transaction t : transactions) {
                System.out.printf("%d. [%s] %.2f - %s (%s) : %s%n",
                        i++,
                        t.getType(),
                        t.getMontant(),
                        (t.getCategory() != null ? t.getCategory() : "Sans catégorie"),
                        t.getAujourd(),
                        t.getDescription());
            }
        }
    }

    // Afficher le solde actuel
    public void afficherSolde(double solde) {
        System.out.printf("\nSolde actuel : %.2f\n", solde);
    }

    // Afficher un message d'information
    public void afficherMessage(String message) {
        System.out.println(message);
    }

    // Demander à l'utilisateur de supprimer d'une transaction
    public int demanderIndexTransactionASupprimer(int taille) {
        System.out.println("Entrez l'indice de la transaction à supprimer (de 1 à " + taille + ") : ");
        int index = -1;
        while (index < 1 || index > taille) {
            try {
                index = scanner.nextInt();
                if (index < 1 || index > taille) {
                    System.out.println("Indice invalide. Essayez encore.");
                }
            } catch (Exception e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre.");
                scanner.nextLine(); // nettoyer le buffer
            }
        }
        return index - 1; // on convertit vers l'index de la liste (0-based)
    }

}
