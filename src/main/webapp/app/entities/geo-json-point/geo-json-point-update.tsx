import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './geo-json-point.reducer';
import { IGeoJsonPoint } from 'app/shared/model/geo-json-point.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IGeoJsonPointUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const GeoJsonPointUpdate = (props: IGeoJsonPointUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { geoJsonPointEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/geo-json-point' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...geoJsonPointEntity,
        ...values
      };

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
          <h2 id="gohelpApp.geoJsonPoint.home.createOrEditLabel">
            <Translate contentKey="gohelpApp.geoJsonPoint.home.createOrEditLabel">Create or edit a GeoJsonPoint</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : geoJsonPointEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="geo-json-point-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="geo-json-point-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="xLabel" for="geo-json-point-x">
                  <Translate contentKey="gohelpApp.geoJsonPoint.x">X</Translate>
                </Label>
                <AvField id="geo-json-point-x" type="string" className="form-control" name="x" />
              </AvGroup>
              <AvGroup>
                <Label id="yLabel" for="geo-json-point-y">
                  <Translate contentKey="gohelpApp.geoJsonPoint.y">Y</Translate>
                </Label>
                <AvField id="geo-json-point-y" type="string" className="form-control" name="y" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/geo-json-point" replace color="info">
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
  geoJsonPointEntity: storeState.geoJsonPoint.entity,
  loading: storeState.geoJsonPoint.loading,
  updating: storeState.geoJsonPoint.updating,
  updateSuccess: storeState.geoJsonPoint.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(GeoJsonPointUpdate);
