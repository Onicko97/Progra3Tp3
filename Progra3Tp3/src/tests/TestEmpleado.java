package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import model.Empleado;

public class TestEmpleado {

   
    @Test
    public void calificacion1EsValidaTest() {
        Empleado emp = new Empleado("Programador", 1, "Juan");
        assertEquals(1, emp.get_calificacionHistorica());
    }

    @Test
    public void calificacion5EsValidaTest() {
        Empleado emp = new Empleado("Programador", 5, "Juan");
        assertEquals(5, emp.get_calificacionHistorica());
    }

    @Test
    public void agregarIncompatibleTest() {
        Empleado juan = new Empleado("Programador", 3, "Juan");
        Empleado ana = new Empleado("Tester", 4, "Ana");
        juan.agregarIncompatible(ana);
        assertTrue(juan.getIncompatibles().contains(ana));
    }

    @Test
    public void incompatibilidadNoEsSimetricaTest() {
        Empleado juan = new Empleado("Programador", 3, "Juan");
        Empleado ana = new Empleado("Tester", 4, "Ana");
        juan.agregarIncompatible(ana);
        assertFalse(ana.getIncompatibles().contains(juan));
    }
}