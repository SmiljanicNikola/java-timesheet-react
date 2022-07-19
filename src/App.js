import './App.css';
import { Header } from './components/Header';
import { Footer } from './components/Footer';
import {TeamMembers} from './components/TeamMembers'
import {Clients} from './components/Clients'
import {Projects} from './components/Projects'
import {Categories} from './components/Categories'
import {NewMemberForm} from './components/NewMemberForm'
import {Calendar} from './components/Calendar'
import {BrowserRouter as Router, Routes, Route, Outlet} from 'react-router-dom';
import { TimeSheet } from './components/TimeSheet';
import { Login } from './components/Login';
import { Reports } from './components/Reports';
import { NewProjectForm } from './components/NewProjectForm';
import { NewClientForm } from './components/NewClientForm';
import { Days } from './components/Days';
import { NewCategoryForm } from './components/NewCategoryForm';


function App() {
  return (
    <div className="App">
      <Header />
      <Router>
      <Footer style={{display:'none'}}/>   
        <Routes>

          <Route exact path='/teamMembers' element={<TeamMembers/>}></Route>
          <Route exact path='/clients' element={<Clients/>}></Route>
          <Route exact path='/projects' element={<Projects/>}></Route>
          <Route exact path='/categories' element={<Categories/>}></Route>
          <Route exact path='/newMemberForm' element={<NewMemberForm/>}></Route>
          <Route exact path='/newProjectForm' element={<NewProjectForm/>}></Route>
          <Route exact path='/newClientForm' element={<NewClientForm/>}></Route>
          <Route exact path='/newCategoryForm' element={<NewCategoryForm/>}></Route>
          <Route exact path='/day/:date' element={<Days/>}></Route>
          <Route exact path='/timeSheet' element={<TimeSheet/>}></Route>
          <Route exact path='/login' element={<Login/>}></Route>
          <Route exact path='/reports' element={<Reports/>}></Route>
          <Route exact path='/day' element={<Days/>}></Route>
          <Route exact path='/calendar' element={<Calendar/>}></Route>

        </Routes>
        
      <Footer />

      </Router>
    </div>
  );
}

export default App;
