package com.org.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;



import org.apache.log4j.Logger;

import com.org.Model.BillModel;
import com.org.Model.UserModel;
import com.org.Model.orderModel;

public class orderRepostioryImpl extends DBState implements orderRepository {

	private static final Logger logger = Logger.getLogger(orderRepostioryImpl.class);
	int value = 0;

	public boolean placeOrder(UserModel umodel,String str) {
		try {
			// System.out.println(umodel.getUserId());
 
			logger.info("Placing order for user: " + umodel.getUid());
			stmt = conn.prepareStatement(
					"select p.unit_price,c.item_quantity from product p inner join cart c on p.pid=c.pid where c.uid=?  and c.status='false'");
			stmt.setInt(1, umodel.getUid());
			rs = stmt.executeQuery();
			int total_price=0;
			while (rs.next()) {
				int unit_price = rs.getInt("unit_price");
				// System.out.println(unit_price);
				int item_quantity = rs.getInt("item_quantity");
				// System.out.println(item_quantity);
				total_price = total_price + (unit_price * item_quantity);
			}
			logger.debug("Calculated total price: " + total_price);
			// System.out.println(total_price);

			 // If no items in cart, return false
	        if (total_price == 0) {
	        	logger.warn("No items in the cart to place an order.");
	           
	            return false;
	        }
	        
			
	        // Insert order into orders table
            stmt = conn.prepareStatement("INSERT INTO orders (uid, order_date, total_price, order_status) VALUES (?, CURRENT_TIMESTAMP, ?, 'false')");
            stmt.setInt(1, umodel.getUid());
            stmt.setInt(2, total_price);
            value = stmt.executeUpdate();
            logger.info("Order placed with total price: " + total_price);
			if(value>0)
			{
				stmt=conn.prepareStatement("update cart set status='true' where uid=? AND status = 'false'");
				stmt.setInt(1,umodel.getUid());
				int quant=stmt.executeUpdate();
				if(quant>0)
				{
					logger.info("Cart status updated to 'true' for user: " + umodel.getUid());
				}
				else {
					logger.error("Failed to update cart status for user: " + umodel.getUid());
				}
				
				
			}
			
			
			return value > 0  ? true : false;

		} catch (Exception e) {
			logger.error("Error while placing order: ", e);
			return false;
		}
	}

