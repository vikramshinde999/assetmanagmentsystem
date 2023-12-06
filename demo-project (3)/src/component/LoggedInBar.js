import React from 'react'
import { NavLink } from 'react-router-dom'
import './css/loginBar.css'

function LoggedInBar() {

  return (
      <nav>
      <div className='row' id ='loggedBar'>
        <p md={8}>Hello</p>
        <div>
              <NavLink md={2} to='home'>Home</NavLink><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
              <NavLink md={2} to ='about'>About</NavLink>
           </div>
          </div>
          
     </nav>
  )
}

export default LoggedInBar