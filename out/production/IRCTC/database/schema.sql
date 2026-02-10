CREATE DATABASE irctc;

USE irctc;

-- Train Table
CREATE TABLE trains (
    train_id INT AUTO_INCREMENT PRIMARY KEY,
    train_name VARCHAR(100) NOT NULL,
    source VARCHAR(50) NOT NULL,
    destination VARCHAR(50) NOT NULL,
    total_seats INT NOT NULL,
    available_seats INT NOT NULL,
    fare DOUBLE NOT NULL,
    travel_date DATE NOT NULL
);

-- Passenger Table
CREATE TABLE passengers (
    passenger_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    gender VARCHAR(10) NOT NULL
);

-- Booking Table
CREATE TABLE bookings (
    booking_id INT AUTO_INCREMENT PRIMARY KEY,
    passenger_id INT,
    train_id INT,
    seats_booked INT,
    booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) DEFAULT 'CONFIRMED',
    FOREIGN KEY (passenger_id) REFERENCES passengers(passenger_id),
    FOREIGN KEY (train_id) REFERENCES trains(train_id)
);

-- Sample Train Data
INSERT INTO trains (train_name, source, destination, total_seats, available_seats, fare, travel_date)
VALUES
('Shatabdi Express', 'Delhi', 'Amritsar', 100, 100, 1200.0, '2025-08-30'),
('Rajdhani Express', 'Delhi', 'Mumbai', 150, 150, 2200.0, '2025-08-31');
