package com.capgemini.bankapp.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.capgemini.bankapp.dao.BankAccountDao;
import com.capgemini.bankapp.daoImpl.BankAccountDaoImpl;
import com.capgemini.bankapp.service.BankAccountService;
import com.capgemini.bankapp.util.DbUtil;

public class BankAccountServiceImpl implements BankAccountService {

	private BankAccountDao bankAccountDao = new BankAccountDaoImpl();

	@Override
	public double getBalance(long accountId) {
		return bankAccountDao.getBalance(accountId);
	}

	@Override
	public double withdraw(long accountId, double amount) {

		double newBalance = getBalance(accountId) - amount;
		if (newBalance >= 0) {
			bankAccountDao.updateBalance(accountId, newBalance);
			return newBalance;
		}
		return -1;
	}

	@Override
	public double deposit(long accountId, double amount) {
		double newBalance = getBalance(accountId) + amount;
		if (bankAccountDao.updateBalance(accountId, newBalance))
			return newBalance;
		return 0;
	}

	@Override
	public boolean fundTransfer(long fromAcc, long toAcc, double amount) {
		String query = "select accountId from customers where accountId =?";
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, (int) toAcc);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				if (withdraw(fromAcc, amount) >= 0) {
					deposit(toAcc, amount);
					return true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
