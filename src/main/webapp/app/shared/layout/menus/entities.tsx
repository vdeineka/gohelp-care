import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Translate, translate } from 'react-jhipster';
import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  <NavDropdown
    icon="th-list"
    name={translate('global.menu.entities.main')}
    id="entity-menu"
    style={{ maxHeight: '80vh', overflow: 'auto' }}
  >
    <MenuItem icon="asterisk" to="/request">
      <Translate contentKey="global.menu.entities.request" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/request-type">
      <Translate contentKey="global.menu.entities.requestType" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/geo-json-point">
      <Translate contentKey="global.menu.entities.geoJsonPoint" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/proposal">
      <Translate contentKey="global.menu.entities.proposal" />
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
