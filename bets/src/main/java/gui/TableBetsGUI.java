package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import domain.Registered;

public class TableBetsGUI extends JFrame {

	private JTable taula;
	private Registered erabiltzaile;

	public TableBetsGUI(Registered erabiltzaile) {
		super(ResourceBundle.getBundle("Etiquetas").getString("TableUser") + erabiltzaile.getUsername());
		this.setBounds(100, 100, 700, 400);
		this.erabiltzaile = erabiltzaile;

		RegisteredAdapter adapter = new RegisteredAdapter(erabiltzaile);

		taula = new JTable(adapter);
		
		taula.setPreferredScrollableViewportSize(new Dimension(500, 70));
		
		JScrollPane scrollPane = new JScrollPane(taula);
		
		getContentPane().add(scrollPane, BorderLayout.CENTER);
	}
}
