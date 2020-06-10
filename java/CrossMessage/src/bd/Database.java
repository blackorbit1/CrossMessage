package bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.sql.DataSource;

import com.mysql.jdbc.ConnectionFeatureNotAvailableException;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Database {
	private DataSource dataSource;

	public Database(String jndiname) throws SQLException {
		try {
			dataSource =
					(DataSource) new InitialContext().lookup("java:comp/env/"
							+ jndiname);
		} catch (NamingException e) {
			throw new SQLException(jndiname + " is missing in JNDI! : "
					+ e.getMessage());
		}
	}

	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	
	
	public static Date getDate(int hdecalage){
		GregorianCalendar gc = new GregorianCalendar();
		if(hdecalage==1)
			gc.add(GregorianCalendar.HOUR, 1);
		return gc.getTime();
	
	}
	
	public static int getUserId(String key) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection c= ConnectionTools.getMySQLConnection();
		String query= "select userID from session where cle=\""+key+"\"";
		Statement st = c.createStatement();
		ResultSet rs= st.executeQuery(query);
		if(!rs.next()) throw new SQLException("utilisateur etrangement inexistant");
		int userID = rs.getInt("userID");
		c.close();
		return userID;
	}

	public static String getPseudoFromKey(String key) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection c= ConnectionTools.getMySQLConnection();
		String pseudo = "unknown";

		String query_id = "select userID from session where cle=\""+key+"\"";


		Statement st_id = c.createStatement();
		ResultSet rs_id = st_id.executeQuery(query_id);

		if(rs_id.next()){
			String userID = rs_id.getString("id");
			String queryPseudo= "select nom from user where userID=\""+userID+"\";";

			Statement st_pseudo = c.createStatement();
			ResultSet rs_pseudo = st_pseudo.executeQuery(queryPseudo);

			if(rs_pseudo.next()) pseudo = rs_pseudo.getString("nom");
			
			rs_pseudo.close();
			st_pseudo.close();
		} 
		rs_id.close();
		st_id.close();	


		c.close();
		return pseudo;
	}
	
	
	public static int getUserId(Connection c,String key) throws ClassNotFoundException, SQLException{
		//created a second function where a connection parameter
		String query= "select userID from session where cle=\""+key+"\"";
		Statement st = c.createStatement();
		ResultSet rs= st.executeQuery(query);
		if(!rs.next()) throw new SQLException("utilisateur etrangement inexistant");
		int userID = rs.getInt("userID");
		return userID;
	}
	
	public static int getUserIdFromLogin(Connection c,String login) throws ClassNotFoundException, SQLException{
		//created a second function where a connection parameter
		String query= "select userID from user where login=\""+login+"\"";
		Statement st = c.createStatement();
		ResultSet rs= st.executeQuery(query);
		if(!rs.next()) throw new SQLException("utilisateur etrangement inexistant");
		int userID = rs.getInt("userID");
		return userID;
	}
	
	

}
