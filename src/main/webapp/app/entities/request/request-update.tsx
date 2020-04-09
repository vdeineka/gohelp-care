import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { IGeoJsonPoint } from 'app/shared/model/geo-json-point.model';
import { getEntities as getGeoJsonPoints } from 'app/entities/geo-json-point/geo-json-point.reducer';
import { IRequestType } from 'app/shared/model/request-type.model';
import { getEntities as getRequestTypes } from 'app/entities/request-type/request-type.reducer';
import { getEntity, updateEntity, createEntity, reset } from './request.reducer';
import { IRequest } from 'app/shared/model/request.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IRequestUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const RequestUpdate = (props: IRequestUpdateProps) => {
  const [userId, setUserId] = useState('0');
  const [locationId, setLocationId] = useState('0');
  const [typeId, setTypeId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { requestEntity, users, geoJsonPoints, requestTypes, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/request');
  };

  useEffect(() => {
    if (!isNew) {
      props.getEntity(props.match.params.id);
    }

    props.getUsers();
    props.getGeoJsonPoints();
    props.getRequestTypes();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...requestEntity,
        ...values
      };
      entity.user = users[values.user];

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="gohelpApp.request.home.createOrEditLabel">
            <Translate contentKey="gohelpApp.request.home.createOrEditLabel">Create or edit a Request</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : requestEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="request-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="request-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="textLabel" for="request-text">
                  <Translate contentKey="gohelpApp.request.text">Text</Translate>
                </Label>
                <AvField id="request-text" type="text" name="text" />
              </AvGroup>
              <AvGroup>
                <Label for="request-user">
                  <Translate contentKey="gohelpApp.request.user">User</Translate>
                </Label>
                <AvInput id="request-user" type="select" className="form-control" name="userId">
                  <option value="" key="0" />
                  {users
                    ? users.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.login}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="request-location">
                  <Translate contentKey="gohelpApp.request.location">Location</Translate>
                </Label>
                <AvInput id="request-location" type="select" className="form-control" name="locationId">
                  <option value="" key="0" />
                  {geoJsonPoints
                    ? geoJsonPoints.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="request-type">
                  <Translate contentKey="gohelpApp.request.type">Type</Translate>
                </Label>
                <AvInput id="request-type" type="select" className="form-control" name="typeId">
                  <option value="" key="0" />
                  {requestTypes
                    ? requestTypes.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/request" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  users: storeState.userManagement.users,
  geoJsonPoints: storeState.geoJsonPoint.entities,
  requestTypes: storeState.requestType.entities,
  requestEntity: storeState.request.entity,
  loading: storeState.request.loading,
  updating: storeState.request.updating,
  updateSuccess: storeState.request.updateSuccess
});

const mapDispatchToProps = {
  getUsers,
  getGeoJsonPoints,
  getRequestTypes,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(RequestUpdate);
