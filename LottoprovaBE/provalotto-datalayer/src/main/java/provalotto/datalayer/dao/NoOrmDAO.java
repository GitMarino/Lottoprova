package provalotto.datalayer.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NoOrmDAO {

	public static void main(final String[] args) {
		try (
				// SETTINGS

				// database connection
				Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lotto-prova-db",
						"postgres", "pass");

				// statement object
				Statement statement = connection.createStatement();

				// prepared statement object
				PreparedStatement preparedStatement = connection.prepareStatement(
						"select serial, surname from person where serial > ? and surname = ? order by serial")

		) {
			// QUERIES AND RESULTS

			// statement
			String queryString = "select serial, surname from person order by serial";
			ResultSet queryResult = statement.executeQuery(queryString);
			while (queryResult.next()) {
				Integer serial = queryResult.getInt("serial");
				String surname = queryResult.getString("surname");
				System.out.println(serial + " " + surname);
			}

			System.out.println();

			// prepared statement
			preparedStatement.setInt(1, 90);
			preparedStatement.setString(2, "marino");
			queryResult = preparedStatement.executeQuery();
			while (queryResult.next()) {
				Integer serial = queryResult.getInt("serial");
				String surname = queryResult.getString("surname");
				System.out.println(serial + " " + surname);
			}

		} catch (SQLException e) {
			System.out.println(e);
			System.out.println(e.getMessage());
		}
	}

}