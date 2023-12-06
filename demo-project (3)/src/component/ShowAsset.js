import React from 'react';
import { Component } from 'react';
import { Link } from 'react-router-dom';
import AssetService from '../service/AssetService';
import './css/new.css'

class ShowAsset extends Component {
    
    constructor(props) {
        super(props)
    
        this.state = {
             assets:[]
        }
    }
    
    componentDidMount() {
        AssetService.getAssetFromSystem().then((responce) => {
            this.setState({
                assets: responce.data
            })
        });
    }
 
  render(){
  return (
    //   <div id='show-asset-div' className='col-8'>
    //       <table className='table table-striped' id='asset-table'>
    //               <thead>
    //                   <tr className='table-head'>
    //                       <td>Id</td>
    //                       <td>Name</td>
    //                       <td>Update</td>
    //                       <td>Delete</td>
    //                   </tr>
    //               </thead>
    //           <tbody className='table-body'>
    //                   {
    //                       this.state.assets.map(asset =>
    //                           <tr key ={asset.assetId}>
    //                               <td>{asset.assetId}</td>
    //                               <td>{asset.assetName}</td>
    //                               <td>
    //                                   <button className='button-33' onClick={()=>{}}>Update</button>
    //                               </td>
    //                               <td>
    //                                   <button className='button-33' onClick={() => {
    //                                       AssetService.deleteAssetFromSystem(asset.assetId).then((responce)=>alert(JSON.stringify(responce.data)))
    //                                   }}>Delete</button>
    //                               </td>
    //                          </tr>)
    //                   }
    //               </tbody>
    //           </table>   
    //   </div>
    <div className='col-11'>
    <table className='table table-striped'>
        <thead className='thead'>
            <tr>
            <td>Id</td>
                          <td>Name</td>
                           <td>Update</td>
                          <td>Delete</td>

            </tr>
        </thead>
      
        
        <tbody>
        {
                          this.state.assets.map(asset =>
                               <tr key ={asset.assetId}>
                                  <td>{asset.assetId}</td>
                                   <td>{asset.assetName}</td>
                                   <td>
                                   <Link className='accept-btn' to={{ pathname: `/company/asset/update/${asset.assetId}`}}>Update</Link>
                                   </td>
                                   <td>
                                       <button className='reject-btn' onClick={() => {
                                          AssetService.deleteAssetFromSystem(asset.assetId).then((responce)=>alert(JSON.stringify(responce.data)))
                                      }}>Delete</button>
                                  </td>
                             </tr>)
                      }
        </tbody>
    </table>
</div>
     )
    }
}

export default ShowAsset