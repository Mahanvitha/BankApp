package com.capgemini.bankapp.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.capgemini.bankapp.dao.BankAccountDao;
import com.capgemini.bankapp.util.DbUtil;

public class BankAccountDaoImpl implements BankAccountDao {

	@Override
	public double getBalance(long accountId) {
		String query = "select accountBalance FROM bankAccounts where accountId=?";
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, (int) accountId);
			ResultSet result = statement.executeQuery();
			if(result.next())
			{
				return result.getDouble(1);
			}
			}
	catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public boolean updateBalance(long accountId, double newBalance) {
		
		String query = "UPDATE bankAccounts SET accountBalance=? where accountId=?";
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(2, (int) accountId);
			statement.setDouble(1, newBalance);
			if (statement.executeUpdate() == 1)
				return true;
		return false;
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return false;
}
}
