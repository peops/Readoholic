package readoholic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** 
 * CREATE TABLE users (username varchar(50) PRIMARY KEY, password varchar(20) NOT NULL, 
   						name varchar(50) NOT NULL, email varchar(40) NOT NULL, 
   						phone char(10) NOT NULL, address varchar(50) NOT NULL);
 * @author Innovationchef
 * 
 */

/** 
 * CREATE TABLE assets (assetId varchar(50) PRIMARY KEY, assetName varchar(40) NOT NULL, 
  						assetClass varchar(30) NOT NULL, assetOwnerId varchar(50) NOT NULL, assetBorrowerId varchar(50),
  						assetDescription varchar(100) NOT NULL, assetSecurityDeposit bigint NOT NULL, 
  						assetRequestStatus BOOLEAN NOT NULL, assetAllocationStatus BOOLEAN NOT NULL);
 * @author Innovationchef
 *
 */

/** 
 * CREATE TABLE transactions (transactionid varchar(50) PRIMARY KEY, assetlenderid varchar(50) NOT NULL, 
  						borrowerId varchar(50) NOT NULL, assetid varchar(50) NOT NULL, lenddate varchar(50) NOT NULL,
  						returndate varchar(50) NOT NULL);
 * @author Innovationchef
 *
 */
public interface DBMethod {
	static User user_resultset(ResultSet rs) throws SQLException{
		User user = null;
        String usern = rs.getString("username");
        String name = rs.getString("name");
        String phone = rs.getString("phone");
        String email = rs.getString("email");
        String address = rs.getString("address");
        user = new User(usern, name, address, phone, email);
        return user;
	}
	static User login(Connection conn, String username, String password) throws SQLException{
		PreparedStatement preparedStatement = null;
	    String query = "SELECT * FROM users WHERE username = ? AND password = ?";
	    User user = null;
	    preparedStatement = conn.prepareStatement(query);
    	preparedStatement.setString(1, username);
    	preparedStatement.setString(2, password);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
        	user = user_resultset(rs);
        }
        return user;
	}
	static User read_user(Connection conn, String username) throws SQLException{
		PreparedStatement preparedStatement = null;
	    String query = "SELECT * FROM users WHERE username = ?";
	    User user = null;
	    preparedStatement = conn.prepareStatement(query);
    	preparedStatement.setString(1, username);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
        	user = user_resultset(rs);
        }
        return user;
	}
	static void add_user(Connection conn, User user){
		PreparedStatement preparedStatement = null;
	    String query = "INSERT INTO users(username, password, name, email, phone, address) VALUES(?,?,?,?,?,?)";
	    try{
	    	preparedStatement = conn.prepareStatement(query);
	    	preparedStatement.setString(1, user.getUserName());
	    	preparedStatement.setString(2, user.getPassword());
	    	preparedStatement.setString(3, user.getName());
	    	preparedStatement.setString(4, user.getEmail());
	    	preparedStatement.setString(5, user.getPhoneNo());
	    	preparedStatement.setString(6, user.getAddress());
	        preparedStatement.executeUpdate();
	        preparedStatement.close();
	    } 
	    catch(SQLException e ){
	        System.out.println(e.getMessage());
	    } 
	    finally{
	        if(preparedStatement != null){
	        	try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    }
	}
	
	static Asset asset_resultset(ResultSet rs) throws SQLException{
        String assetId = rs.getString("assetid");
        String assetName = rs.getString("assetname");
        String assetClass = rs.getString("assetclass");
        String assetOwnerId = rs.getString("assetownerid");
        String assetBorrowerId = rs.getString("assetborrowerid");
        String assetDescription = rs.getString("assetdescription");
        int assetSecurityDeposit = rs.getInt("assetsecuritydeposit");
        boolean assetRequestStatus = rs.getBoolean("assetrequeststatus");
        boolean assetAllocationStatus = rs.getBoolean("assetallocationstatus");    
        Asset asset = new Asset(assetId, assetName, assetClass, assetOwnerId, assetBorrowerId, assetDescription,
        						assetSecurityDeposit, assetRequestStatus, assetAllocationStatus);
        return asset;
	}
	static List<Asset> read_assets(Connection conn){
		PreparedStatement preparedStatement = null;
	    String query = "SELECT * FROM assets WHERE assetrequeststatus = FALSE AND assetallocationstatus = FALSE";
	    List<Asset> assets = new ArrayList<>();
	    try{
	    	preparedStatement = conn.prepareStatement(query);
	    	ResultSet rs = preparedStatement.executeQuery();
	    	while(rs.next()){
	    		Asset asset = asset_resultset(rs);
	    		assets.add(asset);
	    	}
	        return assets;
	    } 
	    catch(SQLException e ){
	        System.out.println(e.getMessage());
	        return assets;
	    } 
	    finally{
	        if(preparedStatement != null){
	        	try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	return assets;
	        }   
	    }
	}
	static Asset read_asset(Connection conn, String id){
		PreparedStatement preparedStatement = null;
	    String query = "SELECT * FROM assets where assetid = ?";
	    Asset asset = null;
	    try{
	    	preparedStatement = conn.prepareStatement(query);
	    	preparedStatement.setString(1, id);
	    	ResultSet rs = preparedStatement.executeQuery();
	    	while(rs.next()){
	    		asset = asset_resultset(rs);
	    	}
	        return asset;
	    } 
	    catch(SQLException e ){
	        System.out.println(e.getMessage());
	        return asset;
	    } 
	    finally{
	        if(preparedStatement != null){
	        	try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	return asset;
	        }   
	    }
	}
	static List<Asset> read_requested_assets(Connection conn, String userId){
		PreparedStatement preparedStatement = null;
	    String query = "SELECT * FROM assets WHERE assetrequeststatus = TRUE AND assetownerid = ?";
	    List<Asset> assets = new ArrayList<>();
	    try{
	    	preparedStatement = conn.prepareStatement(query);
	    	preparedStatement.setString(1, userId);
	    	ResultSet rs = preparedStatement.executeQuery();
	    	while(rs.next()){
	    		Asset asset = asset_resultset(rs);
	    		assets.add(asset);
	    	}
	        return assets;
	    } 
	    catch(SQLException e ){
	        System.out.println(e.getMessage());
	        return assets;
	    } 
	    finally{
	        if(preparedStatement != null){
	        	try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	return assets;
	        }   
	    }
	}	
	static List<Asset> read_user_assets(Connection conn, String userId){
		PreparedStatement preparedStatement = null;
	    String query = "SELECT * FROM assets WHERE assetownerid = ?";
	    List<Asset> assets = new ArrayList<>();
	    try{
	    	preparedStatement = conn.prepareStatement(query);
	    	preparedStatement.setString(1, userId);
	    	ResultSet rs = preparedStatement.executeQuery();
	    	while(rs.next()){
	    		Asset asset = asset_resultset(rs);
	    		assets.add(asset);
	    	}
	        return assets;
	    } 
	    catch(SQLException e ){
	        System.out.println(e.getMessage());
	        return assets;
	    } 
	    finally{
	        if(preparedStatement != null){
	        	try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	return assets;
	        }   
	    }
	}	
	static List<Asset> read_borrowed_assets(Connection conn, String userId){
		PreparedStatement preparedStatement = null;
	    String query = "SELECT * FROM assets WHERE assetborrowerid = ? AND assetallocationstatus = TRUE";
	    List<Asset> assets = new ArrayList<>();
	    try{
	    	preparedStatement = conn.prepareStatement(query);
	    	preparedStatement.setString(1, userId);
	    	ResultSet rs = preparedStatement.executeQuery();
	    	while(rs.next()){
	    		Asset asset = asset_resultset(rs);
	    		assets.add(asset);
	    	}
	        return assets;
	    } 
	    catch(SQLException e ){
	        System.out.println(e.getMessage());
	        return assets;
	    } 
	    finally{
	        if(preparedStatement != null){
	        	try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	return assets;
	        }   
	    }
	}	
	static void add_asset(Connection conn, Asset asset){
		PreparedStatement preparedStatement = null;
		String query = "INSERT INTO assets(assetid , assetname , assetclass , assetownerid , assetborrowerid"
	    		+ ", assetdescription , assetsecuritydeposit, assetrequeststatus ,assetallocationstatus"
	    		+ ") VALUES(?,?,?,?,?,?,?,?,?)";
	    try{
	    	preparedStatement = conn.prepareStatement(query);
	    	preparedStatement.setString(1, asset.getAssetId());
	    	preparedStatement.setString(2, asset.getAssetName());
	    	preparedStatement.setString(3, asset.getAssetClass());
	    	preparedStatement.setString(4, asset.getAssetOwnerId());
	    	preparedStatement.setString(5, asset.getAssetBorrowerId());
	    	preparedStatement.setString(6, asset.getAssetDescription());
	    	preparedStatement.setInt(7, asset.getAssetSecurityDeposit());
	    	preparedStatement.setBoolean(8, asset.isAssetRequestStatus());
	    	preparedStatement.setBoolean(9, asset.isAssetAllocationStatus());
	        preparedStatement.executeUpdate();
	    } 
	    catch(SQLException e ){
	        System.out.println(e.getMessage());
	    } 
	    finally{
	        if(preparedStatement != null){
	        	try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    }
	}
	@SuppressWarnings("unused")
	static void delete_asset(Connection conn, String assetId){
		PreparedStatement preparedStatement = null;
		String query = "DELETE FROM assets WHERE assetid = ?";
		try{
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, assetId);
			int temp = preparedStatement.executeUpdate();
		}
		catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
	}
	@SuppressWarnings("unused")
	static void edit_asset_status(Connection conn, String assetId, boolean request_flag, boolean allocation_flag){
		PreparedStatement preparedStatement = null;
		String query = "UPDATE assets SET assetrequeststatus = ?, assetallocationstatus = ? WHERE assetid = ? ";
		try{
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setBoolean(1, request_flag);
			preparedStatement.setBoolean(2, allocation_flag);
			preparedStatement.setString(3, assetId); 
			int temp = preparedStatement.executeUpdate();
		}
		catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
	}
	@SuppressWarnings("unused")
	static void edit_asset_borrower(Connection conn, String assetId, String borrowerid){
		PreparedStatement preparedStatement = null;
		String query = "UPDATE assets SET assetborrowerid = ? WHERE assetid = ? ";
		try{
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, borrowerid);
			preparedStatement.setString(2, assetId);
			int temp = preparedStatement.executeUpdate();
		}
		catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
	}
	
	static Transaction txn_resultset(ResultSet rs) throws SQLException{
		String transactionId = rs.getString("transactionid");
        String lenderId = rs.getString("assetlenderid");
        String borrowerId = rs.getString("borrowerid");
        String assetId = rs.getString("assetid");
        String lendDate = rs.getString("lenddate");
        String returnDate = rs.getString("returndate");
        Transaction transaction = new Transaction(transactionId, lenderId, borrowerId, 
				assetId, lendDate, returnDate);
        return transaction;
	}
	static List<Transaction> read_transactions(Connection conn){
		PreparedStatement preparedStatement = null;
	    String query = "SELECT * FROM transactions";
	    List<Transaction> transactions = new ArrayList<>();
	    try{
	    	preparedStatement = conn.prepareStatement(query);
	    	ResultSet rs = preparedStatement.executeQuery();
	        while (rs.next()) {
	        	Transaction transaction = txn_resultset(rs);
	            transactions.add(transaction);
	        }
	        return transactions;
	    } 
	    catch(SQLException e ){
	        System.out.println(e.getMessage());
	        return transactions;
	    } 
	    finally{
	        if(preparedStatement != null){
	        	try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	return transactions;
	        }
	    }
	}
	static List<Transaction> read_user_transactions(Connection conn, String username){
		PreparedStatement preparedStatement = null;
	    String query = "SELECT * FROM transactions WHERE assetlenderid = ? OR borrowerid = ?";
	    List<Transaction> transactions = new ArrayList<>();
	    try{
	    	preparedStatement = conn.prepareStatement(query);
	    	preparedStatement.setString(1, username);
	    	preparedStatement.setString(2, username);
	        ResultSet rs = preparedStatement.executeQuery();
	        while (rs.next()) {
	        	Transaction transaction = txn_resultset(rs);
	            transactions.add(transaction);
	        }
	        return transactions;
	    } 
	    catch(SQLException e ){
	        System.out.println(e.getMessage());
	        return transactions;
	    } 
	    finally{
	        if(preparedStatement != null){
	        	try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	return transactions;
	        }
	    }
	}
	static void add_transaction(Connection conn, Transaction transaction){
		PreparedStatement preparedStatement = null;
	    String query = "INSERT INTO transactions(transactionid , assetlenderid , borrowerid , assetid , "
	    		+ "lenddate , returndate) VALUES(?,?,?,?,?,?)";
	    try{
	    	preparedStatement = conn.prepareStatement(query);
	    	preparedStatement.setString(1, transaction.gettransactionId());
	    	preparedStatement.setString(2, transaction.getLenderId());
	    	preparedStatement.setString(3, transaction.getBorrowerId());
	    	preparedStatement.setString(4, transaction.getAssetId());
	    	preparedStatement.setString(5, transaction.getLendDate());
	    	preparedStatement.setString(6, transaction.getReturnDate());
	        preparedStatement.executeUpdate();
	    } 
	    catch(SQLException e ){
	        System.out.println(e.getMessage());
	    } 
	    finally{
	        if(preparedStatement != null){
	        	try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    }
	}
}
