import React from 'react'
import { useEffect,useState } from 'react'
import { useParams } from 'react-router'
import Employee from '../model/Employee'
import EmployeeService from '../service/EmployeeService'

function UpdateEmployee() {

    const { empid } = useParams();
   
    const [state, setState] = useState({ employee: new Employee() })
    
  
    const [empFirstNameErr, setEmpFirstNameErr] = useState("");
    const [empLastNameErr, setEmpLastNameErr]=useState("");
    const [empEmailErr, setEmpEmailErr]=useState("");
    const [empManagerIdErr, setEmpManagerIdErr] = useState("");
    const [empRoleIdErr, setEmpRoleIdErr] = useState("");
    
    useEffect(() => {
      return () => {
        
        EmployeeService.getEmpById(empid).then((res) => {
         console.log(JSON.stringify(res.data))
          setState({
               employee: res.data
             })
           }) 
      };
    },[])
   
    const formValidation=()=>{
        
        let isValid=true;
       
        const empFirstNameErr={};
        const empLastNameErr = {};
        const empEmailErr = {};
        const empManagerIdErr = {};
        const empRoleIdErr = {};
     
        
        
        if(state.employee.firstName.trim().length<=0){
            empFirstNameErr.empFirstNameRequired="Employee first Name is invalid";
            isValid=false;
        }
        if(state.employee.lastName.trim().length<=0){
            empLastNameErr.empLastNameRequired="Employee last Name is invalid";
            isValid=false;
        }
        if(state.employee.emailId.trim().length<=0){
            empEmailErr.empEmailRequired="Employee email id is invalid";
            isValid=false;
        }
        if(state.employee.managerId<=0){
            empManagerIdErr.empManagerIdRequired="Employee manager id  is invalid";
            isValid=false;
        }
        if(state.employee.userId === 0){
            empRoleIdErr.empRoleIdRequired="Employee Role id is invalid";
            isValid=false;
      }
       
        setEmpFirstNameErr(empFirstNameErr)
        setEmpLastNameErr(empLastNameErr)
        setEmpEmailErr(empEmailErr)
        setEmpRoleIdErr(empRoleIdErr)
        setEmpManagerIdErr(empManagerIdErr)
       
    
        return isValid;
    }
  return (
      <div className='container' id='add-emp'>
          <form className='col-7' id='add-emp-form' >
             
              
              <table className='emp-form-table'>
              <thead className='emp-form-table-thead'>
                  <h4 className='form-header'>Update Employee</h4>
                  </thead>
                  <tbody>
          <tr>
                      <td><label>Employee ID * :</label></td>
                      <td><input type='text' value={empid}  readOnly={true} ></input></td>
                      
                  </tr>
                 
                  <tr>
                      <td><label>First Name * :</label></td>
                      <td><input type='text' value={state.employee.firstName} onChange={(e) => {
                        
                          setState({
                              employee: {
                                  ...state.employee,
                                  id : empid,
                                  firstName :e.target.value
                              }
                          })
                      }}></input></td>
              
                  </tr>
                  <tr>
                      <td></td>
                      <td >{
                          Object.keys(empFirstNameErr).map((key) => {
                              return <div style={{color:"red"}}>{empFirstNameErr[key]}</div>
                        })
                      }</td>
                  </tr>
                  <tr>
                      <td><label>Last Name * :</label></td>
                      <td><input type='text' value={state.employee.lastName} onChange={(e) => {
                          setState({
                              employee: {
                                  ...state.employee,
                                  lastName :e.target.value
                              }
                          })
                      }}></input></td>
                
                  </tr>
                  <tr>
                      <td></td>
                      <td >{
                          Object.keys(empLastNameErr).map((key) => {
                              return <div style={{color:"red"}}>{empLastNameErr[key]}</div>
                        })
                      }</td>
                  </tr>
                
                  
                  <tr>
                      <td><label>Email * :</label></td>
                      <td><input type='email' value={state.employee.emailId} onChange={(e) => {
                          setState({
                              employee: {
                                  ...state.employee,
                                  emailId :e.target.value
                              }
                          })
                      }}></input></td>
                      
                  </tr>
                  <tr>
                      <td></td>
                      <td >{
                          Object.keys(empEmailErr).map((key) => {
                              return <div style={{color:"red"}}>{empEmailErr[key]}</div>
                        })
                      }</td>
                  </tr>
                  <tr>
                      <td><label>Manager ID * :</label></td>
                      <td><input type='number' value={state.employee.managerId} onChange={(e) => {
                          setState({
                              employee: {
                                  ...state.employee,
                                  managerId :e.target.value
                              }
                          })
                      }}></input></td>
                     
                  </tr>
                  <tr>
                      <td></td>
                      <td >{
                          Object.keys(empManagerIdErr).map((key) => {
                              return <div style={{color:"red"}}>{empManagerIdErr[key]}</div>
                        })
                      }</td>
                  </tr>
                  <tr>
                      <td><label>Role ID * :</label></td>
                      <td><input type='number' value={state.employee.userId} onChange={(e) => {
                          setState({
                              employee: {
                                  ...state.employee,
                                  userId :e.target.value
                              }
                          })
                      }}></input></td>
                   
                  </tr>
                  <tr>
                      <td></td>
                      <td >{
                          Object.keys(empRoleIdErr).map((key) => {
                              return <div style={{color:"red"}}>{empRoleIdErr[key]}</div>
                        })
                      }</td>
                  </tr>
                  <tr>
                      <td><button className='accept-btn' onClick={(e) => {
                          e.preventDefault() 
                         
                          let isValid = formValidation()
                          if (!isValid) {
                              return false
                          } else {
                           
                            EmployeeService.updateEmpInSystem(state.employee).then((resp) => {
                                alert(resp.data)
                            }).catch((err) => {

                                alert(err.data)
                            })
                        }} 
                               
                              
                          }

                          >Update</button></td>
                      <td><button  className='reset-btn' value='reset' form='add-emp-form' type='reset'>Reset</button></td>
                      </tr>
                      </tbody>
              </table>
      </form>
      </div>
  )
}

export default UpdateEmployee