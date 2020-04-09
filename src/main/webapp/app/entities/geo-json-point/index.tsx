import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import GeoJsonPoint from './geo-json-point';
import GeoJsonPointDetail from './geo-json-point-detail';
import GeoJsonPointUpdate from './geo-json-point-update';
import GeoJsonPointDeleteDialog from './geo-json-point-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={GeoJsonPointDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={GeoJsonPointUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={GeoJsonPointUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={GeoJsonPointDetail} />
      <ErrorBoundaryRoute path={match.url} component={GeoJsonPoint} />
    </Switch>
  </>
);

export default Routes;
