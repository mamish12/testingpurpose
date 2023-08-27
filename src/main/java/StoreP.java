import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StoreP extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		// Fetching data from Psignup.html from user
		String up = request.getParameter("Pusername");
		String pp = request.getParameter("Ppassword");
		String cp = request.getParameter("Cpassword");
		String name = request.getParameter("Pname");

		// Asign to another strings

		String a1 = up;
		String a2 = pp;
		String a3 = cp;
		String a4 = name;

		if (a2.equals(a3)) {
			// Connection & storing into Database
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String url = "jdbc:mysql://localhost:3306/event-management";
				String user = "root"; // Username of database
				String pass = "root"; // Password for system
				// Create connection
				Connection con = DriverManager.getConnection(url, user, pass);

				Statement stmt = con.createStatement();
				// SQL statement
				String insert = "insert into plogindetails values('" + a1 + "','" + a2 + "','" + a4 + "') ";
				stmt.execute(insert);

				RequestDispatcher rd = request.getRequestDispatcher("Plogin.html");
				rd.forward(request, response);
				con.close();

			} catch (Exception exe) {
				System.out.println(exe);
			}

		} else {
			out.println("<center><h1>!! Please Enter Password And Confirm Password Same !!</h1><center>");
			RequestDispatcher rd = request.getRequestDispatcher("Psignup.html");
			rd.include(request, response);
		}

	}

}
