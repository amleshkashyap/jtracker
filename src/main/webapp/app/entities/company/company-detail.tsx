import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './company.reducer';

export const CompanyDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const companyEntity = useAppSelector(state => state.company.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="companyDetailsHeading">
          <Translate contentKey="jhipsterJobApp.company.detail.title">Company</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{companyEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="jhipsterJobApp.company.name">Name</Translate>
            </span>
          </dt>
          <dd>{companyEntity.name}</dd>
          <dt>
            <span id="employees">
              <Translate contentKey="jhipsterJobApp.company.employees">Employees</Translate>
            </span>
          </dt>
          <dd>{companyEntity.employees}</dd>
          <dt>
            <span id="glassdoor">
              <Translate contentKey="jhipsterJobApp.company.glassdoor">Glassdoor</Translate>
            </span>
          </dt>
          <dd>{companyEntity.glassdoor}</dd>
          <dt>
            <span id="headquarter">
              <Translate contentKey="jhipsterJobApp.company.headquarter">Headquarter</Translate>
            </span>
          </dt>
          <dd>{companyEntity.headquarter}</dd>
          <dt>
            <span id="domains">
              <Translate contentKey="jhipsterJobApp.company.domains">Domains</Translate>
            </span>
          </dt>
          <dd>{companyEntity.domains}</dd>
        </dl>
        <Button tag={Link} to="/company" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/company/${companyEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CompanyDetail;
