//package Connection;
//package

import java.util.*;
import java.sql.*;

public class grocery_store {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		Mysql_connection mysql_conn=new Mysql_connection();
		Connection con=mysql_conn.conn("root", "password");
		
		System.out.println();
		System.out.println("--------------------------------------------------------------");
		System.out.println("**************************************************************");
		System.out.println("                  WELCOME TO GROCERY PORTAL");
		System.out.println("**************************************************************");
		System.out.println("--------------------------------------------------------------");
		System.out.println();
		System.out.println("Press 1 to continue as Admin \nPress 2 to continue as Customer \n"
				+ "Press 3 to continue as Delivery Executive \nPress 4 to continue as Supplier");
		System.out.println("Press 5 to try out the sentiment analysis of some product feedbacks");
		System.out.println("Press 0 to exit");
		System.out.println();

		int input1=sc.nextInt();
		int flag=0;
		if(input1==1) flag=1;
		if(input1==2) flag=2; //Consumer
		if(input1==3) flag=3; // Delivery Executive
		if(input1==4) flag=4; //Supplier
		if(input1==5) flag=5; //Sentiment analysis
		/// global variables
		///takes care of continuous work flow
// Admin
		Admin ad=new Admin();		
//consumer
		boolean loggedin=false;
		Consumer customer=new Consumer();
//del exec		
		Delivery_Executive delx=new Delivery_Executive();
		int flag1d=1;int regd=-1;
		int delivery=0;
//supplier
		Supplier sup=new Supplier();
		int flag1s=1;int regs=-1;
		int sup_id=0;
		
// senti_analysis
		MainApp obj=new MainApp();
		
		
		while(true && flag!=0) {
			//admin left
			if(flag==1)
				{
			int t=ad.admin_login(con);

					if(t==1) 
					{
						int choice=1;
						System.out.println(" __________________________________________________");
						System.out.println("|===================================================|");
						System.out.println("|                                                   |");
						System.out.println("|        Welcome to the Admin Menu                  |");
						System.out.println("|                                                   |");
						System.out.println("|===================================================|");
						System.out.println("|                                                   |");
						System.out.println("|1. View/Update/Delete Consumer                     |");
						System.out.println("|2. View/Update/Delete Supplier                     |");
						System.out.println("|3. View/Update/Delete Products                     |");
						System.out.println("|4. View/Update/Delete Delievery Executive          |");
						System.out.println("|5. View/Update/Delete Categories                   |");
						System.out.println("|6. View Feedback                                   |");
						System.out.println("|7. Exit                                            |");
						System.out.println("|___________________________________________________|");
						System.out.println("Please choose your option: ");
						choice=sc.nextInt();
						if(choice==1)
						{
							ad.edit_customer(con);
							continue;
						}
						else if(choice==2)
						{
							ad.edit_supplier(con);
							continue;
						}
						else if(choice==3) {
							ad.edit_products(con);
							continue;
						}
						else if(choice==4)
						{
							ad.edit_delieveryexecutive(con);
							continue;
						}
						else if(choice==5) {
							ad.edit_categories(con);continue;}
						else if(choice==6) {
							ad.view_feedback(con); continue;}
						else {
							System.out.println("===================================================");
							System.out.println("				Logout successfull					");
							System.out.println("===================================================");
//							int w=sc.nextInt();
							break;
						}
					
					
					}
					else
					{	System.out.println("===================================================");
						System.out.println(" OOPS!! Login Unsuccessfull!! :( Please try again");
						System.out.println("===================================================");
						System.out.println("Press 0 to exit and press 1 to try again");
						int w=sc.nextInt();
						if(w==1)
							continue;
						else
							break;
						
					}
				}

			
			
			
			
			//consumer part
			if(flag==2) {
				
				
				try {
					if(!loggedin) {
						System.out.println("--------------------------------------------------------------");
						System.out.println("**************************************************************");
						System.out.println("                 Welcome to Consumer Portal");
						System.out.println("**************************************************************");
						System.out.println("--------------------------------------------------------------");	
						System.out.println();
						
						System.out.println("Press 1 to register to our portal as a new user");
						System.out.println("Press 2 to log in");
						System.out.println("Press 0 to exit");
						System.out.println();
						System.out.println("--------------------------------------------------------------");
						int flag1=sc.nextInt();
						if(flag1==1) { //register
							int reg=customer.customer_register(con);
							if(reg==0) {
								System.out.println("Error: You have already registered in our portal or have entered faulty inputs");
								System.out.println();
								System.out.println("--------------------------------------------------------------");
								continue;
							}
							else {
								flag1=2; // login
							}
						}
						if(flag1==2) { //login
							int log=customer.customer_login(con);
							if(log==1) {
								loggedin=true;
								System.out.println("Login Successful");
								System.out.println();
								System.out.println("--------------------------------------------------------------");
								continue;
							}
							else {
								loggedin=false;
								System.out.println("Wrong credentials");
								System.out.println("Press 0 to exit or 1 to try again");
								System.out.println();
								System.out.println("--------------------------------------------------------------");
								int ex=sc.nextInt();
								if(ex==0) break;
								else continue; 
							}
						}
						if(flag1==0) break;
					}
					if(loggedin) {
						System.out.println();
						System.out.println("--------------------------------------------------------------");
						System.out.println();
						System.out.println("Press 1 to display all items");
						System.out.println("Press 2 to see products by category");
						System.out.println("Press 3 to search and add product to cart");
						System.out.println("Press 4 to checkout");
						System.out.println("Press 5 to rate previously ordered products");
						System.out.println("Press 0 to exit");
						System.out.println();
						System.out.println("--------------------------------------------------------------");
						System.out.println();


						int item_flag=sc.nextInt();
						if(item_flag==1) { //display all items
							int noerr=customer.showresult(con, item_flag);
							if(noerr==0) {
								System.out.println("Wrong input");
								continue;
							}
						}
						if(item_flag==2) {
							System.out.println();
							System.out.println("--------------------------------------------------------------");
							System.out.println();
							System.out.println("Below are the available categories, please enter one");
							System.out.println();
							customer.showresult(con, 3); //display all available categories
							int noerr=customer.showresult(con, item_flag); //display all products of the chosen category
							if(noerr==0) {
								System.out.println("Wrong input");
								System.out.println();
								System.out.println("--------------------------------------------------------------");
								System.out.println();
								continue;
							}
						}
						if(item_flag==3) { //search and add product to cart
							int noerr=customer.showresult(con, 4);
							if(noerr==0) {
								System.out.println("Wrong input");
								System.out.println();
								System.out.println("--------------------------------------------------------------");
								System.out.println();
								continue;
							}
						}
						if(item_flag==4) { //checkout
							int noerr=customer.showresult(con, 5);
							if(noerr==0) {
								System.out.println("Wrong input");
								System.out.println();
								System.out.println("--------------------------------------------------------------");
								System.out.println();
								continue;
							}
						}
						if(item_flag==5) { // rate products
							int noerr=customer.showresult(con, 7);
							if(noerr==0) {
								System.out.println("Wrong input");
								System.out.println();
								System.out.println("--------------------------------------------------------------");
								System.out.println();
								continue;
							}
						}
						if(item_flag==0) break;
					}
				}
				catch(Exception e){
					System.out.println(e);
				}
			}
			
			//delivery executive 
			else if(flag==3) {
				try {
					
					
					int hg=1;
					
					while(flag1d==1) 
					{
						System.out.println("--------------------------------------------------------------");
						System.out.println("**************************************************************");
						System.out.println("                 Welcome to Delivery Executive Portal");
						System.out.println("**************************************************************");
						System.out.println("--------------------------------------------------------------");	
						System.out.println();
						
						System.out.println();
						System.out.println("--------------------------------------------------------------");
						System.out.println();
						System.out.println("Press 1 to proceed");
						System.out.println("Press 0 to exit");
						int hh=sc.nextInt();
						if(hh==0) {
							hg=0;
							break;
						}
						System.out.println();
						System.out.println("--------------------------------------------------------------");
						System.out.println();
						
						regd=delx.delivery_executive_login(con);
						if(regd==0) 
						{
							System.out.println();
							System.out.println("--------------------------------------------------------------");
							System.out.println();
							System.out.println("Wrong credentials");
							System.out.println("Press 0 to exit or 1 to try again");
							System.out.println();
							System.out.println("--------------------------------------------------------------");
							System.out.println();
							int ex=sc.nextInt();
							if(ex==0) break;
							else continue; 
						}
						else 
						{
							System.out.println("Login Successful");
							delivery=regd;
							flag1d=2; //logged in
						}
					}
					if(hg==0) break;
					System.out.println();
					System.out.println("--------------------------------------------------------------");
					System.out.println();
					System.out.println("Press 1 display your current details \n "
							+ "Press 2 to view your current pending orders \n "
							+ "Press 3 to view details of a specific order(using order_ID) \n"

							+ "Press 4 to update your details "
							+ "Press 5 to exit");

					int item_flag=sc.nextInt();

					if(item_flag==1 && flag1d==2) { //show details
						delx.show_details(con,delivery);
						
					}

					if(item_flag==2 && flag1d==2) { //display current pending orders
						System.out.println();
						System.out.println("--------------------------------------------------------------");
						System.out.println();
						System.out.println("Your currently pending orders");
						delx.delivery_executive_showresult(con,Integer.toString(delivery),1);
						System.out.println();
						System.out.println("--------------------------------------------------------------");
						System.out.println();

					}

					else if(item_flag==3 && flag1d==2) { //view details of single order
						System.out.println();
						System.out.println("--------------------------------------------------------------");
						System.out.println();
						delx.delivery_executive_showresult(con,Integer.toString(delivery),2);
						System.out.println();
						System.out.println("--------------------------------------------------------------");
						System.out.println();

					}

					else if(item_flag==4 && flag1d==2) { //update details
						System.out.println();
						System.out.println("--------------------------------------------------------------");
						System.out.println();
						delx.delivery_executive_showresult(con,Integer.toString(delivery),3);
						System.out.println();
						System.out.println("--------------------------------------------------------------");
						System.out.println();

					}
					
					if(item_flag==5) break;

				}

				catch(Exception e){
					System.out.println(e);
				}
				
			}
			
			// supplier part
			else if(flag==4) {
				try {
					
					
					
					int hg=1;
					while(flag1s==1) {
						regs=sup.supplier_login(con);
						if(regs==0) {
							System.out.println("--------------------------------------------------------------");
							System.out.println("**************************************************************");
							System.out.println("                 Welcome to Supplier Portal");
							System.out.println("**************************************************************");
							System.out.println("--------------------------------------------------------------");	
							System.out.println();

							System.out.println();
							System.out.println("--------------------------------------------------------------");
							System.out.println();
							System.out.println("Press 1 to proceed");
							System.out.println("Press 0 to exit");
							System.out.println();
							int hh=sc.nextInt();
							if(hh==0) {
								hg=0;
								break;
							}
							System.out.println("--------------------------------------------------------------");
							System.out.println();
							System.out.println();
							System.out.println("--------------------------------------------------------------");
							System.out.println();
							System.out.println("Wrong id entered");
							System.out.println("Press 0 to exit supplier or 1 to try again");
							System.out.println();
							System.out.println("--------------------------------------------------------------");
							System.out.println();
							int ex=sc.nextInt();
							if(ex==0) return; 
							else continue; 
						}
						else {
							
							System.out.println("Login Successful");
							System.out.println();
							System.out.println("--------------------------------------------------------------");
							System.out.println();
							sup_id=regs;
							flag1s=2; //login 
						}
					}
					if(hg==0) break;
				System.out.println();
				
					System.out.println();
					System.out.println("--------------------------------------------------------------");
					System.out.println();
					System.out.println("Press 0 to know your supplied products details \n ");
					System.out.println("Press 1 to know your details & rating \n ");
					System.out.println();
					System.out.println("--------------------------------------------------------------");
					System.out.println();

					int item_flag=sc.nextInt();
					if(item_flag!=0 && item_flag!=1) break;
					if(item_flag==0 && flag1s==2) { //display supplied products
						System.out.println();
						System.out.println("--------------------------------------------------------------");
						System.out.println();
						sup.supplier_showresult(con,Integer.toString(sup_id),1);
						System.out.println();
						System.out.println("--------------------------------------------------------------");
						System.out.println();
						
					}
					else if(item_flag==1 && flag1s==2) { //details and rating
						System.out.println();
						System.out.println("--------------------------------------------------------------");
						System.out.println();
						sup.supplier_showresult(con,Integer.toString(sup_id),44);
						System.out.println();
						System.out.println("--------------------------------------------------------------");
						System.out.println();
					}

				}

				catch(Exception e){
					System.out.println("An error occured. Try sometime later");
					System.out.println(e);
					System.out.println();
					System.out.println("--------------------------------------------------------------");
					System.out.println();
				}

			}else if(flag==5) {
				
					
				try {
					Statement stmt=con.createStatement(); 
					ResultSet rs=stmt.executeQuery("select A.product_name,B.review from products as A,feedback as B where"
							+ " A.product_id=B.product_id");
					
					System.out.println("--------------------------------------------------------------");
					System.out.println("Your analysed Products-*");
					
					while(rs.next()) {
						System.out.println();
						System.out.println("Product:"+rs.getString("A.product_name"));
						System.out.println();
						System.out.println();
						String temp=rs.getString("B.review");
						obj.beta_senti(temp);
						System.out.println();
					}
					break;
					
				}
				
				catch(Exception e){
					System.out.println("An error occured. Try sometime later");
					System.out.println(e);
					System.out.println();
					System.out.println("--------------------------------------------------------------");
					System.out.println();
					break;
				}
				
			}
			else {
				break;
			}
		}
		System.out.println("Exit Successful");
		System.out.println();
		System.out.println("--------------------------------------------------------------");
		System.out.println();
	}

}
