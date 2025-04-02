package fr.univamu.iut.productsandusers;

import java.util.*;

/**
 * Interface d'accès aux données des livres
 */
public interface UserRepositoryInterface {

    /**
     *  Méthode fermant le dépôt où sont stockées les informations sur les livres
     */
    public void close();

    /**
     * Méthode retournant le livre dont la référence est passée en paramètre
     * @param id identifiant du livre recherché
     * @return un objet Book représentant le livre recherché
     */
    public User getUser(int id );

    /**
     * Méthode retournant la liste des livres
     * @return une liste d'objets livres
     */
    public ArrayList<User> getAllUsers() ;

    /**
     * Méthode permettant de mettre à jours un livre enregistré
     * @param id identifiant du livre à mettre à jours
     * @param email nouveau titre du livre
     * @param firstName nouvelle liste d'auteurs
     * @param name nouveau status du livre
     * @param password nouveau status du livre
     * @param role nouveau rôle de l'utilisateur
     * @return true si le livre existe et la mise à jours a été faite, false sinon
     */
    public boolean updateUser(int id, String email, String firstName, String name, String password, String role);

    /**
     * Méthode permettant de créer un nouvel utilisateur
     * @param user objet User représentant le nouvel utilisateur
     */
    public void createUser(User user);

    /**
     * Méthode permettant de supprimer un utilisateur
     * @param id identifiant de l'utilisateur à supprimer
     * @return true si l'utilisateur existe et a été supprimé, false sinon
     */
    public boolean deleteUser(int id);
}
