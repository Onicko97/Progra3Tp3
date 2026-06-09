package model;

import java.util.ArrayList;
import java.util.List;
//algoritmo goloso
public class Heuristica {

    private List<Empleado> lideres;
    private List<Empleado> arquitectos;
    private List<Empleado> testers;
    private List<Empleado> programadores;
    private long tiempoTotal;

    public Heuristica() {
        this.lideres = new ArrayList<>();
        this.arquitectos = new ArrayList<>();
        this.testers = new ArrayList<>();
        this.programadores = new ArrayList<>();
    }

    public List<Empleado> resolver(List<Empleado> empleados, int reqLid, int reqArq, int reqProg, int reqTest) {
        this.lideres.clear();
        this.arquitectos.clear();
        this.programadores.clear();
        this.testers.clear();

        long inicio = System.currentTimeMillis();

        separarEmpleadosPorRol(empleados);

        //ordenar cada lista por calificación descendente con lambdas
        lideres.sort((a, b) -> b.get_calificacionHistorica() - a.get_calificacionHistorica());
        arquitectos.sort((a, b) -> b.get_calificacionHistorica() - a.get_calificacionHistorica());
        programadores.sort((a, b) -> b.get_calificacionHistorica() - a.get_calificacionHistorica());
        testers.sort((a, b) -> b.get_calificacionHistorica() - a.get_calificacionHistorica());

        List<Empleado> equipo = new ArrayList<>();
        equipo.addAll(elegir(lideres, reqLid, equipo));
        equipo.addAll(elegir(arquitectos, reqArq, equipo));
        equipo.addAll(elegir(programadores, reqProg, equipo));
        equipo.addAll(elegir(testers, reqTest, equipo));

        tiempoTotal = System.currentTimeMillis() - inicio;

        return equipo;
    }

    private List<Empleado> elegir(List<Empleado> candidatos, int requeridos, List<Empleado> equipoActual) {
        List<Empleado> elegidos = new ArrayList<>();
        for (Empleado candidato : candidatos) {
            if (elegidos.size() == requeridos) break;
            if (esCompatibleConEquipo(candidato, equipoActual) && esCompatibleConEquipo(candidato, elegidos)) {
                elegidos.add(candidato);
            }
        }
        return elegidos;
    }

    private boolean esCompatibleConEquipo(Empleado candidato, List<Empleado> equipo) {
        for (Empleado e : equipo) {
            if (candidato.getIncompatibles().contains(e)) return false;
        }
        return true;
    }

    private void separarEmpleadosPorRol(List<Empleado> empleados) {
        for (Empleado e : empleados) {
            switch (e.get_rol()) {
                case "Tester":
                    testers.add(e);
                    break;
                case "Programador":
                    programadores.add(e);
                    break;
                case "Arquitecto":
                    arquitectos.add(e);
                    break;
                case "Lider de proyecto":
                    lideres.add(e);
                    break;
            }
        }
    }

    public long getTiempoTotal() { return tiempoTotal; }
}