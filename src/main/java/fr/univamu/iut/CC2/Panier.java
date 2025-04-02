package fr.univamu.iut.CC2;

/**
 * Classe représentant un panier
 */
public class Panier {

    /**
     * identifiant du panier
     */
    protected int IdPanier;

    /**
     * nombre d'article dans le panier
     */
    protected int nbreArticle;

    /**
     * nom de l'article
     */
    protected String nomArticle;

    /**
     * identifiant du client
     */
    protected int IdClient;

    /**
     * Constructeur par défaut
     */
    public Panier(String nomArticle, int nbreArticle, int idPanier){
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
     * Méthode permettant d'accéder à l'identifiant du panier
     * @return un chaîne de caractères avec l'identifiant du panier
     */
    public int getIdPanier() {
        return IdPanier;
    }

    /**
     * Méthode permettant d'accéder au nombre d'article dans le panier
     * @return un chaîne de caractères avec le nombre d'article dans le panier
     */
    public int getNbreArticle() {
        return nbreArticle;
    }

    /**
     * Méthode permettant d'accéder au nom de l'article
     * @return un chaîne de caractères avec le nom de l'article
     */
    public String getNomArticle() {
        return nomArticle;
    }

    /**
     * Méthode permettant d'accéder à l'identifiant du client
     * @return un caractère indiquant l'id du client
     */
    public int getIdClient() {
        return IdClient;
    }

    /**
     * Méthode permettant de modifier l'identifiant du panier
     * @param IdPanier une chaîne de caractères avec l'identifiant à utiliser
     */
    public void IdPanier(int IdPanier) {
        this.IdPanier = IdPanier;
    }

    /**
     * Méthode permettant de modifier le nombre d'article dans le panier
     * @param nbreArticle une chaîne de caractères avec le nombre d'article
     */
    public void setNbreArticle(int nbreArticle) {
        this.nbreArticle = nbreArticle;
    }

    /**
     * Méthode permettant de modifier le nom de l'article
     * @param nomArticle une chaîne de caractères avec le nom de l'article
     */
    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    /**
     * Méthode permettant de modifier l'identifiant du client
     * @param IdClient le caractère à utiliser
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
