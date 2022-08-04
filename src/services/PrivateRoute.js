import React from "react";
import { Navigate, Route, Outlet } from "react-router-dom";
import { AuthenticationService } from "./AuthenticationService";

export const PrivateRoute = ({ component: Component, roles, ...rest }) => (

  <Route
    {...rest}
    render={(props) => {
      const role = AuthenticationService.getRole();
      if (!role) {
        return <Navigate to={{ pathname: "/login" }} />;
      }

      if (roles && !roles.includes(role)) {
        // Ako je korisnik ulogovan ali nema dozvolu pristupa zaštićenoj stranici - vrati ga na glavnu stranicu
        return <Navigate to={{ pathname: "/timesheet" }} />;
      }

      // Vrati stranicu koja se traži
      return <Navigate {...props} />;
    }}
  />
);