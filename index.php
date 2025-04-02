<?php

// charge et initialise les bibliothèques globales
include_once 'data/CommandesSqlAccess.php';
include_once 'data/UserSqlAccess.php';

include_once 'control/Controllers.php';
include_once 'control/Presenter.php';

include_once 'service/CommandesChecking.php';
include_once 'service/UserChecking.php';
include_once 'service/UserCreation.php';

include_once 'gui/layout.php';
include_once 'gui/ViewLogin.php';
include_once 'gui/ViewCommandes.php';
include_once 'gui/ViewPanier.php';
include_once 'gui/ViewError.php';
include_once 'gui/ViewCreate.php';

use control\Controllers;
use control\Presenter;
use data\CommandesSqlAccess;
use data\UserSqlAccess;
use gui\Layout;
use gui\ViewCommandes;
use gui\ViewCreate;
use gui\ViewError;
use gui\ViewLogin;
use gui\ViewPanier;
use service\CommandesChecking;
use service\UserChecking;
use service\UserCreation;

$data = null;
try {
    $bd = new PDO('mysql:host=mysql-vernagut.alwaysdata.net;dbname=vernagut_cooperative_agricole', 'vernagut_cc2', 'cooperative_agricole');
    // construction du modèle
    $dataCommandes = new CommandesSqlAccess($bd);
    $dataUsers = new UserSqlAccess($bd);

} catch (PDOException $e) {
    print "Erreur de connexion !: " . $e->getMessage() . "<br/>";
    die();
}

// initialisation du controller
$controller = new Controllers();

// intialisation du cas d'utilisation service\CommandesChecking
$CommandesCheck = new CommandesChecking() ;

// intialisation du cas d'utilisation service\UserChecking
$userCheck = new UserChecking() ;

// intialisation du cas d'utilisation service\UserCreation
$userCreation = new UserCreation() ;

// intialisation du presenter avec accès aux données de AnnoncesCheking
$presenter = new Presenter($CommandesCheck);

// chemin de l'URL demandée au navigateur
// (p.ex. /index.php)
$uri = parse_url($_SERVER['REQUEST_URI'], PHP_URL_PATH);

// définition d'une session d'une heure
ini_set('session.gc_maxlifetime', 3600);
session_set_cookie_params(3600);
session_start();

// Authentification et création du compte (sauf pour le formulaire de connexion et de création de compte)
if ( '/' != $uri and '/index.php' != $uri and '/index.php/logout' != $uri  and '/index.php/create' != $uri){

    $error = $controller->authenticateAction($userCreation, $userCheck, $dataUsers);

    if( $error != null )
    {
        $uri='/index.php/error' ;
        if( $error == 'bad login or pwd' or $error == 'not connected')
            $redirect = '/index.php';

        if( $error == 'creation impossible')
            $redirect = '/index.php/create';
    }
}

// route la requête en interne
// i.e. lance le bon contrôleur en fonction de la requête effectuée
if ( '/' == $uri || '/index.php' == $uri || '/index.php/logout' == $uri) {
    // affichage de la page de connexion

    session_destroy();
    $layout = new Layout("gui/layout.html" );
    $vueLogin = new ViewLogin( $layout );

    $vueLogin->display();
}
elseif ( '/index.php/create' == $uri ) {
    // Affichage du fromulaire de création de compte

    $layout = new Layout("gui/layout.html" );
    $vueCreate = new ViewCreate( $layout );

    $vueCreate->display();
}
elseif ( '/index.php/annonces' == $uri ){
    // affichage de toutes les annonces

    $controller->annoncesAction($dataCommandes, $CommandesCheck);

    $layout = new Layout("gui/layout.html" );
    $vueCommandes= new ViewCommandes( $layout,  $_SESSION['login'], $presenter);

    $vueCommandes->display();
}
elseif ( '/index.php/panier' == $uri
    && isset($_GET['id'])) {
    // Affichage d'une annonce

    $controller->postAction($_GET['id'], $dataCommandes, $CommandesCheck);

    $layout = new Layout("gui/layout.html" );
    $vuePost= new ViewPanier( $layout,  $_SESSION['login'], $presenter );

    $vuePost->display();
}
elseif ( '/index.php/error' == $uri ){
    // Affichage d'un message d'erreur

    $layout = new Layout("gui/layout.html" );
    $vueError = new ViewError( $layout, $error, $redirect );

    $vueError->display();
}
else {
    header('Status: 404 Not Found');
    echo '<html><body><h1>My Page NotFound</h1></body></html>';
}

?>
