-- Creation of disclaimer table
CREATE TABLE IF NOT EXISTS disclaimer (
    id INT NOT NULL AUTO_INCREMENT,
    'name' VARCHAR(255) NOT NULL,
    text TEXT NOT NULL,
    version VARCHAR(255) NOT NULL,
    create_at DATETIME NOT NULL,
    update_at DATETIME NOT NULL,
    PRIMARY KEY (id)
);

-- Creation of acceptance table
CREATE TABLE IF NOT EXISTS acceptance (
    disclaimer_id INT NOT NULL,
    user_id INT NOT NULL,
    create_at DATETIME NOT NULL,
    PRIMARY KEY (disclaimer_id, user_id),
    FOREIGN KEY (disclaimer_id) REFERENCES disclaimer(id)
);