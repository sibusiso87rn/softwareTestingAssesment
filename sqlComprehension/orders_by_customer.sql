SELECT COUNT(*) FROM orders JOIN customers
    ON orders.customer_id = customers.customer_id
        WHERE customers.customer_id IN (
           SELECT customer_id FROM customers WHERE company_name = 'Simons bistro')
               AND orders.ship_via IN (SELECT shipper_id FROM shippers WHERE company_name = 'United Package')




