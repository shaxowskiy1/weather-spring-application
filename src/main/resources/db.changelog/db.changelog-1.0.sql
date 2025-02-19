--liquibase formatted sql
--changeset shaxowskiy:1
CREATE TABLE Users (
                       ID SERIAL PRIMARY KEY,
                       Login VARCHAR(255) NOT NULL,
                       Password VARCHAR(255) NOT NULL
);
-- rollback DROP TABLE Users;


-- liquibase formatted sql
-- changeset shaxowskiy:2
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
-- rollback DROP TABLE Locations;


-- liquibase formatted sql
-- changeset shaxowskiy:3
CREATE TABLE Sessions (
                          ID UUID PRIMARY KEY,            -- Уникальный идентификатор сессии (GUID)
                          UserId INT NOT NULL,            -- Пользователь, для которого создана сессия
                          ExpiresAt TIMESTAMP NOT NULL,   -- Время истечения сессии
                          CONSTRAINT fk_user
                              FOREIGN KEY (UserId)
                                  REFERENCES Users(ID)
                                  ON DELETE CASCADE            -- Удаление сессий при удалении пользователя
);
-- rollback DROP TABLE Sessions;