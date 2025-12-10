package modelo.entidades;

import java.time.LocalDateTime;

public class HoraActual {

	//Sirven para obtener la hora actual
		LocalDateTime locaDate = LocalDateTime.now();
		protected int horas  = locaDate.getHour();
		protected int minutos = locaDate.getMinute();
}
