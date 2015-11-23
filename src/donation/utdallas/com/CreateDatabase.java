package donation.utdallas.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class CreateDatabase {
	private Connection connect;
	private Statement statement;
	// private PreparedStatement preparedStatement;
	private ResultSet resultset;
	private String Query;
	private String CONNECTION_STRING;

	public CreateDatabase() {
		connect = null;
		statement = null;
		CONNECTION_STRING = "jdbc:mysql://localhost:3307/wedonate";
	}

	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded...");
			connect = DriverManager
					.getConnection(CONNECTION_STRING, "root", "");

			System.out.println("connected");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insert(String name, String uname, String password, String dob,
			String bgroup, String type, String id, String mail, String phone,
			int age, String gender, String address, String orgName) {

		try {
			System.out.println(dob);
			statement = connect.createStatement();

			Query = "INSERT INTO user(username,password,name,dob,blood,idtype,id,email,phone,age,gender,address,orgname) values(\""
					+ uname
					+ "\",\""
					+ password
					+ "\",\""
					+ name
					+ "\",\""
					+ dob
					+ "\",\""
					+ bgroup
					+ "\",\""
					+ type
					+ "\",\""
					+ id
					+ "\",\""
					+ mail
					+ "\",\""
					+ phone
					+ "\",\""
					+ age
					+ "\",\""
					+ gender
					+ "\",\""
					+ address
					+ "\",\""
					+ orgName
					+ "\");";

			System.out.println(Query);

			statement.executeUpdate(Query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void insertBook(String name, int quantity, String aname,
			String dname, String phone) {

		try {

			statement = connect.createStatement();
			Query = "INSERT INTO item(category,itemName,quantity,authorName,name,phone) values(\""
					+ "Book"
					+ "\",\""
					+ name
					+ "\",\""
					+ quantity
					+ "\",\""
					+ aname + "\",\"" + dname + "\",\"" + phone + "\");";

			System.out.println(Query);

			statement.executeUpdate(Query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String retrieve(String username, String password) {
		System.out.println("testing this.....");
		try {
			String name = null, pass = null;
			statement = connect.createStatement();
			Query = "SELECT * FROM user where username = \"" + username + "\";";
			System.out.println(Query);
			resultset = statement.executeQuery(Query);
			while (resultset.next()) {
				name = resultset.getString(1);
				pass = resultset.getString(2);
			}
			if (name != null) {
				if (name.equals(username) && pass.equals(password))
					return "success";
				else
					return "error";

			}
			return "error";

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	}

	public ArrayList<UploadBookInfo> retrieveBookInfo() {
		System.out.println("testing this.....");
		ArrayList<UploadBookInfo> list = new ArrayList<UploadBookInfo>();
		try {
			UploadBookInfo bk;
			statement = connect.createStatement();
			Query = "SELECT * FROM item where category=\"Book\";";
			System.out.println(Query);
			resultset = statement.executeQuery(Query);
			while (resultset.next()) {
				bk = new UploadBookInfo();
				bk.setBname(resultset.getString("itemName"));
				bk.setAname(resultset.getString("authorName"));
				bk.setQuantity(resultset.getInt("quantity"));
				bk.setDname(resultset.getString("name"));
				bk.setContact(resultset.getString("phone"));
				list.add(bk);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public void insertMisc(String name, int quantity, String dname, String phone) {

		try {

			statement = connect.createStatement();
			Query = "INSERT INTO item(category,itemName,quantity,name,phone) values(\""
					+ "Garage"
					+ "\",\""
					+ name
					+ "\",\""
					+ quantity
					+ "\",\""
					+ dname + "\",\"" + phone + "\");";

			System.out.println(Query);

			statement.executeUpdate(Query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<UploadMiscInfo> retrieveMiscInfo() {
		ArrayList<UploadMiscInfo> list = new ArrayList<UploadMiscInfo>();
		try {
			UploadMiscInfo garage;
			statement = connect.createStatement();
			Query = "SELECT * FROM item where category=\"Garage\";";
			System.out.println(Query);
			resultset = statement.executeQuery(Query);
			while (resultset.next()) {
				garage = new UploadMiscInfo();
				garage.setName(resultset.getString("itemName"));
				garage.setQuantity(resultset.getInt("quantity"));
				garage.setDname(resultset.getString("name"));
				garage.setContact(resultset.getString("phone"));
				list.add(garage);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public void insertFood(String name, int quantity, String dname, String phone) {

		try {

			statement = connect.createStatement();
			Query = "INSERT INTO item(category,itemName,quantity,name,phone) values(\""
					+ "Food"
					+ "\",\""
					+ name
					+ "\",\""
					+ quantity
					+ "\",\""
					+ dname + "\",\"" + phone + "\");";

			System.out.println(Query);

			statement.executeUpdate(Query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<UploadFoodInfo> retrieveFoodInfo() {
		ArrayList<UploadFoodInfo> list = new ArrayList<UploadFoodInfo>();
		try {
			UploadFoodInfo food;
			statement = connect.createStatement();
			Query = "SELECT * FROM item where category=\"Food\";";
			System.out.println(Query);
			resultset = statement.executeQuery(Query);
			while (resultset.next()) {
				food = new UploadFoodInfo();
				food.setName(resultset.getString("itemName"));
				food.setQuantity(resultset.getInt("quantity"));
				food.setDname(resultset.getString("name"));
				food.setContact(resultset.getString("phone"));
				list.add(food);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<UploadUserInfo> searchBlood(String bloodGroup) {
		ArrayList<UploadUserInfo> bloodinfo = new ArrayList<UploadUserInfo>();
		UploadUserInfo user;
		try {
			statement = connect.createStatement();
			Query = "SELECT name,email,phone FROM user where blood = \""
					+ bloodGroup + "\";";
			System.out.println(Query);
			resultset = statement.executeQuery(Query);
			while (resultset.next()) {
				user = new UploadUserInfo();
				user.setName(resultset.getString("name"));
				user.setMail(resultset.getString("email"));
				user.setPhone(resultset.getString("phone"));
				bloodinfo.add(user);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return bloodinfo;

	}

}
