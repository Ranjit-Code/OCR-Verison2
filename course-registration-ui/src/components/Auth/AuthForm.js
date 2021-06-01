import { useState, useRef, useContext } from 'react';
import { useHistory } from 'react-router-dom';

import AuthContext from '../../store/auth-context';
import classes from './AuthForm.module.css';

const AuthForm = () => {
  const history = useHistory();
  const emailInputRef = useRef();
  const passwordInputRef = useRef();
  const userTypeInputRef = useRef();

  const authCtx = useContext(AuthContext);

  const [isLogin, setIsLogin] = useState(true);
  const [isLoading, setIsLoading] = useState(false);

  const switchAuthModeHandler = () => {
    setIsLogin((prevState) => !prevState);
  };

  const submitHandler = (event) => {
    event.preventDefault();

    const enteredEmail = emailInputRef.current.value;
    const enteredPassword = passwordInputRef.current.value;
    const enteredUserType = typeof x === "undefined" ? "" : userTypeInputRef.current.value;

    // optional: Add validation

    setIsLoading(true);
    let host = "localhost";
    let port = "8082";
    let url = "http://" + host + ":" + port + "/users";
    if (isLogin) {
      url = url + "/login";
    }
    fetch(url, {
      method: 'POST',
      body: JSON.stringify({
        email: enteredEmail,
        password: enteredPassword,
        type: enteredUserType,
        //returnSecureToken: true,
      }),
      headers: {
        'Content-Type': 'application/json',
      },
    })
      .then((res) => {
        console.log("res: " + res);
        setIsLoading(false);
        if (res.ok) {
          if(!url.endsWith('/login')) {
            alert('Success!');
          }
          return res.json();
        } else {
          // return res.then(() => {
            let errorMessage = 'Failed!';
          //   // if (data && data.error && data.error.message) {
          //   //   errorMessage = data.error.message;
          //   // }

            throw new Error(errorMessage);
          // });
        }
      })
      .then((data) => {
        console.log("data: " + data);
        let time = data.expiresIn; //3600
        const expirationTime = new Date(
          new Date().getTime() + +time * 1000
        );
        console.log(expirationTime);
        authCtx.login(data.token, expirationTime.toISOString());
        history.replace('/');
      })
      .catch((err) => {
        alert(err.message);
        console.log(err);
      });
  };

  return (
    <section className={classes.auth}>
      <h1>{isLogin ? 'Login' : 'Sign Up'}</h1>
      <form onSubmit={submitHandler}>
        <div className={classes.control}>
          <label htmlFor='email'>Your Email</label>
          <input type='email' id='email' required ref={emailInputRef} />
        </div>
        <div className={classes.control}>
          <label htmlFor='password'>Your Password</label>
          <input
            type='password'
            id='password'
            required
            ref={passwordInputRef}
          />
        </div>
        {!isLogin && (<div className={classes.control}>
          <label htmlFor='UserType'>You are a</label>
          <select id='UserType' required ref={userTypeInputRef}>
            <option selected value="student">Student</option>
            <option value="instructor">Instructor</option>
          </select>
        </div>)}
        <div className={classes.actions}>
          {!isLoading && (
            <button>{isLogin ? 'Login' : 'Create Account'}</button>
          )}
          {isLoading && <p>Sending request...</p>}
          <button
            type='button'
            className={classes.toggle}
            onClick={switchAuthModeHandler}
          >
            {isLogin ? 'Create new account' : 'Login with existing account'}
          </button>
        </div>
      </form>
    </section>
  );
};

export default AuthForm;
