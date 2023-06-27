CREATE TABLE IF NOT EXISTS users (
  id SERIAL PRIMARY KEY,
  user_name VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  status BOOLEAN NOT NULL,
  role VARCHAR(255) NOT NULL
);

CREATE INDEX idx_users_username_password ON users (user_name, password);

INSERT INTO users (user_name, password, status, role)
VALUES (
        'admin-user',
        '$2a$10$5lrL6L3bwHeqiqGc1gJg6OKRKeBxsxU27gXj2Za/mGYqFYK/SFLF6',
        true,
        'ROLE_ADMIN'
    ),
    (
        'simple-user',
        '$2a$10$5lrL6L3bwHeqiqGc1gJg6OKRKeBxsxU27gXj2Za/mGYqFYK/SFLF6',
        true,
        'ROLE_USER'
    ),
    (
        'blocked-user',
        '$2a$10$5lrL6L3bwHeqiqGc1gJg6OKRKeBxsxU27gXj2Za/mGYqFYK/SFLF6',
        false,
        'ROLE_USER'
    );