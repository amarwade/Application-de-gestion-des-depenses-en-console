package src.model;

import java.util.Date;

public class Transaction {

    private double montant;
    private Date aujourd;
    private Category category;
    private TransactionType type;
    private String description;

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getAujourd() {
        return aujourd;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Transaction() {
    }

    public Transaction(double montant, Category category, TransactionType type, String description) {
        this.montant = montant;
        this.aujourd = new Date();
        this.category = category;
        this.type = type;
        this.description = description;
    }

    public void setDateDepuisTimestamp(long timestamp) {
        this.aujourd = new Date(timestamp);
    }

}
