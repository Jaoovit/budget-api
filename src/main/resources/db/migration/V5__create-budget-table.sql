CREATE TABLE budgets (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(250) NOT NULL,
    created_date TIMESTAMP NOT NULL,
    valid_date TIMESTAMP NOT NULL,
    approved BOOLEAN NOT NULL,
    total_price FLOAT NOT NULL,
    client_id UUID,
    FOREIGN KEY (client_id) REFERENCES clients(id) ON DELETE CASCADE
)