package connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Contract {
	
	private ResultSet resultSet = null;
	
	public void readContractsList(ConnectionDB db) throws SQLException {

		String trasactionScriptContractsList = "select * from contratos";
		PreparedStatement preparedStatement = db.getConnect().prepareStatement(trasactionScriptContractsList);
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

}
