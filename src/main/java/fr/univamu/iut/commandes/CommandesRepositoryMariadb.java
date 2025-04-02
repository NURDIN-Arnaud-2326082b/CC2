package fr.univamu.iut.commandes;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

@ApplicationScoped
public class CommandesRepositoryMariadb implements CommandesRepositoryInterface, Closeable {

    protected Connection dbConnection;
    protected PanierClientInterface panierClient;

    // Constructeur sans paramètres
    public CommandesRepositoryMariadb() {}

    // Constructeur avec paramètres
    public CommandesRepositoryMariadb(String infoConnection, String user, String pwd) throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection(infoConnection, user, pwd);
    }

    /**
     * Définit le client panier.
     *
     * @param panierClient le client panier à définir
     */
    public void setPanierClient(PanierClientInterface panierClient) {
        this.panierClient = panierClient;
    }

    @Override
    public void close() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            System.err.println("Erreur fermeture connexion: " + e.getMessage());
        }
    }

    @Override
    public Commandes getCommande(int id_commande) {
        Commandes selectedCommande = null;
        String query = "SELECT * FROM Commandes WHERE id_commande=?";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id_commande);
            ResultSet result = ps.executeQuery();

            if (result.next()) {
                double prix_total = result.getDouble("prix_total");
                String date_retrait = result.getString("date_retrait");
                String localisation_retrait = result.getString("localisation_retrait");
                String statut = result.getString("statut");

                selectedCommande = new Commandes(id_commande, prix_total, date_retrait, localisation_retrait, statut);
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de la récupération de la commande: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return selectedCommande;
    }

    @Override
    public ArrayList<Commandes> getAllCommandes() {
        ArrayList<Commandes> listCommandes = new ArrayList<>();
        String query = "SELECT * FROM Commandes";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                int id_commande = result.getInt("id_commande");
                double prix_total = result.getDouble("prix_total");
                String date_retrait = result.getString("date_retrait");
                String localisation_retrait = result.getString("localisation_retrait");
                String statut = result.getString("statut");

                Commandes currentCommande = new Commandes(id_commande, prix_total, date_retrait, localisation_retrait, statut);

                listCommandes.add(currentCommande);
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de la récupération de toutes les commandes: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return listCommandes;
    }

    @Override
    public boolean updateCommande(int id_commande, double prix_total, String date_retrait, String localisation_retrait, String statut) {
        String query = "UPDATE Commandes SET prix_total=?, date_retrait=?, localisation_retrait=?, statut=? WHERE id_commande=?";
        int nbRowModified = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setDouble(1, prix_total);
            ps.setString(2, date_retrait);
            ps.setString(3, localisation_retrait);
            ps.setString(4, statut);
            ps.setInt(5, id_commande);

            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de la mise à jour de la commande: " + e.getMessage());
            throw new RuntimeException(e);
        }

        return (nbRowModified != 0);
    }

    @Override
    public boolean createCommande(Commandes commande) {
        String query = "INSERT INTO Commandes (id_commande, prix_total, date_retrait, localisation_retrait, statut) VALUES (?, ?, ?, ?, ?)";
        int nbRowModified = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, commande.getId_commande());
            ps.setDouble(2, commande.getPrix_total());
            ps.setString(3, commande.getDate_retrait());
            ps.setString(4, commande.getLocalisation_retrait());
            ps.setString(5, commande.getStatut());

            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de la création de la commande: " + e.getMessage());
            throw new RuntimeException(e);
        }

        return (nbRowModified != 0);
    }

    @Override
    public boolean deleteCommande(int id_commande) {
        String query = "DELETE FROM Commandes WHERE id_commande=?";
        int nbRowModified = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id_commande);
            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de la suppression de la commande: " + e.getMessage());
            throw new RuntimeException(e);
        }

        return (nbRowModified != 0);
    }

    @Override
    public ArrayList<Panier> getPanierForCommande(int id_commande) {
        ArrayList<Panier> listPanier = new ArrayList<>();
        String query = "SELECT * FROM CommandeContient WHERE id_commande=?";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id_commande);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                int id_panier = result.getInt("id_panier");
                int quantite = result.getInt("quantite");

                Panier currentPanier = new Panier(id_commande, id_panier, quantite);
                listPanier.add(currentPanier);
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de la récupération des paniers pour la commande: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return listPanier;
    }

    @Override
    public boolean addCommandeContient(CommandeContient commandeContient) {
        String query = "INSERT INTO CommandeContient (id_commande, id_panier, quantite) VALUES (?, ?, ?)";
        int nbRowModified = 0;
        
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, commandeContient.getIdCommande());
            ps.setInt(2, commandeContient.getIdPanier());
            ps.setInt(3, commandeContient.getQuantite());
            
            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de l'ajout d'un produit à une commande: " + e.getMessage());
            throw new RuntimeException(e);
        }
        
        return (nbRowModified != 0);
    }

    @Override
    public boolean removeCommandeContient(int idCommande, int idPanier) {
        String query = "DELETE FROM CommandeContient WHERE id_commande=? AND id_panier=?";
        int nbRowModified = 0;
        
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, idCommande);
            ps.setInt(2, idPanier);
            
            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de la suppression d'un produit d'une commande: " + e.getMessage());
            throw new RuntimeException(e);
        }
        
        return (nbRowModified != 0);
    }

    @Override
    public boolean updateCommandeContient(int idCommande, int idPanier, int quantite) {
        String query = "UPDATE CommandeContient SET quantite=? WHERE id_commande=? AND id_panier=?";
        int nbRowModified = 0;
        
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, quantite);
            ps.setInt(2, idCommande);
            ps.setInt(3, idPanier);
            
            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de la mise à jour d'un produit dans une commande: " + e.getMessage());
            throw new RuntimeException(e);
        }
        
        return (nbRowModified != 0);
    }

    @Override
    public double getCommandeTotal(int idCommande) {
        double total = 0.0;

        try {
            ArrayList<Panier> contenuCommande = getPanierForCommande(idCommande);

            if (panierClient != null) {
                for (Panier item : contenuCommande) {
                    int idPanier = item.getId_panier();
                    int quantite = item.getQuantite();

                    double panierPrix = panierClient.getPanierTotal(String.valueOf(idPanier));
                    total += panierPrix * quantite;
                }
            } else {
                System.err.println("PanierClient non initialisé");
            }
        } catch (Exception e) {
            System.err.println("Erreur lors du calcul du prix total de la commande: " + e.getMessage());
            throw new RuntimeException(e);
        }

        return total;
    }
}
