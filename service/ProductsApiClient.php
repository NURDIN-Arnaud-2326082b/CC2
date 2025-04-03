<?php

namespace service;

class ProductsApiClient implements ProductsApiInterface
{
    private $apiUrl;

    /**
     * Constructeur
     * @param string $apiUrl URL de base de l'API produits
     */
    public function __construct($apiUrl) {
        $this->apiUrl = rtrim($apiUrl, '/');
    }

    /**
     * Récupère tous les produits depuis l'API
     * @return array Liste des produits
     */
    public function getAllProducts() {
        return $this->makeRequest('/products');
    }

    /**
     * Récupère un produit spécifique depuis l'API
     * @param int $id Identifiant du produit
     * @return array|null Données du produit ou null si non trouvé
     */
    public function getProductById($id) {
        return $this->makeRequest('/products/' . $id);
    }

    /**
     * Effectue une requête vers l'API
     * @param string $endpoint Point de terminaison de l'API
     * @param string $method Méthode HTTP
     * @param array $data Données à envoyer (pour POST/PUT)
     * @return array|null Réponse de l'API ou null en cas d'erreur
     */
    private function makeRequest($endpoint, $method = 'GET', $data = null) {
        $curl = curl_init();

        $options = [
            CURLOPT_URL => $this->apiUrl . $endpoint,
            CURLOPT_RETURNTRANSFER => true,
            CURLOPT_HTTPHEADER => ['Accept: application/json']
        ];

        if ($method !== 'GET' && $data) {
            $options[CURLOPT_CUSTOMREQUEST] = $method;
            $options[CURLOPT_POSTFIELDS] = json_encode($data);
            $options[CURLOPT_HTTPHEADER][] = 'Content-Type: application/json';
        }

        curl_setopt_array($curl, $options);

        $response = curl_exec($curl);
        $httpCode = curl_getinfo($curl, CURLINFO_HTTP_CODE);

        if (curl_errno($curl) || $httpCode >= 400) {
            curl_close($curl);
            return null;
        }

        curl_close($curl);
        return json_decode($response, true);
    }
}