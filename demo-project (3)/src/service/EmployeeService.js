import axios from "axios";

const BASE_EMPLOYEE_URL = 'http://localhost:8888/company/1/employee'

class EmployeeService{
    
    getAllEmployees() {
        return axios.get(BASE_EMPLOYEE_URL+'/allemp')
    }

    addEmployeeInSystem(employee) {
        return axios.post(BASE_EMPLOYEE_URL+'/addemp',employee)
    }

    getEmpById(empId) {
        return axios.get('http://localhost:8888/company/employee/' + empId)
    }

    updateEmpInSystem(employee) {  
        
        console.log('service'+JSON.stringify(employee))
        return axios.put(BASE_EMPLOYEE_URL +'/update',employee)
    }
    
    deleteEmpById(id) {
        return axios.delete(BASE_EMPLOYEE_URL+'/deleteemp/'+id)
    }


}
export default new EmployeeService ()