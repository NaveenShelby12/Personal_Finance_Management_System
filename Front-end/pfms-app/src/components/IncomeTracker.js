import React, { useState, useEffect } from 'react';
import { Paper, Typography, TextField, Button, List, ListItem, ListItemText, CircularProgress } from '@mui/material';
import { addIncome, fetchIncomes } from '../services/api';

function IncomeTracker() {
  const [incomes, setIncomes] = useState([]);
  const [newIncome, setNewIncome] = useState({ description: '', amount: '' });
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const loadIncomes = async () => {
    setLoading(true);
    setError(null);
    try {
      const data = await fetchIncomes();
      console.log("Fetched incomes:", data); // Debugging log
      setIncomes(Array.isArray(data) ? data : (data?.data || []));
    } catch (err) {
      console.error("Error fetching incomes:", err);
      setError("Failed to load incomes. Please try again later.");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadIncomes();
  }, []);

  const handleAddIncome = async () => {
    if (newIncome.description && newIncome.amount) {
      setLoading(true);
      try {
        await addIncome(newIncome);
        await loadIncomes(); // Reload incomes after adding
        setNewIncome({ description: '', amount: '' });
      } catch (err) {
        console.error("Error adding income:", err);
        setError("Failed to add income. Please try again.");
      } finally {
        setLoading(false);
      }
    }
  };

  return (
    <Paper elevation={3} style={{ padding: '20px' }}>
      <Typography variant="h6" gutterBottom>
        Income Tracker
      </Typography>
      <TextField
        label="Description"
        value={newIncome.description}
        onChange={(e) => setNewIncome({ ...newIncome, description: e.target.value })}
        fullWidth
        margin="normal"
      />
      <TextField
        label="Amount"
        type="number"
        value={newIncome.amount}
        onChange={(e) => setNewIncome({ ...newIncome, amount: e.target.value })}
        fullWidth
        margin="normal"
      />
      <Button variant="contained" color="primary" onClick={handleAddIncome} disabled={loading}>
        {loading ? <CircularProgress size={24} /> : 'Add Income'}
      </Button>
      {error && <Typography color="error">{error}</Typography>}
      {loading ? (
        <CircularProgress />
      ) : (
        <List>
          {incomes.length > 0 ? (
            incomes.map((income) => (
              <ListItem key={income.id}>
                <ListItemText primary={income.description} secondary={`$${income.amount}`} />
              </ListItem>
            ))
          ) : (
            <ListItem>
              <ListItemText primary="No incomes to display" />
            </ListItem>
          )}
        </List>
      )}
    </Paper>
  );
}

export default IncomeTracker;