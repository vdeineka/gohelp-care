import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './geo-json-point.reducer';
import { IGeoJsonPoint } from 'app/shared/model/geo-json-point.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IGeoJsonPointDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const GeoJsonPointDetail = (props: IGeoJsonPointDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { geoJsonPointEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="gohelpApp.geoJsonPoint.detail.title">GeoJsonPoint</Translate> [<b>{geoJsonPointEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="x">
              <Translate contentKey="gohelpApp.geoJsonPoint.x">X</Translate>
            </span>
          </dt>
          <dd>{geoJsonPointEntity.x}</dd>
          <dt>
            <span id="y">
              <Translate contentKey="gohelpApp.geoJsonPoint.y">Y</Translate>
            </span>
          </dt>
          <dd>{geoJsonPointEntity.y}</dd>
        </dl>
        <Button tag={Link} to="/geo-json-point" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/geo-json-point/${geoJsonPointEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ geoJsonPoint }: IRootState) => ({
  geoJsonPointEntity: geoJsonPoint.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(GeoJsonPointDetail);
