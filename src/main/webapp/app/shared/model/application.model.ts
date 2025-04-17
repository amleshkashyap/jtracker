export interface IApplication {
  id?: number;
  job_id?: string | null;
  status?: string | null;
  date?: string | null;
  referral_id?: number | null;
}

export const defaultValue: Readonly<IApplication> = {};
