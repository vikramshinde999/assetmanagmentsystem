import React from 'react'
import './css/navbar.css'
import logo from './css/logo.png'
export const Navbar = () => {
  return (
    <div>
    <nav class="navbar navbar-light bg-light" id='navbar'>
    <a class="navbar-brand" href="#">
      <img src={logo} width="70" class="d-inline-block align-middle" alt=""/>
      <></>   Capgemini IT Asset Management 
    </a>
      </nav>
      {/* <div className='jumbotron'>
      <h1 className="display-4">Asset System</h1>
            <hr />
            <p className="my-4">
                Management of the assets 
            </p>

       </div> */}
      </div>
  )
}
