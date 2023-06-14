drop table product;
create table product(id bigint auto_increment, product_id UUID, price DECFLOAT, discount_type INTEGER);
INSERT INTO product (product_id, price, discount_type) VALUES ('68b0a517-ebee-4e75-a514-ccba8f4af4ba', 300, 0);
INSERT INTO product (product_id, price, discount_type) VALUES ('3c56bd40-9c2d-4c96-b130-d836d29bcd52', 200, 1);
