package com.training.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcProductDAO {
	
	public Product save(Product toBeSaved) {
		try(Connection c = ConnectionUtil.getConnection()){
			String sql = "insert into product(product_name,product_price,product_qoh) values(?,?,?)";
			PreparedStatement s = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			s.setString(1, toBeSaved.getName());
			s.setFloat(2, toBeSaved.getPrice());
			s.setInt(3, toBeSaved.getQoh());
			s.executeUpdate();
			
			ResultSet keys = s.getGeneratedKeys();
			keys.next();
			int id = keys.getInt(1);
			toBeSaved.setId(id);
			
			return toBeSaved;
		}catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}
	
	public Optional<Product> findById(int id) {
		try(Connection c = ConnectionUtil.getConnection()){
			String sql = "select * from product where product_id=?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setInt(1, id);
			ResultSet rs = s.executeQuery();
			if(rs.next()) {
				Product p = mapRow(rs);
				return Optional.of(p);
			}else {
				return Optional.empty();
			}
		}catch (SQLException e) {
			throw new DataAccessException(e);
		}
		
	}
	
	public List<Product> findAll(){
		try(Connection c = ConnectionUtil.getConnection()){
			
			List<Product> all = new ArrayList<>();
			String sql = "select * from product";
			PreparedStatement s = c.prepareStatement(sql);
			ResultSet rs = s.executeQuery();
			while(rs.next()) {
				Product p = mapRow(rs);
				all.add(p);
			}
			return all;
		}catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}
	
	public boolean deleteById(int id) {
		try(Connection c = ConnectionUtil.getConnection()){
			String sql = "delete from product where product_id=?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setInt(1, id);
			int rowsAffected = s.executeUpdate();
			return rowsAffected > 0;
		}catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	private Product mapRow(ResultSet rs) throws SQLException {
		Product p = new Product();
		p.setId(rs.getInt(1));
		p.setName(rs.getString(2));
		p.setPrice(rs.getFloat(3));
		p.setQoh(rs.getInt(4));
		return p;
	}
	
//	try(Connection c = ConnectionUtil.getConnection()){
//		
//	}catch (SQLException e) {
//		throw new DataAccessException(e);
//	}
}
