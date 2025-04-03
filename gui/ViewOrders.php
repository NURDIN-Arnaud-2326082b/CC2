<?php
namespace gui;

class ViewOrders
{
    private $layout;
    private $login;
    private $presenter;

    public function __construct($layout, $login, $presenter)
    {
        $this->layout = $layout;
        $this->login = $login;
        $this->presenter = $presenter;
    }

    public function display()
    {
        $orders = $this->presenter->getOrders();

        $content = "<h2>Orders for {$this->login}</h2>";

        if (empty($orders)) {
            $content .= "<p>No orders found.</p>";
        } else {
            $content .= "<table class='table'>
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Date</th>
                        <th>Total</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>";

            foreach ($orders as $order) {
                $content .= "<tr>
                    <td>{$order['id']}</td>
                    <td>{$order['date']}</td>
                    <td>{$order['total']} €</td>
                    <td>{$order['status']}</td>
                    <td>
                        <a href='/index.php/order?id={$order['id']}' class='btn btn-primary'>View Details</a>
                    </td>
                </tr>";
            }

            $content .= "</tbody></table>";
        }

        $this->layout->setTitle("Orders");
        $this->layout->setNavigation("<a href='/index.php/panier'>Panier</a> | <a href='/index.php/orders'>Orders</a> | <a href='/index.php/logout'>Logout</a>");
        $this->layout->setContent($content);
        $this->layout->render();
    }
}
