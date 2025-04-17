import dayjs from 'dayjs';
import { IApplication } from 'app/shared/model/application.model';
import { IUser } from 'app/shared/model/user.model';

export interface IReferral {
  id?: number;
  job_id?: number | null;
  referrer_id?: number | null;
  date?: dayjs.Dayjs | null;
  application?: IApplication | null;
  user?: IUser | null;
}

export const defaultValue: Readonly<IReferral> = {};
