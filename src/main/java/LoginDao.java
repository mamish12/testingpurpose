import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDao {

	public static boolean validate(String User_name, String Pass_word) {
		boolean status = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/event-management", "root",
					"root");
			PreparedStatement ps = con
					.prepareStatement("select * from plogindetails where user_name=? and pass_word=?");
			ps.setString(1, User_name);
			ps.setString(2, Pass_word);
			ResultSet rs = ps.executeQuery();
			status = rs.next();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
}
