import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import RequestType from './request-type';
import RequestTypeDetail from './request-type-detail';
import RequestTypeUpdate from './request-type-update';
import RequestTypeDeleteDialog from './request-type-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={RequestTypeDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={RequestTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={RequestTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={RequestTypeDetail} />
      <ErrorBoundaryRoute path={match.url} component={RequestType} />
    </Switch>
  </>
);

export default Routes;
