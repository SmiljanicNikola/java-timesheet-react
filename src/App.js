import './App.css';

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
import { AuthenticationService } from './services/AuthenticationService';
import { Navigate } from 'react-router-dom';

const Private = ({Component, roles, path, ...rest}) => {
  const role = AuthenticationService.getRole();
  if (!role) {
    return <Navigate to={{ pathname: "/login" }} />;
  }

  if (roles && !roles.includes(role)) {
    // Ako je korisnik ulogovan ali nema dozvolu pristupa zaštićenoj stranici - vrati ga na glavnu stranicu
    return <Navigate to={{ pathname: "/timesheet" }} />;
  }

  if (roles && roles.includes(role)) {
  return <Component></Component>
  }

}

function App() {
  return (
    <div className="App">
      <Router>

        
        <Routes>

          {/*<Route exact path='/teamMembers' element={<TeamMembers/>}></Route>*/}
          <Route exact path='/login' element={<Login/>}></Route>
          <Route exact path='/calendar' element={<Calendar/>}></Route>

          <Route exact path='/day/:date' element={<Private Component={Days} roles={["ROLE_ADMIN", "ROLE_WORKER"]} exact path='/day/:date'/>} />
          <Route exact path='/timeSheet' element={<Private Component={TimeSheet} roles={["ROLE_ADMIN", "ROLE_WORKER"]} exact path='/timeSheet'/>} />
          <Route exact path='/reports' element={<Private Component={Reports} roles={["ROLE_ADMIN", "ROLE_WORKER"]} exact path='/reports'/>} />
          <Route exact path='/teamMembers' element={<Private Component={TeamMembers} roles={["ROLE_ADMIN"]} exact path='/teamMembers'/>} />
          <Route exact path='/projects' element={<Private Component={Projects} roles={["ROLE_ADMIN","ROLE_WORKER"]} exact path='/projects'/>} />
          <Route exact path='/clients' element={<Private Component={Clients} roles={["ROLE_ADMIN","ROLE_WORKER"]} exact path='/clients'/>} />
          <Route exact path='/categories' element={<Private Component={Categories} roles={["ROLE_ADMIN","ROLE_WORKER"]} exact path='/categories'/>} />
          <Route exact path='/newMemberForm' element={<Private Component={NewMemberForm} roles={["ROLE_ADMIN"]} exact path='/newMemberForm'/>} />
          <Route exact path='/newProjectForm' element={<Private Component={NewProjectForm} roles={["ROLE_ADMIN"]} exact path='/newProjectForm'/>} />
          <Route exact path='/newClientForm' element={<Private Component={NewClientForm} roles={["ROLE_ADMIN"]} exact path='/newClientForm'/>} />
          <Route exact path='/newCategoryForm' element={<Private Component={NewCategoryForm} roles={["ROLE_ADMIN"]} exact path='/newCategoryForm'/>} />
      
        </Routes>
      </Router>

    </div>
  );
}

export default App;
