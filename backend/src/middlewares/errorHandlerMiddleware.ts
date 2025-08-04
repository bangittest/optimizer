import { Request, Response, NextFunction } from 'express';
import HttpError from '../utils/HttpErrorUtils';

export function errorHandler(
  err: Error | HttpError,
  req: Request,
  res: Response,
  next: NextFunction
) {
  if (err instanceof HttpError) {
    return res.status(err.statusCode).json({
      error_status: true,
      error_message: err.message,
      error_code: err.errorCode,
      data: null,
    });
  }

  console.error(err);

  return res.status(500).json({
    error_status: true,
    error_message: 'Internal server error',
    error_code: 'INTERNAL_SERVER_ERROR',
    data: null,
  });
}
