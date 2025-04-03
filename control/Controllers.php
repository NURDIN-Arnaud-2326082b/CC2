<?php
namespace control;

class Controllers
{

    public function ordersListAction($login) {
        $orders = $this->ordersCheck->getUserOrders($login);
        $this->presenter->setOrders($orders);
    }

    public function orderDetailAction($orderId) {
        $order = $this->ordersCheck->getOrderById($orderId);
        $this->presenter->setOrderDetail($order);
    }

    public function paniersListAction($data, $commandesCheck) {
        // Récupérer tous les produits/paniers de l'utilisateur connecté
        $paniers = $commandesCheck->getCommandes($data);
        $this->presenter->setPaniers($paniers);
    }

    public function authenticateAction($userCreation, $userCheck, $dataUsers)
    {

        // Si l'utilisateur n'a pas de session ouverte
        if (!isset($_SESSION['login'])) {

            // Si la page d'origine est le formulaire de connexion ou de création de compte
            if (isset($_POST['login']) && isset($_POST['password'])) {
                // Création du compte si la précédente page était le formulaire de création de compte
                if (isset($_POST['name']) && isset($_POST['firstName'])) {
                    if (!$userCreation->createUser($_POST['login'], $_POST['password'], $_POST['name'], $_POST['firstName'], $dataUsers)) {
                        // retourne une erreur si la création du compte a échoué
                        $error = 'creation impossible';
                        return $error;
                    } else {
                        // Enregistrement des informations de session si la création du compte a réussi
                        $_SESSION['login'] = $_POST['login'];
                    }
                } else { // Vérification de l'authentification si la précédente page était le formulaire de connexion
                    if (!$userCheck->authenticate($_POST['login'], $_POST['password'], $dataUsers)) {
                        // retourne une erreur si le compte n'est pas enregistré
                        $error = 'bad login or pwd';
                        return $error;

                    } // Enregistrement des informations de session après une authentification réussie
                    else {
                        $_SESSION['login'] = $_POST['login'];
                    }
                }
            } else {
                // retourne une erreur si la personne ne passe pas par le forumlaire de création ou de connexion
                $error = 'not connected';
                return $error;
            }

        }
    }

    private $ordersCheck;

    /**
     * Set the orders checking service
     * @param \service\OrdersChecking $ordersCheck The orders checking service
     * @return void
     */
    public function setOrdersChecking($ordersCheck) {
        $this->ordersCheck = $ordersCheck;
    }

    /**
     * @var \control\Presenter
     */
    private $presenter;

    /**
     * Set the presenter
     * @param \control\Presenter $presenter The presenter
     * @return void
     */
    public function setPresenter($presenter) {
        $this->presenter = $presenter;
    }

    /**
     * Handle commands/products display
     * @param mixed $dataCommandes The data source
     * @param \service\CommandesChecking $commandesCheck The commandes checking service
     * @return void
     */
    public function commandesAction($dataCommandes, $commandesCheck) {
        // Get all commands/products
        $commandes = $commandesCheck->getCommandes($dataCommandes);
        $this->presenter->setCommandes($commandes);
    }

    /**
     * Handle displaying a specific item from the cart
     * @param int $id The item ID
     * @param mixed $dataCommandes The data source
     * @param \service\CommandesChecking $commandesCheck The commandes checking service
     * @return void
     */
    public function panierAction($id, $dataCommandes, $commandesCheck) {
        // Get specific item from the cart
        $panier = $commandesCheck->getPanier($id, $dataCommandes);
        $this->presenter->setPanier($panier);
    }

}
