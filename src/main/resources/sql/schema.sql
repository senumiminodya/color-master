CREATE DATABASE color_master;

USE color_master;

CREATE TABLE customer (
                          cus_id VARCHAR(12) PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          address VARCHAR(255) NOT NULL,
                          phone_no VARCHAR(20) NOT NULL
);

CREATE TABLE supplier (
                          sup_id VARCHAR(12) PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          phone_no VARCHAR(20) NOT NULL,
                          product VARCHAR(255) NOT NULL
);

CREATE TABLE base_stock (
                            base_id VARCHAR(12) PRIMARY KEY,
                            base_type VARCHAR(255) NOT NULL,
                            sup_id VARCHAR(12),
                            size VARCHAR(20) NOT NULL,
                            qty INT(12) NOT NULL,
                            FOREIGN KEY (sup_id) REFERENCES supplier(sup_id)
);

CREATE TABLE paint_stock (
                             paint_id VARCHAR(12) PRIMARY KEY,
                             name VARCHAR(255) NOT NULL,
                             type VARCHAR(255) NOT NULL,
                             base_id VARCHAR(12),
                             size VARCHAR(20) NOT NULL,
                             qty INT(12) NOT NULL,
                             FOREIGN KEY (base_id) REFERENCES base_stock(base_id)
);

CREATE TABLE driver (
                        driver_id VARCHAR(12) PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        address VARCHAR(255) NOT NULL,
                        phone_no VARCHAR(20) NOT NULL
);

CREATE TABLE vehicle (
                         vehicle_id VARCHAR(12) PRIMARY KEY,
                         owner_name VARCHAR(255) NOT NULL,
                         owner_phone_no VARCHAR(20) NOT NULL
);

CREATE TABLE orders (
                        order_no VARCHAR(12) PRIMARY KEY,
                        cus_id VARCHAR(12),
                        description VARCHAR(255) NOT NULL,
                        size VARCHAR(20) NOT NULL,
                        qty INT(12) NOT NULL,
                        payment_type VARCHAR(50) NOT NULL,
                        pay_amount DECIMAL(10, 2) NOT NULL,
                        FOREIGN KEY (cus_id) REFERENCES customer(cus_id)
);

CREATE TABLE delivery (
                          order_no VARCHAR(12) PRIMARY KEY,
                          vehicle_id VARCHAR(12),
                          driver_id VARCHAR(12),
                          FOREIGN KEY (vehicle_id) REFERENCES vehicle(vehicle_id),
                          FOREIGN KEY (driver_id) REFERENCES driver(driver_id)
);

CREATE TABLE order_paint_details (
                                     order_no VARCHAR(12),
                                     paint_id VARCHAR(12),
                                     date DATE NOT NULL,
                                     FOREIGN KEY (order_no) REFERENCES orders(order_no),
                                     FOREIGN KEY (paint_id) REFERENCES paint_stock(paint_id)
);

SELECT o.order_no, o.cus_id, o.pay_amount, o.date, c.name
FROM orders o
JOIN customer c ON o.cus_id = c.cus_id
ORDER BY o.date DESC;





















