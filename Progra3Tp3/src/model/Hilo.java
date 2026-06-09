package model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingWorker;
import presenter.SoftwareFactoryPresenter;
//1er parametro = lo que devuelve doInBackground al terminar
//2do parametro = lo que se devuelve paso por paso a informar
public class Hilo extends SwingWorker<Void, Integer>{

	private SoftwareFactoryPresenter _presenter;
	private GestorEmpleados _gestor;
	private List<Empleado> resultado;
	
	public Hilo(SoftwareFactoryPresenter presenter, GestorEmpleados gestor) {
		this._presenter = presenter;
		this._gestor = gestor;
	}
	
	@Override
	protected Void doInBackground() throws Exception {
	    List<String> reqStrings = _gestor.getStringsRequerimientos();
	    List<Empleado> todosLosEmpleados = new ArrayList<>(_gestor.getListaEmpleados().values());

	    int reqLid  = Integer.parseInt(reqStrings.get(0));
	    int reqArq  = Integer.parseInt(reqStrings.get(1));
	    int reqProg = Integer.parseInt(reqStrings.get(2));
	    int reqTest = Integer.parseInt(reqStrings.get(3));

	    _gestor.ejecutarBacktracking(todosLosEmpleados, reqLid, reqArq, reqProg, reqTest);
	    _gestor.ejecutarHeuristica(todosLosEmpleados, reqLid, reqArq, reqProg, reqTest);

	    return null;
	}

	@Override
	protected void done() {
	    if (_presenter != null) {
	        _presenter.equipoCalculadoExitosamente();
	    }
	}
}	