package fr.univamu.iut.productsandusers;

/**
 * Classe représentant un user
 */
public class User {

    /**
     * id du user
     */
    protected int id;

    /**
     * email du user
     */
    protected String email;

    /**
     * prénom du user
     */
    protected String firstName;

    /**
     * nom du user
     */
    protected String name;

    protected String password;

    /**
     * Rôle de l'user
     */
    public enum Role {
        Client, Gestionnaire, Aucun
    }

    protected Role role;

    /**
     * Constructeur par défaut
     */
    public User(){
    }

    /**
     * Constructeur de user
     * @param id id du user
     * @param email email du user
     * @param firstName prénom du user
     * @param name nom du user
     * @param password mot de passe du user
     * @param role rôle de l'user
     */
    public User(int id, String email, String firstName, String name, String password, Role role){
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    /**
     * Méthode permettant d'accéder à l'id du user
     * @return un chaîne de caractères avec l'id du user
     */
    public int getId() {
        return id;
    }

    /**
     * Méthode permettant d'accéder au email du user
     * @return un chaîne de caractères avec le email du user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Méthode permettant d'accéder au prénom du user
     * @return un chaîne de caractères avec le prénom du user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Méthode permettant d'accéder au nom du user
     * @return une chaîne de caractère avec nom du user
     */
    public String getName() {
        return name;
    }

    /**
     * Méthode permettant d'accéder au mot de passe du user
     * @return une chaîne de caractères indiquant le mot de passe du user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Méthode permettant d'accéder au rôle de l'user
     * @return un chaîne de caractères avec le rôle de l'user
     */
    public Role getRole() {
        return role;
    }

    /**
     * Méthode permettant de modifier la id du user
     * @param id une chaîne de caractères avec l'id à utiliser
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Méthode permettant de modifier l'email du user
     * @param email une chaîne de caractères avec l'email à utiliser
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Méthode permettant de modifier le prénom du user
     * @param firstName une chaîne de caractères avec le prénom
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Méthode permettant de modifier le nom du user
     * @param name une chaîne de caractères avec le nom
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Méthode permettant de modifier le mot de passe du user
     * @param password une chaîne de caractères avec le mot de passe
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Méthode permettant de modifier le rôle de l'user
     * @param role une chaîne de caractères avec le rôle à utiliser
     */
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", name=" + name + '\'' +
                ", password=" + password + '\'' +
                ", role=" + role +
                '}';
    }
}

