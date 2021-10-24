  (SELECT mostorders FROM
        (SELECT customers.contact_name AS mostorders, COUNT(orders.customer_id) AS maxordercount
           FROM orders JOIN customers ON customers.customer_id = orders.customer_id
             GROUP BY customers.contact_name ORDER BY maxordercount DESC LIMIT 1)
	  t1)