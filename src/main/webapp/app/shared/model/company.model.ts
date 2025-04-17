export interface ICompany {
  id?: number;
  name?: string | null;
  employees?: number | null;
  glassdoor?: number | null;
  headquarter?: string | null;
  domains?: string | null;
}

export const defaultValue: Readonly<ICompany> = {};
