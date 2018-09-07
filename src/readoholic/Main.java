package readoholic;

import java.util.Scanner;
import java.util.*;
import java.sql.*;

public class Main {
	private static User user;
	private static Connection conn;
    
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		@SuppressWarnings("unused")
		DBConnection dbcon = DBConnection.getInstance();
		conn = DBConnection.getStatement();
        
		entryScreen();
	}
	public static void entryScreen() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Scanner sc = new Scanner(System.in);
		System.out.println("Make your choice:");
		System.out.println("1. Login");
		System.out.println("2. Sign Up");
		System.out.println("3. Exit");
		int Choice = sc.nextInt();
		sc.nextLine();
		if(Choice == 1){
			login();
		}
		else if(Choice == 2){
			signup();
		}
		else if(Choice == 3){
			System.exit(0);
		}
		else{
			System.out.println("Invalid Input. Please try again!");
			entryScreen();
		}
		sc.close();
	}
	public static void signup() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Details:");
		System.out.println("Set username:");
		String username = sc.nextLine();
		System.out.println("Set Password:");
		String password = sc.nextLine();
		System.out.println("Your Name");
		String name = sc.nextLine();
		System.out.println("Your Address");
		String address = sc.nextLine();
		System.out.println("Your Phone No");
		String phone = sc.nextLine();
		System.out.println("Your Email Address");
		String email = sc.nextLine();
		User user = new User(username, password, name, address, phone, email);
		int Choice = 0;
		System.out.println("Choose option");
		System.out.println("1. Are you sure? Want to submit?");
		System.out.println("2. Fill the form again");
		System.out.println("3. Go to Log In screen");
		System.out.println("4. Exit");
		Choice = sc.nextInt();
		sc.nextLine();
		if(Choice == 1){
			System.out.println(user);
			DBMethod.add_user(conn, user);
			System.out.println("Thank you for signing up. Keep borrowing, keep lending!");
			entryScreen();
		}
		else if(Choice == 2){
			signup();
		}
		else if(Choice == 3){
			login();
		}
		else if(Choice == 4){
			System.exit(0);
		}
		else{
			System.out.println("Invalid Input. Please try again!");
			signup();
		}
		sc.close();
	}
	public static void login() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter user ID:");
		String username = sc.nextLine();
		System.out.println("Enter you password:");
		String password = sc.nextLine();
		int Choice;
		System.out.println("Choose option");
		System.out.println("1. Submit");
		System.out.println("2. Fill the login credentials again");
		System.out.println("3. Go to Sign up screen");
		System.out.println("4. Exit");
		Choice = sc.nextInt();
		sc.nextLine();
		if(Choice == 1){
			try {
				user = DBMethod.login(conn, username, password);
				loggedin();
			} catch (SQLException e) {
				System.out.println("Incorrect Id/Password! Please try again.");
			}
		}
		else if(Choice == 2){
			login();
		}
		else if(Choice == 3){
			signup();
		}
		else if(Choice == 4){
			System.exit(0);
		}
		else{
			System.out.println("Invalid Input. Please try again!");
			login();
		}
		sc.close();
	}
	public static void loggedin() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Scanner sc = new Scanner(System.in);
		System.out.println("Make your choice:");
		System.out.println("1. Lend");
		System.out.println("2. Borrow");
		System.out.println("3. Show Notifications");
		System.out.println("4. Logout");
		System.out.println("5. Exit");
		int Choice = sc.nextInt();
		sc.nextLine();
		if(Choice==1){
			lendTrue();
		}
		else if(Choice==2){
			borrowTrue();
		}
		else if(Choice==3){
			notifications();
		}
		else if(Choice==4){
			login();
		}
		else if(Choice==5){
			System.exit(0);
		}
		else{
			System.out.println("Enter valid input");
			loggedin();
		}
		sc.close();
	}
	public static void notifications() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Scanner sc = new Scanner(System.in);
		List<Transaction> transactions = DBMethod.read_user_transactions(conn, user.getUserName());
		for(Transaction t: transactions){
			System.out.println(t);
			System.out.println("---------------------------------------------------");
		}
		System.out.println("To go back, type-in 0;");
		int check = sc.nextInt();
		sc.nextLine();
		if(check==0){
			loggedin();
		}
		sc.close();
	}	
	public static void lendTrue() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Scanner sc = new Scanner(System.in);
		System.out.println("Make your choice:");
		System.out.println("1. Add asset to portal");
		System.out.println("2. Approve borrow requests");
		System.out.println("3. Check Your assets");
		System.out.println("4. Go back");
		System.out.println("5. Exit");
		int Choice = sc.nextInt();
		sc.nextLine();
		if(Choice==1){
			addAsset();
		}
		else if(Choice==2){
			approveBorrowRequests();
		}
		else if(Choice==3){
			yourLendedAssets();
		}
		else if(Choice==4){
			loggedin();
		}
		else if(Choice==5){
			System.exit(0);;
		}
		else{
			System.out.println("Invalid Input. Please try again!");
			lendTrue();
		}
		sc.close();
	}
	public static void addAsset() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Scanner sc = new Scanner(System.in);
		System.out.println("Fill in the following form:");
		System.out.println("1. Asset Name");
		String assetName = sc.nextLine();
		System.out.println("2. Asset Type(Eg: AC, Fridge, Utensils, Furniture)");
		String assetClass = sc.nextLine();
		System.out.println("3. Describe you asset");
		String assetDescription = sc.nextLine();
		System.out.println("5. Security Deposit for the asset");
		int assetSecurityDeposit = sc.nextInt();
		sc.nextLine();
		Asset asset = new Asset(assetName, assetClass, user.getUserName(), assetDescription, assetSecurityDeposit);
		int Choice = 0;
		System.out.println("Choose option");
		System.out.println("1. Enlist asset on the portal");
		System.out.println("2. Fill in the details again");
		System.out.println("3. Go back");
		System.out.println("4. Exit");
		Choice = sc.nextInt();
		sc.nextLine();
		if(Choice == 1){
			DBMethod.add_asset(conn, asset);
			System.out.println("Asset added to the DB");
			lendTrue();
		}
		else if(Choice == 2){
			addAsset();
		}
		else if(Choice == 3){
			lendTrue();
		}
		else if(Choice == 4){
			System.exit(0);
		}
		else{
			System.out.println("Invalid Input. Please try again!");
			addAsset();
		}
		sc.close();
	}
	public static void approveBorrowRequests() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Scanner sc = new Scanner(System.in);
		List<Asset> assets = DBMethod.read_requested_assets(conn, user.getUserName());
		int n = assets.size();
		if(n>0){
			System.out.println("Requests for your asset are as follows:");
			for(Asset a: assets){
				System.out.println(a);
				System.out.println("----------------------------------------------");
			}
			System.out.println("Which item you want to approve? Enter asset Id:");
			String assetId = sc.nextLine();
			System.out.println("For how long do you want to lend?");
			int ndays = sc.nextInt();
			sc.nextLine();
			Asset asset = DBMethod.read_asset(conn, assetId);
			String borrowerid = asset.getAssetBorrowerId();
			Transaction transaction = new Transaction(assetId, user.getUserName(), borrowerid ,ndays);
			
			System.out.println("Allocated. \nInvoice Generated:");
			System.out.println(transaction);
			DBMethod.add_transaction(conn, transaction);
			DBMethod.edit_asset_status(conn, assetId, false, true);
			System.out.println("The asset has been allocated.");
		}
		int Choice = 0;
		System.out.println("Choose option");
		System.out.println("1. Go back");
		System.out.println("2. Exit");
		Choice = sc.nextInt();
		sc.nextLine();
		if(Choice == 1){
			lendTrue();
		}
		else if(Choice == 2){
			System.exit(0);
		}
		else{
			System.out.println("Invalid Input. Please try again!");
			yourLendedAssets();
		}
		sc.close();
	}	
	public static void yourLendedAssets() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Scanner sc = new Scanner(System.in);
		System.out.println("Products listed by you on our portal:");
		List<Asset> assets = DBMethod.read_user_assets(conn, user.getUserName());
		int x=0;
		for(Asset a: assets){
			x++;
			System.out.println(a);
			System.out.println("Allocation status: "+ a.isAssetAllocationStatus());
			System.out.println("Request status: "+ a.isAssetRequestStatus());
			System.out.println("---------------------------------------------------");
		}
		if(x!=0){
			System.out.println("Do you want to delete any asset(Y/N)?");
			String yn = sc.nextLine();
			if(yn.equals("Y")){
				System.out.println("Enter asset ID:");
				String id = sc.nextLine();
				DBMethod.delete_asset(conn, id);
				yourLendedAssets();
			}
		}
		else if(x==0){
		}

		int Choice = 0;
		System.out.println("Choose option");
		System.out.println("1. Go back");
		System.out.println("2. Exit");
		Choice = sc.nextInt();
		sc.nextLine();
		if(Choice == 1){
			lendTrue();
		}
		else if(Choice == 2){
			System.exit(0);
		}
		else{
			System.out.println("Invalid Input. Please try again!");
			yourLendedAssets();
		}
		sc.close();
	}	

	public static void printAssets() throws ClassNotFoundException, InstantiationException, IllegalAccessException{	
		System.out.println("The available items for borrowing are:");
		List<Asset> assets = DBMethod.read_assets(conn);
		for(Asset a: assets){
			if(a.isAssetAllocationStatus()==false&&a.getAssetOwnerId().matches(user.getUserName())==false){
				System.out.println(a);
				System.out.println("---------------------------------------------------");
			}
		}
	}
	public static int borrowedAssets() throws ClassNotFoundException, InstantiationException, IllegalAccessException{	
		System.out.println("You have borrowed the following assets:");
		int x =0;
		List<Asset> assets = DBMethod.read_borrowed_assets(conn, user.getUserName());
		for(Asset a: assets){
			System.out.println(a);
			x++;
			System.out.println("---------------------------------------------------");
		}
		return x;
	}
	public static void borrowTrue() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Scanner sc = new Scanner(System.in);
		int Choice = 0;
		System.out.println("Choose option");
		System.out.println("1. Look for available assets");
		System.out.println("2. Check borrowed assets");
		System.out.println("3. Go back");
		System.out.println("4. Exit");
		Choice = sc.nextInt();
		sc.nextLine();
		if(Choice == 1){
			printAssets();
			System.out.println("Enter the item Id to be borrowed:");
			String selectedItem = sc.nextLine();
			System.out.println("Make your choice:");
			System.out.println("1. Submit request");
			System.out.println("2. Choose other items");
			System.out.println("3. Go back");
			System.out.println("4. Exit");
			int newChoice = sc.nextInt();
			sc.nextLine();
			if(newChoice==1){ 
				DBMethod.edit_asset_status(conn, selectedItem, true, false);
				DBMethod.edit_asset_borrower(conn, selectedItem, user.getUserName());
				System.out.println("The asset has been requested. Check back with us later!");
				loggedin();
			}
			else if(newChoice==2){
				borrowTrue();
			}
			else if(newChoice==3){
				loggedin();
			}
			else if(newChoice==4){
				System.exit(0);
			}
			else{
				System.out.println("Enter valid input!");
				borrowTrue();
			}
		}
		else if(Choice == 2){
			int x = borrowedAssets();
			if(x!=0){
				System.out.println("Do you want to return(Y/N)?");
				String yn = sc.nextLine();
				if(yn.equals("Y")){
					System.out.println("Enter asset ID:");
					String id = sc.nextLine();
					DBMethod.edit_asset_status(conn, id, false, false);
				}
			}
			else if(x==0){
				System.out.println("You have no borrowed items");
			}
			borrowTrue();
		}
		else if(Choice == 3){
			loggedin();
		}
		else if(Choice == 4){
			System.exit(0);;
		}
		else{
			System.out.println("Invalid Input. Please try again!");
			borrowTrue();
		}
		sc.close();
	}
}
