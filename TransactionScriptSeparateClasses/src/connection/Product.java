package connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Product
{
	private ResultSet resultSet = null;
	
	public void readProductList(ConnectionDB db) throws SQLException {

		String trasactionScriptSelectProduct = "select * from produtos";
		PreparedStatement preparedStatement = db.getConnect().prepareStatement(trasactionScriptSelectProduct);
		resultSet = preparedStatement.executeQuery();

		System.out.println("Chave -- Nome ------------------ Tipo");

		while (resultSet.next()) {
			int key = resultSet.getInt("key");
			String nome = resultSet.getString("nome");
			String tipo = resultSet.getString("tipo");
			System.out.println(key + " ------ " + nome + " ------ " + tipo);
		}
	}
	
}
