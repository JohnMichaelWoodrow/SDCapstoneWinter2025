USE taylorinsurance;

SELECT * FROM user;
SELECT * FROM customer;
SELECT * FROM agent;
SELECT * FROM admin;
SELECT * FROM home_quote;
SELECT * FROM quote;
SELECT * FROM policy;

INSERT INTO User (id, role) VALUES
(1, 'Customer'),
(2, 'Customer'),
(3, 'Admin'),
(4, 'Agent');

INSERT INTO Customer (id, name, email, PASSWORD, user_id) VALUES
(1, 'Alice Smith', 'alice.smith@example.com', 'password1', 1),
(2, 'Bob Johnson', 'bob.johnson@example.com', 'password2', 2);

INSERT INTO Admin (id, name, email, PASSWORD, user_id) VALUES
(1, 'Mike Johnson', 'mike.johnson@example.com', 'password3', 3);

INSERT INTO Agent (id, name, email, PASSWORD, user_id) VALUES
(1, 'John Doe', 'john.doe@example.com', 'password4', 4);


INSERT INTO Home (id, address, year_built, home_value, type_of_dwelling, heating_type, location, liability_limit) VALUES
(1, '123 Main St', 1980, 400000, 'Standalone', 'Oil', 'Urban', 1000000),
(2, '456 Elm St', 1995, 300000, 'Bungalow', 'Electric', 'Rural', 2000000);

INSERT INTO Vehicle (id, make, model, year, vin) VALUES
(1, 'Toyota', 'Camry', 2015, 'VIN1234567890'),
(2, 'Honda', 'Civic', 2010, 'VIN5678901234');

INSERT INTO Quote (id, quote_type, quote_price, expiry_date, is_paid, customer_id) VALUES
(1, 'Home', 5750, '2025-05-09', FALSE, 1),
(2, 'Auto', 8625, '2025-05-09', FALSE, 2);

INSERT INTO Home_Quote (id, home_id, base_rate, risk_factor) VALUES
(1, 1, 1200, 380);

INSERT INTO Auto_Quote (id, vehicle_id, driver_age, accident_count) VALUES
(2, 2, 24, 1);

INSERT INTO Policy (id, policy_number, policy_type, start_date, end_date, status, is_paid, base_premium, tax_rate, total_premium, customer_id, quote_id) VALUES
(1, 'P-1', 'Home', '2025-04-01', '2026-04-01', 'Active', TRUE, 5000, 0.15, 5750, 1, 1),
(2, 'P-2', 'Auto', '2025-04-01', '2026-04-01', 'Active', TRUE, 7500, 0.15, 8625, 2, 2);