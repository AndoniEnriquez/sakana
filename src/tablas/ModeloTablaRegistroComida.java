package tablas;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import ConexionDB.DAOComida;
import ConexionDB.DAORegComida;
import ConexionDB.DAOTipoComida;
import VarTypes.Comida;
import VarTypes.Pecera;
import VarTypes.RegComida;
import VarTypes.TipoComida;

public class ModeloTablaRegistroComida extends AbstractTableModel {

	ModeloColumnasTablaRegistroComida columnas;
	Pecera pecera;
	ArrayList<RegComida> listaReservas;

	public ModeloTablaRegistroComida (ModeloColumnasTablaRegistroComida columnas,Pecera pecera) throws Exception{
		super();
		this.columnas = columnas;
		this.pecera = pecera;
		listaReservas =  DAORegComida.getRegistrosPorPecera(pecera);

	}


	public RegComida getReservaAt(int indice){
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
		return getFieldAt(a,columna);

	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {

		return false;
	}
	@Override
	public Class<?> getColumnClass(int columnIndex) {

		return getValueAt(0,columnIndex).getClass();
	}

	public void actualizar() throws Exception {

		/*DAORegComida.cleanReservas();
		listaReservas = DAOReservas.getReservasRecurso(recurso);

		this.fireTableDataChanged();*/
	}

	public Object getFieldAt(RegComida reserva,int columna) {
		switch (columna){
		case 0: return reserva.getId();
		case 1: return reserva.getDatetime();
		case 2: return this.getTipoComida(reserva);


		}
		return null;
	}


	private Object getTipoComida(RegComida reg) {
		String tipoComida = "";
		try {
			Comida c = DAOComida.buscarPorID(reg.getComida_id());
			TipoComida tc = DAOTipoComida.buscarPorID(c.getTipocomida_id());
			tipoComida = tc.getDescripcionTipoComida();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tipoComida;
	}

}
