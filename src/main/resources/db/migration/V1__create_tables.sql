
CREATE TABLE Users (
                       id SERIAL PRIMARY KEY,
                       login VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL
);
-- rollback DROP TABLE Users;


CREATE TABLE Locations (
                           id SERIAL PRIMARY KEY,          -- Автоинкремент, первичный ключ
                           name VARCHAR(255) NOT NULL,     -- Название локации
                           user_id INT NOT NULL,            -- Пользователь, добавивший локацию
                           latitude DECIMAL(9, 6) NOT NULL, -- Широта
                           longitude DECIMAL(9, 6) NOT NULL, -- Долгота
                           CONSTRAINT fk_user
                               FOREIGN KEY (user_id)
                                   REFERENCES Users(ID)
                                   ON DELETE CASCADE            -- Удаление локаций при удалении пользователя
);

CREATE TABLE Sessions (
                          id serial PRIMARY KEY,            -- Уникальный идентификатор сессии (GUID)
                          user_id INT NOT NULL,            -- Пользователь, для которого создана сессия
                          expires_at TIMESTAMP NOT NULL,   -- Время истечения сессии
                          CONSTRAINT fk_user
                              FOREIGN KEY (user_id)
                                  REFERENCES Users(id)
                                  ON DELETE CASCADE            -- Удаление сессий при удалении пользователя
);
