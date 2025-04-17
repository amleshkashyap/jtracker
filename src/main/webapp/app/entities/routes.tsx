import React from 'react';
import { Route } from 'react-router'; // eslint-disable-line

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Application from './application';
import Job from './job';
import Role from './role';
import Company from './company';
import Referral from './referral';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="application/*" element={<Application />} />
        <Route path="job/*" element={<Job />} />
        <Route path="role/*" element={<Role />} />
        <Route path="company/*" element={<Company />} />
        <Route path="referral/*" element={<Referral />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
