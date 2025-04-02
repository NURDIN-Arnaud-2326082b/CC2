<?php
namespace gui;
class layout
{
    protected $templateFile;

    public function __construct( $templateFile )
    {
        $this->templateFile = $templateFile;
    }

    public function display( $title, $connexion, $content )
    {
        $page = file_get_contents( $this->templateFile );
        $page = str_replace( ['%title%','%connexion%','%content%'], [$title, $connexion, $content], $page);
        echo $page;
    }

}