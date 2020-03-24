/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Eelinki
 */
public class KassapaateTest {
    
    Kassapaate kassa;
    
    public KassapaateTest() {
        kassa = new Kassapaate();
    }
    
    @Test
    public void kassaAlussaOikein() {
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    // Käteisostot
    @Test
    public void edullistenLounaidenKateisMaksuOk() {
        assertEquals(60, kassa.syoEdullisesti(300));
        assertEquals(100240, kassa.kassassaRahaa());
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullistenLounaidenKateisMaksuEiOk() {
        assertEquals(220, kassa.syoEdullisesti(220));
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaidenLounaidenKateisMaksuOk() {
        assertEquals(50, kassa.syoMaukkaasti(450));
        assertEquals(100400, kassa.kassassaRahaa());
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maukkaidenLounaidenKateisMaksuEiOk() {
        assertEquals(320, kassa.syoMaukkaasti(320));
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    // Korttiostot
    @Test
    public void edullistenLounaidenKorttiMaksuOk() {
        Maksukortti kortti = new Maksukortti(1000);
        
        assertTrue(kassa.syoEdullisesti(kortti));
        assertEquals(760, kortti.saldo());
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullistenLounaidenKorttiMaksuEiOk() {
        Maksukortti kortti = new Maksukortti(100);
        
        assertFalse(kassa.syoEdullisesti(kortti));
        assertEquals(100, kortti.saldo());
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaidenLounaidenKorttiMaksuOk() {
        Maksukortti kortti = new Maksukortti(1000);
        
        assertTrue(kassa.syoMaukkaasti(kortti));
        assertEquals(600, kortti.saldo());
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maukkaidenLounaidenKorttiMaksuEiOk() {
        Maksukortti kortti = new Maksukortti(100);
        
        assertFalse(kassa.syoMaukkaasti(kortti));
        assertEquals(100, kortti.saldo());
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kortilleVoiLadataRahaa() {
        Maksukortti kortti = new Maksukortti(100);
        
        kassa.lataaRahaaKortille(kortti, 300);
        assertEquals(400, kortti.saldo());
        assertEquals(100300, kassa.kassassaRahaa());
    }
    
    @Test
    public void kortilleEiVoiLadataNegatiivista() {
        Maksukortti kortti = new Maksukortti(1000);
        
        kassa.lataaRahaaKortille(kortti, -300);
        assertEquals(1000, kortti.saldo());
        assertEquals(100000, kassa.kassassaRahaa());
    }
}
