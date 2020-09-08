package connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Contract {

	private ResultSet resultSet = null;

	public ResultSet readContractsList(ConnectionDB db) throws SQLException {
		String trasactionScriptContractsList = "select * from contratos";
		PreparedStatement preparedStatement = db.getConnect().prepareStatement(trasactionScriptContractsList);
		return preparedStatement.executeQuery();
	}

}
