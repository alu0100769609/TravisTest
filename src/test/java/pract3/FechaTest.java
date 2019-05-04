package test.java.pract3;

/**
  * Class for testing the class Fecha
  *
  * @see Fecha
  *
  * @author David Afonso Dorta
  * @since 2019-02-19
  * e-mail: alu0101015255@ull.edu.es
  *
  */

import org.junit.jupiter.api.*;

import main.java.pract3.Fecha;

import static org.junit.jupiter.api.Assertions.*;

public class FechaTest {

  Fecha defaultDate_; // 01/01/0001

  @BeforeEach
  public void setUp() throws Exception {
    defaultDate_ = new Fecha();
  }

  @Test
  public void testYearIsLeap() {
    assertTrue(Fecha.esBisiesto(2020) == true);  // Divisible by 400
    assertTrue(Fecha.esBisiesto(1996) == true);  // Divisible by 4 and not 100
    assertTrue(Fecha.esBisiesto(1900) == false); // Divisible by 100

    assertTrue(true);
  }

  @Test
  public void testGetAnioAndSetAnio() {
    int newAnio = 1999;
    defaultDate_.setAnio(newAnio);
    assertEquals(defaultDate_.getAnio(), newAnio);
  }

  @Test
  public void setIllegalYear() {
    // Can't set a negative year
    assertThrows(IllegalStateException.class, () -> {
      defaultDate_.setAnio(-11);
    });

    // Can't set a year bigger than 9999
    assertThrows(IllegalStateException.class, () -> {
      defaultDate_.setAnio(10000);
    });
  }

  @Test
  public void testSetMesAndGetMes() {
    int newMes = 10;
    defaultDate_.setMes(newMes);
    assertEquals(defaultDate_.getMes(), newMes);
  }

  @Test
  public void setIllegalMonth() {
    // Can't set a negative month
    assertThrows(IllegalArgumentException.class, () -> {
      defaultDate_.setMes(-11);
    });

    // Can't set a month bigger than 12
    assertThrows(IllegalArgumentException.class, () -> {
      defaultDate_.setMes(13);
    });
  }

  @Test
  public void testSetDiaAndGetDia() {
    int newDia = 15;
    defaultDate_.setDia(newDia);
    assertEquals(defaultDate_.getDia(), newDia);
  }

  @Test
  public void setIllegalDay() {
    // Can't set a negative day
    assertThrows(IllegalArgumentException.class, () -> {
      defaultDate_.setDia(-11);
    });

    // Can't set day 31 if month has 30
    assertThrows(IllegalArgumentException.class, () -> {
      defaultDate_.setMes(4);       // April has 30 days
      defaultDate_.setDia(31);      // Throws exception
    });

    // Can set day 29 if month is February but year is not Leap
    assertAll(() -> {
      defaultDate_.setAnio(2020);   // Leap year
      defaultDate_.setMes(2);       // April has 30 days
      defaultDate_.setDia(29);      // Throws exception
    });
  }

  @Test
  public void testSetFecha() {
    // Can set a valid date
    defaultDate_.setFecha(2019, 2, 9);

    // Can't set an invalid date
    assertThrows(IllegalArgumentException.class, () -> {
      defaultDate_.setFecha(2019, 17, 3); // Invalid month
    });
  }

  @Test
  public void testGetDiaDeSemana() {
    // 0 is SUNDAY, not MONDAY
    Fecha sunday_date = new Fecha(2019, 2, 17);
    assertTrue(sunday_date.getDiaDeSemana() == 0);

    Fecha tuesday_date = new Fecha(2019, 2, 19);
    assertTrue(tuesday_date.getDiaDeSemana() == 2);

    Fecha saturday_date = new Fecha(2019, 2, 23);
    assertTrue(saturday_date.getDiaDeSemana() == 6);
  }

