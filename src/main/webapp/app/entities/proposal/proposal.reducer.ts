import axios from 'axios';
import {
  parseHeaderForLinks,
  loadMoreDataWhenScrolled,
  ICrudGetAction,
  ICrudGetAllAction,
  ICrudPutAction,
  ICrudDeleteAction
} from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IProposal, defaultValue } from 'app/shared/model/proposal.model';

export const ACTION_TYPES = {
  FETCH_PROPOSAL_LIST: 'proposal/FETCH_PROPOSAL_LIST',
  FETCH_PROPOSAL: 'proposal/FETCH_PROPOSAL',
  CREATE_PROPOSAL: 'proposal/CREATE_PROPOSAL',
  UPDATE_PROPOSAL: 'proposal/UPDATE_PROPOSAL',
  DELETE_PROPOSAL: 'proposal/DELETE_PROPOSAL',
  RESET: 'proposal/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IProposal>,
  entity: defaultValue,
  links: { next: 0 },
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ProposalState = Readonly<typeof initialState>;

// Reducer

export default (state: ProposalState = initialState, action): ProposalState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PROPOSAL_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PROPOSAL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PROPOSAL):
    case REQUEST(ACTION_TYPES.UPDATE_PROPOSAL):
    case REQUEST(ACTION_TYPES.DELETE_PROPOSAL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PROPOSAL_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PROPOSAL):
    case FAILURE(ACTION_TYPES.CREATE_PROPOSAL):
    case FAILURE(ACTION_TYPES.UPDATE_PROPOSAL):
    case FAILURE(ACTION_TYPES.DELETE_PROPOSAL):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PROPOSAL_LIST): {
      const links = parseHeaderForLinks(action.payload.headers.link);

      return {
        ...state,
        loading: false,
        links,
        entities: loadMoreDataWhenScrolled(state.entities, action.payload.data, links),
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    }
    case SUCCESS(ACTION_TYPES.FETCH_PROPOSAL):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PROPOSAL):
    case SUCCESS(ACTION_TYPES.UPDATE_PROPOSAL):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PROPOSAL):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/proposals';

// Actions

export const getEntities: ICrudGetAllAction<IProposal> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_PROPOSAL_LIST,
    payload: axios.get<IProposal>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IProposal> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PROPOSAL,
    payload: axios.get<IProposal>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IProposal> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PROPOSAL,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const updateEntity: ICrudPutAction<IProposal> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PROPOSAL,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IProposal> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PROPOSAL,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
