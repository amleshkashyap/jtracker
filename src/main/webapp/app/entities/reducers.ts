import application from 'app/entities/application/application.reducer';
import job from 'app/entities/job/job.reducer';
import role from 'app/entities/role/role.reducer';
import company from 'app/entities/company/company.reducer';
import referral from 'app/entities/referral/referral.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  application,
  job,
  role,
  company,
  referral,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
