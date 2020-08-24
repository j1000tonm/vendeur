package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SetorDao;
import model.entities.Setor;

public class SetorService {

	private SetorDao dao = DaoFactory.createSetorDao();
	
	public List<Setor> findAll() {
		return dao.findAll();
	}
	
	public void saveOrUpdate(Setor obj) {
		if (obj.getId() == null) {
			dao.insert(obj);
		}
		else {
			dao.update(obj);
		}
	}
	
	public void remove(Setor obj) {
		dao.deleteById(obj.getId());
	}
}
