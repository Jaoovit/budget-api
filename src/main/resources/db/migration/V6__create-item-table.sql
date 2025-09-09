CREATE TABLE items (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    quantity INTEGER NOT NULL,
    product_id UUID,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
    budget_id UUID,
    FOREIGN KEY (budget_id) REFERENCES budgets(id) ON DELETE CASCADE
)