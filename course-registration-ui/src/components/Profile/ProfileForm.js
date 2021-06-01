import { useRef, useContext } from 'react';
import { useHistory } from 'react-router-dom';

import AuthContext from '../../store/auth-context';
import classes from './ProfileForm.module.css';

const ProfileForm = () => {
  const history = useHistory();

  const firstNameInputRef = useRef();
  const lastNameInputRef = useRef();
  const addressInputRef = useRef();
  const phoneNumberInputRef = useRef();
  const authCtx = useContext(AuthContext);

  const submitHandler = (event) => {
    event.preventDefault();

    const enteredFirstName = firstNameInputRef.current.value;
    const enteredLastName = lastNameInputRef.current.value;
    const enteredAddress = addressInputRef.current.value;
    const enteredPhoneNumber = phoneNumberInputRef.current.value;

    // add validation

    let host = "localhost";
    let port = "8082";
    let url = "http://" + host + ":" + port + "/profiles";

    fetch(url, {
      method: 'POST',
      body: JSON.stringify({
        userId: "TESTUSERID",
        firstName: enteredFirstName,
        lastName: enteredLastName,
        address: enteredAddress,
        phoneNumber: enteredPhoneNumber
      }),
      headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer' + authCtx.token
      }
    }).then(res => {
      // assumption: Always succeeds!

      history.replace('/');
    });
  };

  return (
    <form className={classes.form} onSubmit={submitHandler}>
      <div className={classes.control}>
        <label htmlFor='firstName'>First Name</label>
        <input type='text' id='firstName' minLength="1" ref={firstNameInputRef} />
      </div>
      <div className={classes.control}>
        <label htmlFor='lastName'>Last Name</label>
        <input type='text' id='lastName' minLength="1" ref={lastNameInputRef} />
      </div>
      <div className={classes.control}>
        <label htmlFor='address'>Address</label>
        <input type='text' id='address' minLength="1" ref={addressInputRef} />
      </div>
      <div className={classes.control}>
        <label htmlFor='phoneNumber'>Phone Number</label>
        <input type='text' id='phoneNumber' minLength="10" ref={phoneNumberInputRef} />
      </div>
      <div className={classes.action}>
        <button>Save profile details</button>
      </div>
    </form>
  );
};

export default ProfileForm;
