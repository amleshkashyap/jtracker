import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities as getApplications } from 'app/entities/application/application.reducer';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { createEntity, getEntity, reset, updateEntity } from './referral.reducer';

export const ReferralUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const applications = useAppSelector(state => state.application.entities);
  const users = useAppSelector(state => state.userManagement.users);
  const referralEntity = useAppSelector(state => state.referral.entity);
  const loading = useAppSelector(state => state.referral.loading);
  const updating = useAppSelector(state => state.referral.updating);
  const updateSuccess = useAppSelector(state => state.referral.updateSuccess);

  const handleClose = () => {
    navigate(`/referral${location.search}`);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getApplications({}));
    dispatch(getUsers({}));
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
    if (values.job_id !== undefined && typeof values.job_id !== 'number') {
      values.job_id = Number(values.job_id);
    }
    if (values.referrer_id !== undefined && typeof values.referrer_id !== 'number') {
      values.referrer_id = Number(values.referrer_id);
    }

    const entity = {
      ...referralEntity,
      ...values,
      application: applications.find(it => it.id.toString() === values.application?.toString()),
      user: users.find(it => it.id.toString() === values.user?.toString()),
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
          ...referralEntity,
          application: referralEntity?.application?.id,
          user: referralEntity?.user?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="jhipsterJobApp.referral.home.createOrEditLabel" data-cy="ReferralCreateUpdateHeading">
            <Translate contentKey="jhipsterJobApp.referral.home.createOrEditLabel">Create or edit a Referral</Translate>
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
                  id="referral-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('jhipsterJobApp.referral.job_id')}
                id="referral-job_id"
                name="job_id"
                data-cy="job_id"
                type="text"
              />
              <ValidatedField
                label={translate('jhipsterJobApp.referral.referrer_id')}
                id="referral-referrer_id"
                name="referrer_id"
                data-cy="referrer_id"
                type="text"
              />
              <ValidatedField label={translate('jhipsterJobApp.referral.date')} id="referral-date" name="date" data-cy="date" type="date" />
              <ValidatedField
                id="referral-application"
                name="application"
                data-cy="application"
                label={translate('jhipsterJobApp.referral.application')}
                type="select"
              >
                <option value="" key="0" />
                {applications
                  ? applications.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField id="referral-user" name="user" data-cy="user" label={translate('jhipsterJobApp.referral.user')} type="select">
                <option value="" key="0" />
                {users
                  ? users.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/referral" replace color="info">
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

export default ReferralUpdate;
