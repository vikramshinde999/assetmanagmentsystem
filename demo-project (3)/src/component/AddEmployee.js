
import React,{useState} from 'react'
import Employee from '../model/Employee'
import EmployeeService from '../service/EmployeeService'
import './css/employee.css'

function AddEmployee() {
    const [state, setState] = useState({ employee: new Employee() })
    
    // const [empIdErr, setEmployeeIdErr] = useState("");
    const [empFirstNameErr, setEmpFirstNameErr] = useState([]);
    const [empLastNameErr, setEmpLastNameErr]=useState("");
    const [empEmailErr, setEmpEmailErr]=useState("");
    const [empManagerIdErr, setEmpManagerIdErr] = useState("");
    const [empRoleIdErr, setEmpRoleIdErr]=useState("");

    const formValidation=()=>{
        
        let isValid=true;
        // const empIdErr={};
        const empFirstNameErr={};
        const empLastNameErr = {};
        const empEmailErr = {};
        const empManagerIdErr = {};
        const empRoleIdErr = {};
     
        
        // if(state.employee.employeeId.trim().length<=0){
        //     empIdErr.empIdRequired="Employee ID is required";
        //     isValid=false;
        // }
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
        if(state.employee.managerId.trim().length<=0){
            empManagerIdErr.empManagerIdRequired="Employee manager id  is invalid";
            isValid=false;
        }
        if(state.employee.userId.length === 0){
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
          <form className='col-7' id='add-emp-form'>
             
              
              <table className='emp-form-table'>
              <thead className='emp-form-table-thead'>
                  <h4 className='form-header'>Add Employee</h4>
             </thead>
                  <tr>
                      <td><label>First Name * :</label></td>
                      <td><input type='text' value={state.employee.firstName} onChange={(e) => {
                          setState({
                              employee: {
                                  ...state.employee,
                                  id:0,
                                  firstName :e.target.value
                              }
                          })
                      }}></input></td>
                      <span></span>
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
                      <span></span>
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
                      <span></span>
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
                      <span></span>
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
                      <span></span>
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
                          console.log(JSON.stringify(state.employee))
                          let isValid = formValidation()
                          if (!isValid) {
                              return false
                          } else {
                              EmployeeService.addEmployeeInSystem(state.employee).then((resp) => {
                              }).catch((err) => {

                                  alert(err.data)
                              })

                          }
                      }
                              
                              
                      }

                          >Add</button></td>
                      <td><button  className='reset-btn' type='reset'>Reset</button></td>
                  </tr>
              </table>
          </form>
          {/* <form>
              <div>
                  <h4>Add Employee</h4>
              </div>
              <div>
                  <label>First Name</label>
                  <input type='text' onChange={(e) => {
                          setState({
                              employee: {
                                  ...state.employee,
                                  firstName :e.target.value
                              }
                          })
                      }}></input>
                  
              </div>
              <div>
                  <label>Last Name</label>
                  <input type='text' onChange={(e) => {
                          setState({
                              employee: {
                                  ...state.employee,
                                  lastName :e.target.value
                              }
                          })
                      }}></input>
                  
              </div>
              <div>
                  <label>Email</label>
                  <input type='email' onChange={(e) => {
                          setState({
                              employee: {
                                  ...state.employee,
                                  emailId :e.target.value
                              }
                          })
                      }}></input>
                  
              </div>
              <div>
                  <label>Manager ID</label>
                  <input type='number'onChange={(e) => {
                          setState({
                              employee: {
                                  ...state.employee,
                                  managerId :e.target.value
                              }
                          })
                      }}></input>
                  
              </div>
              <div>
                  <label>Role ID</label>
                  <input type='number' onChange={(e) => {
                          setState({
                              employee: {
                                  ...state.employee,
                                  userId :e.target.value
                              }
                          })
                      }}></input>
              </div>
              <div>
                <button onClick={() => {
                            EmployeeService.addEmployeeInSystem(state.employee).then((resp) => {
                                alert(resp.data)
                            })
                    }}  >Add Employee</button>
                    <button type='reset'>Reset</button>
              </div>
          </form>           */}

          
    </div>
  )
}

export default AddEmployee