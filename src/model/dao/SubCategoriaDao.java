package model.dao;

import java.util.List;

import model.entities.SubCategoria;

public interface SubCategoriaDao {

	void insert(SubCategoria obj);
	void update(SubCategoria obj);
	void deleteById(Long id);
	SubCategoria findById(Long id);
	List<SubCategoria> findAll();
}
