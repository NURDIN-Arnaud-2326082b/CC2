package fr.univamu.iut.CC2;

import java.util.*;

/**
 * Interface d'accès aux données des paniers
 */
public interface PanierRepositoryInterface {

    /**
     *  Méthode fermant le dépôt où sont stockées les informations sur les paniers
     */
    public void close();


    /**
     * Méthode retournant le panier dont la référence est passée en paramètre
     * @param IdPanier identifiant du panier recherché
     * @return un objet Panier représentant le panier  recherché
     */
    public Panier getPanier(int IdPanier );

    /**
     * Méthode retournant la liste des paniers
     * @return une liste d'objets paniers
     */
    public ArrayList<Panier> getAllPanier() ;

    /**
     * Méthode permettant de mettre à jours un panier enregistré
     * @param IdPanier identifiant du panier à mettre à jours
     * @param nbreArticle nouveau nombre du panier
     * @param nomArticle nouveau nom de l'article
     * @param idClient nouveau identifiant du client
     * @return true si le panier existe et la mise à jours a été faite, false sinon
     */
    public boolean UpdatePanier( int IdPanier, int nbreArticle, String nomArticle, int idClient);


}

