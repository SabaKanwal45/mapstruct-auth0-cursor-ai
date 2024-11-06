-- Insert Authors
INSERT INTO author (id, name, surname, birth_date) VALUES
(1, 'Patricia', 'Brown', NULL),
(2, 'James', 'Smith', '1964-07-01');

-- Insert Books
INSERT INTO book (id, title, release_date) VALUES
(1, 'The Adventure', '2019-07-14'),
(2, 'DTOs Are Awesome!', '2020-06-11'),
(3, '9 Keys To Academic Success', NULL);

-- Insert Users
INSERT INTO user (id, email, password, name, surname) VALUES
(1, 'test@test.com', 'test', 'John', 'Smith');

-- Insert Author-Book relationships
INSERT INTO author_book (author_id, book_id) VALUES
(1, 2),
(1, 3),
(2, 1),
(2, 2);