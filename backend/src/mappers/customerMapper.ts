
import { CustomerResponseDTO } from '../domains/dtos/response/customerResponseDTO';


export function toCustomerResponseDTO(customer: any): CustomerResponseDTO {
  return {
    id: customer.id,
    customerCode: customer.customer_code,
    fullName: customer.full_name,
    mobileNo: customer.mobile_no,
    birthDate: customer.birth_date,
    sex: customer.sex,
    address: customer.address,
    note: customer.note,
    bankName: customer.bank_name,
    accountBank: customer.account_bank,
  };
}