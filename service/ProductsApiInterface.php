<?php

namespace service;

interface ProductsApiInterface
{
    /**
     * Récupère tous les produits depuis l'API
     * @return array Liste des produits
     */
    public function getAllProducts();

    /**
     * Récupère un produit spécifique depuis l'API
     * @param int $id Identifiant du produit
     * @return array|null Données du produit ou null si non trouvé
     */
    public function getProductById($id);
}
