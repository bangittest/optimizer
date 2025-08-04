import Customer from '../domains/models/customer';

export async function findAllActiveCustomers() {
  return Customer.findAll({
    where: { del_flag: '0' },
    attributes: ['id', 'customer_code', 'full_name', 'mobile_no', 'birth_date', 'sex',
      'address', 'note', 'bank_name', 'account_bank'] // tuỳ chọn
  });
}
