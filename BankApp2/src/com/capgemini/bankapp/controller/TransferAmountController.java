package com.capgemini.bankapp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.capgemini.bankapp.exceptions.InsufficientBalanceException;
import com.capgemini.bankapp.model.BankAccount;
import com.capgemini.bankapp.model.Customer;
import com.capgemini.bankapp.service.BankAccountService;
import com.capgemini.bankapp.serviceImpl.BankAccountServiceImpl;

@WebServlet("/transferAmount.do")
public class TransferAmountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BankAccountService bankAccountService;
    
    public TransferAmountController() {
        super();
        bankAccountService = new BankAccountServiceImpl();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		HttpSession session = request.getSession();
		
		long fromAccId = Integer.parseInt(request.getParameter("fromAccId"));
		long toAccId = Integer.parseInt(request.getParameter("toAccId"));
		long amount = Integer.parseInt(request.getParameter("amount"));
		RequestDispatcher dispatcher = null;
		BankAccount account = new BankAccount(fromAccId,"",0);
		try {
			if(bankAccountService.fundTransfer(fromAccId, toAccId, amount))
			{	
				dispatcher = request.getRequestDispatcher("successfullTransfer.jsp");
				dispatcher.forward(request, response);
			}
			else {
				throw new InsufficientBalanceException("Insufficient balance in the account for transaction");
			}
		} 
		catch (InsufficientBalanceException e) {
			e.printStackTrace();
		    dispatcher = request.getRequestDispatcher("insufficientBalance.jsp");
			dispatcher.forward(request, response);
		}
	}
}
