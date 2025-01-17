package businessLogic;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import configuration.ConfigXML;
import domain.Event;

public class Adibidea {
	
	public static void main(String[] args) {
		boolean isLocal=true;
		BLFactory factory;
		factory = new BusinessLogicLocal();
		ConfigXML c = ConfigXML.getInstance();
		//Facade objektua lortu lehendabiziko ariketa erabiliz
		BLFacade facadeInterface= factory.createBLFacade(c);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date data;
		try {
			data = sdf.parse("17/12/2023");
			ExtendedIterator<Event> ite = facadeInterface.getEvents(data);
			Event ev;
			
			System.out.println("*************** ATZERAKA ****************");
			ite.goLast();
			while(ite.hasPrevious()) {
				ev = ite.previous();
				System.out.println(ev.toString());
			}
			System.out.println("\n");
			
			System.out.println("*************** AURRERAKA ****************");
			ite.goFirst();
			while(ite.hasNext()) {
				ev = (Event) ite.next();
				System.out.println(ev.toString());
			}
			
		}catch(ParseException e) {
			System.out.println("errorea");
		}
		
	}
}
