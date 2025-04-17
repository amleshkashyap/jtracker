export interface IJob {
  id?: number;
  company_id?: number | null;
  job_id?: string | null;
  status?: string | null;
  location?: string | null;
  url?: string | null;
  role_id?: number | null;
  min_experience?: number | null;
  languages?: string | null;
}

export const defaultValue: Readonly<IJob> = {};
