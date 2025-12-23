CREATE TABLE author_sales_summary (
                                      id SERIAL PRIMARY KEY,
                                      author_id INT NOT NULL REFERENCES authors(id),
                                      total_quantity INT NOT NULL,
                                      total_revenue NUMERIC(10,2) NOT NULL,
                                      calculated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

-- FUNCTION: calculează venitul total pentru o carte
CREATE OR REPLACE FUNCTION get_book_revenue(p_book_id INT)
RETURNS NUMERIC(10,2)
LANGUAGE plpgsql
AS $$
DECLARE
total_revenue NUMERIC(10,2);
BEGIN
SELECT COALESCE(SUM(s.quantity * b.price), 0)
INTO total_revenue
FROM sales s
         JOIN books b ON b.id = s.book_id
WHERE s.book_id = p_book_id;

RETURN total_revenue;
END;
$$;

-- PROCEDURE: calculează total vânzări pentru un autor și salvează în tabela de summary
CREATE OR REPLACE PROCEDURE calculate_author_sales(p_author_id INT)
LANGUAGE plpgsql
AS $$
DECLARE
v_total_quantity INT;
    v_total_revenue NUMERIC(10,2);
BEGIN
SELECT
    COALESCE(SUM(s.quantity), 0),
    COALESCE(SUM(s.quantity * b.price), 0)
INTO
    v_total_quantity,
    v_total_revenue
FROM sales s
         JOIN books b ON b.id = s.book_id
WHERE b.author_id = p_author_id;

INSERT INTO author_sales_summary(author_id, total_quantity, total_revenue)
VALUES (p_author_id, v_total_quantity, v_total_revenue);
END;
$$;
