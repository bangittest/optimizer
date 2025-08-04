import 'express';

declare module 'express' {
  export interface Request {
    __: (phraseOrOptions: any, ...replace: any[]) => string;
  }
}
