import React from 'react'
import Asset from '../model/Asset'
import { useState } from 'react'
import AssetService from '../service/AssetService'
import { useParams } from 'react-router'
import './css/asset.css'
function UpdateAsset() {
    
    const [state, setState] = useState({ asset: new Asset() })
    const [empAssetNameErr, setAssetNameErr] = useState("");
    const formValidation=()=>{
        
        let isValid=true;
        // eslint-disable-next-line react-hooks/rules-of-hooks
        const { asetid } = useParams();
        const empAssetNameErr={};
      
        if(state.asset.assetName.trim().length<=0){
            empAssetNameErr.empFirstNameRequired="Asset Name is invalid";
            isValid=false;
        }

        setAssetNameErr(empAssetNameErr)
        return isValid;
    }

 
  return (
    <div className='container' id='add-emp'>
          {/* <h3>Update Asset</h3>
        <form id=''>
            <div>
                  <input type='number' placeholder='Input Asset Id' value={state.asset.assetId}
                      onChange={(e)=>{
                          setState({
                              asset: {
                                  ...state.asset,
                                  assetId : e.target.value
                            }
                          })
                      }
                      }
                  ></input>
              </div>
              <div>
                  <input type='text' placeholder='Input Asset Name' value={state.asset.assetName}
                      onChange={(e) =>{
                          setState({
                              asset: {
                                  ...state.asset,
                                  assetName: e.target.value
                          
                              }
                          })
                      }}
                  ></input>
              </div>
              <div>
                  <button onClick={() => {
                      AssetSerive.updateAssetInSystem(state.asset).then((resp)=>alert(JSON.stringify(resp.data) ))
                  }}>Update</button>
              </div>
        </form> */}
          <form className='col-7' id='add-emp-form'>
          <table className='emp-form-table'>
              <thead className='emp-form-table-thead'>
                  <h3 form-header>Asset Update</h3>
              </thead>
              <tbody>
                  <div>
                  <tr>
                      <td><label>Asset Name * :</label></td>
                      <td><input type='text' value={state.asset.id}></input></td>
              
                  </tr>
                      
                  </div>
                  <div>
                  <tr>
                      <td><label>Asset Name * :</label></td>
                      <td><input type='text' value={state.asset.assetName} onChange={(e) => {
                        
                          setState({
                              employee: {
                                  ...state.employee,
                    
                                  assetName :e.target.value
                              }
                          })
                      }}></input></td>
              
                  </tr>
                  <tr>
                      <td></td>
                      <td >{
                          Object.keys(empAssetNameErr).map((key) => {
                              return <div style={{color:"red"}}>{empAssetNameErr[key]}</div>
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
                           
                            AssetService.updateAssetInSystem(state.employee).then((resp) => {
                                alert(resp.data)
                            }).catch((err) => {

                                alert(err.data)
                            })
                        }} 
                               
                              
                          }

                          >Update</button></td>
                      <td><button  className='reset-btn' value='reset' form='add-emp-form' type='reset'>Reset</button></td>
                      </tr>
                  </div>
              </tbody>
              </table>
          </form>
    </div>
  )
}

export default UpdateAsset