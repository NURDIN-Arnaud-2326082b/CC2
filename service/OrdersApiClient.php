<?php
namespace service;

class OrdersApiClient implements OrdersApiInterface
{
    private $baseUrl;

    public function __construct($baseUrl) {
        $this->baseUrl = $baseUrl;
    }

    public function getUserOrders($userId) {
        $url = $this->baseUrl . "/orders?userId=" . urlencode($userId);
        return $this->makeApiCall($url);
    }

    public function getOrderById($orderId) {
        $url = $this->baseUrl . "/orders/" . urlencode($orderId);
        return $this->makeApiCall($url);
    }

    private function makeApiCall($url) {
        $curl = curl_init($url);
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
        $response = curl_exec($curl);
        $status = curl_getinfo($curl, CURLINFO_HTTP_CODE);
        curl_close($curl);

        if ($status === 200) {
            return json_decode($response, true);
        }
        return null;
    }
}