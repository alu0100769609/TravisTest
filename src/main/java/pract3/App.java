package main.java.pract3;

import java.time.LocalDateTime;

public class App {
  public static void main(String[] args) {
    /* printFrom31March1963(); */

    System.out.println("----");

    Fecha last_day_of_month = new Fecha(2019, 1, 31);
    System.out.println(last_day_of_month.siguienteMes());

    Fecha fecha1 = new Fecha(2012, 2, 28);
    System.out.println(fecha1);                 // Martes, 28 Feb 2012
    System.out.println(fecha1.siguienteDia());  // Miércoles, 29 Feb 2012
    System.out.println(fecha1.siguienteDia());  // Jueves, 1 Mar 2012
    System.out.println(fecha1.siguienteMes());  // Domingo, 1 Apr 2012
    System.out.println(fecha1.siguienteAnio()); // Lunes, 1 Apr 2013

    System.out.println("----");

    Fecha fecha2 = new Fecha(2012, 1, 2);
    System.out.println(fecha2);                 // Lunes, 2 Ene 2012
    System.out.println(fecha2.anteriorDia());   // Domingo, 1 Ene 2012
    System.out.println(fecha2.anteriorDia());   // Sábado, 31 Dic 2011
    System.out.println(fecha2.anteriorMes());   // Miércoles, 30 Nov 2011
    System.out.println(fecha2.anteriorAnio());  // Martes, 30 Nov 2010

    System.out.println("----");

    Fecha d3 = new Fecha(2012, 2, 29);
    System.out.println(d3.anteriorAnio());      // Lunes, 28 Feb 2011

    /* Fecha fecha4 = new Fecha(2099, 11, 31); */
    /* Fecha fecha5 = new Fecha(2011, 2, 29); */
  }

  public static void printFrom31March1963() {
    LocalDateTime d = LocalDateTime.now();
    Fecha today = new Fecha(d.getYear(), d.getMonthValue(), d.getDayOfMonth());
    Fecha march31 = new Fecha(1963, 3, 31);

    while (march31.compareTo(today) != 0)
      System.out.println(march31.siguienteDia());
  }
}
