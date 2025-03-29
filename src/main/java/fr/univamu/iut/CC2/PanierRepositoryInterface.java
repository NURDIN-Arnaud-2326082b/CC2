package fr.univamu.iut.CC2;

import java.util.*;

/**
 * Interface d'accès aux données des livres
 */
public interface PanierRepositoryInterface {

    /**
     *  Méthode fermant le dépôt où sont stockées les informations sur les livres
     */
    public void close();

    /**
     * Méthode retournant le livre dont la référence est passée en paramètre
     * @param IdPanier identifiant du livre recherché
     * @return un objet Book représentant le livre recherché
     */
    public Panier getPanier(int IdPanier );

    /**
     * Méthode retournant la liste des livres
     * @return une liste d'objets livres
     */
    public ArrayList<Panier> getAllPanier() ;

    /**
     * Méthode permettant de mettre à jours un livre enregistré
     * @param IdPanier identifiant du livre à mettre à jours
     * @param nbreArticle nouveau titre du livre
     * @param nomArticle nouvelle liste d'auteurs
     * @param status nouveau status du livre
     * @return true si le livre existe et la mise à jours a été faite, false sinon
     */
    public boolean UpdatePanier( int IdPanier, int nbreArticle, String nomArticle, char status);


}

