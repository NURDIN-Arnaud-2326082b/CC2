package fr.univamu.iut.productsandusers;

import java.util.*;

/**
 * Interface d'accès aux données des users
 */
public interface UserRepositoryInterface {

    /**
     *  Méthode fermant le dépôt où sont stockées les informations sur les users
     */
    public void close();

    /**
     * Méthode retournant le user dont la référence est passée en paramètre
     * @param id identifiant du user recherché
     * @return un objet User représentant le user recherché
     */
    public User getUser(int id );

    /**
     * Méthode retournant la liste des users
     * @return une liste d'objets users
     */
    public ArrayList<User> getAllUsers() ;

    /**
     * Méthode permettant de mettre à jours un user enregistré
     * @param id identifiant du user à mettre à jours
     * @param email nouveau email
     * @param firstName nouveau prénom
     * @param name nouveau nom
     * @param password nouveau mot de passe
     * @param role nouveau rôle
     * @return true si le user existe et la mise à jours a été faite, false sinon
     */
    public boolean updateUser(int id, String email, String firstName, String name, String password, String role);

    /**
     * Méthode permettant de créer un nouvel user
     * @param user objet User représentant le nouvel user
     */
    public void createUser(User user);

    /**
     * Méthode permettant de supprimer un user
     * @param id identifiant de l'user à supprimer
     * @return true si l'user existe et a été supprimé, false sinon
     */
    public boolean deleteUser(int id);
}
