package model.dao;

import java.util.List;

import model.entities.SubSetor;

public interface SubSetorDao {

	void insert(SubSetor obj);
	void update(SubSetor obj);
	void deleteById(Long id);
	SubSetor findById(Long id);
	List<SubSetor> findAll();
}
