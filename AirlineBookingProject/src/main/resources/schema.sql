CREATE TABLE IF NOT EXISTS app_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    bookings INT NOT NULL,
    grade VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS flight (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    departure_date DATE NOT NULL,
    return_date DATE NOT NULL,
    available_seats INT NOT NULL,
    destination VARCHAR(255) NOT NULL,
    passengers INT NOT NULL DEFAULT 0 -- 추가된 컬럼, 기본값 0
);
