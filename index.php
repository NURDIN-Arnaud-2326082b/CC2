<?php

// charge et initialise les bibliothèques globales

include_once 'control/Controllers.php';
include_once 'control/Presenter.php';

include_once 'service/CommandesChecking.php';
include_once 'service/UserChecking.php';
include_once 'service/UserCreation.php';
include_once 'service/AuthApiInterface.php';
include_once 'service/AuthApiClient.php';
include_once 'service/ProductsApiInterface.php';
include_once 'service/ProductsApiClient.php';
include_once 'service/OrdersApiInterface.php';
include_once 'service/OrdersApiClient.php';
include_once 'service/OrdersChecking.php';

include_once 'gui/layout.php';
include_once 'gui/ViewLogin.php';
include_once 'gui/ViewCommandes.php';
include_once 'gui/ViewPanier.php';
include_once 'gui/ViewError.php';
include_once 'gui/ViewCreate.php';
include_once 'gui/ViewOrders.php';
include_once 'gui/ViewOrderDetail.php';

use control\Controllers;
use control\Presenter;
use gui\Layout;
use gui\ViewCommandes;
use gui\ViewCreate;
use gui\ViewError;
use gui\ViewLogin;
use gui\ViewOrderDetail;
use gui\ViewOrders;
use gui\ViewPanier;
use service\CommandesChecking;
use service\UserChecking;
use service\UserCreation;

$dataUsers = null;
$dataCommandes = null;

// initialisation du controller
$controller = new Controllers();



$ordersApiClient = new service\OrdersApiClient('');
$ordersCheck = new service\OrdersChecking($ordersApiClient);

$productsApiClient = new service\ProductsApiClient('');
$CommandesCheck = new CommandesChecking($productsApiClient);
$controller->setOrdersChecking($ordersCheck);

// Configuration de l'API d'authentification
$authApiClient = new service\AuthApiClient('');

// intialisation du cas d'utilisation service\UserChecking avec l'API
$userCheck = new UserChecking($authApiClient);

// intialisation du cas d'utilisation service\UserCreation
$userCreation = new UserCreation() ;

// intialisation du presenter avec accès aux données de AnnoncesCheking
$presenter = new Presenter($CommandesCheck);
$controller->setPresenter($presenter);

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
    if ($error == null) {
        // Authentification réussie, redirection vers la page des paniers
        header('Location: /index.php/panier');
        exit;
    }

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

    $controller->commandesAction($dataCommandes, $CommandesCheck);

    $layout = new Layout("gui/layout.html" );
    $vueCommandes= new ViewCommandes( $layout,  $_SESSION['login'], $presenter);

    $vueCommandes->display();
}
elseif (strpos($uri, '/index.php/panier') === 0) {
    // Affichage du panier

    if (isset($_GET['id'])) {
        // Affichage d'un élément spécifique du panier
        $controller->panierAction($_GET['id'], $dataCommandes, $CommandesCheck);
    } else {
        // Affichage de tous les éléments du panier
        $controller->paniersListAction($dataCommandes, $CommandesCheck);
    }

    $layout = new Layout("gui/layout.html");
    $vuePanier = new ViewPanier($layout, $_SESSION['login'], $presenter);

    $vuePanier->display();
}
elseif ( '/index.php/error' == $uri ){
    // Affichage d'un message d'erreur

    $layout = new Layout("gui/layout.html" );
    $vueError = new ViewError( $layout, $error, $redirect );

    $vueError->display();
}
elseif ( '/index.php/orders' == $uri ){
    // Display all orders for the user
    $controller->ordersListAction($_SESSION['login']);

    $layout = new Layout("gui/layout.html");
    $vueOrders = new ViewOrders($layout, $_SESSION['login'], $presenter);

    $vueOrders->display();
}
elseif ( strpos($uri, '/index.php/order') === 0 ){
    // Display specific order details
    if (isset($_GET['id'])) {
        $controller->orderDetailAction($_GET['id']);

        $layout = new Layout("gui/layout.html");
        $vueOrderDetail = new ViewOrderDetail($layout, $_SESSION['login'], $presenter);

        $vueOrderDetail->display();
    } else {
        header('Location: /index.php/orders');
        exit;
    }
}
else {
    header('Status: 404 Not Found');
    echo '<html lang="fr"><body><h1>My Page NotFound</h1></body></html>';
}


