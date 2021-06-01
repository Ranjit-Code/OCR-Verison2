import classes from './StartingPageContent.module.css';
import univImage from '../../assets/univ.jpg'

const StartingPageContent = () => {
  return (
    <section className={classes.starting}>
      <h1>Course Registration</h1>
      <div className={classes['main-image']}>
            <img src={univImage} alt="My Univ Banner" />
        </div>
    </section>
  );
};

export default StartingPageContent;
