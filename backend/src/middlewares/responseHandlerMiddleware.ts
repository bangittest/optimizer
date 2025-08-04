import { Request, Response, NextFunction } from 'express';

declare global {
  namespace Express {
    interface Response {
      success: (data: any) => Response;
      error: (message?: string, code?: string, statusCode?: number) => Response;
    }
  }
}

export default function responseHandler(req: Request, res: Response, next: NextFunction) {
  res.success = function (data: any) {
    return res.json({
      error_status: false,
      error_message: null,
      error_code: null,
      data,
    });
  };

  res.error = function (
    message = 'Error',
    code = 'INTERNAL_ERROR',
    statusCode = 500,
    data = null
  ) {
    return res.status(statusCode).json({
      error_status: true,
      error_message: message,
      error_code: code,
      data,
    });
  };

  next();
}