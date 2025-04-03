<?php

namespace service;

class AuthApiClient implements AuthApiInterface
{
    private $apiUrl;

    /**
     * Constructeur
     * @param string $apiUrl URL de l'API d'authentification
     */
    public function __construct($apiUrl) {
        $this->apiUrl = $apiUrl;
    }

    /**
     * Authentifie un utilisateur via l'API externe
     * @param string $login Identifiant de l'utilisateur
     * @param string $password Mot de passe de l'utilisateur
     * @return array Résultat avec 'success' (bool) et 'user' ou 'error' (string)
     */
    public function authenticate($login, $password) {
        // Préparation des données pour la requête API
        $data = array(
            'login' => $login,
            'password' => $password
        );

        // Initialisation de la session cURL
        $curl = curl_init();

        // Configuration des options cURL
        curl_setopt_array($curl, array(
            CURLOPT_URL => $this->apiUrl,
            CURLOPT_RETURNTRANSFER => true,
            CURLOPT_POST => true,
            CURLOPT_POSTFIELDS => json_encode($data),
            CURLOPT_HTTPHEADER => array(
                'Content-Type: application/json',
                'Accept: application/json'
            )
        ));

        // Exécution de la requête
        $response = curl_exec($curl);
        $httpCode = curl_getinfo($curl, CURLINFO_HTTP_CODE);

        if (curl_errno($curl)) {
            curl_close($curl);
            return ['success' => false, 'error' => 'Erreur de connexion à l\'API'];
        }

        curl_close($curl);

        // Analyse de la réponse JSON
        $result = json_decode($response, true);

        // Retourner le résultat d'authentification
        if ($httpCode == 200 && isset($result['authenticated']) && $result['authenticated'] === true) {
            return ['success' => true, 'user' => isset($result['user']) ? $result['user'] : ['login' => $login]];
        }

        return ['success' => false, 'error' => isset($result['message']) ? $result['message'] : 'Échec de l\'authentification'];
    }
}