  @Test
  public void testToString() {
    Fecha tuesday_febrary = new Fecha(2019, 2, 19);
    assertEquals(tuesday_febrary.toString(), "Martes, 19 de febrero de 2019");

    Fecha sunday_dec = new Fecha(2019, 12, 22);
    assertEquals(sunday_dec.toString(), "Domingo, 22 de diciembre de 2019");
  }

  @Test
  public void testSiguienteDia() {
    // Test when moving to the next day also changes the month
    Fecha last_day_of_month = new Fecha(2019, 1, 31);
    last_day_of_month.siguienteDia();
    assertTrue(last_day_of_month.getDia() == 1);
    assertTrue(last_day_of_month.getMes() == 2); // Now in February

    // Test when moving to the next day also changes the year
    Fecha last_day_of_year = new Fecha(2019, 12, 31);
    last_day_of_year.siguienteDia();
    assertTrue(last_day_of_year.getDia() == 1);
    assertTrue(last_day_of_year.getMes() == 1);
    assertTrue(last_day_of_year.getAnio() == 2020);
  }

  @Test
  public void testAnteriorDia() {
    // Test when moving from 1 march to 29 february
    Fecha first_day_of_march = new Fecha(2020, 3, 1);
    first_day_of_march.anteriorDia();
    assertTrue(first_day_of_march.getDia() == 29);
    assertTrue(first_day_of_march.getMes() == 2);

    // Test when moving from 1 january to 31 december of previous year
    Fecha first_day_of_year = new Fecha(2020, 1, 1);
    first_day_of_year.anteriorDia();
    assertTrue(first_day_of_year.getDia() == 31);
    assertTrue(first_day_of_year.getMes() == 12);
    assertTrue(first_day_of_year.getAnio() == 2019);
  }

  @Test
  public void testSiguienteMes() {
    // Move from last day of january to last day of february (no leap year)
    Fecha last_day_of_month = new Fecha(2019, 1, 31);
    last_day_of_month.siguienteMes();
    assertTrue(last_day_of_month.getDia() == 28); // Last day is 28!
    assertTrue(last_day_of_month.getMes() == 2);

    // Same as before (last day of january) but testing when the year is leap
    last_day_of_month.setFecha(2020, 1, 31);
    last_day_of_month.siguienteMes();
    assertTrue(last_day_of_month.getDia() == 29); // Now last day is 29!
    assertTrue(last_day_of_month.getMes() == 2);

    // Testing when changing month also moves to the next year
    Fecha last_month_of_year = new Fecha(2019, 12, 31);
    last_month_of_year.siguienteMes();
    assertTrue(last_month_of_year.getDia() == 31);
    assertTrue(last_month_of_year.getMes() == 1);
    assertTrue(last_month_of_year.getAnio() == 2020);
  }

  @Test
  public void testAnteriorMes() {
    // Move from last of march (month with 31 days) to february on non leap year
    Fecha march = new Fecha(2019, 3, 31);
    march.anteriorMes();
    assertTrue(march.getDia() == 28); // Now day is capped to 28, not 31
    assertTrue(march.getMes() == 2);

    // Move from january to december
    Fecha january = new Fecha(2020, 1, 1);
    january.anteriorMes();
    assertTrue(january.getMes() == 12);
    assertTrue(january.getAnio() == 2019);
  }

  @Test
  public void testSiguienteAnio() {
    // Test change from leap to non-leap year
    Fecha last_day_of_february = new Fecha(2019, 2, 28);
    last_day_of_february.siguienteAnio();
    assertTrue(last_day_of_february.getAnio() == 2020);
    assertTrue(last_day_of_february.getDia() == 28);
  }

  @Test
  public void testAnteriorAnio() {
    // Test change from leap to non-leap year
    Fecha last_day_of_february = new Fecha(2020, 2, 29);
    last_day_of_february.anteriorAnio();
    assertTrue(last_day_of_february.getAnio() == 2019);
    assertTrue(last_day_of_february.getDia() == 28);
  }
}
