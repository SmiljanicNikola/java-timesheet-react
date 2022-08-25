import React, { useState } from 'react'
import { AuthenticationService } from '../../services/AuthenticationService';
import LogoLarge from '../../assets/img/logo-large.png'


export const Login = () => {
    const [credentials, setCredentials] = useState({
        username: "",
        password: "",

    });

    const handleFormInputChange = (name) => (event) => {
        const val = event.target.value;
    
        // ... - Destructuring assignment - omogućuje raspakivanje vrednosti objekata ili nizova
        // setCredentails će zameniti stanje novim objektom koji uzima vrednosti iz prethodnog stanja kredencijala
        // ali sa ažuriranom vrednošću [name] polja
        setCredentials({ ...credentials, [name]: val });
      };

    const login = async () => {

        AuthenticationService.login(credentials);

    
    };

    return (
        <div class="wrapper centered">
            <div class="logo-wrap">
                <a href="index.html" class="inner">
                    <img src={LogoLarge} alt="logo" />
                </a>
            </div>
            <div class="centered-content-wrap">
                <div class="centered-block">
                    <h1>Login</h1>
                    <ul>
                        <li>
                            <input type="text" placeholder="Email" value={credentials.username} onChange={handleFormInputChange("username")} class="in-text large" />
                        </li>
                        <li>
                            <input type="password" placeholder="Password" value={credentials.password} onChange={handleFormInputChange("password")} class="in-pass large" />
                        </li>
                        <li class="last">
                            <input type="checkbox" class="in-checkbox" id="remember" />
                            <label class="in-label" for="remember">Remember me</label>
                            <span class="right">
                                {/* eslint-disable-next-line jsx-a11y/anchor-is-valid*/}
                                <a className="link">Forgot password?</a>

                                {/* eslint-disable-next-line jsx-a11y/anchor-is-valid*/}
                                <a onClick={login} className="btn orange">Login</a>
                            </span>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    )
}
