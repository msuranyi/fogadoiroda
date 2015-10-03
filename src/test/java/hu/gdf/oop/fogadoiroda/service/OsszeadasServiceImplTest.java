package hu.gdf.oop.fogadoiroda.service;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OsszeadasServiceImplTest {

    private OsszeadasService osszeadasService;

    @Before
    public void setup() {
        osszeadasService = new OsszeadasServiceImpl();
    }

    @Test
    public void testOsszead() {
        assertEquals(7, osszeadasService.osszead(3, 4).intValue());
        assertEquals(12, osszeadasService.osszead(3, 4, 5).intValue());
        assertEquals(11, osszeadasService.osszead(3, 4, 8, -4).intValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOsszeadEgyParameterrel() {
        assertEquals(3, osszeadasService.osszead(3).intValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOsszeadParameterNelkul() {
        assertEquals(3, osszeadasService.osszead().intValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOsszeadNullParameterrel() {
        assertEquals(3, osszeadasService.osszead(2, 1, null).intValue());
    }
}
