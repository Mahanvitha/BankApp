package com.capgemini.bankapp.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.capgemini.bankapp.model.Customer;
import com.capgemini.bankapp.service.CustomerService;
import com.capgemini.bankapp.serviceImpl.CustomerServiceImpl;


@WebServlet("/editProfile.do")
public class EditProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CustomerService customerService;
    
    public EditProfileController() {
        super();
        customerService = new CustomerServiceImpl();
       
    }
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		int customerId = Integer.parseInt(request.getParameter("customerId"));
		String customerName = request.getParameter("customerName");
		String customerPassword = request.getParameter("customerPassword");
		String customerEmail = request.getParameter("customerEmail");
		String customerAddress = request.getParameter("customerAddress");
		String customerDob = request.getParameter("customerDob");

		LocalDate dob = LocalDate.parse(customerDob);
		RequestDispatcher dispatcher = null;
		Customer customer = new Customer(customerId,customerName,customerPassword,customerEmail,customerAddress,dob,null);
		customerService.updateProfile(customer);
		if (customer.getCustomerId() != 0) {
			session.setAttribute("customer", customer);
			dispatcher = request.getRequestDispatcher("successEdit.jsp");
			dispatcher.forward(request, response);
		} else {
			dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
		}	
	}
}

