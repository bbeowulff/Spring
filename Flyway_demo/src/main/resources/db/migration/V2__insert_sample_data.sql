INSERT INTO authors (name) VALUES
                               ('J. K. Rowling'),
                               ('J. R. R. Tolkien'),
                               ('George R. R. Martin');

INSERT INTO books (title, author_id, published_year, price) VALUES
                                                                ('Harry Potter and the Philosopher''s Stone', 1, 1997, 39.99),
                                                                ('The Hobbit', 2, 1937, 29.99),
                                                                ('A Game of Thrones', 3, 1996, 49.99);

INSERT INTO sales (book_id, sold_at, quantity) VALUES
                                                   (1, NOW() - INTERVAL '10 days', 5),
                                                   (1, NOW() - INTERVAL '2 days', 3),
                                                   (2, NOW() - INTERVAL '7 days', 4),
                                                   (3, NOW() - INTERVAL '1 day', 2);
