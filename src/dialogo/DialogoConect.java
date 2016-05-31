package dialogo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import conexionDB.DAOPecera;
import conexionMicro.InterfazMicro;
import fabrica.FabricaAcciones;
import varTypes.Pecera;

@SuppressWarnings("serial")
public class DialogoConect extends JDialog implements ActionListener {

	final static String TITULO = "Gestion Micro";

	JLabel txPH, txPHMin, txPHMax, txTemp, txTempMin, txTempMax, txFedTime, txCurTime, txPHOff, txFischN, txMeals;

	Pecera p;
	FabricaAcciones fabrica;
	InterfazMicro interfaz;

	Float ph, phOff, temp;
	int meal, fishN;
	Calendar dateTime, feedTime;

	public DialogoConect(JFrame frame, FabricaAcciones fabrica, Pecera p, boolean modo) {

		super(frame, TITULO, modo);
		this.p = p;
		this.fabrica = fabrica;
		interfaz = new InterfazMicro(p);

		if (sincronizarDatos() == true) {
			crearVentana();
		}

		this.setVisible(true);
	}

	private void crearVentana() {

		this.setLocation(600, 300);
		this.setSize(550, 400);
		this.setContentPane(crearPanelDialogo());
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	private Container crearPanelDialogo() {

		JPanel panel = new JPanel(new BorderLayout(0, 20));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.add(crearPanelCampos(), BorderLayout.CENTER);
		panel.add(crearPanelBotones(), BorderLayout.SOUTH);
		return panel;
	}

	private Component crearPanelBotones() {

		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));

		JButton bSinc = new JButton("Sincronizar");
		bSinc.setActionCommand("Sinc");
		bSinc.addActionListener(this);

		JButton bActu = new JButton("Refresh");
		bActu.setActionCommand("Refresh");
		bActu.addActionListener(this);

		JButton bCancel = new JButton("Cancelar");
		bCancel.setActionCommand("Cancelar");
		bCancel.addActionListener(this);

		JButton bFeed = new JButton("Feed");
		bFeed.setActionCommand("Feed");
		bFeed.addActionListener(this);

		JButton bCalibrar = new JButton("Calibrar");
		bCalibrar.setActionCommand("Calibrar");
		bCalibrar.addActionListener(this);

		panel.add(bActu);
		panel.add(bSinc);
		panel.add(bFeed);
		panel.add(bCalibrar);
		panel.add(bCancel);

		return panel;
	}

	private Component crearPanelCampos() {

		JPanel panel = new JPanel(new GridLayout(6, 2, 0, 20));

		panel.add(txPH = crearCampo("PH"));
		panel.add(txPHOff = crearCampo("Sensor calibrado a:"));

		panel.add(txPHMin = crearCampo("PHMin"));
		panel.add(txPHMax = crearCampo("PHMax"));

		panel.add(txTemp = crearCampo("Temperatura"));

		panel.add(txTempMin = crearCampo("Temp Minima"));
		panel.add(txTempMax = crearCampo("Temp Maxima"));

		panel.add(txCurTime = crearCampo("Time"));
		panel.add(txFedTime = crearCampo("Hora de comer"));

		panel.add(txMeals = crearCampo("Comidas restantes"));
		panel.add(txFischN = crearCampo("Numero de peces"));

		colocarDatos();

		return panel;
	}

	private JLabel crearCampo(String titulo) {

		JLabel campo = new JLabel();
		campo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.PINK), titulo));
		return campo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {

		case "Refresh":

			if (sincronizarDatos() == true) {
				colocarDatos();
			}

			break;

		case "Sinc":

			sincronizarMicro();

			if (sincronizarDatos() == true) {
				colocarDatos();
			}

			break;

		case "Feed":

			interfaz.feed(DAOPecera.getCantidadPeces(p));
			JOptionPane.showMessageDialog(this, "Dando de comer", "Accion realizada", JOptionPane.INFORMATION_MESSAGE);

			if (sincronizarDatos() == true) {
				colocarDatos();
			}

			break;

		case "Calibrar":

			try {

				String a = JOptionPane.showInputDialog(this, "Nivel del ph del liquido");
				float phOff = Float.parseFloat(a);

				interfaz.setPhOffset(phOff);

				if (sincronizarDatos() == true) {
					colocarDatos();
				}

			} catch (NumberFormatException e2) {

				JOptionPane.showMessageDialog(this, "Introduce el numero correctamente", "Error Introduccion",
						JOptionPane.ERROR_MESSAGE);
			}

			break;

		case "Cancelar":

			this.dispose();

			break;

		default:
			break;
		}

	}

	public boolean sincronizarDatos() {

		if ((ph = interfaz.getPh()) != -1 && (phOff = interfaz.getPhOffset()) != -1 && (temp = interfaz.getTemp()) != -1
				&& (meal = interfaz.getMeals()) != -1 && (fishN = interfaz.getFishNo()) != -1
				&& (dateTime = interfaz.getDateTime()) != null && (feedTime = interfaz.getFeedTime()) != null) {

			return true;

		} else {

			JOptionPane.showMessageDialog(this, "Conexion Fallida", "Accion no realizada", JOptionPane.ERROR_MESSAGE);

			return false;
		}
	}

	public void colocarDatos() {

		float[] phValues = DAOPecera.getPhporPecera(p);
		float[] tmpValues = DAOPecera.getTempporPecera(p);

		txPH.setText(String.valueOf(ph));
		txPHOff.setText(String.valueOf(phOff));
		txTemp.setText(String.valueOf(temp));
		txMeals.setText(String.valueOf(meal));
		txFischN.setText(String.valueOf(fishN));

		txPHMin.setText(String.valueOf(phValues[0]));
		txPHMax.setText(String.valueOf(phValues[1]));

		txTempMin.setText(String.valueOf(tmpValues[0]));
		txTempMax.setText(String.valueOf(tmpValues[1]));

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
		String feedT = simpleDateFormat.format(feedTime.getTime());

		simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm");
		String dateT = simpleDateFormat.format(dateTime.getTime());

		txFedTime.setText(feedT);
		txCurTime.setText(dateT);

	}

	public void sincronizarMicro() {

		float ph[] = new float[2];
		float temp[] = new float[2];

		Calendar calendar = Calendar.getInstance();

		ph = DAOPecera.getPhporPecera(p);
		temp = DAOPecera.getTempporPecera(p);

		interfaz.setPhMin(ph[0]);
		interfaz.setPhMax(ph[1]);
		interfaz.setTempMin(temp[0]);
		interfaz.setTempMax(temp[1]);
		interfaz.setFishNo(DAOPecera.getCantidadPeces(p));
		interfaz.setDateTime(calendar);
		System.out.println("Ahora: "+ (calendar.getTimeInMillis()/1000 % 43200));
		calendar.setTime(p.getHoracomida());
		//calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY)-1);
		interfaz.setFeedTime(calendar);
		System.out.println("Horario: "+ (calendar.getTimeInMillis()/1000 % 43200));

		String a = JOptionPane.showInputDialog(this, "Introduce la cantidad de comidas que quedan disponibles");
		try {

			int meals = Integer.parseInt(a);
			interfaz.setMeals(meals);

		} catch (NumberFormatException e) {

			JOptionPane.showMessageDialog(this, "Introduce el numero correctamente", "Error Introduccion",
					JOptionPane.ERROR_MESSAGE);

		}
	}
}
