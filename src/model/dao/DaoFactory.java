package model.dao;

import db.DB;
import model.dao.impl.CategoriaDaoJDBC;
import model.dao.impl.MarcaDaoJDBC;
import model.dao.impl.SetorDaoJDBC;
import model.dao.impl.SubCategoriaDaoJDBC;
import model.dao.impl.SubSetorDaoJDBC;

public class DaoFactory {
	
	public static MarcaDao createMarcaDao() {
		return new MarcaDaoJDBC(DB.getConnection());
	}

	public static SetorDao createSetorDao() {
		return new SetorDaoJDBC(DB.getConnection());
	}

	public static SubSetorDao createSubSetorDao() {
		return new SubSetorDaoJDBC(DB.getConnection());
	}

	public static CategoriaDao createCategoriaDao() {
		return new CategoriaDaoJDBC(DB.getConnection());
	}

	public static SubCategoriaDao createSubCategoriaDao() {
		return new SubCategoriaDaoJDBC(DB.getConnection());
	}
}
