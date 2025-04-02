<?php

namespace domain;
class User
{
    protected $login;
    protected $password;
    protected $name;
    protected $firstName;

    public function __construct($login, $password, $name, $firstName)
    {
        $this->login = $login;
        $this->password = $password;
        $this->name = $name;
        $this->firstName = $firstName;
    }

    public function getLogin()
    {
        return $this->login;
    }
}
