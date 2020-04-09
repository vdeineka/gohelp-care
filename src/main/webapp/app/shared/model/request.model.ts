export interface IRequest {
  id?: number;
  text?: string;
  userLogin?: string;
  userId?: number;
  locationId?: number;
  typeId?: number;
}

export const defaultValue: Readonly<IRequest> = {};
