package connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Gateway {
	
	private Product pd = null;
	private Contract ct = null;
	private ServiceLauch sl = null;
	public ConnectionDB db = null;
	
	public Gateway() throws Exception {
		super();
		
		//Inicia o Banco
		db = new ConnectionDB();
		db.readDataBase();
		
		
		pd = new Product();
		ct = new Contract();
		sl = new ServiceLauch();
	}

	public void readProductList() throws SQLException {
		pd.readProductList(db);
	}

	public void readContractsList() throws SQLException {
		ct.readContractsList(db);
	}

	public void returnAllFinancialLaunch() throws SQLException {
		ResultSet resultSet = sl.returnAllFinancialLaunch(db);

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

	public void returnSpecifiedFinancialLaunchByContract(int contractID) throws SQLException {
		ResultSet resultSet = sl.returnSpecifiedFinancialLaunchByContract(db, contractID);
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

	public void returnSpecifiedFinancialLaunchByContractAndDate(int contractID, String date) throws SQLException {
		ResultSet resultSet = sl.returnSpecifiedFinancialLaunchByContractAndDate(db, contractID, date);
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
	
	public void returnFinancialLaunchByDateInterval(String initDate, String finalDate) throws SQLException 
	{
		ResultSet resultSet = sl.returnFinancialLaunchByDateInterval(db, initDate, finalDate);
		
		//Calcula o Lançamento da Receita
		Double receita = 0.0;		
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String serviceLauchDate = simpleDateFormat.format(new Date());
		
		System.out.println("Chave -- Nome ------------------ Tipo");
		Double quantia = 0.0;
		while (resultSet.next()) {
			int key1 = resultSet.getInt("Contrato");
			String nome = resultSet.getString("Produto");
			String tipo = resultSet.getString("Tipo");
			receita = resultSet.getDouble("Receita");
			quantia += receita; 
			String assinatura = resultSet.getString("Assinatura");
			System.out.println(key1 + " ------ " + nome + " ------ " + tipo + " ------ " + receita + " ------ " + assinatura);
		}
		sl.launchFinancialIncome(db, quantia, serviceLauchDate);		
	}
	
	public void returnFinancialLaunchByDateIntervalSqlSum(String initDate, String finalDate) throws SQLException {
		ResultSet resultSet = sl.returnFinancialLaunchByDateIntervalSqlSum(db, initDate, finalDate);
		
		Double quantia = null;
		System.out.println("Chave -- Nome ------------------ Tipo");
		
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String serviceLauchDate = simpleDateFormat.format(new Date());
		
		while (resultSet.next()) 
		{
			quantia = resultSet.getDouble("ReceitaTotal");
			String Lancadoen = serviceLauchDate;
			System.out.println("Valor Total: "+ quantia +" Data do Lançamento: "+ Lancadoen);
		}
		sl.launchFinancialIncome(db, quantia, serviceLauchDate);
	}
	
	public void returnAllFinancialLaunchCreated() throws SQLException {
		ResultSet resultSet = sl.returnAllFinancialLaunchCreated(db);

		System.out.println("Chave -- Quantia ------ Lançado Em:");

		while (resultSet.next()) {
			int key = resultSet.getInt("contrato");
			Double quantia = resultSet.getDouble("quantia");
			String lancamento = resultSet.getString("lancadaem");
			System.out.println(
					key + " ------ " + quantia + " ------ " + lancamento);
		}
	}

	public void closeConnection() throws SQLException {
		db.close();
	}
}
