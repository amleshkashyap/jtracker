package com.example.jhipster.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertApplicationAllPropertiesEquals(Application expected, Application actual) {
        assertApplicationAutoGeneratedPropertiesEquals(expected, actual);
        assertApplicationAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertApplicationAllUpdatablePropertiesEquals(Application expected, Application actual) {
        assertApplicationUpdatableFieldsEquals(expected, actual);
        assertApplicationUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertApplicationAutoGeneratedPropertiesEquals(Application expected, Application actual) {
        assertThat(actual)
            .as("Verify Application auto generated properties")
            .satisfies(a -> assertThat(a.getId()).as("check id").isEqualTo(expected.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertApplicationUpdatableFieldsEquals(Application expected, Application actual) {
        assertThat(actual)
            .as("Verify Application relevant properties")
            .satisfies(a -> assertThat(a.getJob_id()).as("check job_id").isEqualTo(expected.getJob_id()))
            .satisfies(a -> assertThat(a.getStatus()).as("check status").isEqualTo(expected.getStatus()))
            .satisfies(a -> assertThat(a.getDate()).as("check date").isEqualTo(expected.getDate()))
            .satisfies(a -> assertThat(a.getReferral_id()).as("check referral_id").isEqualTo(expected.getReferral_id()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertApplicationUpdatableRelationshipsEquals(Application expected, Application actual) {
        // empty method
    }
}
