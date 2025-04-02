package fr.univamu.iut.productsandusers;

/**
 * Classe représentant un produit
 */
public class Product {

    /**
     * Référence du produit
     */
    protected String reference;

    /**
     * nom du produit
     */
    protected String name;

    /**
     * stock du produit
     */
    protected String category;

    /**
     * stock du produit
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
     * Constructeur de produit
     * @param reference référence du produit
     * @param name nom du produit
     * @param category auteurs du produit
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
     * Méthode permettant d'accéder à la réference du produit
     * @return un chaîne de caractères avec la référence du produit
     */
    public String getReference() {
        return reference;
    }

    /**
     * Méthode permettant d'accéder au nom du produit
     * @return un chaîne de caractères avec le nom du produit
     */
    public String getName() {
        return name;
    }

    /**
     * Méthode permettant d'accéder au catégorie du produit
     * @return un chaîne de caractères avec la catégorie du produit
     */
    public String getCategory() {
        return category;
    }

    /**
     * Méthode permettant d'accéder au stock du produit
     * @return une chaîne de caractère avec le stock du produit
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
     * Méthode permettant de modifier la référence du produit
     * @param reference une chaîne de caractères avec la référence à utiliser
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * Méthode permettant de modifier le titre du produit
     * @param name une chaîne de caractères avec le nom à utiliser
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Méthode permettant de modifier la catégorie du produit
     * @param category une chaîne de caractères avec la catégorie du produit
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Méthode permettant de modifier le stock du produit
     * @param stock le stock du produit
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
