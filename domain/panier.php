<?php

namespace domain;

class panier
{
    protected $Id_client;
    protected $Id_panier;
    protected $nbreArticle;
    protected $Nom_Article;

    public function __construct($Id_client, $Id_panier, $nbreArticle, $Nom_Article)
    {
        $this->Id_client = $Id_client;
        $this->Id_panier = $Id_panier;
        $this->nbreArticle = $nbreArticle;
        $this->Nom_Article = $Nom_Article;
    }

    public function getId_client()
    {
        return $this->Id_client;
    }

    public function getId_panier()
    {
        return $this->Id_panier;
    }

    public function getNbreArticle()
    {
        return $this->nbreArticle;
    }

    public function getNom_Article()
    {
        return $this->Nom_Article;
    }

}