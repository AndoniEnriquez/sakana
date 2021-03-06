package tablas;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class TrazadorTablaRegistroComida extends DefaultTableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object valor, boolean isSelected, boolean hasFocus,
			int fila, int columna) {

		super.getTableCellRendererComponent(table, valor, isSelected, hasFocus, fila, columna);

		switch (columna) {

		case 0:

			super.setText(formatearFecha(valor));
			super.setHorizontalAlignment(CENTER);
			
			break;
		case 1:

			super.setHorizontalAlignment(LEFT);
			
			break;
		}

		return this;
	}

	private String formatearFecha(Object valor) {
		
		Date fecha = (Date) valor;
		SimpleDateFormat simpleDateFormat;
		
		simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		
		return simpleDateFormat.format(fecha);
	}

}