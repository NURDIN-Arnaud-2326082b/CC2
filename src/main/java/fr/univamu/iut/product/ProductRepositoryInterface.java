package fr.univamu.iut.product;

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
     * @param title nouveau titre du livre
     * @param authors nouvelle liste d'auteurs
     * @param status nouveau status du livre
     * @return true si le livre existe et la mise à jours a été faite, false sinon
     */
    public boolean updateProduct( String reference, String title, String authors, char status);
}

