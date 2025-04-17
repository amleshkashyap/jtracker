import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { createEntity, getEntity, reset, updateEntity } from './job.reducer';

export const JobUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const jobEntity = useAppSelector(state => state.job.entity);
  const loading = useAppSelector(state => state.job.loading);
  const updating = useAppSelector(state => state.job.updating);
  const updateSuccess = useAppSelector(state => state.job.updateSuccess);

  const handleClose = () => {
    navigate(`/job${location.search}`);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    if (values.id !== undefined && typeof values.id !== 'number') {
      values.id = Number(values.id);
    }
    if (values.company_id !== undefined && typeof values.company_id !== 'number') {
      values.company_id = Number(values.company_id);
    }
    if (values.role_id !== undefined && typeof values.role_id !== 'number') {
      values.role_id = Number(values.role_id);
    }
    if (values.min_experience !== undefined && typeof values.min_experience !== 'number') {
      values.min_experience = Number(values.min_experience);
    }

    const entity = {
      ...jobEntity,
      ...values,
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...jobEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="jhipsterJobApp.job.home.createOrEditLabel" data-cy="JobCreateUpdateHeading">
            <Translate contentKey="jhipsterJobApp.job.home.createOrEditLabel">Create or edit a Job</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="job-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('jhipsterJobApp.job.company_id')}
                id="job-company_id"
                name="company_id"
                data-cy="company_id"
                type="text"
              />
              <ValidatedField label={translate('jhipsterJobApp.job.job_id')} id="job-job_id" name="job_id" data-cy="job_id" type="text" />
              <ValidatedField label={translate('jhipsterJobApp.job.status')} id="job-status" name="status" data-cy="status" type="text" />
              <ValidatedField
                label={translate('jhipsterJobApp.job.location')}
                id="job-location"
                name="location"
                data-cy="location"
                type="text"
              />
              <ValidatedField label={translate('jhipsterJobApp.job.url')} id="job-url" name="url" data-cy="url" type="text" />
              <ValidatedField
                label={translate('jhipsterJobApp.job.role_id')}
                id="job-role_id"
                name="role_id"
                data-cy="role_id"
                type="text"
              />
              <ValidatedField
                label={translate('jhipsterJobApp.job.min_experience')}
                id="job-min_experience"
                name="min_experience"
                data-cy="min_experience"
                type="text"
              />
              <ValidatedField
                label={translate('jhipsterJobApp.job.languages')}
                id="job-languages"
                name="languages"
                data-cy="languages"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/job" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default JobUpdate;
