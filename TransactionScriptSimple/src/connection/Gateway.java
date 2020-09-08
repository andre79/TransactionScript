package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Gateway {

	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	final private String host = "localhost";
	final private String user = "root";
	final private String passwd = "";

	public Connection getConnect() {
		return connect;
	}

	public void setConnect(Connection connect) {
		this.connect = connect;
	}

	public void readDataBase() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(
					"jdbc:mysql://" + host + "/transaction_script?" + "user=" + user + "&password=" + passwd);
			setConnect(connect);
			System.out.println("Conectado com sucesso.");

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void readProductList() throws SQLException {

		String trasactionScriptSelectProduct = "select * from produtos";
		PreparedStatement preparedStatement = getConnect().prepareStatement(trasactionScriptSelectProduct);
		resultSet = preparedStatement.executeQuery();

		System.out.println("Chave -- Nome ------------------ Tipo");

		while (resultSet.next()) {
			int key = resultSet.getInt("key");
			String nome = resultSet.getString("nome");
			String tipo = resultSet.getString("tipo");
			System.out.println(key + " ------ " + nome + " ------ " + tipo);
		}
	}

	public void readContractsList() throws SQLException {

		String trasactionScriptContractsList = "select * from contratos";
		PreparedStatement preparedStatement = getConnect().prepareStatement(trasactionScriptContractsList);
		resultSet = preparedStatement.executeQuery();

		System.out.println("Contrato -- Produto ------------------ Receita ------------------ Data");

		while (resultSet.next()) {
			int codigocontrato = resultSet.getInt("key");
			int codigoproduto = resultSet.getInt("produto");
			String receita = resultSet.getString("receita");
			String dataAssinatura = resultSet.getString("dataAssinattura");
			System.out.println(
					codigocontrato + " ------ " + codigoproduto + " ------ " + receita + " ------ " + dataAssinatura);
		}
	}

	public void createAllFinancialLaunch() throws SQLException {

		String trasactionAllFinancialLaunch = "SELECT contratos.key as Contrato, produtos.nome as Produto, produtos.tipo as Tipo, contratos.receita as Receita, contratos.dataAssinattura as Assinatura FROM contratos INNER JOIN produtos ON contratos.produto = produtos.key";
		PreparedStatement preparedStatement = getConnect().prepareStatement(trasactionAllFinancialLaunch);
		resultSet = preparedStatement.executeQuery();

		System.out.println("Chave -- Nome ------------------ Tipo");

		while (resultSet.next()) {
			int key = resultSet.getInt("Contrato");
			String nome = resultSet.getString("Produto");
			String tipo = resultSet.getString("Tipo");
			Double receita = resultSet.getDouble("Receita");
			String assinatura = resultSet.getString("Assinatura");
			System.out.println(
					key + " ------ " + nome + " ------ " + tipo + " ------ " + receita + " ------ " + assinatura);
		}
	}

	public void createAllFinancialLaunchSpecifiedContract(int contractKey) throws SQLException {
		String trasactionAllFinancialLaunch = "SELECT contratos.key as Contrato, produtos.nome as Produto, produtos.tipo as Tipo, contratos.receita as Receita, contratos.dataAssinattura as Assinatura FROM contratos INNER JOIN produtos ON contratos.produto = produtos.key WHERE contratos.key = ?";
		PreparedStatement preparedStatement = getConnect().prepareStatement(trasactionAllFinancialLaunch);
		preparedStatement.setInt(1, contractKey);
		resultSet = preparedStatement.executeQuery();

		System.out.println("Chave -- Nome ------------------ Tipo");

		while (resultSet.next()) {
			int key = resultSet.getInt("Contrato");
			String nome = resultSet.getString("Produto");
			String tipo = resultSet.getString("Tipo");
			Double receita = resultSet.getDouble("Receita");
			String assinatura = resultSet.getString("Assinatura");
			System.out.println(
					key + " ------ " + nome + " ------ " + tipo + " ------ " + receita + " ------ " + assinatura);
		}
	}

	public void createAllFinancialLaunchSpecifiedContractDate(int contractKey, String date) throws SQLException {
		String trasactionAllFinancialLaunchSpecifiedContractDate = "SELECT contratos.key as Contrato, produtos.nome as Produto, produtos.tipo as Tipo, contratos.receita as Receita, contratos.dataAssinattura as Assinatura FROM contratos INNER JOIN produtos ON contratos.produto = produtos.key WHERE contratos.key = ? and contratos.dataAssinattura = ? ";
		PreparedStatement preparedStatement = getConnect().prepareStatement(trasactionAllFinancialLaunchSpecifiedContractDate);
		preparedStatement.setInt(1, contractKey);
		preparedStatement.setString(2, date);
		resultSet = preparedStatement.executeQuery();

		System.out.println("Chave -- Nome ------------------ Tipo");

		while (resultSet.next()) {
			int key1 = resultSet.getInt("Contrato");
			String nome = resultSet.getString("Produto");
			String tipo = resultSet.getString("Tipo");
			Double receita = resultSet.getDouble("Receita");
			String assinatura = resultSet.getString("Assinatura");
			System.out.println(
					key1 + " ------ " + nome + " ------ " + tipo + " ------ " + receita + " ------ " + assinatura);
		}

	}
	
	public void createAllFinancialLaunchSpecifiedDate(String initDate, String finalDate) throws SQLException {
		String trasactionAllFinancialLaunchSpecifiedDate = "SELECT contratos.key as Contrato, produtos.nome as Produto, produtos.tipo as Tipo, contratos.receita as Receita, contratos.dataAssinattura as Assinatura FROM contratos INNER JOIN produtos ON contratos.produto = produtos.key WHERE contratos.dataAssinattura BETWEEN ? AND ? ";
		PreparedStatement preparedStatement = getConnect().prepareStatement(trasactionAllFinancialLaunchSpecifiedDate);
		preparedStatement.setString(1, initDate);
		preparedStatement.setString(2, finalDate);
		resultSet = preparedStatement.executeQuery();
		Double receita = 0.0;
		
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		
		System.out.println("Chave -- Nome ------------------ Tipo");
		int quantia = 0;
		while (resultSet.next()) {
			int key1 = resultSet.getInt("Contrato");
			String nome = resultSet.getString("Produto");
			String tipo = resultSet.getString("Tipo");
			receita = resultSet.getDouble("Receita");
			quantia += receita; 
			String assinatura = resultSet.getString("Assinatura");
			System.out.println(key1 + " ------ " + nome + " ------ " + tipo + " ------ " + receita + " ------ " + assinatura);
		}
		
		String trasactionInsertAllFinancialLaunchSpecifiedDate = "INSERT INTO `lancamentosdereceitas` (`contrato`, `quantia`, `lancadaEm`) VALUES (null, ?, ?)";
		PreparedStatement preparedStatementInsert = getConnect().prepareStatement(trasactionInsertAllFinancialLaunchSpecifiedDate);
		preparedStatementInsert.setDouble(1, quantia);
		preparedStatementInsert.setString(2, date);
		preparedStatementInsert.executeUpdate();

	}

	public void createAllFinancialLaunchSpecifiedDateSum(String initDateSum, String finalDateSum) throws SQLException {
		String trasactionAllFinancialLaunchSpecifiedDate = "SELECT sum(contratos.receita) as ReceitaTotal, NOW() as LancadoEn  FROM contratos INNER JOIN produtos ON contratos.produto = produtos.key WHERE contratos.dataAssinattura  BETWEEN '"+initDateSum+"' AND '"+finalDateSum+"'";
		Statement statement = getConnect().createStatement();
		resultSet = statement.executeQuery(trasactionAllFinancialLaunchSpecifiedDate);
		Double quantia = null;
		System.out.println("Chave -- Nome ------------------ Tipo");
		
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		
		while (resultSet.next()) {
			quantia = resultSet.getDouble("ReceitaTotal");
			String Lancadoen = date;
			System.out.println("Valor Total: "+quantia+" Data do Lançamento: "+ Lancadoen);
		}
		
		String trasactionInsertAllFinancialLaunchSpecifiedDate = "INSERT INTO `lancamentosdereceitas` (`contrato`, `quantia`, `lancadaEm`) VALUES (null, ?, ?)";
		PreparedStatement preparedStatement = getConnect().prepareStatement(trasactionInsertAllFinancialLaunchSpecifiedDate);
		preparedStatement.setDouble(1, quantia);
		preparedStatement.setString(2, date);
		preparedStatement.executeUpdate();
	}
}
