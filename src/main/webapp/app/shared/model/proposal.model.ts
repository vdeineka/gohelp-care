export interface IProposal {
  id?: number;
  text?: string;
  userLogin?: string;
  userId?: number;
  requestId?: number;
}

export const defaultValue: Readonly<IProposal> = {};
