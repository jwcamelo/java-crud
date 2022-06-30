package br.com.safv.jwcamelo.VIEW;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.safv.jwcamelo.DAO.ConnectionFactory;
import br.com.safv.jwcamelo.DAO.MotoristaDAO;
import br.com.safv.jwcamelo.DTO.MotoristaDTO;

public class CadastroMotoristaVIEW extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroMotoristaVIEW frame = new CadastroMotoristaVIEW();
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
	public CadastroMotoristaVIEW() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCadastroDeMotorista = new JLabel("Cadastro de Motorista");
		lblCadastroDeMotorista.setBounds(138, 26, 170, 15);
		contentPane.add(lblCadastroDeMotorista);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(75, 87, 160, 15);
		contentPane.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(75, 114, 160, 19);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(75, 156, 70, 15);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(75, 183, 160, 19);
		contentPane.add(txtEmail);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConnectionFactory cf = new ConnectionFactory();
				String nome, email;
				
				nome = txtNome.getText();
				email = txtEmail.getText();
				
				MotoristaDTO motoristaDTO = new MotoristaDTO();
				motoristaDTO.setNome(nome);
				motoristaDTO.setEmail(email);
				
				MotoristaDAO motoristaDAO;
				try (Connection con = cf.recuperarConexao()){
					motoristaDAO = new MotoristaDAO(con);
					motoristaDAO.cadastrar(motoristaDTO);
					JOptionPane.showMessageDialog(null, "Motorista cadastrado com Sucesso!","Motorista Cadastrado",1);
					txtNome.setText("");
					txtEmail.setText("");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1,"Erro Cadastro Motorista",2);
				}
				
			}
		});
		btnCadastrar.setBounds(300, 183, 117, 25);
		contentPane.add(btnCadastrar);
	}
}
