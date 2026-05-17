package tests;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.security.InvalidParameterException;

import org.junit.jupiter.api.Test;

import model.GestorEmpleados;

class TestGestorPersonas {

	@Test
	void agregarEmpleadoSinNombreTest() {
		assertThrows(InvalidParameterException.class, ()->{
			GestorEmpleados gestor = new GestorEmpleados();
			gestor.agregarEmpleado("", "tecnico", 5);
		});
	}
	
	@Test
	void agregarEmpleadoSinRolTest() {
		assertThrows(InvalidParameterException.class, ()->{
			GestorEmpleados gestor = new GestorEmpleados();
			gestor.agregarEmpleado("Juan", "", 5);
		});
	}
	
	@Test
	void agregarEmpleadoConCalificaionInvalidaMayorTest() {
		assertThrows(InvalidParameterException.class, ()->{
			GestorEmpleados gestor = new GestorEmpleados();
			gestor.agregarEmpleado("Juan", "tecnico", 6);
		});
	}
	
	@Test
	void agregarEmpleadoConCalificaionInvalidaMenorTest() {
		assertThrows(InvalidParameterException.class, ()->{
			GestorEmpleados gestor = new GestorEmpleados();
			gestor.agregarEmpleado("Juan", "tecnico", 0);
		});
	}

}
