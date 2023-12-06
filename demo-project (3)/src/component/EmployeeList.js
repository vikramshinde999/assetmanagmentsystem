import React, { useState } from 'react'
import EmployeeService from '../service/EmployeeService'
import './css/employee.css'
import { useEffect } from 'react'
import { Link } from 'react-router-dom'
export function EmployeeList (){
    
    const [employees,setEmployees]=useState({employees: []})
          
    useEffect(() => {
        return () => {
            EmployeeService.getAllEmployees().then((response) => {
                
                setEmployees({
                   employees:response.data
               })
            }).catch((err) => {
                alert(err)
            })
        };
    },[])
   
    
    return (
        <div className='col-11'>
            <table  className='table table-striped'>
                <thead className='thead'>
                    <tr>
                        <td>ID</td>
                        <td>First Name</td>
                        <td>lastName</td>
                        <td>Email Id</td>
                        <td>Manager ID</td>
                        <td>Role ID</td>
                        <td>Update</td>
                        <td>Delete</td>
                    </tr>
                </thead>
                <tbody>
                    {
                        employees.employees.map(emp => {
                            return (
                                <tr key={emp.id}>
                                <td>{emp.id}</td>
                                <td>{emp.firstName}</td>
                                <td>{emp.lastName}</td>
                                <td>{emp.emailId}</td>
                                <td>{emp.managerId}</td>
                                <td>{emp.userId}</td>
                                    <td>
                                        {/* <button onClick={() => {
                                            navigate(`employee/update/${emp.id}`)
                                        }}></button> */}
                                        <Link className='accept-btn' to={{ pathname: `/company/employee/update/${emp.id}`}}>Update</Link></td>
                                    <td><button className='reject-btn' onClick={() => {
                                        EmployeeService.deleteEmpById(emp.id).then((res) => {
                                             alert(res.data)
                                        }).catch((error) => {
                                            alert(error)
                                         })
                                }}>delete</button></td>
                            </tr>  
                            )
                        })
                    }
                </tbody>
            </table>
       </div>
    )
  }


export default EmployeeList