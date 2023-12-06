import React from 'react'
import { Outlet, useNavigate } from 'react-router'
import './css/asset.css'


function AssetComponents() {
    const navigate = useNavigate()
    
  return (
    //   <div>
    //       <div className='d-flex justify-content-between'>
    //           <div className='asset-com-div2'>
    //                <h3>Assets</h3>
    //           </div>
    //           <div className='asset-com-div3'>
    //           <input type='text' placeholder='Asset Name'></input><button>Search</button>
    //           </div>
    //       </div>
    //       <div className='' id='asset-button'>
    //           <button className='button-1' onClick={() => {
    //               navigate('allasset')
    //           }}>All Asset</button>
    //           <button  className='button-1'onClick={() => {
    //               navigate('addasset')
    //           }}>Add Asset</button>
    //           <button  className='button-1' onClick={() => {
    //               navigate('addasset')
    //           }}>Place Order</button>
    //       </div>
    //       <div>
             
    //       </div>
    //       <Outlet/>
          
    //  </div>
    <div class="container-fluid p-0">
        
          
    <div class="row-12" id = 'asset-head' >
              <h2 className='col'>Assets</h2>
              <div className='col'>
                  <input type="text" aria-label="Search"></input><button onClick={() => {
                      
              }}>Search</button>
              </div>
              
    </div>
    
    <div class="row" id = 'row'>
        
        <div class="col">
              <button className='btn btn-primary' onClick={() => {
                   navigate('allasset')
              }}>All Asset</button>
          </div>
          
          <div class="col">
              <button className='btn btn-primary' onClick={() => {
                  navigate('addasset')
               }}>Add Asset</button>
              </div>
              <div class="col">
              <button className='btn btn-primary'  onClick={() => {
                  navigate('placeorder')
               }}>Raise Order</button>
          </div>
        
      </div>
      <Outlet/>
    
  </div>
  )
}

export default AssetComponents