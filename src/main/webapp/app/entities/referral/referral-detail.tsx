import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './referral.reducer';

export const ReferralDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const referralEntity = useAppSelector(state => state.referral.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="referralDetailsHeading">
          <Translate contentKey="jhipsterJobApp.referral.detail.title">Referral</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{referralEntity.id}</dd>
          <dt>
            <span id="job_id">
              <Translate contentKey="jhipsterJobApp.referral.job_id">Job Id</Translate>
            </span>
          </dt>
          <dd>{referralEntity.job_id}</dd>
          <dt>
            <span id="referrer_id">
              <Translate contentKey="jhipsterJobApp.referral.referrer_id">Referrer Id</Translate>
            </span>
          </dt>
          <dd>{referralEntity.referrer_id}</dd>
          <dt>
            <span id="date">
              <Translate contentKey="jhipsterJobApp.referral.date">Date</Translate>
            </span>
          </dt>
          <dd>{referralEntity.date ? <TextFormat value={referralEntity.date} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}</dd>
          <dt>
            <Translate contentKey="jhipsterJobApp.referral.application">Application</Translate>
          </dt>
          <dd>{referralEntity.application ? referralEntity.application.id : ''}</dd>
          <dt>
            <Translate contentKey="jhipsterJobApp.referral.user">User</Translate>
          </dt>
          <dd>{referralEntity.user ? referralEntity.user.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/referral" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/referral/${referralEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ReferralDetail;
