package com.capgemini.bankapp.service;

import com.capgemini.bankapp.model.Customer;

public interface CustomerService {

	public Customer authenticate(Customer customer);
	public Customer updateProfile(Customer customer);
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword) ;

}
