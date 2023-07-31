package dbDeneme;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class Main {

	
	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost:3306/digital_eglence_platformu";
		String username = "root";
		String password = "root";

		try (Connection con = DriverManager.getConnection(url, username, password)) {

			Statement statement1 = con.createStatement();
			String query1 = "SELECT kullanici.kullaniciID, kullanici.eposta "
					+ "FROM kullanici JOIN uyedir ON kullanici.kullaniciID = uyedir.kullaniciID "
					+ "JOIN uyelik ON uyedir.uyelikID = uyelik.uyelikID WHERE uyelik.ekranSayisi > 2;";

			ResultSet resultSet = statement1.executeQuery(query1);

			while (resultSet.next()) {
				int kullaniciID = resultSet.getInt("kullaniciID");
				String eposta = resultSet.getString("eposta");
				System.out.println(kullaniciID + "-" + eposta);
			}

			Statement stmt2 = con.createStatement();
			String query2 = "SELECT kullanici.kullaniciID, kullanici.eposta "
					+ "FROM kullanici INNER JOIN begenir ON kullanici.kullaniciID = begenir.kullaniciID "
					+ "INNER JOIN video ON begenir.videoID = video.videoID WHERE video.baslik = 'Scarface'";

			ResultSet resultSet2 = stmt2.executeQuery(query2);

			while (resultSet2.next()) {
				int kullaniciID = resultSet2.getInt("kullaniciID");
				String eposta = resultSet2.getString("eposta");
				System.out.println(kullaniciID + "-" + eposta);
			}

			String query3 = "SELECT AVG(uyelik.ucret) "
					+ "FROM uyelik JOIN uyedir ON uyelik.uyelikID = uyedir.uyelikID "
					+ "JOIN kullanici ON uyedir.kullaniciID = kullanici.kullaniciID "
					+ "JOIN begenir ON kullanici.kullaniciID = begenir.kullaniciID "
					+ "JOIN video ON begenir.videoID = video.videoID "
					+ "JOIN yonetir ON video.videoID = yonetir.videoID "
					+ "JOIN yonetmen ON yonetir.yonetmenID = yonetmen.yonetmenID " + "GROUP BY yonetmen.yonetmenID";

			Statement statement3 = con.createStatement();
			ResultSet resultSet3 = statement3.executeQuery(query3);

			while (resultSet3.next()) {
				double ortalamaUcret = resultSet3.getDouble(1);
				System.out.println(ortalamaUcret);
			}

			String query4 = "SELECT uyedir.kullaniciID, MAX(uyelik.ekranSayisi) AS ekranSayisi " + "FROM uyedir "
					+ "JOIN kullanici ON uyedir.kullaniciID = kullanici.kullaniciID "
					+ "JOIN uyelik ON uyedir.uyelikID = uyelik.uyelikID "
					+ "JOIN begenir ON kullanici.kullaniciID = begenir.kullaniciID " + "GROUP BY uyedir.kullaniciID "
					+ "HAVING COUNT(DISTINCT begenir.videoID) > 1";

			Statement statement4 = con.createStatement();
			ResultSet resultSet4 = statement4.executeQuery(query4);

			while (resultSet4.next()) {
				int kullaniciID = resultSet4.getInt("kullaniciID");
				int ekranSayisi = resultSet4.getInt("ekranSayisi");

				System.out.println(kullaniciID + "-" + ekranSayisi);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}

	}
}

