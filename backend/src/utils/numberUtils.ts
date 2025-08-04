export const toNumberOrZero = (value: any): number => {
  if (value === null || value === undefined || value === "") return 0;
  return Number(value);
};
