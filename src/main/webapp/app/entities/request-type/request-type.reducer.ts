import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IRequestType, defaultValue } from 'app/shared/model/request-type.model';

export const ACTION_TYPES = {
  FETCH_REQUESTTYPE_LIST: 'requestType/FETCH_REQUESTTYPE_LIST',
  FETCH_REQUESTTYPE: 'requestType/FETCH_REQUESTTYPE',
  CREATE_REQUESTTYPE: 'requestType/CREATE_REQUESTTYPE',
  UPDATE_REQUESTTYPE: 'requestType/UPDATE_REQUESTTYPE',
  DELETE_REQUESTTYPE: 'requestType/DELETE_REQUESTTYPE',
  RESET: 'requestType/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IRequestType>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type RequestTypeState = Readonly<typeof initialState>;

// Reducer

export default (state: RequestTypeState = initialState, action): RequestTypeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_REQUESTTYPE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_REQUESTTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_REQUESTTYPE):
    case REQUEST(ACTION_TYPES.UPDATE_REQUESTTYPE):
    case REQUEST(ACTION_TYPES.DELETE_REQUESTTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_REQUESTTYPE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_REQUESTTYPE):
    case FAILURE(ACTION_TYPES.CREATE_REQUESTTYPE):
    case FAILURE(ACTION_TYPES.UPDATE_REQUESTTYPE):
    case FAILURE(ACTION_TYPES.DELETE_REQUESTTYPE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_REQUESTTYPE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_REQUESTTYPE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_REQUESTTYPE):
    case SUCCESS(ACTION_TYPES.UPDATE_REQUESTTYPE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_REQUESTTYPE):
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

const apiUrl = 'api/request-types';

// Actions

export const getEntities: ICrudGetAllAction<IRequestType> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_REQUESTTYPE_LIST,
    payload: axios.get<IRequestType>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IRequestType> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_REQUESTTYPE,
    payload: axios.get<IRequestType>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IRequestType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_REQUESTTYPE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IRequestType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_REQUESTTYPE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IRequestType> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_REQUESTTYPE,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
