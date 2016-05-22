package tablas;

import java.awt.Component;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TrazadorTablaRegistroComida extends DefaultTableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object valor,
			boolean isSelected, boolean hasFocus, int fila, int columna) {
		
		super.getTableCellRendererComponent(table, valor, isSelected, hasFocus, fila, columna);
		switch (columna ){
		case 0: super.setHorizontalAlignment(LEFT);break;
		case 1: super.setText(formatearFecha(valor));
			    super.setHorizontalAlignment(CENTER);break;
		case 2: super.setHorizontalAlignment(LEFT);break;

		}
		
		
		return this;
	}

	private String formatearFecha(Object valor) {
		Date fecha = (Date) valor;
		String fechaFormato = fecha.getYear() + "/" + fecha.getMonth() + "/" + fecha.getDay() + "  " + fecha.getHours() + ":" +fecha.getMinutes();
		DateTimeFormatter formatter =   DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm");
	    return fechaFormato;
		
	}

	
}