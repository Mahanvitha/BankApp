package com.capgemini.bankapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.capgemini.bankapp.model.Customer;
import com.capgemini.bankapp.service.BankAccountService;
import com.capgemini.bankapp.serviceImpl.BankAccountServiceImpl;


@WebServlet("/checkBalance.do")
public class CheckBalanceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BankAccountService bankAccountService;
	
    public CheckBalanceController() {
        super();
        bankAccountService = new BankAccountServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher dispatcher = null;
		
		HttpSession session = request.getSession();
		Customer customer =(Customer) session.getAttribute("customer");
		double balance = bankAccountService.getBalance(customer.getCustomerAccount().getAccountId());
		customer.getCustomerAccount().setAccountBalance(balance);
		session.setAttribute("customer", customer);
//		request.setAttribute("balance",balance);
		
		dispatcher = request.getRequestDispatcher("checkBalance.jsp");
		dispatcher.forward(request, response);
	}
}
