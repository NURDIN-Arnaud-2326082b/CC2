<?php

namespace service;

interface AuthApiInterface
{
    /**
     * Authentifie un utilisateur via une API externe
     * @param string $login Identifiant de l'utilisateur
     * @param string $password Mot de passe de l'utilisateur
     * @return array Résultat avec 'success' (bool) et 'user' ou 'error' (string)
     */
    public function authenticate($login, $password);
}
