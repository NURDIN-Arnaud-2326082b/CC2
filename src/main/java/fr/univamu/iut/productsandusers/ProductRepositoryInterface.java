package fr.univamu.iut.productsandusers;

import java.util.*;

/**
 * Interface d'accès aux données des produits
 */
public interface ProductRepositoryInterface {

    /**
     *  Méthode fermant le dépôt où sont stockées les informations sur les produits
     */
    public void close();

    /**
     * Méthode retournant le produit dont la référence est passée en paramètre
     * @param reference identifiant du produit recherché
     * @return un objet Book représentant le produit recherché
     */
    public Product getProduct(String reference );

    /**
     * Méthode retournant la liste des produits
     * @return une liste d'objets produits
     */
    public ArrayList<Product> getAllProducts() ;

    /**
     * Méthode permettant de mettre à jours un produit enregistré
     * @param reference identifiant du produit à mettre à jours
     * @param name nouveau produit du livre
     * @param category nouvelle catégorie du produit
     * @param stock nouveau stock du produit
     * @param unit nouvelle unité du produit
     * @param price nouveau prix du produit
     * @return true si le produit existe et la mise à jours a été faite, false sinon
     */
    public boolean updateProduct(String reference, String name, String category, int stock, String unit, float price);

    /**
     * Méthode permettant de créer un nouveau produit
     * @param product le produit à créer
     * @return true si le produit a été créé avec succès, false sinon
     */
    public boolean createProduct(Product product);

    /**
     * Méthode permettant de supprimer un produit
     * @param reference la référence du produit à supprimer
     * @return true si le produit a été supprimé avec succès, false sinon
     */
    public boolean deleteProduct(String reference);
}
