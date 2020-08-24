package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SubSetorDao;
import model.entities.SubSetor;

public class SubSetorService {

	private SubSetorDao dao = DaoFactory.createSubSetorDao();
	
	public List<SubSetor> findAll() {
		return dao.findAll();
	}
	
	public void saveOrUpdate(SubSetor obj) {
		if (obj.getId() == null) {
			dao.insert(obj);
		}
		else {
			dao.update(obj);
		}
	}
	
	public void remove(SubSetor obj) {
		dao.deleteById(obj.getId());
	}
}
