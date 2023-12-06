import axios, { Axios } from 'axios'

const BASE_ORDER_URL = 'http://localhost:8888/company/2/order'

export class OrderService{
    
    raiseOrderInSystem(order) {
        alert('order placing')
        return axios.post(BASE_ORDER_URL + '/raise',order)
    }

    showAllOrdersInSystem() {
      
        return axios.get(BASE_ORDER_URL+'/allorders')
    }
    showPendingOrder() {
        return axios.get(BASE_ORDER_URL+'/pending')
    }
    allocateOrder(id) {
        
        return axios.put(BASE_ORDER_URL+'/allocate/'+id)
    }
    rejectOrder(id) {
        return axios.put(BASE_ORDER_URL+'/reject/'+id)
    }
    showAllocatedOrder() {
        return axios.get(BASE_ORDER_URL+'/allocated')
    }
    releaseOrder(id) {
        return axios.put(BASE_ORDER_URL+'/release/'+id)
    }
    showReleasedOrder() {
        return axios.get(BASE_ORDER_URL+'/released')
    }

}

export default new OrderService ()