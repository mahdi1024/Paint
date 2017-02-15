import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonEnManager {
	private Person person = new Person();
	private ResultSet rs;
	private String massage;
	private boolean result = false;

	public String getMassage() {
		return massage;
	}

	public Person getPerson() {
		checkUserPass();
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public boolean isResult() {
		return result;
	}

	public void checkUserPass() {
		try {
			String user = person.getUser();
			String pass = person.getPass();
			String sql;

			if (user.isEmpty() || pass.isEmpty()) {
				return;
			} else {
				sql = "SELECT * FROM paint.login_tb where username = '" + user
						+ "' and password = md5('" + pass + "');";
			}

			rs = Connect.stmt.executeQuery(sql);

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				person.setUserID(rs.getInt("idlogin_tb"));
				person.setUser(rs.getString("username"));
				person.setPass(rs.getString("password"));
				result = true;
			}

			if (result) {
				rs.close();
				massage = "ok";
			} else {
				rs.close();
				massage = "Your username or password is wrong.";
			}
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
			massage = "Database error";
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
			massage = "Database error";
		} finally {

		}
	}
	
	public boolean checkUsername(String str){
		try {
			String sql = "SELECT * FROM paint.login_tb where username = '" + str + "';";
			rs = Connect.stmt.executeQuery(sql);
			
			while(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
			return true;
		}
		return false;
	}
	
	public void addUserDatabase(String username, String password) {
		try {
			// STEP 4: Execute a query
			String sql = "INSERT INTO `paint`.`login_tb` (`username`, `password`) VALUES ('" + username + "', md5('" + password + "'));";
			Connect.stmt.executeUpdate(sql);
			massage = "Sing Up successfully.";
		} catch (SQLException se) {
			// Handle errors for JDBC
			// se.printStackTrace();
			massage = "Sing Up failed!";
		} catch (Exception e) {
			// Handle errors for Class.forName
			massage = "Sing Up failed!!";
		} finally {
			// finally block used to close resources
		}
	}
}
