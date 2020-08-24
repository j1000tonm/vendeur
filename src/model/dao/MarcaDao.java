package model.dao;

import java.util.List;

import model.entities.Marca;

public interface MarcaDao {

	void insert(Marca obj);
	void update(Marca obj);
	void deleteById(Long id);
	Marca findById(Long id);
	List<Marca> findAll();
}
