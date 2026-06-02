package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingWorker;

import presenter.SoftwareFactoryPresenter;

import model.Empleado; // <--- Importante si Hilo y Empleado están en carpetas distintas
import model.MejorCalificado;

public class Hilo extends SwingWorker<Integer, Integer>{
	//1er parametro = lo que devuelve doInBackground al terminar
	//2do parametro = lo que se devuelve paso por paso a informar

	private SoftwareFactoryPresenter _presenter;
	private GestorEmpleados _gestor;
	
	public Hilo(SoftwareFactoryPresenter presenter, GestorEmpleados gestor) {
		this._presenter = presenter;
		this._gestor = gestor;
	}
	
	@Override
	protected Integer doInBackground() throws Exception {
		for (int i = 0; i < 100; i++) {
			publish(new Integer[] {i});		//para probar hice que cuente a 100
			Thread.sleep(10);
		}
		return 0;
	}
	
	@Override
	protected void done() {
		
		//para probar por consola
	    if (_presenter != null && _gestor != null) {
	        
	        List<String> reqStrings = _gestor.getStringsRequerimientos();
	        if (reqStrings == null || reqStrings.isEmpty()) {
	            return;
	        }
	        MejorCalificado modelo = new MejorCalificado();
	        List<Empleado> todosLosEmpleados = new ArrayList<>(_gestor.getListaEmpleados().values());
	       
	        int reqLid  = Integer.parseInt(reqStrings.get(0));
	        int reqArq  = Integer.parseInt(reqStrings.get(1));
	        int reqProg = Integer.parseInt(reqStrings.get(2));
	        int reqTest = Integer.parseInt(reqStrings.get(3));
	        
	        List<Empleado> equipoGanador = modelo.resolver(todosLosEmpleados, reqLid, reqArq, reqProg, reqTest);
	        
	        _presenter.recibirEquipoResultante(equipoGanador);
	    }
	}
	@Override
	protected void process(List<Integer> lista) {
		for (Integer integer : lista) {
			System.out.println(integer);
		}
	}

}
