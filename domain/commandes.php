<?php

namespace domain;
class commandes
{
    protected $id_commande;
    protected $id_panier;
    protected $id_abonne;
    protected $moyen_paiement;
    protected $localisation_retrait;
    protected $date_commande;
    protected $date_retrait;
    protected $prix_total;
    protected $statues;


    public function __construct($id_commande, $id_panier, $id_abonne, $moyen_paiement, $localisation_retrait, $date_commande, $date_retrait, $prix_total, $statues)
    {
        $this->id_commande = $id_commande;
        $this->id_panier = $id_panier;
        $this->id_abonne = $id_abonne;
        $this->moyen_paiement = $moyen_paiement;
        $this->localisation_retrait = $localisation_retrait;
        $this->date_commande = $date_commande;
        $this->date_retrait = $date_retrait;
        $this->prix_total = $prix_total;
        $this->statues = $statues;
    }


    public function getIdCommande()
    {
        return $this->id_commande;
    }
    public function getIdPanier()
    {
        return $this->id_panier;
    }
    public function getIdAbonne()
    {
        return $this->id_abonne;
    }
    public function getMoyenPaiement()
    {
        return $this->moyen_paiement;
    }
    public function getLocalisationRetrait()
    {
        return $this->localisation_retrait;
    }
    public function getDateCommande()
    {
        return $this->date_commande;
    }
    public function getDateRetrait()
    {
        return $this->date_retrait;
    }
    public function getPrixTotal()
    {
        return $this->prix_total;
    }
    public function getStatues()
    {
        return $this->statues;
    }
}
