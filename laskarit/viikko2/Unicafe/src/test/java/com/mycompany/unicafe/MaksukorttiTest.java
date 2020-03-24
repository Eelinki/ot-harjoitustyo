package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void kortilleVoiLadataRahaa() {
        kortti.lataaRahaa(20);
        assertEquals(30, kortti.saldo());
    }
    
    @Test
    public void saldoVaheneeOikein() {
        kortti.otaRahaa(6);
        assertEquals(4, kortti.saldo());
    }
    
    @Test
    public void saldoEiMeneNegatiiviseksi() {
        kortti.otaRahaa(11);
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void palauttaaTrueKunMaksuOnnistuu() {
        assertTrue(kortti.otaRahaa(6));
    }
    
    @Test
    public void palauttaaFalseKunMaksuEiOnnistu() {
        assertFalse(kortti.otaRahaa(12));
    }
    
    @Test
    public void toStringToimiiOikein() {
        kortti.lataaRahaa(200);
        assertEquals("saldo: 2.10", kortti.toString());
    }
}
