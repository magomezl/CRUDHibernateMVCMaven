package controlador;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.Departamentos;
import modelo.Proceso;
import modelo.Utilidad;
import vista.FrmPrincipal;

public class Controlador implements ActionListener {
	private Proceso modelo;
	private FrmPrincipal vista;
	
	public void deshabilitarControles(JPanel panel) {
		Component[] componentes = panel.getComponents();
		
		for(int i=0; i<componentes.length; i++) {
			if (componentes[i] instanceof JTextField || componentes[i] instanceof JButton ||
				componentes[i] instanceof JRadioButton || componentes[i] instanceof JComboBox){
				componentes[i].setEnabled(false);
			}
		}
	}
	
	
	
	
	public Controlador(Proceso modelo, FrmPrincipal vista) {
		this.modelo = modelo;
		this.vista = vista;
		
		//La clase Controlador va a gestionar los clics (eventos)
		this.vista.mnDptoC.addActionListener(this);
		this.vista.mnDptoR.addActionListener(this);
		
		
		this.vista.mnEmplC.addActionListener(this);
		this.vista.mnEmplR.addActionListener(this);
		this.vista.mnEmplU.addActionListener(this);
		this.vista.mnEmplD.addActionListener(this);
		
		this.vista.btnGuardar.addActionListener(this);
		this.vista.btnListarTodos.addActionListener(this);
		this.vista.btnBuscar.addActionListener(this);
		
		this.vista.rdbtnLocalidad.addActionListener(this);
		this.vista.rdbtnNombre.addActionListener(this);
		this.vista.rdbtnNumero.addActionListener(this);
		
		
		
		
		this.vista.resultados.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (vista.resultados.getSelectedValue()!=null) {
					deshabilitarControles(vista.pnlDpto);
					vista.btnModificar.setEnabled(true);
					vista.btnEliminar.setEnabled(true);
					
					
					
					vista.txtNombre.setEnabled(true);
					vista.txtLocalidad.setEnabled(true);
					
					//TODO 07/02/2022
					vista.txtNumero.setVisible(true);
					vista.txtNombre.setVisible(true);
					vista.txtLocalidad.setVisible(true);
					
					vista.cmbNumero.setVisible(false);
					vista.cmbNombre.setVisible(false);
					vista.cmbLocalidad.setVisible(false);
					
					vista.txtNumero.setText(String.valueOf(vista.resultados.getSelectedValue().getDeptno()));
					vista.txtNombre.setText(vista.resultados.getSelectedValue().getDnombre());
					vista.txtLocalidad.setText(vista.resultados.getSelectedValue().getLoc());
				}
			}
		});
		
	}
	
	public void limpiaColocaCursor(JTextField componente) {
		componente.setText("");
		componente.requestFocus();
	}

	public boolean comprobarDatosDepartamento(byte dptoNum, String nombre, String localidad){
		// TODO prescindir
		if (dptoNum<1 || dptoNum>255) {
			JOptionPane.showMessageDialog(null, "El número de departamento debe estar comprendido entre 1 y 255");
			limpiaColocaCursor(vista.txtNumero);
			return false;
		}else if (nombre.length()<3 || nombre.length()>15) {
			JOptionPane.showMessageDialog(null, "El nombre del departamento no debe exceder de 15 caracteres");
			limpiaColocaCursor(vista.txtNumero);
			return false;
		}else if (localidad.length()<3 || localidad.length()>15) {
			JOptionPane.showMessageDialog(null, "La localidad del departamento no debe exceder de 15 caracteres");
			limpiaColocaCursor(vista.txtLocalidad);
			return false;
		}
		return true;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==vista.mnDptoC ) {
			vista.pnlDpto.setVisible(true);
			vista.pnlEmpl.setVisible(false);
			
			deshabilitarControles(vista.pnlDpto);
			
			Component[] componentes = vista.pnlDpto.getComponents();
			for(int i=0; i<componentes.length; i++) {
				if (componentes[i] instanceof JComboBox){
					componentes[i].setVisible(false);
				}else if (componentes[i] instanceof JTextField) {
					componentes[i].setVisible(true);
					componentes[i].setEnabled(true);
				}else if (componentes[i] instanceof JButton) {
					if (componentes[i].getName()=="btnGuardar" || componentes[i].getName()=="btnCancelar") {
						componentes[i].setEnabled(true);
					}
				}
			}

		}else if (e.getSource()==vista.mnDptoR ) { 
			
			vista.pnlDpto.setVisible(true);
			vista.pnlEmpl.setVisible(false);
			
			deshabilitarControles(vista.pnlDpto);
			
			Component[] componentes = vista.pnlDpto.getComponents();
			for(int i=0; i<componentes.length; i++) {
				if (componentes[i] instanceof JComboBox){
					componentes[i].setVisible(true);
				}else if (componentes[i] instanceof JTextField) {
					componentes[i].setVisible(false);
					//componentes[i].setEnabled(false);
				}else if (componentes[i] instanceof JRadioButton) {
					
					componentes[i].setEnabled(true);
				}else if (componentes[i] instanceof JButton) {
					if (componentes[i].getName()=="btnListarTodos" || componentes[i].getName()=="btnBuscar") {
						componentes[i].setEnabled(true);
					}
				}
			}
			
			//TODO se repetirían los valores de nombre y la localidad. Lo ideal es que además de no repetirse aparezcan ordenados
			for (Departamentos dpto: Proceso.listarDptos(Utilidad.getSesionFactoria(), (byte)0, null, null)) {
				vista.cmbLocalidad.addItem(dpto.getLoc());
				vista.cmbNombre.addItem(dpto.getDnombre());
				vista.cmbNumero.addItem(dpto.getDeptno());
			}
			
			
			
			
		
		}else if (e.getSource()==vista.btnGuardar ) {
			// TODO Capturar excepcion valor no numerico
			try {
				if (comprobarDatosDepartamento(Byte.parseByte(vista.txtNumero.getText()), vista.txtNombre.getText(), vista.txtLocalidad.getText())){
					if (Proceso.anadirDpto(Utilidad.getSesionFactoria(), Byte.parseByte(vista.txtNumero.getText()), vista.txtNombre.getText(),
						vista.txtLocalidad.getText())) {
						JOptionPane.showMessageDialog(null, "Departamento añadido correctamente");
						limpiaColocaCursor(vista.txtNombre);
						limpiaColocaCursor(vista.txtLocalidad);
						limpiaColocaCursor(vista.txtNumero);
					}else
						JOptionPane.showMessageDialog(null, "Departamento no añadido correctamente");
				}
			}catch (NumberFormatException err ) {
				JOptionPane.showMessageDialog(null, "El número de departamento debe ser un valor entre 1 y 255");
				limpiaColocaCursor(vista.txtNumero);
			}catch (Exception err) {
				JOptionPane.showMessageDialog(null, "Error en los datos introduccidos");
				
			}
		}else if (e.getSource()==vista.btnListarTodos ) {
			
			vista.modeloLista.clear();
			for(Departamentos dpto: Proceso.listarDptos(Utilidad.getSesionFactoria(), Byte.parseByte("0"), null, null) ) {
				vista.modeloLista.addElement(new Departamentos(dpto.getDeptno(), dpto.getDnombre(), dpto.getLoc(), null));
			}			
		}else if (e.getSource()==vista.btnBuscar ) { 
			vista.modeloLista.clear();
			if (vista.rdbtnNumero.isSelected()) {
				for(Departamentos dpto: Proceso.listarDptos(Utilidad.getSesionFactoria(), 
						vista.cmbNumero.getItemAt(vista.cmbNumero.getSelectedIndex()), null, null) ) {
					vista.modeloLista.addElement(new Departamentos(dpto.getDeptno(), dpto.getDnombre(), dpto.getLoc(), null));
				}	
			}else if (vista.rdbtnNombre.isSelected()) {
				for(Departamentos dpto: Proceso.listarDptos(Utilidad.getSesionFactoria(), (byte)0, 
						vista.cmbNombre.getItemAt(vista.cmbNombre.getSelectedIndex()), null) ) {
					vista.modeloLista.addElement(new Departamentos(dpto.getDeptno(), dpto.getDnombre(), dpto.getLoc(), null));
				}	
			}else if (vista.rdbtnLocalidad.isSelected()) {
				for(Departamentos dpto: Proceso.listarDptos(Utilidad.getSesionFactoria(), (byte)0, 
						null, vista.cmbLocalidad.getItemAt(vista.cmbLocalidad.getSelectedIndex())) ) {
					vista.modeloLista.addElement(new Departamentos(dpto.getDeptno(), dpto.getDnombre(), dpto.getLoc(), null));
				}	
			}
		
		}else if (e.getSource() == vista.rdbtnNumero) {
			vista.cmbNombre.setEnabled(false);
			vista.cmbLocalidad.setEnabled(false);
			vista.cmbNumero.setEnabled(true);
		}else if (e.getSource() == vista.rdbtnNombre) {
			vista.cmbLocalidad.setEnabled(false);
			vista.cmbNumero.setEnabled(false);
			vista.cmbNombre.setEnabled(true);
		}else if (e.getSource() == vista.rdbtnLocalidad) {
			vista.cmbNombre.setEnabled(false);
			vista.cmbNumero.setEnabled(false);
			vista.cmbLocalidad.setEnabled(true);
		
		
		
		
		
		
		}else if (e.getSource()==vista.mnEmplC || e.getSource()==vista.mnEmplR ||
				e.getSource()==vista.mnEmplU || e.getSource()==vista.mnEmplD) {
			vista.pnlDpto.setVisible(false);
			vista.pnlEmpl.setVisible(true);
		}
	}
	
	
	public void abrirVentana() {
		vista.setTitle("Gestión de RRHH");
		vista.setLocationRelativeTo(null);
		vista.setVisible(true);
	}
	
}
