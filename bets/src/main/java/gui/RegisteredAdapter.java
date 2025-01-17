package gui;

import java.util.ResourceBundle;

import javax.swing.table.AbstractTableModel;

import domain.ApustuAnitza;
import domain.Apustua;
import domain.Registered;

public class RegisteredAdapter extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	protected Registered erabiltzailea;
	protected String[] zutabeenInfo = new String[] { ResourceBundle.getBundle("Etiquetas").getString("Event"),
			ResourceBundle.getBundle("Etiquetas").getString("Queries"),
			ResourceBundle.getBundle("Etiquetas").getString("Date"),
			ResourceBundle.getBundle("Etiquetas").getString("MainTitle") + "(€)" };

	public RegisteredAdapter(Registered erabiltzailea) {
		this.erabiltzailea = erabiltzailea;
	}

	public String getColumnName(int columnIndex) {
		return zutabeenInfo[columnIndex];
	}

	public int getColumnCount() {
		return 4;
	}

	public int getRowCount() {
		return erabiltzailea.getApustuAnitzak().size();
	}

	public String getValueAt(int errenI, int zutabeI) {

		Apustua bet = getBetInTable(errenI);

		if (zutabeI == 0) {
			return bet.getKuota().getQuestion().getEvent().getDescription();

		} else if (zutabeI == 1) {
			return bet.getKuota().getQuestion().getQuestion();
			
		} else if (zutabeI == 2) {
			return bet.getApustuAnitza().getData().toLocaleString();
			
		} else if (zutabeI == 3) {
			return bet.getKuota().getQuote().toString();
			
		} else {
			return null;
		}

	}

	public Apustua getBetInTable(int errenI) {
		int totalBets = 0;

		for (ApustuAnitza apustuAnitza : erabiltzailea.getApustuAnitzak()) {
			for (Apustua apustua : apustuAnitza.getApustuak()) {
				if (totalBets == errenI) {
					return apustua;
				}
				totalBets++;
			}
		}

		return null;
	}
}
