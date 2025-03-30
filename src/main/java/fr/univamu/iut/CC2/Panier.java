package fr.univamu.iut.CC2;

/**
 * Classe représentant un livre
 */
public class Panier {

    /**
     * Référence du livre
     */
    protected int IdPanier;

    /**
     * titre du livre
     */
    protected int nbreArticle;

    /**
     * Auteurs du livre
     */
    protected String nomArticle;

    /**
     * Statut du livre
     * ('r' pour réservé, 'e' pour emprunté, et 'd' pour disponible)
     */
    protected int IdClient;

    /**
     * Constructeur par défaut
     */
    public Panier(){
    }

    /**
     * Constructeur du Panier
     * @param IdPanier identifiant du panier
     * @param nbreArticle nombre d'article dans le panier
     * @param nomArticle nom de l'article
     * @param IdClient identifiant du client
     */
    public Panier(int IdPanier, int nbreArticle, String nomArticle,int IdClient){
        this.IdPanier = IdPanier;
        this.nbreArticle = nbreArticle;
        this.nomArticle = nomArticle;
        this.IdClient = IdClient;
    }

    /**
     * Méthode permettant d'accéder à la réference du livre
     * @return un chaîne de caractères avec la référence du livre
     */
    public int getIdPanier() {
        return IdPanier;
    }

    /**
     * Méthode permettant d'accéder au titre du livre
     * @return un chaîne de caractères avec le titre du livre
     */
    public int getNbreArticle() {
        return nbreArticle;
    }

    /**
     * Méthode permettant d'accéder aux auteurs du livre
     * @return un chaîne de caractères avec la liste des auteurs
     */
    public String getNomArticle() {
        return nomArticle;
    }

    /**
     * Méthode permettant d'accéder au statut du livre
     * @return un caractère indiquant lestatu du livre ('r' pour réservé, 'e' pour emprunté, et 'd' pour disponible)
     */
    public int getIdClient() {
        return IdClient;
    }

    /**
     * Méthode permettant de modifier la référence du livre
     * @param IdPanier une chaîne de caractères avec la référence à utiliser
     */
    public void IdPanier(int IdPanier) {
        this.IdPanier = IdPanier;
    }

    /**
     * Méthode permettant de modifier le titre du livre
     * @param nbreArticle une chaîne de caractères avec le titre à utiliser
     */
    public void setNbreArticle(int nbreArticle) {
        this.nbreArticle = nbreArticle;
    }

    /**
     * Méthode permettant de modifier les autheurs du livre
     * @param nomArticle une chaîne de caractères avec la liste des auteurs
     */
    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    /**
     * Méthode permettant de modifier le statut du livre
     * @param IdClient le caractère 'r' pour réservé, 'e' pour emprunté, ou 'd' pour disponible
     */
    public void setIdClient(int IdClient) {
        this.IdClient = IdClient;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "IdPanier='" + IdPanier + '\'' +
                ", nbreArticle='" + nbreArticle + '\'' +
                ", nomArticle='" + nomArticle + '\'' +
                ", IdClient=" + IdClient +
                '}';
    }
}
