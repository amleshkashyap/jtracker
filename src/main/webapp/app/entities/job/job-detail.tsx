import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './job.reducer';

export const JobDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const jobEntity = useAppSelector(state => state.job.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="jobDetailsHeading">
          <Translate contentKey="jhipsterJobApp.job.detail.title">Job</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{jobEntity.id}</dd>
          <dt>
            <span id="company_id">
              <Translate contentKey="jhipsterJobApp.job.company_id">Company Id</Translate>
            </span>
          </dt>
          <dd>{jobEntity.company_id}</dd>
          <dt>
            <span id="job_id">
              <Translate contentKey="jhipsterJobApp.job.job_id">Job Id</Translate>
            </span>
          </dt>
          <dd>{jobEntity.job_id}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="jhipsterJobApp.job.status">Status</Translate>
            </span>
          </dt>
          <dd>{jobEntity.status}</dd>
          <dt>
            <span id="location">
              <Translate contentKey="jhipsterJobApp.job.location">Location</Translate>
            </span>
          </dt>
          <dd>{jobEntity.location}</dd>
          <dt>
            <span id="url">
              <Translate contentKey="jhipsterJobApp.job.url">Url</Translate>
            </span>
          </dt>
          <dd>{jobEntity.url}</dd>
          <dt>
            <span id="role_id">
              <Translate contentKey="jhipsterJobApp.job.role_id">Role Id</Translate>
            </span>
          </dt>
          <dd>{jobEntity.role_id}</dd>
          <dt>
            <span id="min_experience">
              <Translate contentKey="jhipsterJobApp.job.min_experience">Min Experience</Translate>
            </span>
          </dt>
          <dd>{jobEntity.min_experience}</dd>
          <dt>
            <span id="languages">
              <Translate contentKey="jhipsterJobApp.job.languages">Languages</Translate>
            </span>
          </dt>
          <dd>{jobEntity.languages}</dd>
        </dl>
        <Button tag={Link} to="/job" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/job/${jobEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default JobDetail;
