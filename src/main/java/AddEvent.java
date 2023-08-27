import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class AddEvent extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		// Fetching data from HTML form
		String a1 = request.getParameter("EventNo");
		String a2 = request.getParameter("EventName");
		String a3 = request.getParameter("coordinatorName");
		String a4 = request.getParameter("CoordinatorNo");
		String a5 = request.getParameter("fee");
		String a6 = request.getParameter("venue");
		String a7 = request.getParameter("date");

		// Connection to Database
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/event-management";
			String user = "root";
			String pass = "root";
			Connection con = DriverManager.getConnection(url, user, pass);// Establish Connection

			Statement stmt = con.createStatement();
			String Qs = "insert into event values('" + a1 + "','" + a2 + "','" + a3 + "','" + a4 + "','" + a5 + "','"
					+ a6 + "','" + a7 + "') ";
			stmt.execute(Qs);

			RequestDispatcher rd = request.getRequestDispatcher("CreateE.html");
			rd.include(request, response);

			out.println("<br><center><h3> Event Added</h3></center>");
			System.out.println("Added to database!");
			con.commit();
			con.close();
		} catch (Exception exe) {
			System.out.println("Exception caught" + exe);
		}
	}
}
