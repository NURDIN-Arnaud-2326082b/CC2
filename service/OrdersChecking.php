<?php
namespace service;

class OrdersChecking
{
    private $ordersApi;

    public function __construct(OrdersApiInterface $ordersApi = null) {
        $this->ordersApi = $ordersApi;
    }

    public function getUserOrders($userId) {
        if ($this->ordersApi) {
            return $this->ordersApi->getUserOrders($userId);
        }
        return [];
    }

    public function getOrderById($orderId) {
        if ($this->ordersApi) {
            return $this->ordersApi->getOrderById($orderId);
        }
        return null;
    }
}