	@Override
	public List viewCustomerSprcificOrders(UserModel umodel) {
		try {
			logger.info("Fetching orders for user: " + umodel.getUid());
			List viewOrderList = new ArrayList();
			stmt = conn.prepareStatement("select * from orders where uid=?");
			stmt.setInt(1,umodel.getUid());
			rs = stmt.executeQuery();
			while (rs.next())
			{
				orderModel oModel = new orderModel(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4));
				viewOrderList.add(oModel);
			}
			logger.info("Fetched orders for user: " + umodel.getUid());
			return viewOrderList;
		} catch (Exception e) {
			logger.error("Error while fetching customer-specific orders: ", e);
			return null;
		}
	}

	@Override
	public List viewTotalOrders(UserModel umodel) {
		try {
			logger.info("Fetching all orders...");
			List viewOrder = new ArrayList();
			stmt = conn.prepareStatement("select * from orders");
			rs = stmt.executeQuery();
			while (rs.next()) {
				orderModel oModel = new orderModel(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4));
				viewOrder.add(oModel);
			}
			logger.info("Fetched all orders.");
			return viewOrder;
		} catch (Exception e) {
			logger.error("Error while fetching total orders: ", e);
			return null;
		}
	}
	

	public List<BillModel> getBill(UserModel umodel, orderModel ord) {
	    try {
	    	logger.info("Generating bill for order: " + ord.getOrderId());
	        List<BillModel> billList = new ArrayList<>();

	        // Prepare the SQL query
	        stmt = conn.prepareStatement(
	            "SELECT p.pname, " +
	            "p.unit_price, " +
	            "SUM(c.item_quantity) AS item_quantity, " +
	            "o.order_date, " +
	            "SUM(p.unit_price * c.item_quantity) AS product_total_price, " +
	            "MAX(o.total_price) AS order_total_price " +
	            "FROM product p " +
	            "INNER JOIN cart c ON p.pid = c.pid " +
	            "INNER JOIN orders o ON c.uid = o.uid " +
	            "WHERE DATE(o.order_date) = ? AND o.order_id = ? " +
	            "GROUP BY p.pname, p.unit_price, o.order_date, o.total_price"
	        );

	        // Set parameters
	        stmt.setDate(1, java.sql.Date.valueOf(ord.getOrderDate())); // Ensure order date is in 'YYYY-MM-DD' format
	        stmt.setInt(2, ord.getOrderId());

	        // Execute the query
	        rs = stmt.executeQuery();

	        // Process the result set
	        while (rs.next()) {
	            BillModel bill = new BillModel();
	            bill.setProductName(rs.getString("pname"));
	            bill.setUnitPrice(rs.getDouble("unit_price"));
	            bill.setItemQuantity(rs.getInt("item_quantity"));
	            bill.setOrderDate(rs.getDate("order_date"));
	            bill.setTotalPrice(rs.getDouble("product_total_price"));
	            bill.setTotalPrivenew(rs.getDouble("order_total_price"));

	            billList.add(bill);
	            
	            stmt=conn.prepareStatement("update orders set bill_status='true' where uid=? and order_id=?");
	            stmt.setInt(1, umodel.getUid());
	            stmt.setInt(2, ord.getOrderId());
	            value=stmt.executeUpdate();
	            if(value>0)
	            {
	            	System.out.println("Bill paid");
	            }
	            else {
					System.out.println("Bill not paid");
				}
	        }
	        logger.info("Bill generated successfully for order: " + ord.getOrderId());
	        return billList;

	    } catch (Exception e) {
	    	logger.error("Error while generating bill: ", e);
	        return null;
	    }
	}

	public List<Integer> DisplayOrderId(String Order_Date) {
	    try {
	    	logger.info("Fetching order IDs for date: " + Order_Date);
	        List<Integer> extactorderidIntegers = new ArrayList<>();
	        stmt = conn.prepareStatement("SELECT order_id FROM orders WHERE DATE(order_date) = ?");
	        stmt.setDate(1, java.sql.Date.valueOf(Order_Date));
	        rs = stmt.executeQuery();

	        while (rs.next()) {
	            int order_id = rs.getInt(1);
	            extactorderidIntegers.add(order_id); // Add the fetched order_id to the list
	        }
	        logger.info("Fetched order IDs for date: " + Order_Date);
	        return extactorderidIntegers;

	    } catch (Exception e) {
	    	logger.error("Error while fetching order IDs: ", e);
	        return null;
	    }
	}

	@Override
	public List viewCustomerSprcificOrdersnew(int user_id) {
		try {
			logger.info("Fetching orders for user: " + user_id);
			List viewOrderList = new ArrayList();
			stmt = conn.prepareStatement("select * from orders where uid=?");
			stmt.setInt(1,user_id);
			rs = stmt.executeQuery();
			while (rs.next())
			{
				orderModel oModel = new orderModel(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4));
				viewOrderList.add(oModel);
			}
			logger.info("Fetched orders for user: " + user_id);
			return viewOrderList;
		} catch (Exception e) {
			logger.error("Error while fetching customer-specific orders: ", e);
			return null;
		}
		
	}

	@Override
	public List<orderModel> PlacedOrder() {
		try {
			logger.info("place Order");
			List<orderModel>placedOrders = new ArrayList<orderModel>();
			stmt = conn.prepareStatement("select * from orders where order_status='true'");
			rs = stmt.executeQuery();
			while (rs.next())
			{
				orderModel oModel = new orderModel(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4));
				placedOrders.add(oModel);
			}
			logger.info("Fetched place order: " );
			return placedOrders;
		} catch (Exception e) {
			logger.error("Error while fetching place Order", e);
			return null;
		}
	}

	@Override
	public List<orderModel> UnPlacedOrder() {
		try {
			logger.info("UNplace Order");
			List<orderModel>unplacedOrders = new ArrayList<orderModel>();
			stmt = conn.prepareStatement("select * from orders where order_status='false'");
			rs = stmt.executeQuery();
			while (rs.next())
			{
				orderModel oModel = new orderModel(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4));
				unplacedOrders.add(oModel);
			}
			logger.info("Fetched place order: " );
			return unplacedOrders;
		} catch (Exception e) {
			logger.error("Error while fetching UNplace Order", e);
			return null;
		}
	}

	@Override
	public String BillStatus(int order_id) {
		try {
			logger.info("Bill Status for specific order_id");
			List<orderModel>unplacedOrders = new ArrayList<orderModel>();
			stmt = conn.prepareStatement("select bill_status from orders where order_id=?");
			stmt.setInt(1,order_id);
			String bill_Status="";
			rs = stmt.executeQuery();
			while (rs.next())
			{
				bill_Status=rs.getString(1);
			}
			logger.info("Fetched place order: " );
			return bill_Status;
		} catch (Exception e) {
			logger.error("Error while fetching UNplace Order", e);
			return null;
		}
	}




	

	

}
