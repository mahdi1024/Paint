public class Person {
	private String user;
	private String pass;
	private int userID;

	public Person(int a) {
		user = "";
		pass = "";
		userID = 0;
	}

	public Person() {
		userID = 0;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
