package lk.ijse.colorMaster.bo.custom.impl;

import lk.ijse.colorMaster.bo.custom.CustomerFormBO;
import lk.ijse.colorMaster.dao.custom.CustomerDAO;
import lk.ijse.colorMaster.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.colorMaster.dto.CustomerDto;
import lk.ijse.colorMaster.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerFormBOImpl implements CustomerFormBO {
    CustomerDAO customerDAO = new CustomerDAOImpl();
    @Override
    public ArrayList<CustomerDto> searchCustomer(String id) throws SQLException, ClassNotFoundException {
        List<Customer> searchCustomers = customerDAO.search(id);
        ArrayList<CustomerDto> customerDTOS = new ArrayList<>();

        for (Customer customer : searchCustomers) {
            customerDTOS.add(new CustomerDto(customer.getId(),customer.getName(),customer.getAddress(),customer.getPhoneNo()));
        }

        return customerDTOS;
    }

    @Override
    public boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(dto.getId(), dto.getName(), dto.getAddress(), dto.getPhoneNo()));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(dto.getId(), dto.getName(), dto.getAddress(), dto.getPhoneNo()));
    }

    @Override
    public List<CustomerDto> getAllCustomer() throws SQLException, ClassNotFoundException {
        List<Customer> allcustomers = customerDAO.getAll();
        ArrayList<CustomerDto> customerDTOS = new ArrayList<>();

        for (Customer customer : allcustomers) {
            customerDTOS.add(new CustomerDto(customer.getId(), customer.getName(), customer.getAddress(), customer.getPhoneNo()));
        }

        return customerDTOS;
    }
}
