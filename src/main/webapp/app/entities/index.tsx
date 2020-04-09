import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Request from './request';
import RequestType from './request-type';
import GeoJsonPoint from './geo-json-point';
import Proposal from './proposal';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}request`} component={Request} />
      <ErrorBoundaryRoute path={`${match.url}request-type`} component={RequestType} />
      <ErrorBoundaryRoute path={`${match.url}geo-json-point`} component={GeoJsonPoint} />
      <ErrorBoundaryRoute path={`${match.url}proposal`} component={Proposal} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
