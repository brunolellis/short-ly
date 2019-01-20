
CREATE TABLE urls(
    id INTEGER(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    short_url VARCHAR(100) NOT NULL,
    url VARCHAR(5000) NOT NULL,
    visits INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY (ID)
);
