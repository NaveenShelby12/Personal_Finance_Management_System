import React, { useState, useEffect } from 'react';
import { Grid, Paper, Typography } from '@mui/material';
import ExpenseTracker from './ExpenseTracker';
import IncomeTracker from './IncomeTracker';
import Chart from './Chart';
import { fetchDashboardData } from '../services/api';

function Dashboard() {
  const [dashboardData, setDashboardData] = useState(null);

  console.log("dashboard = ", dashboardData);
  useEffect(() => {
    const loadDashboardData = async () => {
      const data = await fetchDashboardData();
      setDashboardData(Array.isArray(data) ? data : (data?.data || []));
    };
    loadDashboardData();
  }, []);

  if (!dashboardData) return <Typography>Loading...</Typography>;

  return (
    <Grid container spacing={3}>
      <Grid item xs={12}>
        <Typography variant="h4" gutterBottom>
          Dashboard
        </Typography>
      </Grid>
      <Grid item xs={12} md={8}>
        <Paper elevation={3} style={{ padding: '20px' }}>
          {dashboardData.financialOverviewList && dashboardData.financialOverviewList.length > 0 ? (
            <Chart data={dashboardData.financialOverviewList} />
          ) : (
            <Typography variant="body1" align="center">
              No financial overview data available
            </Typography>
          )}
        </Paper>
      </Grid>
      <Grid item xs={12} md={4}>
        <Paper elevation={3} style={{ padding: '20px' }}>
          <Typography variant="h6" gutterBottom>
            Quick Summary
          </Typography>
          <Typography>Total Income: ${dashboardData.totalIncome}</Typography>
          <Typography>Total Expenses: ${dashboardData.totalExpenses}</Typography>
          <Typography>Net Savings: ${dashboardData.netSavings}</Typography>
        </Paper>
      </Grid>
      <Grid item xs={12} md={6}>
        <ExpenseTracker />
      </Grid>
      <Grid item xs={12} md={6}>
        <IncomeTracker />
      </Grid>
    </Grid>
  );
}

export default Dashboard;