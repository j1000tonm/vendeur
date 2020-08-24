package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SubCategoriaDao;
import model.entities.SubCategoria;

public class SubCategoriaService {

	private SubCategoriaDao dao = DaoFactory.createSubCategoriaDao();
	
	public List<SubCategoria> findAll() {
		return dao.findAll();
	}
	
	public void saveOrUpdate(SubCategoria obj) {
		if (obj.getId() == null) {
			dao.insert(obj);
		}
		else {
			dao.update(obj);
		}
	}
	
	public void remove(SubCategoria obj) {
		dao.deleteById(obj.getId());
	}
}
