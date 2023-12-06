import React, { Component } from 'react'
import './css/order.css'
import OrderService from '../service/OrderService'

export class ReleasedOrder extends Component {
    constructor(props) {
        super(props)
    
        this.state = {
            tickets: []
        }
        
        }
    componentDidMount() {
        OrderService.showReleasedOrder().then((response) => {
            console.log(JSON.stringify(response.data))
            this.setState({
                tickets :response.data
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

export default ReleasedOrder