package fr.univamu.iut.productsandusers;

import java.util.*;

/**
 * Interface d'accès aux données des livres
 */
public interface ProductRepositoryInterface {

    /**
     *  Méthode fermant le dépôt où sont stockées les informations sur les livres
     */
    public void close();

    /**
     * Méthode retournant le livre dont la référence est passée en paramètre
     * @param reference identifiant du livre recherché
     * @return un objet Book représentant le livre recherché
     */
    public Product getProduct(String reference );

    /**
     * Méthode retournant la liste des livres
     * @return une liste d'objets livres
     */
    public ArrayList<Product> getAllProducts() ;

    /**
     * Méthode permettant de mettre à jours un livre enregistré
     * @param reference identifiant du livre à mettre à jours
     * @param name nouveau titre du livre
     * @param category nouvelle liste d'auteurs
     * @param stock nouveau stock du livre
     * @param unit nouvelle unité du produit
     * @param price nouveau prix du produit
     * @return true si le livre existe et la mise à jours a été faite, false sinon
     */
    public boolean updateProduct(String reference, String name, String category, int stock, String unit, float price);
}

