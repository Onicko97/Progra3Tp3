package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import model.Requerimiento;
import java.security.InvalidParameterException;

public class TestRequerimiento {

    @Test(expected = InvalidParameterException.class)
    public void lideresNegativosTest() {
        new Requerimiento(-1, 0, 0, 0);
    }

    @Test(expected = InvalidParameterException.class)
    public void arquitectosNegativosTest() {
        new Requerimiento(0, -1, 0, 0);
    }

    @Test(expected = InvalidParameterException.class)
    public void programadoresNegativosTest() {
        new Requerimiento(0, 0, -1, 0);
    }

    @Test(expected = InvalidParameterException.class)
    public void testersNegativosTest() {
        new Requerimiento(0, 0, 0, -1);
    }

    @Test
    public void ceroEsValidoTest() {
        Requerimiento req = new Requerimiento(0, 0, 0, 0);
        assertEquals(0, req.getCantLideres());
    }

    @Test
    public void valoresCorrectosTest() {
        Requerimiento req = new Requerimiento(1, 2, 3, 4);
        assertEquals(1, req.getCantLideres());
        assertEquals(2, req.getCantArquitectos());
        assertEquals(3, req.getCantProgramadores());
        assertEquals(4, req.get_cantTesters());
    }
}