package com.org.ClientApplication;

import com.org.Model.*;
import com.org.services.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class OnlineShoppingApplication {
	private static Logger logger=Logger.getLogger(OnlineShoppingApplication.class);
	static {
		try
		{
			
		PropertyConfigurator.configure("src\\main\\resources\\log4j.properties");
		logger.info("OnlineShoppingApplication:Log4j setup ready");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.fatal("OnlineShoppingApplication;Problem while setting log4j setUp");
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		
	
		UserService user=new UserServiceImpl();
		ProductService pService=new ProductServiceImpl();
	    cartService cService=new cartServiceImpl();
		orderService oService=new orderServiceImpl();
		stockService stockService=new stockServiceImpl();
		productCategoryService pCategoryService=new productCategoryServiceImpl();
		Scanner sobj=new Scanner(System.in); 
		boolean b;
		String useremail;
		String password;
		String contact;
		UserModel CustomerLogin=null;
		
		logger.info("Application started.");
		System.out.println("**************************************************************");
		System.out.println();
		System.out.println(" Welcome To Online Shopping Mart ");
		System.out.println();
		System.out.println("**************************************************************");
		
		
		do {
			
			System.out.println("Select Appropriate Choice !");
			System.out.println("1:Register New User");
			System.out.println("2:Login");
			
			System.out.println("Enter your choice");
			int choice=sobj.nextInt();
			switch (choice) 
			{
			
			case 1:
				logger.info("Registering a new user.");
				System.out.println("Enter useremail ");
				sobj.nextLine();
				useremail=sobj.nextLine();
				
				System.out.println("Enter of password");
		        password=sobj.nextLine();
		        
		        System.out.println("Enter contct of user");
		        contact=sobj.nextLine();
		              
				UserModel model=new UserModel(0,useremail,password,contact);
				b=user.isAddUser(model);
				if(b)
				{
					logger.info("User added successfully: " + useremail);
					System.out.println("User Added !");
				}
				else {
					logger.error("Error while adding user: " + useremail);
					System.out.println("Some problem is there");
				}
				
				break;
				
			case 2:
				logger.info("User attempting login.");
				System.out.println("Enter email of user");
				sobj.nextLine();
				useremail=sobj.nextLine();
				
				System.out.println("Enter  of password");
			    password=sobj.nextLine();
			    CustomerLogin=user.login(useremail, password);
				if(CustomerLogin!=null)
				{
					logger.info("User login successful: " + useremail);
					System.out.println("Login Done!");
				}
				else {
					 logger.warn("Login failed for user: " + useremail);
					System.out.println("Problem in customer login!");
				}
				String role=user.role(useremail);
				if(role.equalsIgnoreCase("Admin"))
				{
					logger.info("Admin logged in: " + useremail);
						System.out.println("*****************************************************************************");
						System.out.println();
						System.out.println(" This is a Admin Panel ");
						System.out.println();
						System.out.println("*****************************************************************************");
						boolean exitAdmin=false;
						while(!exitAdmin)
						{
						
							logger.info("Entering Admin Panel.");
							System.out.println("1.Customer Operations");
							System.out.println("2.Product Category");
							System.out.println("3.Product Operation");
							System.out.println("4.Update Stock");
							System.out.println("5:Report");
							System.out.println("6.LogOut");
							System.out.println("Select Appropriate option");
							choice=sobj.nextInt();
							switch(choice)
							{
							
							 case 1:
								 logger.info("Admin selected Customer Operations.");
								 boolean exitCustomerOperations = false;
									do
									{
										
										System.out.println("1.View All Customers");
										System.out.println("2:Add customer");
										System.out.println("3.Delete customer");
										System.out.println("4.exit");
										
										
				                        System.out.println("Select Appropriate option");
				                        choice=sobj.nextInt();
				                        switch(choice)
				                        {
				                          case 1:
				                        	  System.out.println("Customers Details Are:");
											   List<UserModel> allCustomersList=user.viewList();
											   if(allCustomersList!=null)
											   {
												   for(UserModel u:allCustomersList)
												   {
													   System.out.println("useremail-->"+u.getUseremail()+"  role-->"+u.getRole()+"  contact-->"+u.getContact());
												   }
											   }
				                        	  break;
				                        
				                          case 2:
				                        	   System.out.println("Enter useremail ");
												sobj.nextLine();
												useremail=sobj.nextLine();
												
												System.out.println("Enter of password");
										        password=sobj.nextLine();
										        
										        System.out.println("Enter contct of user");
										        contact=sobj.nextLine();
										              
												UserModel model2=new UserModel(0,useremail,password,contact);
												b=user.isAddUser(model2);
												if(b)
												{
													System.out.println("User Added !");
												}
												else {
													System.out.println("Some problem is there");
												}
												
				                        	  
				                        	  break;
				                        	  
				                         
				                        	  
				                          case 3:
				                        	  System.out.println("Enter customermail for delete");
												 sobj.nextLine();
												 String custemailString=sobj.nextLine();
												 b=user.isDeleteCustomer(custemailString);
												 if(b)
												 {
													 System.out.println("Customer is deleted");
												 }
												 else {
													System.out.println("Customer is not deleted");
												}
												 
				                        	  break;
				                        
				                          case 4 :
				                              System.out.println("Exiting Customer Operations...");
				                              exitCustomerOperations = true;
				                              break;
				                        	  
				                        	  default:
				                        		  System.out.println("Select correct option!");
				                           
				                        
				                        }
				                        
									}while(!exitCustomerOperations);
									
									break;
								
							 case 2:
								 
								 logger.info("Admin selected Product Category Operations.");
								 boolean exitProductCategoryOperations = false;
									do
									{
										System.out.println("1.Add Product Category");
										System.out.println("2:delete product Category");
										System.out.println("3.Update Product Category");
										System.out.println("4.View All product Category");
										System.out.println("5.exit");
										
										
				                        System.out.println("Select Appropriate option");
				                        choice=sobj.nextInt();
				                        switch(choice)
				                        {
				                          case 1:
				                        	  System.out.println("Enter the product category");
											   sobj.nextLine();
											   String productCategory=sobj.nextLine();
											   b=pCategoryService.isAddCategory(productCategory);
											   if(b)
											   {
												   System.out.println("Product Category Added!");
											   }
											   else 
											   {
												System.out.println("There is some problem!");
											   }
											 
											 break;
				                        
				                          case 2:
				                        	   System.out.println("Enter product_category name");
												sobj.nextLine();
												String prodCateName=sobj.nextLine();
												b=pCategoryService.isDeleteProdCategory(prodCateName);
												if(b)
												{
													System.out.println("Product Category deleted!");
												}
												else {
													System.out.println("Some problem is there");
												}
								
				                        	  break;
				                        	  
				                         
				                        	  
				                          case 3:
				                        	    System.out.println("Enter Old_product_category name");
												sobj.nextLine();
												String OldprodCateName=sobj.nextLine();
												System.out.println("Enter New_product_category name");
												String NewprodCateName=sobj.nextLine();
												b=pCategoryService.isUpdatedProdCategory(OldprodCateName,NewprodCateName);
												if(b)
												{
													System.out.println("Product Category Updated!");
												}
												else {
													System.out.println("Some problem is there");
												}
								
				                        	  break;
				                        	  
				                          case 4:
				                               List<String>viewAllProductCategoryList=new ArrayList<String>();
				                               viewAllProductCategoryList=pCategoryService.productCatList();
				                               if(viewAllProductCategoryList!=null)
				                               {
				                            	   for(String s:viewAllProductCategoryList)
				                            	   {
				                            		   System.out.println(s);
				                            	   }
				                               }
				                               break;
				                               
				                          case 5:
				                              System.out.println("Exiting productCategory Operations...");
				                              exitProductCategoryOperations = true;
				                              break;
				                        	  
				                        	default:
				                        		
				                        		System.out.println("Selete Correct choice!");
				                           
				                        
				                        }
				                        
									}while(!exitProductCategoryOperations);
									
									break;
								 
								 
						
							 case 3:
								 logger.info("Admin selected Product Operations.");
								 boolean exitProductOperations = false;
								  do
								  {
									    System.out.println("1.Add Product");
										System.out.println("2.View Products");
										System.out.println("3.Delete Product");
										System.out.println("4.Update Product");
										System.out.println("5.Exit");
										
										System.out.println("Select Appropriate option");
										choice=sobj.nextInt();
									    switch(choice)
									    {
									    
									      case 1:
									    	  System.out.println("Enter the product categaory");
									    	  sobj.nextLine();
									    	  String prodCategoryString=sobj.nextLine();
									    	  System.out.println("Enter the name of product");
									    	  String productnameString=sobj.nextLine();
									    	  System.out.println("Enter unit_price");
									    	  int u_price=sobj.nextInt();
									    	  System.out.println("Enter stock_quant");
									    	  int stock_quant=sobj.nextInt();
									    	  sobj.nextLine();
									    	  b=pService.isAddProduct(prodCategoryString,productnameString,u_price,stock_quant);
									    	  if(b)
									    	  {
									    		  System.out.println("Product is added!");
									    	  }
									    	  else {
									    		  System.out.println("There is some problem is there!");
											}
									    	  break;
									    	
									      case 2:
									    	  boolean viewProd=false;
									    	  while(!viewProd)
									    	  {
									    		  System.out.println("1:View Product By Category");
									    		  System.out.println("2.View Product By Name");
									    		  System.out.println("3.Exit");
									    		  
									    		  System.out.println("Choose Correct Option!");
									    		  choice=sobj.nextInt();
									    		  switch(choice)
									    		  {
									    		  case 1:
									    			  System.out.println("Enter Product_category");
									    			  sobj.nextLine();
									    			  String prodCateString=sobj.nextLine();
									    			  List<productModel> viewProductsByCategoryList=pService.getProductDetailByCategoryList(prodCateString);
									    			  if(viewProductsByCategoryList!=null)
									    			  {
									    				  for(productModel p:viewProductsByCategoryList)
									    				  {
									    					  System.out.println("Product_name-->"+p.getPname()+"  unit_price-->"+p.getUnit_price());
									    				  }
									    			  }
									    			  else {
														System.out.println("There is some problem");
													}
									    			  
									    			break;
									    			
									    		  case 2:
									    			  System.out.println("Enter the name of product");
									    			  sobj.nextLine();
									    			  String prodnameString=sobj.nextLine();
									    			  List<productModel>productList=pService.list(prodnameString);
									    			  if(productList!=null)
									    			  {
									    				  for(productModel p:productList)
									    				  {
									    					  System.out.println("Product_name-->"+p.getPname()+"  unit_price-->"+p.getUnit_price());
									    				  }
									    			  }
									    			  else {
														System.out.println("There is some problem");
													}
									    			  
									    			  break;
									    			  
									    		  case 3:
									    			  System.out.println("exit");
									    			  viewProd=false;
									    			  break;
									    			  
									    		default:
									    			System.out.println("Select Correct Choice!");
									    		  }
									    		  
									    	  }
											break;

									    
									      case 3:
									    	  System.out.println("Enter product name");
											  sobj.nextLine();
											  String productname=sobj.nextLine();
											 Boolean  f=pService.isDeleteProduct(productname);
											  if(f)
											  {
												  System.out.println("Product is deleted");
											  }
											  else {
												System.out.println("There is some problem");
											}
											 break;

									      case 4:
									    	  System.out.println("Enter oldproductname");
											  sobj.nextLine();
											  String oldproductname=sobj.nextLine();
											  System.out.println("Enter newproductname");
											  String newproductname=sobj.nextLine();
											  b=pService.isUpdateProduct(oldproductname, newproductname);
											  if(b)
											  {
												  System.out.println("Product name is updated");
											  }
											  else {
												System.out.println("Some problem is there");
											}
											  break;
									    	  
									      
										  case 5:
				                              System.out.println("Exiting Customer Operations...");
				                              exitProductOperations = true;
				                              break;
										  
										  default:
			                        		  System.out.println("Select correct option!");
									    }
										
								  }while(!exitProductOperations);
								  break;
								  
							 case 4:
								 logger.info("Admin selected Update Stock.");
								  System.out.println("Enter product name");
						    	  sobj.nextLine();
						    	  String prodName2=sobj.nextLine();
						    	  System.out.println("Enter updated Stock_quantity");
						    	  int stock_quantity=sobj.nextInt();
						    	  b=stockService.isUpdateStock(prodName2, stock_quantity);
						    	  if(b)
						    	  {
						    		  System.out.println("Stock_updated");
						    	  }
						    	  else {
									System.out.println("There is some problem");
								}
						    	  
								 break;
							 
							 case 5:
								 boolean exitReport = false;
									do
									{
										
										System.out.println("1.View All Customers");
										System.out.println("2:Show All Orders");
										System.out.println("3.Show Customer Specific Orders");
										System.out.println("4.Placed Orders");
										System.out.println("5.Unplaced Order");
										System.out.println("6:Bill status for specific order");
										System.out.println("6.exit");
										
										
				                        System.out.println("Select Appropriate option");
				                        choice=sobj.nextInt();
				                        switch(choice)
				                        {
				                         
				                        case 1:
				                        	System.out.println("Customers Details Are:");
											   List<UserModel> allCustomersList=user.viewList();
											   if(allCustomersList!=null)
											   {
												   for(UserModel u:allCustomersList)
												   {
													   System.out.println("useremail-->"+u.getUseremail()+"  role-->"+u.getRole()+"  contact-->"+u.getContact());
												   }
										   }
				                        	break;
				                        case 2:
				                        	logger.info("Admin selected Show Orders.");
											 List<orderModel> viewAllOrders=new ArrayList<orderModel>();
											 viewAllOrders=oService.viewTotalOrders(CustomerLogin);
											 if(viewAllOrders!=null)
											 {
												 for(orderModel o:viewAllOrders)
												 {
													 System.out.println("order_id-->"+o.getOrderId()+" uid-->"+o.getUid()+" order_date-->"+o.getOrderDate()+" total_price-->"+o.getTotalPrice());
												 }
											 }
											 else {
												System.out.println("No data present");
											}
											break;
				                        case 3:
				                        	
				                        	 logger.info("Customer selected View Customer Specific Orders.");
				                        	 System.out.println("Enter Uid for View the orders");
				                        	 int order_id=sobj.nextInt();
											 List<orderModel> viewCustomerSprcificOrders1=new ArrayList<orderModel>();
											 viewCustomerSprcificOrders1=oService.viewCustomerSprcificOrdersnew(order_id);
											 if(viewCustomerSprcificOrders1!=null)
											 {
												 for(orderModel o:viewCustomerSprcificOrders1)
												 {
													 System.out.println("order_id-->"+o.getOrderId()+" uid-->"+o.getUid()+" order_date-->"+o.getOrderDate()+" total_price-->"+o.getTotalPrice());
												 }
												 logger.info("Customer specific orders displayed successfully.");
											 }
											 else {
												 logger.warn("No data present for specific customer orders.");
												System.out.println("No data present");
											}
				                        	break;
				                        	
				                        case 4:
				                        	 logger.info("View Placed Orders");
											 List<orderModel> viewPlacedListOrders=new ArrayList<orderModel>();
											 viewPlacedListOrders=oService.PlacedOrder();
											 if(viewPlacedListOrders!=null)
											 {
												 for(orderModel o:viewPlacedListOrders)
												 {
													 System.out.println("order_id-->"+o.getOrderId()+" uid-->"+o.getUid()+" order_date-->"+o.getOrderDate()+" total_price-->"+o.getTotalPrice());
												 }
											 }
											 else {
												System.out.println("No data present");
											}
				                        	break;
				                        
				                        case 5:
				                        	logger.info("View Un-Placed Orders");
											 List<orderModel> viewUnPlacedListOrders=new ArrayList<orderModel>();
											 viewUnPlacedListOrders=oService.UnPlacedOrder();
											 if(viewUnPlacedListOrders!=null)
											 {
												 for(orderModel o:viewUnPlacedListOrders)
												 {
													 System.out.println("order_id-->"+o.getOrderId()+" uid-->"+o.getUid()+" order_date-->"+o.getOrderDate()+" total_price-->"+o.getTotalPrice());
												 }
											 }
											 else {
												System.out.println("No data present");
											}
				                        	break;
				                        
				                        case 6:
				                        	 logger.info("View Bill-Status for specific Orders");
				                        	 System.out.println("Enter order_id");
				                        	 int order_id1=sobj.nextInt();
											 String bill_status=oService.BillStatus(order_id1);
											 if(bill_status.equalsIgnoreCase("true"))
											 {
												 System.out.println("Bill Paid");
											 }
											 else {
												 System.out.println("Bill not Paid");
											 }
				                        	break;
				                        
				                        }
				                        
									}while(!exitReport);
								 
								 break;
							 case 6:
								 logger.info("Admin logged out.");
								    System.out.println("Logging out from Admin Panel...");
								    exitAdmin = true;
								    break;
							 
								
							default:
								logger.warn("Invalid admin panel option selected.");
								System.out.println("Choose Correct options provided!");
							}
						} 
						
				}
				else 
				{
					logger.info("Entering Customer Panel.");
						System.out.println("*****************************************************************************");
						System.out.println();
						System.out.println("This is a customer panel");
						System.out.println();
						System.out.println("*****************************************************************************");
						
						boolean exitCustomer = false;
					    while (!exitCustomer) 
					    {
							System.out.println("Select Appropriate option");
							System.out.println("1.View all available products");
							System.out.println("2.Add Product to cart");
							System.out.println("3.View Cart");
							System.out.println("4.place Orders");
							System.out.println("5:ViewCustomerSpecificOrders");
							System.out.println("6.Generate Bill");
							System.out.println("7:LogOut");
							int ch=sobj.nextInt();
							switch (ch) 
							{
							 
							case 1:
								logger.info("Customer selected View Products.");
								  boolean viewProd=false;
						    	  while(!viewProd)
						    	  {
						    		  System.out.println("1:View Product By Category");
						    		  System.out.println("2.View Product By Name");
						    		  System.out.println("3.Exit");
						    		  
						    		  System.out.println("Choose Correct Option!");
						    		  choice=sobj.nextInt();
						    		  switch(choice)
						    		  {
						    		  case 1:
						    			  System.out.println("Enter Product_category");
						    			  sobj.nextLine();
						    			  String prodCateString=sobj.nextLine();
						    			  List<productModel> viewProductsByCategoryList=pService.getProductDetailByCategoryList1(prodCateString);
						    			  if(viewProductsByCategoryList!=null)
						    			  {
						    				  for(productModel p:viewProductsByCategoryList)
						    				  {
						    					  System.out.println("Product_name-->"+p.getPname()+"  unit_price-->"+p.getUnit_price());
						    				  }
						    			  }
						    			  else {
						    				  logger.warn("No products found for category: " + prodCateString);
											System.out.println("There is some problem");
										}
						    			  
						    			break;
						    			
						    		  case 2:
						    			  System.out.println("Enter the name of product");
						    			  sobj.nextLine();
						    			  String prodnameString=sobj.nextLine();
						    			  List<productModel>productList=pService.list1(prodnameString);
						    			  if(productList!=null)
						    			  {
						    				  for(productModel p:productList)
						    				  {
						    					  System.out.println("Product_name-->"+p.getPname()+"  unit_price-->"+p.getUnit_price());
						    				  }
						    				  logger.info("Products displayed by name: " + prodnameString);
						    			  }
						    			  else {
						    				  logger.warn("No products found for name: " + prodnameString);
											System.out.println("Check the product are available or not || product is out of the stock");
										}
						    			  
						    			  break;
						    			  
						    		  case 3:
						    			  logger.info("Exiting View Products.");
						    			  System.out.println("exit");
						    			  viewProd=false;
						    			  break;
						    			  
						    		default:
						    			 logger.warn("Invalid option in View Products menu.");
						    			System.out.println("Select Correct Choice!");
						    		  }
						    		  
						    	  }
								break;
							
							case 2:
								 logger.info("Customer selected Add Product to Cart.");
								  System.out.println("Enter product name");
								  sobj.nextLine();
								  String prodname1=sobj.nextLine();
								  System.out.println("Enter the quantity");
								  int quantity=sobj.nextInt();
								  b=cService.addProductToCart(CustomerLogin,prodname1,quantity);
								  if(b)
								  {
									  logger.info("Product added to cart: " + prodname1 + ", Quantity: " + quantity);
									  System.out.println("Product added to cart!");
								  }
								  else {
									  logger.error("Error adding product to cart: " + prodname1);
									System.out.println("Some problem is there");
								}
								  
							
							  break;
							  
							case 3:
								logger.info("Customer selected View Cart.");
								List<cartModel> viewCart=cService.viewCart(CustomerLogin);
								if(viewCart!=null)
								{
									for(cartModel v:viewCart)
									{
										System.out.println("cid-->"+v.getCid()+" uid-->"+v.getUid()+" pid-->"+v.getPid()+" quantity-->"+v.getItemQuantity());
									}
									logger.info("Cart displayed successfully.");
								}
								else
								{
									 logger.warn("Cart is empty or error fetching cart details.");
								}
							 
								break;
							
							 case 4:
								 logger.info("Customer selected Place Order.");
								 System.out.println("Do you want to place order y/n");
								 sobj.nextLine();
								 String str=sobj.nextLine();
								 if(str.equalsIgnoreCase("yes"))
								 {
									  String btr="true"; 
									  b=oService.placeOrder(CustomerLogin,btr);
									  if(b)
									  {
										  logger.info("Order placed successfully with status: " + btr);
										  System.out.println("Order Placed");  
									  }
									  else {
										  logger.error("Error placing order with status: " + btr);
										  System.out.println("Order UnPlaced");
									}
									  
								 }
								 else 
								 {
									  String btr="false"; 
									  b=oService.placeOrder(CustomerLogin,btr);
									  System.out.println("Order Placed");
									  logger.error("cancle order " + btr);
								 }
								 break;
								
							 case 5:
								 logger.info("Customer selected View Customer Specific Orders.");
								 List<orderModel> viewCustomerSprcificOrders=new ArrayList<orderModel>();
								 viewCustomerSprcificOrders=oService.viewCustomerSprcificOrders(CustomerLogin);
								 if(viewCustomerSprcificOrders!=null)
								 {
									 for(orderModel o:viewCustomerSprcificOrders)
									 {
										 System.out.println("order_id-->"+o.getOrderId()+" uid-->"+o.getUid()+" order_date-->"+o.getOrderDate()+" total_price-->"+o.getTotalPrice());
									 }
									 logger.info("Customer specific orders displayed successfully.");
								 }
								 else {
									 logger.warn("No data present for specific customer orders.");
									System.out.println("No data present");
								}
							   
								 break;
								 
							 case 6:
								 logger.info("Customer selected Generate Bill.");
								 orderModel ord= new orderModel();
								 System.out.println("Enter order date");
								 sobj.nextLine();
								 String order_date=sobj.nextLine();
								 List<Integer>dateSpecificOrderId=oService.DisplayOrderId(order_date);
								 if(dateSpecificOrderId!=null)
								 {
									 for(Integer i:dateSpecificOrderId)
									 {
										 System.out.println(i);
									 }
									 logger.info("Displayed specific order IDs for date: " + order_date);
								 }
								 System.out.println("Enter specific order_id");
								 int order_id=sobj.nextInt();
								 List<BillModel>billList=new ArrayList<BillModel>();
								 ord.setOrderDate(order_date);
								 ord.setOrderId(order_id);
								 billList=oService.getBill(CustomerLogin,ord);
								 if(billList!=null)
								 {
									 for(BillModel b1:billList)
									 {
										 System.out.println("product_name-->"+b1.getProductName()+" unit_price-->"+b1.getUnitPrice()+"  item_quantity-->"+b1.getItemQuantity()+" order-date-->"+b1.getOrderDate()+"  ProductSpecific_price-->"+b1.getTotalPrice()+"  Total_price-->"+b1.getTotalPrivenew());
									 }
								 }
								 else {
									 logger.error("Error generating bill for order_id: " + order_id);
									System.out.println("There is some problem");
								}
								 break;
							 case 7:
								 logger.info("Customer logged out.");
								    System.out.println("Logging out from Customer Panel...");
								    exitCustomer = true;
								    break;
							default:
								 logger.warn("Invalid option in Customer Panel.");
								  System.out.println("Chooese correct option!");
								break;
							  
							  
							}
								
								  
					  }
				}
				
			default:
				System.out.println("Incorrect Choice");
			}
		} while (true);
		
	}

}

