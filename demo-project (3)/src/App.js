
import './App.css';
import { Routes, Route} from "react-router-dom";
import AddAsset from './component/AddAsset';
import UpdateAsset from './component/UpdateAssset';
import ShowAsset from './component/ShowAsset'
import RaiseOrder from './component/RaiseOrder';
import ShowAllOrders from './component/ShowAllOrders';

import { Navbar } from './component/Navbar';
import EmployeeList from './component/EmployeeList';
import AddEmployee from './component/AddEmployee';
import AssetComponents from './component/AssetComponents';
import OrderComponent from './component/OrderComponent';
import PendingOrder from './component/PendingOrder';
import AllocatedOrders from './component/AllocatedOrders';
import ReleasedOrder from './component/ReleasedOrder';
import EmployeeComponent from './component/EmployeeComponent';
import UpdateEmployee from './component/UpdateEmployee';
import Home from './component/Home';
import Login from './component/LogIn';
function App() {
  return (
    <div className="App">
      
      <Navbar />
      

      
      <Routes >
        <Route path='login' element={ <Login/> } /> 
        <Route index element={<Login/>}/>
        <Route path = '/login' element={<Login/>} />
        <Route index element={<Home/>}/>
        <Route path='company' element={<Home />}> </Route>
        <Route index element={<EmployeeComponent />}/>
        <Route exact path='company/employee' element={<EmployeeComponent />} >
          <Route exact path='emplist' element={<EmployeeList />}>  </Route>
          <Route exact path='update/:empid' element={<UpdateEmployee />}> </Route>
          <Route exact path='addemp' element={<AddEmployee/>} ></Route>
        </Route>    
      
        <Route index element={<AssetComponents />}/>
        <Route exact path='company/asset' element={<AssetComponents />}>
          <Route exact path='allasset' element={<ShowAsset />} />
          <Route exact path='addasset' element={<AddAsset />} />
          <Route exact path='update/:assetid' element={<UpdateAsset />} ></Route>
          <Route exact path='placeorder' element={<RaiseOrder/>}/>
        </Route>
        <Route index element={<OrderComponent />}/>
          <Route  exact path='company/order' element={<OrderComponent />}>
            <Route  exact path='allorder' element={<ShowAllOrders/>}></Route>
              <Route  exact path='pending' element={<PendingOrder />} />
              <Route  exact path='allocated' element={<AllocatedOrders />}></Route>
              <Route exact  path='released'  element={<ReleasedOrder/>}/>
        
            </Route>
      </Routes>
    </div>
  );
}

export default App;
