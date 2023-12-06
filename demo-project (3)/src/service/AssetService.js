import axios from 'axios'


const BASE_URL = 'http://localhost:8888/company/1/asset'

export class AssetService{
    
    getAssetFromSystem() {    
        return axios.get(BASE_URL + '/allasset')
    }

    addAssetInSystem(asset) {
        return axios.post(BASE_URL+'/add',asset)
    }
    deleteAssetFromSystem(id) {
       alert('Deleting Asset')
        return axios.delete(BASE_URL+'/delete/'+id)
    }
    updateAssetInSystem(asset) {
        alert('updating asset')
        return axios.put(BASE_URL +'/update',asset)
    }
    searchAsset(asset) {
        return axios.get(BASE_URL+'/search',asset)
    }
}
export default new AssetService ()