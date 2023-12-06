import React from 'react'
import './css/order.css'
import { Outlet, useNavigate } from 'react-router'

function OrderComponent() {
    const navigate = useNavigate()
  return (
      
      <div class="container-fluid p-0">
        
        <div class="row-12" id='order-head'>
            <h2>Order</h2>
        </div>
        
        <div class="row" id = 'row'>
            
            <div class="col">
                  <button className='btn btn-primary' onClick={() => {
                      navigate('allorder')
                }}>All Order</button>
              </div>
              
              <div class="col">
                  <button className='btn btn-primary' onClick={() => {
                      navigate('pending')
                }}>Pending Order</button>
              </div>
              <div class="col">
                  <button className='btn btn-primary'
                  onClick={() => {
                    navigate('allocated')
              }}>Allocated order</button>
              </div>
              <div class="col">
                <button className='btn btn-primary'  onClick={() => {
                    navigate('released')
              }}>Released order</button>
              </div>
              
            
          </div>
          <Outlet/>
        
      </div>
      
  )
}

export default OrderComponent