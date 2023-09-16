package br.com.SaulProgramador.hotel.visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.SaulProgramador.hotel.controle.HospedesController;
import br.com.SaulProgramador.hotel.controle.ReservasController;
import br.com.SaulProgramador.hotel.modelo.Hospede;
import br.com.SaulProgramador.hotel.modelo.Reserva;

@SuppressWarnings("serial")
public class Buscar extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHospedes;
	private JTable tbReservas;
	private DefaultTableModel modeloReservas;
	private DefaultTableModel modeloHospedes;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	boolean tabelaReservaSelecionada = false;
	boolean tabelaHospedeSelecionada = false;

	/**
	 * Criação da tela.
	 */
	public Buscar() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Buscar.class.getResource("/imagens/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblTitulo = new JLabel("SISTEMA DE BUSCA");
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblTitulo.setBounds(331, 62, 280, 42);
		contentPane.add(lblTitulo);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		tbReservas = new JTable();
		
		tbReservas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tabelaReservaSelecionada = true;
				tabelaHospedeSelecionada = false;
			}
		});
		
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloReservas = (DefaultTableModel) tbReservas.getModel();
		modeloReservas.addColumn("Numero de Reserva");
		modeloReservas.addColumn("Data Check In");
		modeloReservas.addColumn("Data Check Out");
		modeloReservas.addColumn("Valor");
		modeloReservas.addColumn("Forma de Pago");
		listarNaTabelaReservas(new ReservasController().listar());
		JScrollPane scroll_tableReservas = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Buscar.class.getResource("/imagens/reservado.png")), scroll_tableReservas, null);
		scroll_tableReservas.setVisible(true);

		tbHospedes = new JTable();
		
		tbHospedes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tabelaReservaSelecionada = false;
				tabelaHospedeSelecionada = true;
			}
		});
		
		tbHospedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHospedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHospedes = (DefaultTableModel) tbHospedes.getModel();
		modeloHospedes.addColumn("Numero de Hóspede");
		modeloHospedes.addColumn("Nome");
		modeloHospedes.addColumn("Sobrenome");
		modeloHospedes.addColumn("Data de Nascimento");
		modeloHospedes.addColumn("Nacionalidade");
		modeloHospedes.addColumn("Telefone");
		modeloHospedes.addColumn("Numero de Reserva");
		listarNaTabelaHospedes(new HospedesController().listar());
		
		JScrollPane scroll_tableHospedes = new JScrollPane(tbHospedes);
		panel.addTab("Hospedes", new ImageIcon(Buscar.class.getResource("/imagens/pessoas.png")), scroll_tableHospedes,
				null);
		scroll_tableHospedes.setVisible(true);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Buscar.class.getResource("/imagens/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);

		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) { // Quando o usuário passa o mouse sobre o botão, ele muda de cor
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) { // Quando o usuário remove o mouse do botão, ele retornará ao estado
													// original
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);

		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limparTabela();
					if(!txtBuscar.getText().isBlank()) {
						if(verificarSeENumero(txtBuscar.getText())) {
							listarNaTabelaReservas(new ReservasController().buscarPorId(Integer.valueOf(txtBuscar.getText())));
							listarNaTabelaHospedes(new HospedesController().buscarPorIdDeReserva(Integer.valueOf(txtBuscar.getText())));
						} else {
							listarNaTabelaReservas(new ReservasController().buscarPorSobrenomeDeHospede(txtBuscar.getText()));
							listarNaTabelaHospedes(new HospedesController().buscarPorSobrenome(txtBuscar.getText()));
						}
					} else {
						listarNaTabelaReservas(new ReservasController().listar());
						listarNaTabelaHospedes(new HospedesController().listar());
					}
				}
			});

		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tabelaReservaSelecionada) {
					AtualizarReservas();
				}
				if(tabelaHospedeSelecionada) {
					AtualizarHospedes();
				}
			}
		});

		JPanel btnDeletar = new JPanel();
		btnDeletar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int linhaReservas = tbReservas.getSelectedRow();
				int linhaHospedes = tbHospedes.getSelectedRow();
				
				if(linhaReservas >= 0) {
					deletarReserva(linhaReservas); 
				}
				
				if(linhaHospedes >= 0) {
					deletarHospede(linhaHospedes);
				}
			}
		});

		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);

		btnDeletar.setLayout(null);
		btnDeletar.setBackground(new Color(12, 138, 199));
		btnDeletar.setBounds(767, 508, 122, 35);
		btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnDeletar);

		JLabel lblExcluir = new JLabel("DELETAR");
		lblExcluir.setHorizontalAlignment(SwingConstants.CENTER);
		lblExcluir.setForeground(Color.WHITE);
		lblExcluir.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblExcluir.setBounds(0, 0, 122, 35);
		btnDeletar.add(lblExcluir);
		setResizable(false);
	}


	private void listarNaTabelaReservas(List<Reserva> reservas) {
		for (int i = 0; i < reservas.size(); i++) {
			Reserva reserva = reservas.get(i);
			String[] linhaDeReservas = { reserva.getId().toString(), reserva.getDataEntrada().toString(),
					reserva.getDataSaida().toString(), reserva.getValor().toString(), reserva.getFormaPagamento() };
			modeloReservas.addRow(linhaDeReservas);
		}
	}
	
	private void listarNaTabelaHospedes(List<Hospede> hospedes ) {
		for (int i = 0; i < hospedes.size(); i++) {
			Hospede hospede = hospedes.get(i);
			String[] linhaDeHospedes = { hospede.getId().toString(), hospede.getNome(), hospede.getSobrenome(),
					hospede.getDataNascimento().toString(), hospede.getNacionalidade(), hospede.getTelefone(),
					hospede.getIdReserva().toString() };
			modeloHospedes.addRow(linhaDeHospedes);
		}
	}
	
	private void AtualizarReservas() {
		Optional.ofNullable(modeloReservas.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
		.ifPresentOrElse(linha ->{
			
			Date dataEntrada = Date.valueOf(modeloReservas.getValueAt(tbReservas.getSelectedRow(), 1).toString());
			Date dataSaida = Date.valueOf(modeloReservas.getValueAt(tbReservas.getSelectedRow(), 2).toString());
			Double valor = Double.valueOf(modeloReservas.getValueAt(tbReservas.getSelectedRow(), 3).toString());
			String formaPagamento = modeloReservas.getValueAt(tbReservas.getSelectedRow(), 4).toString();
			Integer id = Integer.valueOf(modeloReservas.getValueAt(tbReservas.getSelectedRow(), 0).toString());
			new ReservasController().atualizar(id, dataEntrada, dataSaida, valor, formaPagamento);
			JOptionPane.showMessageDialog(this, String.format("Registro modificado com sucesso"));
		}, () -> JOptionPane.showMessageDialog(this, "Por favor, escolhar um registro"));
		
	}
	
	private void AtualizarHospedes() {
		Optional.ofNullable(modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), tbHospedes.getSelectedColumn()))
		.ifPresentOrElse(linha ->{
			
			String nome = modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 1).toString();
			String sobrenome = modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 2).toString();
			Date dataNascimento = Date.valueOf(modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 3).toString());
			String nacionalidade= modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 4).toString();
			String telefone = modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 5).toString();
			Integer idReserva = Integer.valueOf(modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 6).toString());
			Integer id = Integer.valueOf(modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 0).toString());
			new HospedesController().atualizar(id, nome, sobrenome, dataNascimento, nacionalidade, telefone, idReserva);
			JOptionPane.showMessageDialog(this, String.format("Registro modificado com sucesso"));
		}, () -> JOptionPane.showMessageDialog(this, "Por favor, escolhar um registro"));
		
	}
	
	private void deletarReserva(int linhaReservas) {
		int confirmarReserva = JOptionPane.showConfirmDialog(null, "Deseja deletar os dados?");
		
		if(confirmarReserva == JOptionPane.YES_OPTION) {
			
			Integer id = Integer.valueOf(tbReservas.getValueAt(linhaReservas, 0).toString());
			new HospedesController().deletar(id);
			new ReservasController().deletar(id);
			JOptionPane.showMessageDialog(contentPane, "Registro excluido!");
			System.out.println("passei aqui");
			limparTabela();
			listarNaTabelaReservas(new ReservasController().listar());
			listarNaTabelaHospedes(new HospedesController().listar());
		}
	}
	
	private void deletarHospede(int linhaHospedes) {
		int confirmarHospede = JOptionPane.showConfirmDialog(null, "Deseja deletar os dados?");
		
		if(confirmarHospede == JOptionPane.YES_OPTION) {
			
			Integer id = Integer.valueOf(tbHospedes.getValueAt(linhaHospedes, 0).toString());
			Integer idReserva = new HospedesController().buscarIdDeReserva(id);
			new HospedesController().deletar(id);
			new ReservasController().deletar(idReserva);
			JOptionPane.showMessageDialog(contentPane, "Registro excluido!");
			limparTabela();
			listarNaTabelaReservas(new ReservasController().listar());
			listarNaTabelaHospedes(new HospedesController().listar());
		}
	}
	
	private void limparTabela() {
		((DefaultTableModel) tbReservas.getModel()).setRowCount(0);
		((DefaultTableModel) tbHospedes.getModel()).setRowCount(0);
	}
	
	private boolean verificarSeENumero(String palavra) {
		try {
			Integer.valueOf(palavra);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Código que permite movimentar a janela pela tela seguindo a posição de "x" e
	// "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}
}
