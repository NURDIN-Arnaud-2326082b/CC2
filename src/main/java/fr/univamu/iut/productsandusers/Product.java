package fr.univamu.iut.productsandusers;

/**
 * Classe représentant un livre
 */
public class Product {

    /**
     * Référence du livre
     */
    protected String reference;

    /**
     * titre du livre
     */
    protected String name;

    /**
     * Auteurs du livre
     */
    protected String category;

    /**
     * Statut du livre
     * ('r' pour réservé, 'e' pour emprunté, et 'd' pour disponible)
     */
    protected int stock;

    /**
     * Unité du produit
     */
    protected String unit;

    /**
     * Prix du produit
     */
    protected float price;

    /**
     * Constructeur par défaut
     */
    public Product(){
    }

    /**
     * Constructeur de livre
     * @param reference référence du livre
     * @param name titre du livre
     * @param category auteurs du livre
     */
    public Product(String reference, String name, String category){
        this.reference = reference;
        this.name = name;
        this.category = category;
        this.stock = 0;
    }

    /**
     * Constructeur de livre
     * @param reference référence du livre
     * @param name titre du livre
     * @param category auteurs du livre
     * @param unit unité du produit
     * @param price prix du produit
     */
    public Product(String reference, String name, String category, String unit, float price){
        this.reference = reference;
        this.name = name;
        this.category = category;
        this.unit = unit;
        this.price = price;
        this.stock = 0;
    }

    /**
     * Méthode permettant d'accéder à la réference du livre
     * @return un chaîne de caractères avec la référence du livre
     */
    public String getReference() {
        return reference;
    }

    /**
     * Méthode permettant d'accéder au titre du livre
     * @return un chaîne de caractères avec le titre du livre
     */
    public String getName() {
        return name;
    }

    /**
     * Méthode permettant d'accéder aux auteurs du livre
     * @return un chaîne de caractères avec la liste des auteurs
     */
    public String getCategory() {
        return category;
    }

    /**
     * Méthode permettant d'accéder au statut du livre
     * @return un caractère indiquant lestatu du livre ('r' pour réservé, 'e' pour emprunté, et 'd' pour disponible)
     */
    public int getStock() {
        return stock;
    }

    /**
     * Méthode permettant d'accéder à l'unité du produit
     * @return une chaîne de caractères avec l'unité du produit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Méthode permettant d'accéder au prix du produit
     * @return un float avec le prix du produit
     */
    public float getPrice() {
        return price;
    }

    /**
     * Méthode permettant de modifier la référence du livre
     * @param reference une chaîne de caractères avec la référence à utiliser
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * Méthode permettant de modifier le titre du livre
     * @param name une chaîne de caractères avec le titre à utiliser
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Méthode permettant de modifier les autheurs du livre
     * @param category une chaîne de caractères avec la liste des auteurs
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Méthode permettant de modifier le statut du livre
     * @param stock le caractère 'r' pour réservé, 'e' pour emprunté, ou 'd' pour disponible
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Méthode permettant de modifier l'unité du produit
     * @param unit une chaîne de caractères avec l'unité à utiliser
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * Méthode permettant de modifier le prix du produit
     * @param price un float avec le prix à utiliser
     */
    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "reference='" + reference + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", unit='" + unit + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
