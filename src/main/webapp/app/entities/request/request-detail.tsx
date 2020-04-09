import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './request.reducer';
import { IRequest } from 'app/shared/model/request.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IRequestDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const RequestDetail = (props: IRequestDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { requestEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="gohelpApp.request.detail.title">Request</Translate> [<b>{requestEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="text">
              <Translate contentKey="gohelpApp.request.text">Text</Translate>
            </span>
          </dt>
          <dd>{requestEntity.text}</dd>
          <dt>
            <Translate contentKey="gohelpApp.request.user">User</Translate>
          </dt>
          <dd>{requestEntity.userLogin ? requestEntity.userLogin : ''}</dd>
          <dt>
            <Translate contentKey="gohelpApp.request.location">Location</Translate>
          </dt>
          <dd>{requestEntity.locationId ? requestEntity.locationId : ''}</dd>
          <dt>
            <Translate contentKey="gohelpApp.request.type">Type</Translate>
          </dt>
          <dd>{requestEntity.typeId ? requestEntity.typeId : ''}</dd>
        </dl>
        <Button tag={Link} to="/request" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/request/${requestEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ request }: IRootState) => ({
  requestEntity: request.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(RequestDetail);
