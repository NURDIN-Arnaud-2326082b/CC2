<?php

namespace service;

class UserChecking
{
    private $authApi;

    /**
     * Constructeur avec injection de l'API d'authentification
     * @param AuthApiInterface|null $authApi Client API d'authentification
     */
    public function __construct(AuthApiInterface $authApi = null) {
        $this->authApi = $authApi;
    }

    /**
     * Authentifie un utilisateur via l'API ou la base de données
     * @param mixed $data Accès aux données utilisateur
     * @param string $login Identifiant de l'utilisateur
     * @param string $password Mot de passe de l'utilisateur
     * @return bool Résultat de l'authentification
     */
    public function authenticate($login, $password, $data = null) {
        // Si l'API est disponible, l'utiliser
        if ($this->authApi) {
            $result = $this->authApi->authenticate($login, $password);
            return $result['success'];
        }

        // Sinon utiliser la méthode classique avec la base de données
        return ($data->getUser($login, $password) != null);
    }

    /**
     * Méthode pour maintenir la compatibilité avec le code existant
     */
    public function checkAuth($data, $login, $password) {
        return $this->authenticate($login, $password, $data);
    }
}
