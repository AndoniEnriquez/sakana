package tablas;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import conexionDB.DAOComida;
import conexionDB.DAORegComida;
import varTypes.Comida;
import varTypes.Pecera;
import varTypes.RegComida;

@SuppressWarnings("serial")
public class ModeloTablaRegistroComida extends AbstractTableModel {

	ModeloColumnasTablaRegistroComida columnas;
	Pecera pecera;
	ArrayList<RegComida> listaReservas;

	public ModeloTablaRegistroComida(ModeloColumnasTablaRegistroComida columnas, Pecera pecera) throws Exception {
		super();
		
		this.columnas = columnas;
		this.pecera = pecera;
		
		listaReservas = DAORegComida.getRegistrosPorPecera(pecera);
	}

	public RegComida getReservaAt(int indice) {
		return listaReservas.get(indice);
	}

	@Override
	public int getColumnCount() {

		return columnas.getColumnCount();
	}

	@Override
	public int getRowCount() {

		return listaReservas.size();
	}

	@Override
	public Object getValueAt(int fila, int columna) {
		
		RegComida a = listaReservas.get(fila);
		
		return getFieldAt(a, columna);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {

		return false;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {

		return getValueAt(0, columnIndex).getClass();
	}

	public Object getFieldAt(RegComida reserva, int columna) {
		
		switch (columna) {
			
		case 0:
			return reserva.getDatetime();
			
		case 1:
			return this.getTipoComida(reserva);
		}
		
		return null;
	}

	private Object getTipoComida(RegComida reg) {
		
		String tipoComida = "";
		
		try {
			
			Comida c = DAOComida.buscarPorID(reg.getComida_id());
			tipoComida = c.getNombreComida();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return tipoComida;
	}

}
