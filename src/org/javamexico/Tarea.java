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

import org.javamexico.util.Utils;

/** Esta clase abstracta implementa Runnable y cada ejercicio va a tener
 * una version concreta que haga distintas cosas. La clase abstracta existe
 * solamente para simplificar el codigo de las subclases; aqui se hacen
 * algunas cosas en comun como guardar el indice de la tarea, permitir
 * que se reinicie, guardar algunas propiedades y realizar alguna operacion
 * que consuma tiempo de CPU para que se tarden en actualizarse las GUIs.
 * 
 * @author Enrique Zamudio
 */
public abstract class Tarea implements Runnable {

	protected int avance;
	private int idx;
	protected int lim;
	protected Owner owner;

	/** Crea una tarea con los parametros especificados.
	 * @param creador El objeto que va a controlar la tarea.
	 * @param indice El indice de la tarea (para distinguirlas cuando hay varias)
	 * @param limite El numero hasta el cual tiene que ir avanzando. */
	public Tarea(Owner creador, int indice, int limite) {
		owner = creador;
		idx = indice;
		lim = limite;
	}

	public void reset() {
		avance = 0;
	}
	public int getAvance() {
		return avance;
	}

	public void setIndice(int value) {
		idx = value;
	}
	public int getIndice() {
		return idx;
	}

	public void setLimite(int value) {
		lim = value;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	protected void hazAlgo() {
		//Para hacer mas rapido o mas lento, cambiar el numero de veces que
		//se realiza esto; 800 en un Core 2 Duo de 2.4GHz por ejemplo, o 300 en un CPU
		//Atom de 1.6GHz, etc.
		for (int i = 0; i < 300; i++) {
			long now = System.currentTimeMillis();
			Utils.esFibo(now);
		}
		owner.avanza(this);
	}

}
