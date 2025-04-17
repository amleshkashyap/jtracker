import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { createEntity, getEntity, reset, updateEntity } from './application.reducer';

export const ApplicationUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const applicationEntity = useAppSelector(state => state.application.entity);
  const loading = useAppSelector(state => state.application.loading);
  const updating = useAppSelector(state => state.application.updating);
  const updateSuccess = useAppSelector(state => state.application.updateSuccess);

  const handleClose = () => {
    navigate(`/application${location.search}`);
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
    if (values.referral_id !== undefined && typeof values.referral_id !== 'number') {
      values.referral_id = Number(values.referral_id);
    }

    const entity = {
      ...applicationEntity,
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
          ...applicationEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="jhipsterJobApp.application.home.createOrEditLabel" data-cy="ApplicationCreateUpdateHeading">
            <Translate contentKey="jhipsterJobApp.application.home.createOrEditLabel">Create or edit a Application</Translate>
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
                  id="application-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('jhipsterJobApp.application.job_id')}
                id="application-job_id"
                name="job_id"
                data-cy="job_id"
                type="text"
              />
              <ValidatedField
                label={translate('jhipsterJobApp.application.status')}
                id="application-status"
                name="status"
                data-cy="status"
                type="text"
              />
              <ValidatedField
                label={translate('jhipsterJobApp.application.date')}
                id="application-date"
                name="date"
                data-cy="date"
                type="time"
                placeholder="HH:mm"
              />
              <ValidatedField
                label={translate('jhipsterJobApp.application.referral_id')}
                id="application-referral_id"
                name="referral_id"
                data-cy="referral_id"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/application" replace color="info">
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

export default ApplicationUpdate;
