<?php
namespace control;
class Presenter
{
    protected $panierCheck;

    public function __construct($panierCheck)
    {
        $this->panierCheck = $panierCheck;
    }

    public function getAllCommandeHTML()
    {
        $content = null;
        if ($this->panierCheck->getAnnoncesTxt() != null) {
            $content = '<h1>List of Panier</h1>  <ul>';
            foreach ($this->panierCheck->getAnnoncesTxt() as $commande) {
                $content .= ' <li>';
                $content .= '<a href="/index.php/post?id=' . $commande['id'] . '">' . $commande['title'] . '</a>';
                $content .= ' </li>';
            }
            $content .= '</ul>';
        }
        return $content;
    }
}