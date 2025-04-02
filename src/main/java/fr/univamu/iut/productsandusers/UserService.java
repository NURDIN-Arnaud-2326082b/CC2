package fr.univamu.iut.productsandusers;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;

/**
 * Classe utilisée pour récupérer les informations nécessaires à la ressource
 * (permet de dissocier ressource et mode d'accès aux données)
 */
public class UserService {

    /**
     * Objet permettant d'accéder au dépôt où sont stockées les informations sur les utilisateurs
     */
    protected UserRepositoryInterface userRepo;

    /**
     * Constructeur permettant d'injecter l'accès aux données
     * @param userRepo objet implémentant l'interface d'accès aux données
     */
    public UserService(UserRepositoryInterface userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Méthode retournant les informations sur les utilisateurs au format JSON
     * @return une chaîne de caractère contenant les informations au format JSON
     */
    public String getAllUsersJSON(){

        ArrayList<User> allUsers = userRepo.getAllUsers();

        // création du json et conversion de la liste d'utilisateurs
        String result = null;
        try( Jsonb jsonb = JsonbBuilder.create()){
            result = jsonb.toJson(allUsers);
        }
        catch (Exception e){
            System.err.println( e.getMessage() );
        }

        return result;
    }

    /**
     * Méthode retournant au format JSON les informations sur un utilisateur recherché
     * @param id la référence de l'utilisateur recherché
     * @return une chaîne de caractère contenant les informations au format JSON
     */
    public String getUserJSON(int id ){
        String result = null;
        User myUser = userRepo.getUser(id);

        // si l'utilisateur a été trouvé
        if( myUser != null ) {

            // création du json et conversion de l'utilisateur
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myUser);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }

    /**
     * Méthode permettant de mettre à jour les informations d'un utilisateur
     * @param id référence de l'utilisateur à mettre à jour
     * @param user les nouvelles informations à utiliser
     * @return true si l'utilisateur a pu être mis à jour
     */
    public boolean updateUser(int id, User user) {
        return userRepo.updateUser(id, user.email, user.firstName, user.name, user.password, user.role.name());
    }

    /**
     * Méthode permettant de créer un nouvel utilisateur
     * @param user les informations de l'utilisateur à créer
     */
    public void createUser(User user) {
        userRepo.createUser(user);
    }

    /**
     * Méthode permettant de supprimer un utilisateur
     * @param id référence de l'utilisateur à supprimer
     * @return true si l'utilisateur a pu être supprimé
     */
    public boolean deleteUser(int id) {
        return userRepo.deleteUser(id);
    }
}
