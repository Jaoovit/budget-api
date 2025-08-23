CREATE TABLE products (
   id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
   name VARCHAR(100) NOT NULL,
   description VARCHAR(250) NOT NULL,
   price NUMERIC(10,2) NOT NULL,
   user_id UUID,
   FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);