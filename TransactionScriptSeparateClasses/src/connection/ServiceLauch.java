package connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceLauch {

	public ResultSet returnAllFinancialLaunch(ConnectionDB db) throws SQLException {
		String trasactionAllFinancialLaunch = "SELECT contratos.key as Contrato, produtos.nome as Produto, produtos.tipo as Tipo, contratos.receita as Receita, contratos.dataAssinattura as Assinatura FROM contratos INNER JOIN produtos ON contratos.produto = produtos.key";
		PreparedStatement preparedStatement = db.getConnect().prepareStatement(trasactionAllFinancialLaunch);
		return preparedStatement.executeQuery();
	}
	
	public ResultSet returnSpecifiedFinancialLaunchByContract(ConnectionDB db, int contractKey) throws SQLException {
		String trasactionAllFinancialLaunch = "SELECT contratos.key as Contrato, produtos.nome as Produto, produtos.tipo as Tipo, contratos.receita as Receita, contratos.dataAssinattura as Assinatura FROM contratos INNER JOIN produtos ON contratos.produto = produtos.key WHERE contratos.key = ?";
		PreparedStatement preparedStatement = db.getConnect().prepareStatement(trasactionAllFinancialLaunch);
		preparedStatement.setInt(1, contractKey);
		return preparedStatement.executeQuery();		 
	}
	
	public ResultSet returnSpecifiedFinancialLaunchByContractAndDate(ConnectionDB db, int contractKey, String date) throws SQLException {
		String trasactionAllFinancialLaunchSpecifiedContractDate = "SELECT contratos.key as Contrato, produtos.nome as Produto, produtos.tipo as Tipo, contratos.receita as Receita, contratos.dataAssinattura as Assinatura FROM contratos INNER JOIN produtos ON contratos.produto = produtos.key WHERE contratos.key = ? and contratos.dataAssinattura = ? ";
		PreparedStatement preparedStatement = db.getConnect().prepareStatement(trasactionAllFinancialLaunchSpecifiedContractDate);
		preparedStatement.setInt(1, contractKey);
		preparedStatement.setString(2, date);
		return preparedStatement.executeQuery();		 
	}

	public ResultSet returnFinancialLaunchByDateInterval(ConnectionDB db, String initDate, String finalDate) throws SQLException {
		String trasactionAllFinancialLaunchSpecifiedDate = "SELECT contratos.key as Contrato, produtos.nome as Produto, produtos.tipo as Tipo, contratos.receita as Receita, contratos.dataAssinattura as Assinatura FROM contratos INNER JOIN produtos ON contratos.produto = produtos.key WHERE contratos.dataAssinattura BETWEEN ? AND ? ";
		PreparedStatement preparedStatement = db.getConnect().prepareStatement(trasactionAllFinancialLaunchSpecifiedDate);
		preparedStatement.setString(1, initDate);
		preparedStatement.setString(2, finalDate);
		return preparedStatement.executeQuery();
	}
	
	public void launchFinancialIncome(ConnectionDB db, Double quantia, String date) throws SQLException {
		String trasactionInsertAllFinancialLaunchSpecifiedDate = "INSERT INTO `lancamentosdereceitas` (`contrato`, `quantia`, `lancadaEm`) VALUES (null, ?, ?)";
		PreparedStatement preparedStatementInsert = db.getConnect().prepareStatement(trasactionInsertAllFinancialLaunchSpecifiedDate);
		preparedStatementInsert.setDouble(1, quantia);
		preparedStatementInsert.setString(2, date);
		preparedStatementInsert.executeUpdate();
	}

	public ResultSet returnFinancialLaunchByDateIntervalSqlSum(ConnectionDB db, String initDate, String finalDate) throws SQLException {
		String trasactionAllFinancialLaunchSpecifiedDate = "SELECT sum(contratos.receita) as ReceitaTotal, NOW() as LancadoEn  FROM contratos INNER JOIN produtos ON contratos.produto = produtos.key WHERE contratos.dataAssinattura  BETWEEN ? AND ?";
		PreparedStatement preparedStatement = db.getConnect().prepareStatement(trasactionAllFinancialLaunchSpecifiedDate);
		preparedStatement.setString(1, initDate);
		preparedStatement.setString(2, finalDate);
		return preparedStatement.executeQuery();		
	}

	public ResultSet returnAllFinancialLaunchCreated(ConnectionDB db) throws SQLException {
		String trasactionAllFinancialLaunch = "SELECT * FROM lancamentosdereceitas";
		PreparedStatement preparedStatement = db.getConnect().prepareStatement(trasactionAllFinancialLaunch);
		return preparedStatement.executeQuery();		
	}
}
