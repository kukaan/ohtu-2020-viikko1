package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto epatyhjaVarasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        epatyhjaVarasto = new Varasto(10, 2);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
        
        epatyhjaVarasto.lisaaVarastoon(2.2);
        assertEquals(4.2, epatyhjaVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void NegatiivinenLisaysEiMuutaSaldoa() {
        varasto.lisaaVarastoon(-1);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ylisuuriLisaysAsettaaSaldonMaksimiin() {
        varasto.lisaaVarastoon(12);

        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);

        double saatuMaara2 = epatyhjaVarasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void negatiivinenOttaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(2);

        double saatuMaara = varasto.otaVarastosta(-2);

        assertEquals(0, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ylisuuriOttaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(2);

        double saatuMaara = varasto.otaVarastosta(4);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);

        epatyhjaVarasto.otaVarastosta(2);

        assertEquals(10, epatyhjaVarasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoEpatyhjanVaraston() {
        assertEquals(2, epatyhjaVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaEpatyhjallaVarastollaOikeaTilavuus() {
        assertEquals(10, epatyhjaVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoNegatiivisenTilanTyhjaksiVarastoksi() {
        Varasto negatiivinenTila1 = new Varasto(-1);
        Varasto negatiivinenTila2 = new Varasto(-1, -1);
        assertEquals(0, negatiivinenTila1.getSaldo(), vertailuTarkkuus);
        assertEquals(0, negatiivinenTila1.getSaldo(), vertailuTarkkuus);
        assertEquals(0, negatiivinenTila2.paljonkoMahtuu(), vertailuTarkkuus);
        assertEquals(0, negatiivinenTila2.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void uudellaYlitaydellaVarastollaOikeaSaldo() {
        Varasto ylitaysi = new Varasto(10,12);
        
        assertEquals(10, ylitaysi.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void toStringPalauttaaOikeanMuodon() {
        String s = epatyhjaVarasto.toString();
        
        assertEquals("saldo = 2.0, vielä tilaa 8.0", epatyhjaVarasto.toString());
    }
}