import React, { useState, useEffect } from 'react';
import { Paper, Typography, TextField, Button, List, ListItem, ListItemText, CircularProgress } from '@mui/material';
import { addExpense, fetchExpenses } from '../services/api';

function ExpenseTracker() {
  const [expenses, setExpenses] = useState([]);
  const [newExpense, setNewExpense] = useState({ description: '', amount: '' });
  const [loading, setLoading] = useState(true);

  const loadExpenses = async () => {
    setLoading(true);
    try {
      const data = await fetchExpenses();
      console.log("Fetched expenses:", data); // Debugging log
      setExpenses(Array.isArray(data) ? data : (data?.data || []));
    } catch (error) {
      console.error("Error fetching expenses:", error);
      setExpenses([]);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadExpenses();
  }, []);

  const handleAddExpense = async () => {
    if (newExpense.description && newExpense.amount) {
      try {
        await addExpense(newExpense);
        setNewExpense({ description: '', amount: '' });
        await loadExpenses();
      } catch (error) {
        console.error("Error adding expense:", error);
      }
    }
  };

  return (
    <Paper elevation={3} style={{ padding: '20px' }}>
      <Typography variant="h6" gutterBottom>
        Expense Tracker
      </Typography>
      <TextField
        label="Description"
        value={newExpense.description}
        onChange={(e) => setNewExpense({ ...newExpense, description: e.target.value })}
        fullWidth
        margin="normal"
      />
      <TextField
        label="Amount"
        type="number"
        value={newExpense.amount}
        onChange={(e) => setNewExpense({ ...newExpense, amount: e.target.value })}
        fullWidth
        margin="normal"
      />
      <Button variant="contained" color="primary" onClick={handleAddExpense}>
        Add Expense
      </Button>
      {loading ? (
        <CircularProgress />
      ) : (
        <List>
          {expenses.length > 0 ? (
            expenses.map((expense) => (
              <ListItem key={expense.id || `expense-${expense.description}-${expense.amount}`}>
                <ListItemText primary={expense.description} secondary={`$${expense.amount}`} />
              </ListItem>
            ))
          ) : (
            <ListItem key="no-expenses">
              <ListItemText primary="No expenses to display" />
            </ListItem>
          )}
        </List>
      )}
    </Paper>
  );
}

export default ExpenseTracker;