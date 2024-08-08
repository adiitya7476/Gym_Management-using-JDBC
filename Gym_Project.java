package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;


public class Gym_Project
{
	static Scanner sc;
	public static Connection connect()
	{
		String url = "jdbc:mysql://localhost:3306/Codenera";
		String user = "root";
		String pass = "12345";
		Connection con = null;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,pass);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	public static void main(String[] args) throws InterruptedException
	{
		sc = new Scanner(System.in);
		String s1 = "**** Welcome to Gym Management System ****";
		for(int i=0;i<s1.length();i++)
		{
			System.out.print(s1.charAt(i));
			Thread.sleep(150);
		}
		System.out.println();
		while(true)
		{
			String s2 = "* * * * Enter your choice * * * *";
			String s3 = "1. Add new Member";
			String s4 = "2. View all Members";
			String s5 = "3. Update Members profile";
			String s6 = "4. Delete Member";
			String s7 = "5. Search Member";
			String s8 = "6. Exit";
			
			
			for(int i=0;i<s2.length();i++)
			{
				System.out.print(s2.charAt(i));
				Thread.sleep(150);

			}
			System.out.println();
			for(int i=0;i<s3.length();i++)
			{
				System.out.print(s3.charAt(i));
				Thread.sleep(150);

			}
			System.out.println();
			for(int i=0;i<s4.length();i++)
			{
				System.out.print(s4.charAt(i));
				Thread.sleep(150);

			}
			System.out.println();
			for(int i=0;i<s5.length();i++)
			{
				System.out.print(s5.charAt(i));
				Thread.sleep(150);

			}
			System.out.println();
			for(int i=0;i<s6.length();i++)
			{
				System.out.print(s6.charAt(i));
				Thread.sleep(150);

			}
			System.out.println();
			for(int i=0;i<s7.length();i++)
			{
				System.out.print(s7.charAt(i));
				Thread.sleep(150);

			}
			System.out.println();
			for(int i=0;i<s8.length();i++)
			{
				System.out.print(s8.charAt(i));
				Thread.sleep(150);

			}
			System.out.println();
			int ch = sc.nextInt();
			if(ch == 6)
			{
				System.out.println("\nThank You !");
				break;
			}
			switch(ch)
			{
			case 1: addMember();
				break;
			
			case 2 : viewMembers();
				break;
				
			case 3 : updateData();
				break;
				
			case 4 : deleteData();
				break;
				
			case 5 : search();
				break;
			
			default : System.out.println("Invalid input !");
				break;
			
			}
			
		}
	}
	
	public static void addMember()
	{
		Connection con = Gym_Project.connect();
		sc = new Scanner(System.in);
		System.out.println("\n\n*** Please Enter Member Details ***");
		System.out.print("Enter Id : ");
		int id = sc.nextInt();
		System.out.print("Enter Name : ");
		String name = sc.next();
		System.out.print("Enter Validity (in months) : ");
		String val = sc.next();
		System.out.print("Enter Joining date (YYYY-MM-DD) : ");
		String date = sc.next();
		
		String sql ="insert into gym_members values(?,?,?,?)";
		try
		{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, val);
			ps.setString(4, date);
			
			int status = ps.executeUpdate();
			
			if(status != 0)
				System.out.println("Member Succesfully added...\n\n");
			else
				System.out.println("Something went wrong while adding data...\n");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void viewMembers()
	{
		Connection con = Gym_Project.connect();
		System.out.println("\n\n*** All members Data ***");
		String sql = "select * from gym_members;";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			System.out.println("-----------------------------------------------");
			System.out.println(" Id\tName\t  Validity\tJoin_date");
			System.out.println("-----------------------------------------------");
			while(rs.next())
			{
				System.out.println(" "+rs.getInt(1)+"\t"+rs.getString(2)+"\t  "+rs.getString(3)+"  \t"+rs.getString(4));
			}
			System.out.println("-----------------------------------------------\n\n");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void updateData()
	{
		Connection con = Gym_Project.connect();
		System.out.print("\n\nEnter Id to update details : ");
		int id = sc.nextInt();
		System.out.println("What you want to Update :\n"
				+ "1.Name\t2.Validity\t3.Join Date");
		int ch = sc.nextInt();
		String up ="";
		switch(ch)
		{
		case 1 : up ="name";
			break;
		case 2 : up = "validity";
			break;
		case 3 : up = "join_date";
			break;
			
		default : System.out.print("Please select correct option.");
		}
		System.out.print("Enter new value for selected field : ");
		String newval = sc.next();
		String sql = "update gym_members set "+up+" = ? where id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, newval);
			ps.setInt(2, id);
			
			int status = ps.executeUpdate();
			if(status != 0)
				System.out.println("Data Updated....\n\n");
			else
				System.out.println("Something went wrong...\n\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteData()
	{
		Connection con = Gym_Project.connect();
		System.out.print("\n\nEnter Id to delete data : ");
		int id = sc.nextInt();
		
		String sql = "delete from gym_members where id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			int status = ps.executeUpdate();
			if(status !=0)
				System.out.println("Data Deleted...\n\n");
			else
				System.out.println("Something went wrong...\n\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void search()
	{
		Connection con = Gym_Project.connect();
		System.out.println("\n\nEnter Member name to search details : ");
		String name = sc.next();
		String sql = "select * from gym_members where name = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				System.out.println("Member details found ->");
				System.out.println("-----------------------------------------------");
				System.out.println(" Id\tName\t  Validity\tJoin_date");
				System.out.println("-----------------------------------------------");
				do
				{
					System.out.println(" "+rs.getInt(1)+"\t"+rs.getString(2)+"\t  "+rs.getString(3)+"  \t"+rs.getString(4));
				}while(rs.next());
				System.out.println("-----------------------------------------------\n\n");
			}
			else
			{
				System.out.println("No Member found with name = \"" + name + "\"\n\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


}
