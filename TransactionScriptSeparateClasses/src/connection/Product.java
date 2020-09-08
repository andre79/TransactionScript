package connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Product
{
	private ResultSet resultSet = null;
	
	public ResultSet readProductList(ConnectionDB db) throws SQLException {

		String trasactionScriptSelectProduct = "select * from produtos";
		PreparedStatement preparedStatement = db.getConnect().prepareStatement(trasactionScriptSelectProduct);
		return preparedStatement.executeQuery();		
	}
	
}
