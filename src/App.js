import './App.css';
import { Header } from './components/layout/Header';
import { Footer } from './components/layout/Footer';
import {TeamMembers} from './components/pages/TeamMembers'
import {Clients} from './components/pages/Clients'
import {Projects} from './components/pages/Projects'
import {Categories} from './components/pages/Categories'
import {NewMemberForm} from './components/forms/NewMemberForm'
import {Calendar} from './components/calendar-feature/Calendar'
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import { TimeSheet } from './components/pages/TimeSheet';
import { Login } from './components/pages/Login';
import { Reports } from './components/pages/Reports';
import { NewProjectForm } from './components/forms/NewProjectForm';
import { NewClientForm } from './components/forms/NewClientForm';
import { Days } from './components/pages/Days';
import { NewCategoryForm } from './components/forms/NewCategoryForm';


function App() {
  return (
    <div className="App">
      <Header /><br></br><br></br>
      <Router>
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
      
      </Router>
      <Footer />

    </div>
  );
}

export default App;
