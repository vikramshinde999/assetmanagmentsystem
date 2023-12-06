import React from 'react'
import './css/home.css'
import { useNavigate } from 'react-router'
import LoggedInBar from './LoggedInBar'


function Home() {
    const navigate = useNavigate()

    return (
        <div className='container' id='home-con'>
          
            <div class="row" id = 'home-buttons'>
                <button className='accept-btn' onClick={() => {
                    navigate('/company/asset')
                }} >Asset</button>

                <button className='accept-btn' onClick={() => {
                    navigate('/company/employee')
                }}>Employee</button>

                <button className='accept-btn' onClick={() => {
                    navigate('/company/order')
                }}>Order</button>
            </div>
        </div>
    )
}

export default Home