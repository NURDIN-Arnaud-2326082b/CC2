<?php
namespace service;

/**
 * Client pour l'API des commandes
 * Implémente l'interface OrdersApiInterface pour interagir avec l'API des commandes
 */
class OrdersApiClient implements OrdersApiInterface
{
    /**
     * URL de base de l'API des commandes
     * @var string
     */
    private $baseUrl;

    /**
     * Constructeur du client API
     * @param string $baseUrl URL de base de l'API des commandes
     */
    public function __construct($baseUrl) {
        $this->baseUrl = $baseUrl;
    }

    /**
     * Récupère toutes les commandes d'un utilisateur depuis l'API
     * @param string $userId Identifiant de l'utilisateur
     * @return array|null Liste des commandes ou null en cas d'erreur
     */
    public function getUserOrders($userId) {
        $url = $this->baseUrl . "/orders?userId=" . urlencode($userId);
        return $this->makeApiCall($url);
    }

    /**
     * Récupère les détails d'une commande spécifique depuis l'API
     * @param int|string $orderId Identifiant de la commande
     * @return array|null Détails de la commande ou null si non trouvée
     */
    public function getOrderById($orderId) {
        $url = $this->baseUrl . "/orders/" . urlencode($orderId);
        return $this->makeApiCall($url);
    }

    /**
     * Effectue un appel à l'API et traite la réponse
     * @param string $url URL complète de l'appel API
     * @return array|null Données JSON décodées ou null en cas d'erreur
     */
    private function makeApiCall($url) {
        $curl = curl_init($url);
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
        $response = curl_exec($curl);
        $status = curl_getinfo($curl, CURLINFO_HTTP_CODE);
        curl_close($curl);

        if ($status === 200) {
            return json_decode($response, true);
        }
        return null;
    }
}