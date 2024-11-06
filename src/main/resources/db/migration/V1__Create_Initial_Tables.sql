CREATE TABLE author (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    birth_date DATE DEFAULT NULL
);

CREATE TABLE book (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    release_date DATE DEFAULT NULL
);

CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(250) NOT NULL,
    password VARCHAR(50) NOT NULL,
    name VARCHAR(50) DEFAULT NULL,
    surname VARCHAR(50) DEFAULT NULL
);

CREATE TABLE author_book (
    author_id INT NOT NULL,
    book_id INT NOT NULL,
    PRIMARY KEY (author_id, book_id),
    CONSTRAINT author_book_author_fk FOREIGN KEY (author_id) REFERENCES author (id),
    CONSTRAINT author_book_book_fk FOREIGN KEY (book_id) REFERENCES book (id)
);

CREATE TABLE user_author (
    user_id INT NOT NULL,
    author_id INT NOT NULL,
    PRIMARY KEY (author_id, user_id),
    CONSTRAINT user_author_user_fk FOREIGN KEY (user_id) REFERENCES user (id),
    CONSTRAINT user_author_author_fk FOREIGN KEY (author_id) REFERENCES author (id)
);