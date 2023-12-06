import React from 'react'
import { Outlet, useNavigate } from 'react-router'
import './css/employee.css'

function EmployeeComponent() {
    const navigate =  useNavigate()
  return (
    <div class="container">
            
    <div class="row" id='emp-con'>
        
        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6" id='emp-head'>
            <h3>Employees</h3>
        </div>
        
        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6" id='emp-btn'>
            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                      <button className='emp-btn1' onClick={() => {
                          navigate('emplist')
                      }}>All Employee</button>
            </div>
            
            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                <button className='emp-btn1' onClick={() => {
                          navigate('addemp')
                }}>Add Employee</button>
            </div>
        </div>
        
        
    </div>
    <Outlet/>
</div>
  )
}

export default EmployeeComponent