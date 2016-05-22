package tablas;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

public class ModeloColumnasTablaRegistroComida extends DefaultTableColumnModel{
	
	TrazadorTablaRegistroComida trazador;
	
	public ModeloColumnasTablaRegistroComida(TrazadorTablaRegistroComida trazador){
		super();
		this.trazador = trazador;
		this.addColumn(crearColumna("ID",0,20));
		this.addColumn(crearColumna("Fecha",1,100));
		this.addColumn(crearColumna("Tipo Comida",2,100));

	}

	private TableColumn crearColumna(String texto, int indice, int ancho) {
		TableColumn columna = new TableColumn(indice,ancho);
		
		columna.setHeaderValue(texto);
		columna.setPreferredWidth(ancho);
		columna.setCellRenderer(trazador);
		
		return columna;
	}

}
