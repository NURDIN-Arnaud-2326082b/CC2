<?php
namespace gui;

class ViewOrderDetail
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
        $order = $this->presenter->getOrderDetail();

        $content = "<h2>Order Details</h2>";

        if (empty($order)) {
            $content .= "<p>Order not found.</p>";
        } else {
            $content .= "<div class='card mb-4'>
                <div class='card-header'>
                    Order #{$order['id']}
                </div>
                <div class='card-body'>
                    <p><strong>Date:</strong> {$order['date']}</p>
                    <p><strong>Status:</strong> {$order['status']}</p>
                    <p><strong>Total:</strong> {$order['total']} €</p>
                </div>
            </div>";

            if (!empty($order['items'])) {
                $content .= "<h3>Order Items</h3>
                <table class='table'>
                    <thead>
                        <tr>
                            <th>Product</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>";

                foreach ($order['items'] as $item) {
                    $content .= "<tr>
                        <td>{$item['product_name']}</td>
                        <td>{$item['quantity']}</td>
                        <td>{$item['unit_price']} €</td>
                        <td>{$item['total']} €</td>
                    </tr>";
                }

                $content .= "</tbody></table>";
            }
        }

        $content .= "<p><a href='/index.php/orders' class='btn btn-secondary'>Back to Orders</a></p>";

        $this->layout->setTitle("Order Details");
        $this->layout->setNavigation("<a href='/index.php/panier'>Panier</a> | <a href='/index.php/orders'>Orders</a> | <a href='/index.php/logout'>Logout</a>");
        $this->layout->setContent($content);
        $this->layout->render();
    }
}
