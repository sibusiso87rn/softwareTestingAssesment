SELECT CAST (EXTRACT(YEAR FROM order_date) AS VARCHAR) AS orderYears, COUNT(order_date) FROM orders WHERE order_date > '1996-01-01' GROUP BY orderYears;
