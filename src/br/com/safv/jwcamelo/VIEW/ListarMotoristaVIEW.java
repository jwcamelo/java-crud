package br.com.safv.jwcamelo.VIEW;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.safv.jwcamelo.DAO.ConnectionFactory;
import br.com.safv.jwcamelo.DAO.MotoristaDAO;
import br.com.safv.jwcamelo.DTO.MotoristaDTO;
import javax.swing.border.BevelBorder;
import java.awt.Dimension;

public class ListarMotoristaVIEW extends JFrame {

	private JPanel contentPane;
	private JTable tbMotorista;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListarMotoristaVIEW frame = new ListarMotoristaVIEW();
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
	public ListarMotoristaVIEW() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		tbMotorista = new JTable();
		tbMotorista.setSize(new Dimension(350, 300));
		tbMotorista.setMinimumSize(new Dimension(350, 300));
		tbMotorista.setMaximumSize(new Dimension(350, 300));
		tbMotorista.setPreferredSize(new Dimension(350, 300));
		tbMotorista.setCellSelectionEnabled(true);
		tbMotorista.setColumnSelectionAllowed(true);
		tbMotorista.setBounds(58, 56, 325, 229);
		panel.add(tbMotorista);
		
		listarValores();
	}
	
	private void listarValores() {
		try {
			ConnectionFactory cf = new ConnectionFactory();
			MotoristaDAO motoristaDAO = new MotoristaDAO(cf.recuperarConexao());
			
			DefaultTableModel model = (DefaultTableModel) tbMotorista.getModel();
			model.setNumRows(0);
			
			ArrayList<MotoristaDTO> lista = motoristaDAO.listar();
			
			for(MotoristaDTO motorista:lista) {
				System.out.println(motorista);
				model.addRow(new Object[] {
						motorista.getId(),
						motorista.getNome(),
						motorista.getEmail()
				});
			}
			
		}catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, "listar valores: "+ex);
		}
	}
}
