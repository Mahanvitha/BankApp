package com.capgemini.bankapp.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.capgemini.bankapp.dao.CustomerDao;
import com.capgemini.bankapp.model.BankAccount;
import com.capgemini.bankapp.model.Customer;
import com.capgemini.bankapp.util.DbUtil;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public Customer authenticate(Customer customer) {
		String query = "SELECT * FROM customers, bankAccounts where customers.accountId = bankAccounts.accountId and customerEmail = ? AND customerPassword = ?";

		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {

			statement.setString(1, customer.getCustomerEmail());
			statement.setString(2, customer.getCustomerPassword());
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				
				 BankAccount account = new BankAccount();
				 account.setAccountId(result.getInt(8));
				 account.setAccountType(result.getString(9));
				 account.setAccountBalance(result.getDouble(10));
				
				 Customer customerObject = new Customer();
				customerObject.setCustomerId(result.getInt(1));
				customerObject.setCustomerName(result.getString(2));
				customerObject.setCustomerPassword(result.getString(3));
				customerObject.setCustomerEmail(result.getString(4));
				customerObject.setCustomerAddress(result.getString(5));
				customerObject.setCustomerDateOfBirth(result.getDate(6).toLocalDate());
				customerObject.setCustomerAccount(account);
				return customerObject;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@Override
	public Customer updateProfile(Customer customer) {
		String query1="update customers set customerName= ? ,customerPassword= ? ,customerEmail= ? ,customerAddress= ? , customerDateOfBirth= ? where customerId= ? ";
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement1 = connection.prepareStatement(query1);) {
			statement1.setString(1,customer.getCustomerName());
			statement1.setString(2, customer.getCustomerPassword());
			statement1.setString(3,customer.getCustomerEmail());
			statement1.setString(4, customer.getCustomerAddress());
			statement1.setDate(5, Date.valueOf(customer.getCustomerDateOfBirth()));
			statement1.setInt(6, (int) customer.getCustomerId());
			if (statement1.executeUpdate() == 1)
			{
				String query2 ="select * from customers where customerId= ?";
				try(PreparedStatement statement2 = connection.prepareStatement(query2)) {
					statement2.setInt(1,(int)customer.getCustomerId());
				ResultSet result = statement2.executeQuery();
			Customer customerObj=new Customer();
			if(result.next())
			{
			customerObj.setCustomerId(result.getInt(1));
			customerObj.setCustomerName(result.getString(2));
			customerObj.setCustomerPassword(result.getString(3));
			customerObj.setCustomerEmail(result.getString(4));
			customerObj.setCustomerAddress(result.getString(5));
			customerObj.setCustomerDateOfBirth(result.getDate(6).toLocalDate());
			customerObj.setCustomerAccount(customer.getCustomerAccount());
			return customerObj;
			}
			}
				}
			}
			 catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword) {
		String query = " update customers set customerPassword= ? WHERE customerId = ? AND customerPassword = ?";

		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);) {
			statement.setString(1, newPassword);
			statement.setInt(2, (int) customer.getCustomerId());
			statement.setString(3, oldPassword);
			if (statement.executeUpdate() == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}

