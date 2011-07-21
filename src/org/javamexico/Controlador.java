/*
Este archivo es parte de PCJ (Proyecto Certificate en Java).

PCJ es software libre: lo puedes redistribuir y/o modificar
bajo los terminos de la GNU General Public License como fue
publicada por la Free Software Foundation, ya sea la version
3 o (a tu eleccion) una version mas reciente.

Este codigo se distribuye con la esperanza de que sea util,
pero SIN NINGUNA GARANTIA; ni siquiera bajo la garantia de
que sea comercializable o apto para un proposito en
particular. Para mas detalles, ver la GPL en el sitio

http://www.gnu.org/licenses

(C)Enrique Zamudio Lopez 2010
*/
package org.javamexico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

/** Control principal del programa. Presenta la GUI con una barra de progreso
 * y un boton de interrupcion por cada tarea que va a correr.
 * Es una clase abstracta porque cada ejercicio la extiende, implementando
 * algunos metodos de manera distinta pero la GUI siempre se debe actualizar
 * de la misma forma.
 * 
 * @author Enrique Zamudio
 */
public abstract class Controlador implements Runnable, Owner, ActionListener {

	public static int LIMITE = 3000;

	protected final JFrame vent;
	protected final ArrayList<Thread> hilos = new ArrayList<Thread>();
	private final ArrayList<JProgressBar> guis = new ArrayList<JProgressBar>();

	public Controlador(String titulo, int hijos) {
		vent = new JFrame(titulo);

		Box vbox = Box.createVerticalBox();
		vbox.add(Box.createVerticalStrut(10));

		//Agregar hijos al vbox
		for (int i = 0; i < hijos; i++) {
			//Crear la GUI de cada tarea
			Box hbox = Box.createHorizontalBox();
			JProgressBar bar = new JProgressBar(0, LIMITE);
			JButton boton = new JButton("Interrumpir");
			boton.addActionListener(this);
			boton.setActionCommand(Integer.toString(i));
			bar.setValue(0);
			guis.add(bar);
			hbox.add(bar);
			hbox.add(boton);
			vbox.add(hbox);
		}

		//Botones de inicio y fin del programa
		final JButton bstart = new JButton("Arrancar");
		final JButton bclose = new JButton("Terminar");
		bclose.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev) {
				vent.setVisible(false);
				System.exit(1);
			}
		});
		bstart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				run();
			}
		});
		Box hbox = Box.createHorizontalBox();
		hbox.add(bstart);
		hbox.add(bclose);
		vbox.add(Box.createVerticalStrut(10));
		vbox.add(hbox);
		vbox.add(Box.createVerticalStrut(10));
		vent.getContentPane().add(vbox);
		vent.pack();
		vent.setSize(800, (int)vent.getSize().getHeight());
		vent.setVisible(true);
		vent.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				System.out.println("Saliendo porque cierran ventana");
				System.exit(0);
			}
		});
	}

	protected int getCuantosHijos() {
		return guis.size();
	}

	protected JProgressBar getGui(int indice) {
		return guis.get(indice);
	}

	/** Las tareas invocan este metodo para refrescar la GUI. */
	public synchronized void avanza(Tarea t) {
		JProgressBar bar = guis.get(t.getIndice());
		bar.setValue(t.getAvance());
	}

	/** Las subclases deben implementar este metodo para crear los Threads
	 * que van a correr en el programa. */
	protected abstract Thread crearHilo(int idx);

	public void run() {
		//Detenemos los threads que haya corriendo
		int cuantos = getCuantosHijos();
		if (hilos.size() > 0) {
			for (Thread t : hilos) {
				t.interrupt();
			}
			hilos.clear();
		}
		//Creamos nuevos Threads y reiniciamos la GUI
		for (int i = 0; i < cuantos; i++) {
			Thread t = crearHilo(i);
			hilos.add(t);
			JProgressBar gui = guis.get(i);
			gui.setValue(0);
			gui.setStringPainted(false);
		}
		//Arrancamos los nuevos hilos
		for (Thread t : hilos) {
			t.start();
		}
	}

	/** Este metodo lo invocan los botones de interrupcion. */
	@Override
	public void actionPerformed(ActionEvent e) {
		//Aqui determinamos a que barra corresponde el boton
		int cual = Integer.parseInt(e.getActionCommand());
		if (cual >= guis.size()) {
			System.out.printf("Action invalida! %d y solamente tengo %d hilos%n", cual, guis.size());
			return;
		}
		//Obtenemos la barra y el Thread correspondientes
		JProgressBar gui = guis.get(cual);
		if (hilos.size() == 0) {
			gui.setString("No corre aun...");
			gui.setStringPainted(true);
			return;
		}
		Thread t = hilos.get(cual);
		if (t.isAlive()) {
			//Detenemos el Thread
			hilos.get(cual).interrupt();
			gui.setString("Interrumpido!");
			gui.setStringPainted(true);
		} else {
			gui.setString("No puedo interrumpir");
			gui.setStringPainted(true);
		}
	}

}
