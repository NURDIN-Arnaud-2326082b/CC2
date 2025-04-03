<?php

namespace service;

class CommandesChecking
{
    private $productsApi;

    /**
     * Constructeur avec injection du client API produits
     * @param ProductsApiInterface|null $productsApi Client API produits
     */
    public function __construct(ProductsApiInterface $productsApi = null) {
        $this->productsApi = $productsApi;
    }

    /**
     * Récupère tous les produits
     * @param mixed $data Accès aux données (pour compatibilité)
     * @return array Liste des produits
     */
    public function getCommandes($data = null) {
        if ($this->productsApi) {
            return $this->productsApi->getAllProducts();
        }

        // Fallback à la méthode traditionnelle
        return $data->getCommandes();
    }

    /**
     * Récupère un produit spécifique
     * @param int $id Identifiant du produit
     * @param mixed $data Accès aux données (pour compatibilité)
     * @return array|null Données du produit
     */
    public function getCommandeById($id, $data = null) {
        if ($this->productsApi) {
            return $this->productsApi->getProductById($id);
        }

        // Fallback à la méthode traditionnelle
        return $data->getCommandeById($id);
    }
}