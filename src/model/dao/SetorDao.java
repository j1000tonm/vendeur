package model.dao;

import java.util.List;

import model.entities.Setor;

public interface SetorDao {

	void insert(Setor obj);
	void update(Setor obj);
	void deleteById(Long id);
	Setor findById(Long id);
	List<Setor> findAll();
}
