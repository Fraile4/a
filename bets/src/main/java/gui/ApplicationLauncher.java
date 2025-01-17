package gui;

import java.awt.Color;
import java.net.URL;
import java.util.Locale;

import javax.swing.UIManager;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import businessLogic.BLFactory;
import businessLogic.BusinessLogicLocal;
import businessLogic.BusinessLogicRemote;
import configuration.ConfigXML;
import dataaccess.DataAccess;

public class ApplicationLauncher {

	public static void main(String[] args) {

		ConfigXML c = ConfigXML.getInstance();

		System.out.println(c.getLocale());

		Locale.setDefault(new Locale(c.getLocale()));

		System.out.println("Locale: " + Locale.getDefault());

		MainGUI a = new MainGUI();
		a.setVisible(false);

		MainUserGUI b = new MainUserGUI();
		b.setVisible(true);

		try {

			BLFacade appFacadeInterface;
			BLFactory blFactory;

//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

			
			//blFactory sortu baina lehen begiratu behar dugu lokalean edo remotoan sortu nahi den.
			//Horretarako baldintza hau. Ondoren appFacadeInterfaceri blFactory.createBLFacade(c) esleitzeko.
			if (c.isBusinessLogicLocal()) {

				blFactory = new BusinessLogicLocal();
				
			}

			else { // If remote

				blFactory = new BusinessLogicRemote();

			}

			appFacadeInterface = blFactory.createBLFacade(c);
			/*
			 * if (c.getDataBaseOpenMode().equals("initialize"))
			 * appFacadeInterface.initializeBD();
			 */
			MainGUI.setBussinessLogic(appFacadeInterface);

		} catch (Exception e) {
			a.jLabelSelectOption.setText("Error: " + e.toString());
			a.jLabelSelectOption.setForeground(Color.RED);

			System.out.println("Error in ApplicationLauncher: " + e.toString());
		}
		// a.pack();

	}

}
