package com.example.jhipster.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class RoleAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertRoleAllPropertiesEquals(Role expected, Role actual) {
        assertRoleAutoGeneratedPropertiesEquals(expected, actual);
        assertRoleAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertRoleAllUpdatablePropertiesEquals(Role expected, Role actual) {
        assertRoleUpdatableFieldsEquals(expected, actual);
        assertRoleUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertRoleAutoGeneratedPropertiesEquals(Role expected, Role actual) {
        assertThat(actual)
            .as("Verify Role auto generated properties")
            .satisfies(a -> assertThat(a.getId()).as("check id").isEqualTo(expected.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertRoleUpdatableFieldsEquals(Role expected, Role actual) {
        assertThat(actual)
            .as("Verify Role relevant properties")
            .satisfies(a -> assertThat(a.getName()).as("check name").isEqualTo(expected.getName()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertRoleUpdatableRelationshipsEquals(Role expected, Role actual) {
        // empty method
    }
}
