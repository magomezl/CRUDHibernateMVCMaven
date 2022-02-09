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
	 * @return true en caso de que el borrado de datos sea aceptado 
	 *         false en caso contrario
	 */
	public static boolean eliminarDpto(SessionFactory sf, byte dptonum) {
		
		try {
			Session mS = sf.openSession();
			Transaction t = mS.beginTransaction();
			Departamentos dpto = mS.load(Departamentos.class, dptonum);
			
			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, 
					"¿Esta seguro de querer eliminar el departamento " + dpto.getDnombre() + "(" + dpto.getLoc() + ")")) {
				mS.delete(dpto);
				t.commit();
				mS.close();
				return true;
			}else {
				t.rollback();
				mS.close();
				return false;
			}
		}catch (Exception e) {
			return false;
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
	
	
	public static boolean modificarDpto(SessionFactory sf, byte dptonum,  String nombre, String localidad) {
		Session mS = sf.openSession();
		Transaction t = mS.beginTransaction();
		Departamentos dpto = mS.get(Departamentos.class, dptonum);
		boolean testigo = false;
		if (nombre!=null && !dpto.getDnombre().equals(nombre)) {
			dpto.setDnombre(nombre);
			testigo = true;
		}
		if(localidad!=null && !dpto.getLoc().equals(localidad)) {
			dpto.setLoc(localidad);
			testigo = true;
		}
		if (testigo) {
			mS.update(dpto);
			t.commit();
			mS.close();
		}else {
			t.rollback();
			mS.close();
		}
		return testigo;
	}
	
	/**
	 * 
	 * @param <T>
	 * @param sf
	 * @param opt 1 si queremos retornar una lista ordenada con los códigos de deparamento, 
	 * 			  2 si queremos retornar una lista ordenada con los nombres de departamento 
	 *            3 para localidades de departamentos ordenadas
	 * @return
	 */
	public static <T> List<T> llenarCombo(SessionFactory sf, int opt){
		String hql=null;
		switch(opt) {
		case 1:
			hql = "select distinct deptno from Departamentos order by deptno";
			break;
		case 2:
			hql = "select distinct dnombre from Departamentos order by dnombre";
			break;
		case 3:
			hql = "select distinct loc from Departamentos order by loc";
			break;
		}
		Session mS = sf.openSession();
		TypedQuery<T> consulta =mS.createQuery(hql);
		return (List<T>) consulta.getResultList();
	}
}

