import React from 'react'

export const Login = () => {
    return (
        <div class="wrapper centered">
            <div class="logo-wrap">
                <a href="index.html" class="inner">
                    <img src="assets/img/logo-large.png" />
                </a>
            </div>
            <div class="centered-content-wrap">
                <div class="centered-block">
                    <h1>Login</h1>
                    <ul>
                        <li>
                            <input type="text" placeholder="Email" class="in-text large" />
                        </li>
                        <li>
                            <input type="password" placeholder="Password" class="in-pass large" />
                        </li>
                        <li class="last">
                            <input type="checkbox" class="in-checkbox" id="remember" />
                            <label class="in-label" for="remember">Remember me</label>
                            <span class="right">
                                <a href="javascript:;" class="link">Forgot password?</a>
                                <a href="javascript:;" class="btn orange">Login</a>
                            </span>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    )
}
