import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './proposal.reducer';
import { IProposal } from 'app/shared/model/proposal.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IProposalDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ProposalDetail = (props: IProposalDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { proposalEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="gohelpApp.proposal.detail.title">Proposal</Translate> [<b>{proposalEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="text">
              <Translate contentKey="gohelpApp.proposal.text">Text</Translate>
            </span>
          </dt>
          <dd>{proposalEntity.text}</dd>
          <dt>
            <Translate contentKey="gohelpApp.proposal.user">User</Translate>
          </dt>
          <dd>{proposalEntity.userLogin ? proposalEntity.userLogin : ''}</dd>
          <dt>
            <Translate contentKey="gohelpApp.proposal.request">Request</Translate>
          </dt>
          <dd>{proposalEntity.requestId ? proposalEntity.requestId : ''}</dd>
        </dl>
        <Button tag={Link} to="/proposal" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/proposal/${proposalEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ proposal }: IRootState) => ({
  proposalEntity: proposal.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ProposalDetail);
