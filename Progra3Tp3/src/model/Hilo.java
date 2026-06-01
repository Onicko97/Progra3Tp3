package model;

import java.util.List;

import javax.swing.SwingWorker;

public class Hilo extends SwingWorker<Integer, Integer>{
	//1er parametro = lo que devuelve doInBackground al terminar
	//2do parametro = lo que se devuelve paso por paso a informar

	public Hilo() {
		
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
        
        System.out.println("El proceso en segundo plano ha terminado.");
    }
	
	@Override
	protected void process(List<Integer> lista) {
		for (Integer integer : lista) {
			System.out.println(integer);
		}
	}

}
