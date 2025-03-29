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
    protected char status;

    /**
     * Constructeur par défaut
     */
    public Panier(){
    }

    /**
     * Constructeur de livre
     * @param IdPanier référence du livre
     * @param nbreArticle titre du livre
     * @param nomArticle auteurs du livre
     */
    public Panier(int IdPanier, int nbreArticle, String nomArticle){
        this.IdPanier = IdPanier;
        this.nbreArticle = nbreArticle;
        this.nomArticle = nomArticle;
        this.status = 'd';
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
    public char getStatus() {
        return status;
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
     * @param status le caractère 'r' pour réservé, 'e' pour emprunté, ou 'd' pour disponible
     */
    public void setStatus(char status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "IdPanier='" + IdPanier + '\'' +
                ", nbreArticle='" + nbreArticle + '\'' +
                ", nomArticle='" + nomArticle + '\'' +
                ", statut=" + status +
                '}';
    }
}
