package com.supermart.service;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.supermart.dbcon.DbConnection;
import com.supermart.dbcon.DbUtil;
import com.supermart.model.Purchase;
import com.supermart.model.Sales;
import com.supermart.model.Stock;
import com.supermart.model.Supplier;

public class SupermartService {
	private Connection connection;
	private PreparedStatement statement;
	
	public SupermartService() {
	}
	
	public static int insertPurchase(Purchase purchase) throws ClassNotFoundException {
		String query = "INSERT INTO purchase (product_name,buying_price,supplier_id,quantity_bought) VALUES (?, ?, ?, ?);";
		int insertResult = 0;
		
		try{
			Connection con = DbConnection.connectDB();
	
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, purchase.getProduct_name());
			statement.setDouble(2, purchase.getBuying_price());
			statement.setInt(3, purchase.getSupplier_id());
			statement.setInt(4, purchase.getQuantity_bought());
			
			insertResult = statement.executeUpdate();		
			
		}catch(Exception e){
			System.out.println(e);
		}
		return insertResult;
	}
	
	public static int insertSales(Sales sales) throws ClassNotFoundException {
		String query = "INSERT INTO sales (product_id,quantity_sold,customer_name,served_by) VALUES (?, ?, ?, ?);";
		int insertResult = 0;
		
		try{
			Connection con = DbConnection.connectDB();
	
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, sales.getProduct_id());
			statement.setInt(2, sales.getQuantity_sold());
			statement.setString(3, sales.getCustomer_name());
			statement.setString(4, sales.getServed_by());
			
			insertResult = statement.executeUpdate();		
			
		}catch(Exception e){
			System.out.println(e);
		}
		return insertResult;
	}
	
	public static int insertStock(Stock stock) throws ClassNotFoundException {
		String query = "INSERT INTO stock (product_id,quantity_available,selling_price) VALUES (?, ?, ?);";
		int insertResult = 0;
		
		try{
			Connection con = DbConnection.connectDB();
	
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, stock.getProduct_id());
			statement.setInt(2, stock.getQuantity_available());
			statement.setDouble(3, stock.getSelling_price());
			
			insertResult = statement.executeUpdate();		
			
		}catch(Exception e){
			System.out.println(e);
		}
		return insertResult;
	}
	
	public static int insertSupplier(Supplier supplier) throws ClassNotFoundException {
		String query = "INSERT INTO supplier (name,contact) VALUES (?, ?);";
		int insertResult = 0;
		
		try{
			Connection con = DbConnection.connectDB();
	
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, supplier.getName());
			statement.setString(2, supplier.getContact());
			
			insertResult = statement.executeUpdate();		
			
		}catch(Exception e){
			System.out.println(e);
		}
		return insertResult;
	}

	
	public static int updatePurchase(Purchase purchase) throws ClassNotFoundException {
		String query = "UPDATE purchase SET product_name=?,buying_price=?,supplier_id=?,quantity_bought=? WHERE product_id=?;";
		int insertResult = 0;
		
		try{
			Connection con = DbConnection.connectDB();
	
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, purchase.getProduct_name());
			statement.setDouble(2, purchase.getBuying_price());
			statement.setInt(3, purchase.getSupplier_id());
			statement.setInt(4, purchase.getQuantity_bought());
			statement.setInt(5, purchase.getProduct_id());
			
			insertResult = statement.executeUpdate();		
			
		}catch(Exception e){
			System.out.println(e);
		}
		return insertResult;
	}
	
	public static int updateSales(Sales sales) throws ClassNotFoundException {
		String query = "UPDATE sales SET product_id=?,quantity_sold=?,customer_name=?,served_by=? WHERE order_id=?;";
		int insertResult = 0;
		
		try{
			Connection con = DbConnection.connectDB();
	
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, sales.getProduct_id());
			statement.setInt(2, sales.getQuantity_sold());
			statement.setString(3, sales.getCustomer_name());
			statement.setString(4, sales.getServed_by());
			statement.setInt(5, sales.getOrder_id());
			
			insertResult = statement.executeUpdate();		
			
		}catch(Exception e){
			System.out.println(e);
		}
		return insertResult;
	}
	
	public static int updateStock(Stock stock) throws ClassNotFoundException {
		String query = "UPDATE stock SET product_id=?,quantity_available=?,selling_price=? WHERE stock_id=?;";
		int insertResult = 0;
		
		try{
			Connection con = DbConnection.connectDB();
	
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, stock.getProduct_id());
			statement.setInt(2, stock.getQuantity_available());
			statement.setDouble(3, stock.getSelling_price());
			statement.setInt(4, stock.getStock_id());
			
			insertResult = statement.executeUpdate();		
			
		}catch(Exception e){
			System.out.println(e);
		}
		return insertResult;
	}
	
	public static int updateSupplier(Supplier supplier) throws ClassNotFoundException {
		String query = "UPDATE supplier SET name=?,contact=? WHERE supplier_id=?;";
		int insertResult = 0;
		
		try{
			Connection con = DbConnection.connectDB();
	
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, supplier.getName());
			statement.setString(2, supplier.getContact());
			statement.setInt(3, supplier.getSupplier_id());
			
			insertResult = statement.executeUpdate();		
			
		}catch(Exception e){
			System.out.println(e);
		}
		return insertResult;
	}

	
	public List<Purchase> getAllPurchases() throws SQLException {
		String query = "SELECT * FROM purchase";
		List<Purchase> list = new ArrayList<Purchase>();
		Purchase purchase = null;
		ResultSet resultSet = null;
		try {
			connection = DbConnection.connectDB();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				purchase = new Purchase();
				/*Retrieve one employee details 
                and store it in employee object*/
				purchase.setProduct_id(resultSet.getInt("product_id"));
				purchase.setProduct_name(resultSet.getString("product_name"));
				purchase.setBuying_price(resultSet.getDouble("buying_price"));
				purchase.setSupplier_id(resultSet.getInt("supplier_id"));
				purchase.setQuantity_bought(resultSet.getInt("quantity_bought"));
				
				list.add(purchase);
			}
		}finally {
			DbUtil.close(resultSet);
			DbUtil.close(statement);
			DbUtil.close(connection);
		}
		return list;
	}
	
	public List<Sales> getAllSales() throws SQLException {
		String query = "SELECT * FROM sales";
		List<Sales> list = new ArrayList<Sales>();
		Sales sales = null;
		ResultSet resultSet = null;
		try {
			connection = DbConnection.connectDB();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				sales = new Sales();
				sales.setProduct_id(resultSet.getInt("product_id"));
				sales.setQuantity_sold(resultSet.getInt("quantity_sold"));
				sales.setCustomer_name(resultSet.getString("customer_name"));
				sales.setServed_by(resultSet.getString("served_by"));
				
				list.add(sales);
			}
		}finally {
			DbUtil.close(resultSet);
			DbUtil.close(statement);
			DbUtil.close(connection);
		}
		return list;
	}
	
	public List<Stock> getAllStock() throws SQLException {
		String query = "SELECT * FROM stock";
		List<Stock> list = new ArrayList<Stock>();
		Stock stock = null;
		ResultSet resultSet = null;
		try {
			connection = DbConnection.connectDB();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				stock = new Stock();
				stock.setProduct_id(resultSet.getInt("product_id"));
				stock.setQuantity_available(resultSet.getInt("quantity_available"));
				stock.setSelling_price(resultSet.getDouble("selling_price"));
				
				list.add(stock);
			}
		}finally {
			DbUtil.close(resultSet);
			DbUtil.close(statement);
			DbUtil.close(connection);
		}
		return list;
	}
	
	public List<Supplier> getAllSuppliers() throws SQLException {
		String query = "SELECT * FROM supplier";
		List<Supplier> list = new ArrayList<Supplier>();
		Supplier supplier = null;
		ResultSet resultSet = null;
		try {
			connection = DbConnection.connectDB();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				supplier = new Supplier();
				supplier.setName(resultSet.getString("name"));
				supplier.setContact(resultSet.getString("contact"));
				
				list.add(supplier);
			}
		}finally {
			DbUtil.close(resultSet);
			DbUtil.close(statement);
			DbUtil.close(connection);
		}
		return list;
	}
	
	
	public Supplier getSupplier(int supplierId) throws SQLException {
		String query = "SELECT * FROM supplier WHERE supplier_id="+supplierId;
		Supplier supplier = null;
		ResultSet resultSet = null;
		try {
			connection = DbConnection.connectDB();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				supplier = new Supplier();
				supplier.setSupplier_id(resultSet.getInt("supplier_id"));
				supplier.setName(resultSet.getString("name"));
				supplier.setContact(resultSet.getString("contact"));
			}
		}finally {
			DbUtil.close(resultSet);
			DbUtil.close(statement);
			DbUtil.close(connection);
		}
		return supplier;
	}
	
	public Sales getSale(int saleId) throws SQLException {
		String query = "SELECT * FROM sales WHERE order_id="+saleId;
		Sales sale = null;
		ResultSet resultSet = null;
		try {
			connection = DbConnection.connectDB();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				sale = new Sales();
				sale.setOrder_id(resultSet.getInt("order_id"));
				sale.setProduct_id(resultSet.getInt("product_id"));
				sale.setQuantity_sold(resultSet.getInt("quantity_sold"));
				sale.setCustomer_name(resultSet.getString("customer_name"));
				sale.setServed_by(resultSet.getString("served_by"));
			}
		}finally {
			DbUtil.close(resultSet);
			DbUtil.close(statement);
			DbUtil.close(connection);
		}
		return sale;
	}
	
	public Stock getStock(int stockId) throws SQLException {
		String query = "SELECT * FROM stock WHERE stock_id="+stockId;
		Stock stock = null;
		ResultSet resultSet = null;
		try {
			connection = DbConnection.connectDB();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				stock = new Stock();
				stock.setStock_id(resultSet.getInt("stock_id"));
				stock.setProduct_id(resultSet.getInt("product_id"));
				stock.setQuantity_available(resultSet.getInt("quantity_available"));
				stock.setSelling_price(resultSet.getDouble("selling_price"));
			}
		}finally {
			DbUtil.close(resultSet);
			DbUtil.close(statement);
			DbUtil.close(connection);
		}
		return stock;
	}
	
	public Purchase getPurchase(int purchaseId) throws SQLException {
		String query = "SELECT * FROM purchase WHERE product_id="+purchaseId;
		Purchase purchase = null;
		ResultSet resultSet = null;
		try {
			connection = DbConnection.connectDB();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery(query);
			
			if(resultSet.next()) {
				purchase = new Purchase();
				purchase.setProduct_id(resultSet.getInt("product_id"));
				purchase.setProduct_name(resultSet.getString("product_name"));
				purchase.setBuying_price(resultSet.getDouble("buying_price"));
				purchase.setSupplier_id(resultSet.getInt("supplier_id"));
				purchase.setQuantity_bought(resultSet.getInt("quantity_bought"));
			}
		}finally {
			DbUtil.close(resultSet);
			DbUtil.close(statement);
			DbUtil.close(connection);
		}
		return purchase;
	}
	
	
	public static int deletePurchase(int product_id) throws ClassNotFoundException {
		String query = "DELETE FROM purchase WHERE product_id="+product_id+";";
		int insertResult = 0;
		
		try{
			Connection con = DbConnection.connectDB();
			PreparedStatement statement = con.prepareStatement(query);
			insertResult = statement.executeUpdate();		
			
		}catch(Exception e){
			System.out.println(e);
		}
		return insertResult;
	}
	
	public static int deleteSale(int order_id) throws ClassNotFoundException {
		String query = "DELETE FROM sales WHERE order_id="+order_id+";";
		int insertResult = 0;
		
		try{
			Connection con = DbConnection.connectDB();
			PreparedStatement statement = con.prepareStatement(query);
			insertResult = statement.executeUpdate();		
			
		}catch(Exception e){
			System.out.println(e);
		}
		return insertResult;
	}
	
	public static int deleteStock(int stock_id) throws ClassNotFoundException {
		String query = "DELETE FROM stock WHERE product_id="+stock_id+";";
		int insertResult = 0;
		
		try{
			Connection con = DbConnection.connectDB();
			PreparedStatement statement = con.prepareStatement(query);
			insertResult = statement.executeUpdate();		
			
		}catch(Exception e){
			System.out.println(e);
		}
		return insertResult;
	}
	
	public static int deleteSupplier(int supplier_id) throws ClassNotFoundException {
		String query = "DELETE FROM supplier WHERE product_id="+supplier_id+";";
		int insertResult = 0;
		
		try{
			Connection con = DbConnection.connectDB();
			PreparedStatement statement = con.prepareStatement(query);
			insertResult = statement.executeUpdate();		
			
		}catch(Exception e){
			System.out.println(e);
		}
		return insertResult;
	}
}
