package com.capgemini.bankapp.dao;

import com.capgemini.bankapp.model.Customer;

public interface CustomerDao {

	public Customer authenticate(Customer customer);
	public Customer updateProfile(Customer customer);
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword);
}
