CREATE TABLE Users (
                       id SERIAL PRIMARY KEY,
                       login VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL
);


CREATE TABLE Locations (
                           id SERIAL PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           user_id INT NOT NULL,
                           latitude DECIMAL(30, 20) NOT NULL,
                           longitude DECIMAL(30, 20) NOT NULL,
                           CONSTRAINT fk_user
                               FOREIGN KEY (user_id)
                                   REFERENCES Users(ID)
                                   ON DELETE CASCADE
);

CREATE TABLE Sessions (
                          session_id varchar(36) PRIMARY KEY,
                          user_id INT NOT NULL,
                          expires_at TIMESTAMP NOT NULL,
                          CONSTRAINT fk_user
                              FOREIGN KEY (user_id)
                                  REFERENCES Users(id)
                                  ON DELETE CASCADE
);
