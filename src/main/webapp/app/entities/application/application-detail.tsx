import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './application.reducer';

export const ApplicationDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const applicationEntity = useAppSelector(state => state.application.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="applicationDetailsHeading">
          <Translate contentKey="jhipsterJobApp.application.detail.title">Application</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{applicationEntity.id}</dd>
          <dt>
            <span id="job_id">
              <Translate contentKey="jhipsterJobApp.application.job_id">Job Id</Translate>
            </span>
          </dt>
          <dd>{applicationEntity.job_id}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="jhipsterJobApp.application.status">Status</Translate>
            </span>
          </dt>
          <dd>{applicationEntity.status}</dd>
          <dt>
            <span id="date">
              <Translate contentKey="jhipsterJobApp.application.date">Date</Translate>
            </span>
          </dt>
          <dd>{applicationEntity.date}</dd>
          <dt>
            <span id="referral_id">
              <Translate contentKey="jhipsterJobApp.application.referral_id">Referral Id</Translate>
            </span>
          </dt>
          <dd>{applicationEntity.referral_id}</dd>
        </dl>
        <Button tag={Link} to="/application" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/application/${applicationEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ApplicationDetail;
