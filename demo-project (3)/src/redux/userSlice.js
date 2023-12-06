import axios from "axios";
import { createSlice, createAsyncThunk } from '@reduxjs/toolkit'

const initialState = {
  loading: false,
  user: '',
  loggedin:false,
  error: ''
}

// Generates pending, fulfilled and rejected action types
export const login = createAsyncThunk('/login', async (userCredential) => {
  
  console.log(JSON.stringify(userCredential))
  return axios
    .get('http://localhost:8888/company/login',userCredential)
    .then((response) => response.data)
})

const userSlice = createSlice({
  name: 'user',
  initialState,
  extraReducers: builder => {
    builder.addCase(login.pending, state => {
      state.loading = true
    })
    builder.addCase(login.fulfilled, (state, action) => {
      state.loading = false
      state.user = action.payload
      state.loggedin = true
      state.error = ''
      
    })
    builder.addCase(login.rejected, (state, action) => {
      state.loading = false
      state.user = []
      state.error = action.error.message
    })
  }
})

export default userSlice.reducer