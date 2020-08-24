package model.dao;

import java.util.List;

import model.entities.Categoria;

public interface CategoriaDao {

	void insert(Categoria obj);
	void update(Categoria obj);
	void deleteById(Long id);
	Categoria findById(Long id);
	List<Categoria> findAll();
}
