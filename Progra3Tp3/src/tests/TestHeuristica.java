package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import model.Empleado;
import model.Heuristica;
import java.util.ArrayList;
import java.util.List;

public class TestHeuristica {

    @Test
    public void encuentraEquipoValidoTest() {
        Heuristica h = new Heuristica();
        List<Empleado> resultado = h.resolver(equipoBasico(), 1, 0, 1, 0);
        assertEquals(2, resultado.size());
    }

    @Test
    public void listaVaciaDevuelveVacioTest() {
        Heuristica h = new Heuristica();
        List<Empleado> resultado = h.resolver(new ArrayList<>(), 0, 0, 0, 0);
        assertTrue(resultado.isEmpty());
    }

    @Test
    public void eligePrimeroAlMejorCalificadoTest() {
        Heuristica h = new Heuristica();
        List<Empleado> resultado = h.resolver(tresProgramadoresDistintaCalif(), 0, 0, 1, 0);
        assertEquals("Juan", resultado.get(0).get_nombre()); // juan es 5
    }

    @Test
    public void eligeElMejorSinMirarHaciaAdelanteTest() {
        Heuristica h = new Heuristica();
        List<Empleado> resultado = h.resolver(dosLideresUnArquitectoIncompat(), 1, 1, 0, 0);
        // la heurística elige a juan(5) pero después no puede completar el equipo
        // porque maria es incompatible con juan
        assertTrue(resultado.size() < 2);
    }

    @Test
    public void equipoIncompletoSiNoHayCompatiblesTest() {
        Heuristica h = new Heuristica();
        List<Empleado> resultado = h.resolver(dosLideresIncompatiblesEntreEllos(), 2, 0, 0, 0);
        //no puede armar el equipo completo
        assertTrue(resultado.size() < 2);
    }

    @Test
    public void noHayEmpleadosSuficientesTest() {
        Heuristica h = new Heuristica();
        List<Empleado> resultado = h.resolver(equipoBasico(), 2, 0, 0, 0);
        //pido 2 lideres pero solo hay 1
        assertTrue(resultado.size() < 2);
    }

    //auxiliares 
    private List<Empleado> equipoBasico() {
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado("Lider de proyecto", 5, "Juan"));
        empleados.add(new Empleado("Programador", 4, "Ana"));
        return empleados;
    }

    private List<Empleado> tresProgramadoresDistintaCalif() {
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado("Programador", 3, "Luis"));
        empleados.add(new Empleado("Programador", 5, "Juan"));
        empleados.add(new Empleado("Programador", 2, "Ana"));
        return empleados;
    }

    private List<Empleado> dosLideresUnArquitectoIncompat() {
        Empleado juan = new Empleado("Lider de proyecto", 5, "Juan");
        Empleado ana = new Empleado("Lider de proyecto", 3, "Ana");
        Empleado maria = new Empleado("Arquitecto", 4, "Maria");
        juan.agregarIncompatible(maria);
        maria.agregarIncompatible(juan);
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(juan);
        empleados.add(ana);
        empleados.add(maria);
        return empleados;
    }

    private List<Empleado> dosLideresIncompatiblesEntreEllos() {
        Empleado juan = new Empleado("Lider de proyecto", 5, "Juan");
        Empleado ana = new Empleado("Lider de proyecto", 4, "Ana");
        juan.agregarIncompatible(ana);
        ana.agregarIncompatible(juan);
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(juan);
        empleados.add(ana);
        return empleados;
    }
}
