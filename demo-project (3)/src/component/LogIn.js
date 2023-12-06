
import React, { useState } from 'react'
import './css/login.css'
import User from '../model/User'
import { useDispatch, useSelector } from 'react-redux';
import { login } from '../redux/userSlice';



function Login () {
    
    const [state, setState] = useState({ user: new User() })
    const [email, setMail] = useState('')
    const [empId, setEmpId] = useState('')


    const dispatch = useDispatch();
    const handleLogin = (e) => {
       
       
     alert(state.user)
        console.log(state.user)
            dispatch(login(state.user))
        }

  return (
      <div className='container' id='login'>
          
          <div class="col-xs-7 col-sm-7 col-md-7 col-lg-7" id ='heading' >
              <div className='jumbotron'>
        <h1 className="display-4">Asset System</h1>
            <hr />
            <p className="my-4">
            Our IT Asset Management software (ITAM) platform gives you complete visibility and control of your assets. It helps you track and manage assets throughout their lifecycle more efficiently with IT workflows that work as you see fit. Our ITAM tracks all history details of hardware and software assets throughout their lifecycles with no limitations.
            </p>

         </div> 
          </div>
          
        <form id='login_form' className='col-xs-5 col-sm-5 col-md-5 col-lg-5'>

        <h1>Login</h1>
              <input type='text' placeholder='Email ID'  
                  onChange={(e) => {
                    e.preventDefault()
                      setState({
                        ...state.user,
                        emailId : e.target.value
                    })
                }}
        />
        <br></br>
        <input type='number' placeholder='Employee ID' 
                  onChange={(e) => {
                      setState({
                    
                          ...state.user,
                        id : e.target.value

                      })
                  }}
        />
        <br></br>
              <button className='log_in_btn' onClick={handleLogin} >Log in</button>
        </form>
      </div>
  
      
  )
}

export default Login;