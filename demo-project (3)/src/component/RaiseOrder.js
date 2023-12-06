import React,{useState} from 'react'
import Order from '../model/Order'
import OrderService from '../service/OrderService'
import './css/asset.css'
function RaiseOrder() {
   
  const [state, setState] = useState( { order : new Order() } )
   

  return (
    <div className='container' id='rasieorder-main'>
      <form  className='col-7' id='raiseorder'>
        <div className='row'>
          <input type='number' placeholder='Input Employee Id' value={state.order.empId}
            onChange={(e) => {
              setState({
                order: {
                  ...state.order,
                empId: e.target.value
                }
                
              })
            }
            }></input>
        </div>
        <div className='row'>
        <input type='number' placeholder='Input Asset Id' value={state.order.assetId}
            onChange={(e) => {
              setState({
                order: {
                  ...state.order,
                assetId: e.target.value
                }
                
              })
            }
            }></input>
        </div>
        <div>
          <button className='accept-btn' id='order-btn' onClick={() => {

            OrderService.raiseOrderInSystem(state.order).then((ressponce) => {
               alert(ressponce.data)
            }).catch((error) => {
               alert('You Do Not Have Authority')
             })
          }}>Order Asset</button>
        </div>
      </form>
          
    </div>
  )
}

export default RaiseOrder