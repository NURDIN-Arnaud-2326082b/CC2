<?php
namespace control;
class Presenter
{
    protected $panierCheck;

    public function __construct($panierCheck)
    {
        $this->panierCheck = $panierCheck;
    }

    public function getAllCommandeHTML()
    {
        $content = null;
        if ($this->panierCheck->getAnnoncesTxt() != null) {
            $content = '<h1>List of Panier</h1>  <ul>';
            foreach ($this->panierCheck->getAnnoncesTxt() as $commande) {
                $content .= ' <li>';
                $content .= '<a href="/index.php/post?id=' . $commande['id'] . '">' . $commande['title'] . '</a>';
                $content .= ' </li>';
            }
            $content .= '</ul>';
        }
        return $content;
    }

    /**
     * Store the orders list
     * @param array $orders List of orders
     */
    public function setOrders($orders) {
        $this->orders = $orders;
    }

    /**
     * Get the orders list
     * @return array List of orders
     */
    public function getOrders() {
        return isset($this->orders) ? $this->orders : [];
    }

    /**
     * Store the order detail
     * @param array $orderDetail Order details
     */
    public function setOrderDetail($orderDetail) {
        $this->orderDetail = $orderDetail;
    }

    /**
     * Get the order detail
     * @return array Order details
     */
    public function getOrderDetail() {
        return isset($this->orderDetail) ? $this->orderDetail : null;
    }
}