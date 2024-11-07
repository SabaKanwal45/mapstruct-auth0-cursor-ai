CREATE TABLE book_review (
    id INT AUTO_INCREMENT PRIMARY KEY,
    book_id INT NOT NULL,
    user_id INT NOT NULL,
    rating INT NOT NULL,
    review_text VARCHAR(1000),
    created_at DATETIME NOT NULL,
    CONSTRAINT fk_review_book FOREIGN KEY (book_id) REFERENCES book(id),
    CONSTRAINT fk_review_user FOREIGN KEY (user_id) REFERENCES user(id),
    CONSTRAINT uk_book_user_review UNIQUE (book_id, user_id),
    CONSTRAINT chk_rating CHECK (rating BETWEEN 1 AND 5)
); 