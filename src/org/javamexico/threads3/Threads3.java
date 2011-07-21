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
package org.javamexico.threads3;

import org.javamexico.Controlador;
import org.javamexico.Tarea;

/** Este programa corre tareas que se esperan despues de un tramo como en el programa 2, pero al interrumpir
 * una las demas ya no pueden continuar.
 * 
 * @author Enrique Zamudio
 * 
 */
public class Threads3 extends Controlador {

	private Tarea previa;
	private int fragmento;

	public Threads3(String t, int h) {
		super(t, h);
	}

	@Override
	protected Thread crearHilo(int idx) {
		if (fragmento == 0) {
			fragmento = Controlador.LIMITE / getCuantosHijos();
		}
		Tarea nuevo = new Tarea3A(this, idx, Controlador.LIMITE, idx * fragmento, previa);
		previa = nuevo;
		return new Thread(nuevo, String.format("Hilo-%d", idx));
	}

	public static void main(String[] args) {
		int hijos = args.length > 0 ? Integer.parseInt(args[0]) : 10;
		new Threads3("Ejemplo 3: Sleep", hijos);
	}

}
