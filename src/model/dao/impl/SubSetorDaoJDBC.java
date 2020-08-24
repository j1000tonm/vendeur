package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.SubSetorDao;
import model.entities.SubSetor;

public class SubSetorDaoJDBC implements SubSetorDao {

	private Connection conn;	
	
	public SubSetorDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(SubSetor obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO subsetor "
					+ "(Nome) "
					+ "VALUES "
					+ "(?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getNome());
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId((long) id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(SubSetor obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE subsetor "
					+ "SET Nome = ? "
					+ "WHERE Id = ?");
			st.setString(1, obj.getNome());
			st.setLong(2, obj.getId());
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Long id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM subsetor WHERE id = ?");
			
			st.setLong(1, id);
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public SubSetor findById(Long id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * " + 
			        "FROM subsetor " + 
					"WHERE id = ?" 
					);
			st.setLong(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				SubSetor obj = instantiateSubSetor(rs);
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private SubSetor instantiateSubSetor(ResultSet rs) throws SQLException {
		SubSetor obj = new SubSetor();
		obj.setId(rs.getLong("Id"));
		obj.setNome(rs.getString("Nome"));
		return obj;
	}

	@Override
	public List<SubSetor> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * FROM subsetor "
					+ "ORDER BY nome");
			rs = st.executeQuery();
			
			List<SubSetor> list = new ArrayList<>();
			
			while (rs.next()) {
				SubSetor obj = instantiateSubSetor(rs);
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
