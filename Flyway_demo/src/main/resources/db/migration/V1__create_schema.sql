CREATE TABLE authors (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(100) NOT NULL
);

CREATE TABLE books (
                       id SERIAL PRIMARY KEY,
                       title VARCHAR(200) NOT NULL,
                       author_id INT NOT NULL REFERENCES authors(id),
                       published_year INT NOT NULL,
                       price NUMERIC(10,2) NOT NULL
);

CREATE TABLE sales (
                       id SERIAL PRIMARY KEY,
                       book_id INT NOT NULL REFERENCES books(id),
                       sold_at TIMESTAMP NOT NULL DEFAULT NOW(),
                       quantity INT NOT NULL
);
