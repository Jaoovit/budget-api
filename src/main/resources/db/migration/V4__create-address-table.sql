CREATE TABLE address (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    state VARCHAR(250) NOT NULL,
    city VARCHAR(250) NOT NULL,
    street VARCHAR(250) NOT NULL,
    number VARCHAR(50) NOT NULL,
    client_id UUID,
    FOREIGN KEY (client_id) REFERENCES clients(id) ON DELETE CASCADE
)