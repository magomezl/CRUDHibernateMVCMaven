package crudmvc;

import controlador.Controlador;
import modelo.Proceso;
import vista.FrmPrincipal;

public class Principal {

	public static void main(String[] args) {

		// Instanciar el controlador, abrir la ventana y hacerla visible
		Controlador ctrl = new Controlador(new Proceso(),  new FrmPrincipal());
		ctrl.abrirVentana();
	}
}
