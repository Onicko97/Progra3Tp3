package tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import model.Empleado;
import model.MejorCalificado;

public class TestMejorCalificado {

    @Test
    public void encuentraEquipoValidoTest() {
        MejorCalificado mc = new MejorCalificado();
        List<Empleado> resultado = mc.resolver(equipoBasico(), 1, 0, 1, 0);
        assertEquals(2, resultado.size());
    }

    @Test
    public void sinSolucionPorIncompatibilidadesTest() {
        MejorCalificado mc = new MejorCalificado();
        List<Empleado> resultado = mc.resolver(dosLideresIncompatibles(), 2, 0, 0, 0);
        assertTrue(resultado.isEmpty());
    }

    @Test
    public void eligeMayorCalificacionTest() {
        MejorCalificado mc = new MejorCalificado();
        List<Empleado> resultado = mc.resolver(tresProgramadoresDistintaCalif(), 0, 0, 2, 0);
        //stream para obtener el nombre sin equals
        boolean contieneAJuana = resultado.stream()
        	    .anyMatch(e -> e.get_nombre().equals("Juana"));
        	assertTrue(contieneAJuana);
    }

    @Test
    public void noIncluyeIncompatiblesTest() {
        MejorCalificado mc = new MejorCalificado();
        List<Empleado> resultado = mc.resolver(tresProgramadoresConIncompat(), 0, 0, 2, 0);
        //Juana y Ana no pueden estar juntas
        assertFalse(resultado.contains(programador("Juana", 5)) 
                 && resultado.contains(programador("Ana", 4)));
    }

    @Test
    public void listaVaciaDevuelveVacioTest() {
        MejorCalificado mc = new MejorCalificado();
        List<Empleado> resultado = mc.resolver(new ArrayList<>(), 0, 0, 0, 0);
        assertTrue(resultado.isEmpty());
    }
    @Test
    public void noHayEmpleadosSuficientesParaElRolTest() {
        // pido 2 lideres pero solo hay 1
        MejorCalificado mc = new MejorCalificado();
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado("Lider de proyecto", 5, "Juana"));
        List<Empleado> resultado = mc.resolver(empleados, 2, 0, 0, 0);
        assertTrue(resultado.isEmpty());
    }

    @Test
    public void eligeExactamenteElEquipoJustoTest() {
        //no hay opciones
        MejorCalificado mc = new MejorCalificado();
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado("Lider de proyecto", 3, "Juana"));
        empleados.add(new Empleado("Programador", 2, "Ana"));
        List<Empleado> resultado = mc.resolver(empleados, 1, 0, 1, 0);
        assertEquals(2, resultado.size());
    }
    
    @Test
    public void eligeMejorEquipoEntreVariasOpcionesTest() {
        // juana(5) y pedro(5) deben ganarle a ana(2) + luis(3)
        MejorCalificado mc = new MejorCalificado();
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado("Programador", 5, "Juana"));
        empleados.add(new Empleado("Programador", 5, "Pedro"));
        empleados.add(new Empleado("Programador", 2, "Ana"));
        empleados.add(new Empleado("Programador", 3, "Luis"));
        List<Empleado> resultado = mc.resolver(empleados, 0, 0, 2, 0);
        boolean juanaEsta = false;
        boolean pedroEsta = false;
        for (Empleado e : resultado) {
            if (e.get_nombre().equals("Juana")) juanaEsta = true;
            if (e.get_nombre().equals("Pedro")) pedroEsta = true;
        }
        assertTrue(juanaEsta && pedroEsta);
    }

    @Test
    public void eligeMejorEquipoCompatibleAunqueNoSeaElAbsolutoTest() {
        //juana(5) es la mejor pero es incompatible con el único arquitecto
        //el resultado debe ser el segundo mejor compatible
        MejorCalificado mc = new MejorCalificado();
        Empleado juana = new Empleado("Lider de proyecto", 5, "Juana");
        Empleado arq = new Empleado("Arquitecto", 4, "Maria");
        juana.agregarIncompatible(arq);
        arq.agregarIncompatible(juana);
        Empleado ana = new Empleado("Lider de proyecto", 3, "Ana");
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(juana);
        empleados.add(ana);
        empleados.add(arq);
        List<Empleado> resultado = mc.resolver(empleados, 1, 1, 0, 0);
        boolean anaEsta = false;
        boolean mariaEsta = false;
        for (Empleado e : resultado) {
            if (e.get_nombre().equals("Ana")) anaEsta = true;
            if (e.get_nombre().equals("Maria")) mariaEsta = true;
        }
        assertTrue(anaEsta && mariaEsta);
    }

    //auxiliares

    private List<Empleado> equipoBasico() {
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado("Lider de proyecto", 5, "Juana"));
        empleados.add(new Empleado("Programador", 4, "Ana"));
        return empleados;
    }

    private List<Empleado> dosLideresIncompatibles() {
        Empleado juana = new Empleado("Lider de proyecto", 5, "Juana");
        Empleado ana = new Empleado("Lider de proyecto", 4, "Ana");
        juana.agregarIncompatible(ana);
        ana.agregarIncompatible(juana);
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(juana);
        empleados.add(ana);
        return empleados;
    }

    private List<Empleado> tresProgramadoresDistintaCalif() {
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado("Programador", 5, "Juana"));
        empleados.add(new Empleado("Programador", 2, "Ana"));
        empleados.add(new Empleado("Programador", 3, "Luis"));
        return empleados;
    }

    private List<Empleado> tresProgramadoresConIncompat() {
        Empleado juana = new Empleado("Programador", 5, "Juana");
        Empleado ana = new Empleado("Programador", 4, "Ana");
        juana.agregarIncompatible(ana);
        ana.agregarIncompatible(juana);
        Empleado luis = new Empleado("Programador", 3, "Luis");
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(juana);
        empleados.add(ana);
        empleados.add(luis);
        return empleados;
    }

    private Empleado programador(String nombre, int calificacion) {
        return new Empleado("Programador", calificacion, nombre);
    }
}