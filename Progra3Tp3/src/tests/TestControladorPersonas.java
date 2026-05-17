package tests;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.security.InvalidParameterException;

import org.junit.jupiter.api.Test;

import model.ControladorPersonas;

class TestControladorPersonas {

	@Test
	void agregarPersonaSinNombreTest() {
		assertThrows(InvalidParameterException.class, ()->{
			ControladorPersonas controlador = new ControladorPersonas();
			controlador.agregarPersona("", "tecnico", 5);
		});
	}
	
	@Test
	void agregarPersonaSinRolTest() {
		assertThrows(InvalidParameterException.class, ()->{
			ControladorPersonas controlador = new ControladorPersonas();
			controlador.agregarPersona("Juan", "", 5);
		});
	}
	
	@Test
	void agregarPersonaConCalificaionInvalidaMayorTest() {
		assertThrows(InvalidParameterException.class, ()->{
			ControladorPersonas controlador = new ControladorPersonas();
			controlador.agregarPersona("Juan", "tecnico", 6);
		});
	}
	
	@Test
	void agregarPersonaConCalificaionInvalidaMenorTest() {
		assertThrows(InvalidParameterException.class, ()->{
			ControladorPersonas controlador = new ControladorPersonas();
			controlador.agregarPersona("Juan", "tecnico", 0);
		});
	}

}
