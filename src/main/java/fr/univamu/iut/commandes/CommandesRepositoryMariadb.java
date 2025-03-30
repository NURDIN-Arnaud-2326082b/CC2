package fr.univamu.iut.commandes;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

public class CommandesRepositoryMariadb implements CommandesRepositoryInterface, Closeable {

    protected Connection dbConnection;

    public CommandesRepositoryMariadb(String infoConnection, String user, String pwd) throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection(infoConnection, user, pwd);
    }

    @Override
    public void close() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
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
                int id_abonne = result.getInt("id_abonne");
                int id_panier = result.getInt("id_panier");
                double prix_total = result.getDouble("prix_total");
                String date_commande = result.getString("date_commande");
                String date_retrait = result.getString("date_retrait");
                String localisation_retrait = result.getString("localisation_retrait");
                String statut = result.getString("statut");
                String moyen_paiement = result.getString("moyen_paiement");

                selectedCommande = new Commandes(id_commande, id_abonne, id_panier, prix_total, date_commande, date_retrait, localisation_retrait, statut, moyen_paiement);
            }
        } catch (SQLException e) {
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
                int id_abonne = result.getInt("id_abonne");
                int id_panier = result.getInt("id_panier");
                double prix_total = result.getDouble("prix_total");
                String date_commande = result.getString("date_commande");
                String date_retrait = result.getString("date_retrait");
                String localisation_retrait = result.getString("localisation_retrait");
                String statut = result.getString("statut");
                String moyen_paiement = result.getString("moyen_paiement");

                Commandes currentCommande = new Commandes(id_commande, id_abonne, id_panier, prix_total, date_commande, date_retrait, localisation_retrait, statut, moyen_paiement);

                listCommandes.add(currentCommande);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listCommandes;
    }

    @Override
    public boolean updateCommande(int id_commande, int id_abonne, int id_panier, double prix_total, String date_commande, String date_retrait, String localisation_retrait, String statut, String moyen_paiement) {
        String query = "UPDATE Commandes SET id_abonne=?, id_panier=?, prix_total=?, date_commande=?, date_retrait=?, localisation_retrait=?, statut=?, moyen_paiement=? WHERE id_commande=?";
        int nbRowModified = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id_abonne);
            ps.setInt(2, id_panier);
            ps.setDouble(3, prix_total);
            ps.setString(4, date_commande);
            ps.setString(5, date_retrait);
            ps.setString(6, localisation_retrait);
            ps.setString(7, statut);
            ps.setString(8, moyen_paiement);
            ps.setInt(9, id_commande);

            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return (nbRowModified != 0);
    }

    @Override
    public boolean createCommande(Commandes commande) {
        String query = "INSERT INTO Commandes (id_commande, id_abonne, id_panier, prix_total, date_commande, date_retrait, localisation_retrait, statut, moyen_paiement) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int nbRowModified = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, commande.getId_commande());
            ps.setInt(2, commande.getId_abonne());
            ps.setInt(3, commande.getId_panier());
            ps.setDouble(4, commande.getPrix_total());
            ps.setString(5, commande.getDate_commande());
            ps.setString(6, commande.getDate_retrait());
            ps.setString(7, commande.getLocalisation_retrait());
            ps.setString(8, commande.getStatut());
            ps.setString(9, commande.getMoyen_paiement());

            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
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
            throw new RuntimeException(e);
        }

        return (nbRowModified != 0);
    }
}