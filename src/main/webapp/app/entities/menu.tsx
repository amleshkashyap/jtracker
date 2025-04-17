import React from 'react';
import { Translate } from 'react-jhipster'; // eslint-disable-line

import MenuItem from 'app/shared/layout/menus/menu-item'; // eslint-disable-line

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/application">
        <Translate contentKey="global.menu.entities.application" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/job">
        <Translate contentKey="global.menu.entities.job" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/role">
        <Translate contentKey="global.menu.entities.role" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/company">
        <Translate contentKey="global.menu.entities.company" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/referral">
        <Translate contentKey="global.menu.entities.referral" />
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;
