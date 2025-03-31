package fr.univamu.iut.product;

/**
 * Classe représentant un livre
 */
public class User {

    /**
     * Référence du livre
     */
    protected int id;

    /**
     * titre du livre
     */
    protected String email;

    /**
     * Auteurs du livre
     */
    protected String firstName;

    /**
     * Statut du livre
     * ('r' pour réservé, 'e' pour emprunté, et 'd' pour disponible)
     */
    protected String name;

    protected String password;

    /**
     * Constructeur par défaut
     */
    public User(){
    }

    /**
     * Constructeur de livre
     * @param id référence du livre
     * @param email titre du livre
     * @param firstName auteurs du livre
     * @param name auteurs du livre
     * @param password auteurs du livre
     */
    public User(int id, String email, String firstName, String name, String password){
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.name = name;
        this.password = password;
    }

    /**
     * Méthode permettant d'accéder à la réference du livre
     * @return un chaîne de caractères avec la référence du livre
     */
    public int getId() {
        return id;
    }

    /**
     * Méthode permettant d'accéder au titre du livre
     * @return un chaîne de caractères avec le titre du livre
     */
    public String getEmail() {
        return email;
    }

    /**
     * Méthode permettant d'accéder aux auteurs du livre
     * @return un chaîne de caractères avec la liste des auteurs
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Méthode permettant d'accéder au statut du livre
     * @return un caractère indiquant lestatu du livre ('r' pour réservé, 'e' pour emprunté, et 'd' pour disponible)
     */
    public String getName() {
        return name;
    }

    /**
     * Méthode permettant d'accéder au statut du livre
     * @return un caractère indiquant lestatu du livre ('r' pour réservé, 'e' pour emprunté, et 'd' pour disponible)
     */
    public String getPassword() {
        return password;
    }

    /**
     * Méthode permettant de modifier la référence du livre
     * @param id une chaîne de caractères avec la référence à utiliser
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Méthode permettant de modifier le titre du livre
     * @param email une chaîne de caractères avec le titre à utiliser
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Méthode permettant de modifier les autheurs du livre
     * @param firstName une chaîne de caractères avec la liste des auteurs
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Méthode permettant de modifier les autheurs du livre
     * @param name une chaîne de caractères avec la liste des auteurs
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Méthode permettant de modifier les autheurs du livre
     * @param password une chaîne de caractères avec la liste des auteurs
     */
    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", name=" + name + '\'' +
                ", password=" + password +
                '}';
    }
}
