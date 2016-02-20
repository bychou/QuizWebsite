package web;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import com.mysql.jdbc.Statement;

public class AccountManager {
	
	private Statement stmt;
	
	/*
	 Given a byte[] array, produces a hex String,
	 such as "234a6f". with 2 chars for each byte in the array.
	 (provided code)
	*/
	public static String hexToString(byte[] bytes) {
		StringBuffer buff = new StringBuffer();
		for (int i=0; i<bytes.length; i++) {
			int val = bytes[i];
			val = val & 0xff;  // remove higher bits, sign
			if (val<16) buff.append('0'); // leading 0
			buff.append(Integer.toString(val, 16));
		}
		return buff.toString();
	}
	
	/* Constructor. */
	public AccountManager(Statement stmt) {
		this.stmt = stmt;
	}
	
	/* Returns true if "username" exists in accounts. */
	public boolean accountExist (String username) {
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM accounts WHERE username = \"" + username + "\";");
			rs.last();
			return rs.getRow() >= 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* Should not reach here. */
		return false;
	}
	
	/* Returns true if the provided password is authenticate for user "username".*/
	public boolean checkCredential(String username, String password) {
		if (!accountExist(username)) { return false; }
		
		try {			
			/* Hash the password to encrypted version. */
			String encryptedPassword = generateHash(password);
			
			/* Compare to the one that stored in database. */
			ResultSet rs = stmt.executeQuery("SELECT * FROM accounts WHERE username = \"" + username + "\";");
			rs.next();
			
			return rs.getString("password").equals(encryptedPassword);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* Should not reach here. */
		return false;
	}
	
	/* Creates an account with username & password. */
	public void createAccount(String username, String password) {
		String encryptedPassword = generateHash(password);
		try {
			stmt.executeQuery("INSERT INTO accounts VALUES (\"" + username + "\",\"" + encryptedPassword + "\");");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/* Returns hashed string of the input string. */
	private String generateHash(String str) {
		String result = null;
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			md.update(str.getBytes());
			result = hexToString(md.digest());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	
}
