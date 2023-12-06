import React from 'react'
import Asset from '../model/Asset'
import { useState } from 'react'
import AssetSerive from '../service/AssetService'
import './css/asset.css'
function AddAsset() {
    
    const [state, setState] = useState({ asset: new Asset() })
    const [nameErr, setNameErr] = useState('')

    function validate() {
        setNameErr({
            
        })
    }
 
  return (
    <div id='add-form' className='container'>
          <h4 className='row' id=''>Add Asset in System</h4>
          <form className='asset-form'>
              
            {/* <div>
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
              </div> */}
              <div className='row'>
                  <input type='text' placeholder='Input Asset Name' value={state.asset.assetName}
                      onChange={(e) =>{
                          setState({
                              asset: {
                                  ...state.asset,
                                  assetId:111111,
                                  assetName: e.target.value
                          
                              }
                          })
                      }}
                  ></input>
              </div>
              <div className='row'>
                  <button className='add-button' onClick={(e) => {
                      AssetSerive.addAssetInSystem(state.asset).then(resp => alert(resp.data)).catch((error) => {
                        alert(JSON.stringify("error: " + error));
                      })
                  }}>Add Asset</button>
              </div>
        </form>
    </div>
  )
}

export default AddAsset