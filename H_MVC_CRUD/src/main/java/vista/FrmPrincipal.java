package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Departamentos;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class FrmPrincipal extends JFrame {

	public JPanel contentPane;
	public JMenuBar menuBar;
	public JMenu mnDptos;
	public JMenuItem mnDptoC;
	public JMenuItem mnDptoR;
	public JMenuItem mnDptoU;
	public JMenuItem mnDptoD;


	public JMenu mnEmpl;
	public JMenuItem mnEmplC;
	public JMenuItem mnEmplR;
	public JMenuItem mnEmplU;
	public JMenuItem mnEmplD;
	
	public JLabel lblGestinDeEmpleados;
	public JButton btnGuardar;
	public JButton btnListarTodos;
	public JButton btnBuscar;
	public JButton btnModificar;
	public JButton btnEliminar;
	public JButton btnCancelar;
	
	
	public JTextField txtNumero;
	public JTextField txtNombre;
	public JTextField txtLocalidad;
	
	public JRadioButton rdbtnNumero;
	public JRadioButton rdbtnNombre;
	public JRadioButton rdbtnLocalidad;
	private ButtonGroup criterioSelect;
	
	public JComboBox<String> cmbLocalidad;
	public JComboBox<String> cmbNombre;
	public JComboBox<Byte> cmbNumero;
	
	public JPanel pnlEmpl;
	public JPanel pnlDpto;
	
	public JScrollPane scrollPane;
	public JList<Departamentos> resultados;
	public DefaultListModel<Departamentos> modeloLista;
	
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPrincipal frame = new FrmPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 924, 541);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 11, 888, 22);
		contentPane.add(menuBar);
		
		
		mnEmpl = new JMenu("Empleados");
		menuBar.add(mnEmpl);
		
		mnEmplC = new JMenuItem("Crear");
		mnEmpl.add(mnEmplC);
		
		mnEmplR = new JMenuItem("Listar");
		mnEmpl.add(mnEmplR);
		
		mnEmplU = new JMenuItem("Modificar");
		mnEmpl.add(mnEmplU);
		
		mnEmplD = new JMenuItem("Eliminar");
		mnEmpl.add(mnEmplD);
		
		mnDptos = new JMenu("Departamentos");
		menuBar.add(mnDptos);
		
		mnDptoC = new JMenuItem("Crear");
		mnDptos.add(mnDptoC);
		
		mnDptoR = new JMenuItem("Listar");
		mnDptos.add(mnDptoR);
		
		mnDptoU = new JMenuItem("Modificar");
		mnDptos.add(mnDptoU);
		
		mnDptoD = new JMenuItem("Eliminar");
		mnDptos.add(mnDptoD);
		
		
		pnlDpto = new JPanel();
		pnlDpto.setBounds(10, 44, 888, 447);
		contentPane.add(pnlDpto);
		pnlDpto.setLayout(null);
		
		JLabel lblNumDpto = new JLabel("Número");
		lblNumDpto.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNumDpto.setBounds(100, 60, 74, 22);
		pnlDpto.add(lblNumDpto);
		
		JLabel lblTitle = new JLabel("Gestión de Departamentos");
		lblTitle.setBounds(348, 5, 321, 31);
		lblTitle.setFont(new Font("Trebuchet MS", Font.BOLD, 26));
		pnlDpto.add(lblTitle);
		
		JLabel lblNombreDpto = new JLabel("Nombre");
		lblNombreDpto.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombreDpto.setBounds(100, 100, 74, 22);
		pnlDpto.add(lblNombreDpto);
		
		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLocalidad.setBounds(100, 140, 74, 22);
		pnlDpto.add(lblLocalidad);
		
		txtNumero = new JTextField();
		txtNumero.setBounds(184, 60, 193, 20);
		pnlDpto.add(txtNumero);
		txtNumero.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(184, 100, 193, 20);
		pnlDpto.add(txtNombre);
		
		txtLocalidad = new JTextField();
		txtLocalidad.setColumns(10);
		txtLocalidad.setBounds(184, 140, 193, 20);
		pnlDpto.add(txtLocalidad);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnGuardar.setBounds(43, 198, 124, 31);
		btnGuardar.setName("btnGuardar");
		pnlDpto.add(btnGuardar);
		
		btnListarTodos = new JButton("Listar todos");
		btnListarTodos.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnListarTodos.setBounds(174, 198, 124, 31);
		btnListarTodos.setName("btnListarTodos");
		pnlDpto.add(btnListarTodos);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBuscar.setBounds(309, 198, 124, 31);
		btnBuscar.setName("btnBuscar");
		pnlDpto.add(btnBuscar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCancelar.setBounds(711, 198, 124, 31);
		btnCancelar.setName("btnCancelar");
		pnlDpto.add(btnCancelar);
		
		rdbtnNumero = new JRadioButton("");
		rdbtnNumero.setBounds(65, 60, 29, 23);
		pnlDpto.add(rdbtnNumero);
		
		rdbtnNombre = new JRadioButton("");
		rdbtnNombre.setBounds(65, 100, 29, 23);
		pnlDpto.add(rdbtnNombre);
		
		rdbtnLocalidad = new JRadioButton("");
		rdbtnLocalidad.setBounds(65, 140, 29, 23);
		pnlDpto.add(rdbtnLocalidad);
		
		
		criterioSelect = new ButtonGroup();
		criterioSelect.add(rdbtnNumero);
		criterioSelect.add(rdbtnNombre);
		criterioSelect.add(rdbtnLocalidad);
		
		cmbNumero = new JComboBox();
		cmbNumero.setBounds(184, 60, 217, 22);
		pnlDpto.add(cmbNumero);
		
		cmbNombre = new JComboBox();
		cmbNombre.setBounds(184, 100, 217, 22);
		pnlDpto.add(cmbNombre);
		
		cmbLocalidad = new JComboBox();
		cmbLocalidad.setBounds(184, 140, 217, 22);
		pnlDpto.add(cmbLocalidad);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnModificar.setBounds(443, 198, 124, 31);
		btnModificar.setName("btnModificar");
		pnlDpto.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEliminar.setBounds(577, 198, 124, 31);
		btnEliminar.setName("btnEliminar");
		pnlDpto.add(btnEliminar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 240, 792, 171);
		pnlDpto.add(scrollPane);
		
		resultados = new JList<Departamentos>();
		scrollPane.setViewportView(resultados);
		modeloLista = new DefaultListModel<Departamentos>();
		resultados.setModel(modeloLista);
		
		pnlEmpl = new JPanel();
		pnlEmpl.setBounds(10, 44, 888, 447);
		contentPane.add(pnlEmpl);
		
		lblGestinDeEmpleados = new JLabel("Gestión de Empleados");
		lblGestinDeEmpleados.setFont(new Font("Trebuchet MS", Font.BOLD, 26));
		pnlEmpl.add(lblGestinDeEmpleados);
		
		
		pnlDpto.setVisible(false);
		pnlEmpl.setVisible(false);
		
	}
}
