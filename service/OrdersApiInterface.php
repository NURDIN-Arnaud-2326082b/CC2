<?php
namespace service;

interface OrdersApiInterface
{
    /**
     * Récupère toutes les commandes d'un utilisateur
     * @param string $userId Identifiant de l'utilisateur
     * @return array Liste des commandes
     */
    public function getUserOrders($userId);

    /**
     * Récupère les détails d'une commande spécifique
     * @param int $orderId Identifiant de la commande
     * @return array|null Détails de la commande
     */
    public function getOrderById($orderId);
}
