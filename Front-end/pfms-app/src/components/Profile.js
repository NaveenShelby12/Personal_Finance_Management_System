import React, { useState, useEffect } from 'react';
import { Paper, Typography, TextField, Button } from '@mui/material';
import { fetchUserProfile, updateUserProfile } from '../services/api';

function Profile() {
  const [profile, setProfile] = useState({ name: '', email: '' });

  useEffect(() => {
    const loadProfile = async () => {
      const data = await fetchUserProfile();
      setProfile(Array.isArray(data) ? data : (data?.data || []));
    };
    loadProfile();
  }, []);

  const handleUpdate = async () => {
    await updateUserProfile(profile);
    // You might want to show a success message here
  };

  return (
    <Paper elevation={3} style={{ padding: '20px' }}>
      <Typography variant="h6" gutterBottom>
        User Profile
      </Typography>
      <TextField
        label="Name"
        value={profile.username}
        onChange={(e) => setProfile({ ...profile, name: e.target.value })}
        fullWidth
        margin="normal"
      />
      <TextField
        label="Email"
        value={profile.email}
        onChange={(e) => setProfile({ ...profile, email: e.target.value })}
        fullWidth
        margin="normal"
      />
      <Button variant="contained" color="primary" onClick={handleUpdate}>
        Update Profile
      </Button>
    </Paper>
  );
}

export default Profile;