import axios from "axios"

const   BASE_LOGIN_URL = 'http://localhost:8888/company/login'

export class loginService{

    loginInSystem (user) {
            return axios.get(BASE_LOGIN_URL,user)
    }
}

export default new loginService ()
