-- Use the IRCTC database
USE irctc;

-- Insert Sample Train Data
INSERT INTO trains (train_name, source, destination, total_seats, available_seats, fare, travel_date)
VALUES
('Shatabdi Express', 'Delhi', 'Amritsar', 100, 100, 1200.0, '2025-08-30'),
('Rajdhani Express', 'Delhi', 'Mumbai', 150, 150, 2200.0, '2025-08-31'),
('Duronto Express', 'Kolkata', 'Pune', 200, 200, 1800.0, '2025-09-01'),
('Garib Rath', 'Delhi', 'Lucknow', 120, 120, 800.0, '2025-08-29'),
('Jan Shatabdi', 'Chandigarh', 'Delhi', 90, 90, 700.0, '2025-08-30'),
('Karnataka Express', 'Bangalore', 'Delhi', 180, 180, 2500.0, '2025-09-02'),
('Goa Express', 'Goa', 'Delhi', 170, 170, 2300.0, '2025-09-03'),
('Howrah Mail', 'Howrah', 'Chennai', 160, 160, 2100.0, '2025-09-04'),
('Konark Express', 'Bhubaneswar', 'Mumbai', 140, 140, 2000.0, '2025-09-01'),
('Mandovi Express', 'Mumbai', 'Goa', 100, 100, 900.0, '2025-08-28');

-- Insert Sample Passengers
INSERT INTO passengers (name, age, gender)
VALUES
('Aarav Sharma', 28, 'Male'),
('Priya Verma', 32, 'Female'),
('Rohit Mehta', 45, 'Male'),
('Ananya Gupta', 26, 'Female'),
('Vikram Singh', 35, 'Male'),
('Sneha Kapoor', 29, 'Female'),
('Arjun Yadav', 40, 'Male'),
('Meera Joshi', 23, 'Female'),
('Kunal Jain', 31, 'Male'),
('Neha Aggarwal', 27, 'Female');

-- Insert Some Bookings (for testing View History & Cancellation)
INSERT INTO bookings (passenger_id, train_id, seats_booked, status)
VALUES
(1, 1, 2, 'CONFIRMED'),
(2, 2, 1, 'CONFIRMED'),
(3, 3, 3, 'CANCELLED'),
(4, 5, 1, 'CONFIRMED'),
(5, 4, 2, 'CONFIRMED'),
(6, 7, 2, 'CANCELLED'),
(7, 6, 4, 'CONFIRMED'),
(8, 9, 1, 'CONFIRMED'),
(9, 8, 2, 'CONFIRMED'),
(10, 10, 1, 'CONFIRMED');
