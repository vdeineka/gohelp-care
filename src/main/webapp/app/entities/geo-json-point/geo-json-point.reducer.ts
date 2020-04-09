import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IGeoJsonPoint, defaultValue } from 'app/shared/model/geo-json-point.model';

export const ACTION_TYPES = {
  FETCH_GEOJSONPOINT_LIST: 'geoJsonPoint/FETCH_GEOJSONPOINT_LIST',
  FETCH_GEOJSONPOINT: 'geoJsonPoint/FETCH_GEOJSONPOINT',
  CREATE_GEOJSONPOINT: 'geoJsonPoint/CREATE_GEOJSONPOINT',
  UPDATE_GEOJSONPOINT: 'geoJsonPoint/UPDATE_GEOJSONPOINT',
  DELETE_GEOJSONPOINT: 'geoJsonPoint/DELETE_GEOJSONPOINT',
  RESET: 'geoJsonPoint/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IGeoJsonPoint>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type GeoJsonPointState = Readonly<typeof initialState>;

// Reducer

export default (state: GeoJsonPointState = initialState, action): GeoJsonPointState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_GEOJSONPOINT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_GEOJSONPOINT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_GEOJSONPOINT):
    case REQUEST(ACTION_TYPES.UPDATE_GEOJSONPOINT):
    case REQUEST(ACTION_TYPES.DELETE_GEOJSONPOINT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_GEOJSONPOINT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_GEOJSONPOINT):
    case FAILURE(ACTION_TYPES.CREATE_GEOJSONPOINT):
    case FAILURE(ACTION_TYPES.UPDATE_GEOJSONPOINT):
    case FAILURE(ACTION_TYPES.DELETE_GEOJSONPOINT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_GEOJSONPOINT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_GEOJSONPOINT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_GEOJSONPOINT):
    case SUCCESS(ACTION_TYPES.UPDATE_GEOJSONPOINT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_GEOJSONPOINT):
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

const apiUrl = 'api/geo-json-points';

// Actions

export const getEntities: ICrudGetAllAction<IGeoJsonPoint> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_GEOJSONPOINT_LIST,
    payload: axios.get<IGeoJsonPoint>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IGeoJsonPoint> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_GEOJSONPOINT,
    payload: axios.get<IGeoJsonPoint>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IGeoJsonPoint> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_GEOJSONPOINT,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IGeoJsonPoint> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_GEOJSONPOINT,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IGeoJsonPoint> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_GEOJSONPOINT,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
