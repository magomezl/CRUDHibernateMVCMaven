package modelo;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Utilidad {
	
	private static final SessionFactory sf = new Configuration().configure().buildSessionFactory();
	
	public static SessionFactory getSesionFactoria() {
		return sf;
	}
	
	public static void closeSesionFactoria() {
		sf.close();
	}

}
