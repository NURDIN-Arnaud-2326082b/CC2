<?php
namespace gui;

include_once "ViewLogged.php";

class ViewAnnonces extends ViewLogged
{
    public function __construct($layout, $login, $presenter)
    {
        parent::__construct($layout, $login);

        $this->title= 'Exemple Commandes Basic PHP: Commandes';

        $this->content = $presenter->getAllAnnoncesHTML();
    }
}
