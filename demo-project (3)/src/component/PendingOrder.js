import React, { Component } from 'react'
import OrderService from '../service/OrderService'

export class PendingOrder extends Component {
    constructor(props) {
      super(props)
    
      this.state = {
         tickets:[]
        }
      
    }
    componentDidMount() {
        
        OrderService.showPendingOrder().then((responce) => {
           
            this.setState({
                tickets:responce.data
            })
        })
    }
  render() {
    return (
        <div className='col-11'>
            
        <table className='table table-striped'>
            <thead className='thead'>
                <tr>
                    <td>Order Id</td>
                    <td>Date</td>
                    <td>Employee ID</td>
                    <td>Asset Name</td>
                    <td>Status</td>
                    <td>Allocate</td>
                    <td>Reject</td>
                </tr>
            </thead>
          
            
            <tbody>
                {
                        this.state.tickets.map(tiket => {
                        
                           return(               
                        <tr key={tiket.orderId}>
                            <td>{tiket.orderId}</td>
                            <td>{tiket.date}</td>
                            <td>{tiket.employeeId}</td>
                            <td>{tiket.assetname}</td>
                            <td>{tiket.status}</td>
                            
                            <td><button className='accept-btn' onClick={() => {
                                       OrderService.allocateOrder(tiket.orderId).then((res) => {
                                           alert(JSON.stringify(res.data))
                                       }).catch((error) => {
                                        alert(JSON.stringify(error.message))
                                    })
                                   }}>Allocate</button></td>  
                                   
                            <td> <button className='reject-btn'onClick={() => {
                                       OrderService.rejectOrder(tiket.orderId).then((res) => {
                                           alert(JSON.stringify(res.data))
                                       }).catch((error) => {
                                           alert(JSON.stringify("error: " + error))
                                       })
                            }}>Reject</button> </td> 
                            </tr>
                           )
                    })
               }
            </tbody>
        </table>
  </div>
    )
  }
}

export default PendingOrder