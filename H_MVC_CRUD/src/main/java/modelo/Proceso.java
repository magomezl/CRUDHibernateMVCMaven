package modelo;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class Proceso {
	
	// TODO devolver en los métodos valores numéricos y que sea el controlador el que muestre los mensajes de confirmación o no
	/**
	 * 
	 * @param sf
	 * @param dptonum
	 * @param nombre
	 * @param localidad
	 * @return true en caso de que la inserción de datos sea correcta false en caso contrario
	 */
	public static boolean anadirDpto(SessionFactory sf, byte dptonum, String nombre, String localidad) {
		try {
			Session mS = sf.openSession();
			Transaction t = mS.beginTransaction();
			Departamentos dpto = new Departamentos();
			dpto.setDeptno(dptonum);
			dpto.setDnombre(nombre);
			dpto.setLoc(localidad);
			mS.save(dpto);
			t.commit();
			mS.close();
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	/**
	 * 
	 * @param sf
	 * @param dptonum
	 * @return 1 en caso de que el borrado de datos sea correcto 0 en caso contrario
	 */
	public static int eliminarDpto(SessionFactory sf, byte dptonum) {
		
		try {
			Session mS = sf.openSession();
			Transaction t = mS.beginTransaction();
			Departamentos dpto = mS.load(Departamentos.class, dptonum);
			
			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, 
					"¿Esta seguro de querer eliminar el departamento " + dpto.getDnombre() + "(" + dpto.getLoc() + ")")) {
				mS.delete(dpto);
				t.commit();
				mS.close();
				return 1;
			}else {
				t.rollback();
				mS.close();
				return 0;
			}
		}catch (Exception e) {
			return 0;
		}
	}
	
	// TODO Pendiente
	
	public static List<Departamentos> listarDptos(SessionFactory sf, byte dptonum,  String nombre, String localidad) {
		// TODO vamos a hacer listar todos
		String hql=null;
		if (dptonum==0 && nombre==null && localidad==null) {
			hql = "from Departamentos";
		}else if (dptonum!=0) {
			hql = "from Departamentos where deptno=" + dptonum + " order by deptno";
		}else if (nombre!=null) {
			hql = "from Departamentos where dnombre='" + nombre + "' order by deptno";
		}else if (localidad!=null) {
			hql = "from Departamentos where loc='" + localidad + "' order by deptno";
		}
		Session mS = sf.openSession();
		TypedQuery<Departamentos> consulta =mS.createQuery(hql, Departamentos.class);
		return consulta.getResultList();
	}
	
	
	public static void modificarDpto(SessionFactory sf, byte dptonum,  String nombre, String localidad) {
		Session mS = sf.openSession();
		Transaction t = mS.beginTransaction();
		Departamentos dpto = mS.get(Departamentos.class, dptonum);
		
		if (!nombre.equals(null)) {
			dpto.setDnombre(nombre);
		}
		if(!localidad.equals(null)) {
			dpto.setLoc(null);
		}
		mS.update(dpto);
		t.commit();
		mS.close();
		JOptionPane.showMessageDialog(null, "Departamento modificado");
		
	}
	
	
}

