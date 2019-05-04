/**
  * Class Fecha for representing and operating with dates
  *
  * @see TestFecha
  *
  * @author David Afonso Dorta
  * @since 2019-02-19
  * e-mail: alu0101015255@ull.edu.es
  *
  */

package main.java.pract3;

public class Fecha implements Comparable<Fecha> {
  // Attributes
  private int year_ = 1;
  private int month_ = 1;
  private int day_ = 1;

  // Constants
  private static final int MIN_YEAR = 1;
  private static final int MAX_YEAR = 9999;
  private static final int MAX_MONTH = 12;

  private static final String[] DAYS_OF_WEEK =
  {"Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"};
  private static final String[] MONTHS = {
    "", "enero", "febrero", "marzo", "abril", "mayo", "junio", "julio",
    "agosto", "septiembre", "octubre", "noviembre", "diciembre"
  };


  // Constructors

  public Fecha() {}
  public Fecha(int year, int month, int day) {
    setFecha(year, month, day);
  }

  /**
    * Checks if the given date is valid before changing the attributes.
    */
  public void setFecha(int year, int month, int day) {
    if (Fecha.esFechaValida(year, month, day)) {
      year_ = year;
      month_ = month;
      day_ = day;
    }
  }


  // Getters

  public int getAnio() {
    return year_;
  }

  public int getMes() {
    return month_;
  }

  public int getDia() {
    return day_;
  }

  /**
    * Get the corresponding day of week, starting by 0 [SUNDAY] and ending by 6
    * [SATURDAY]. Source of the algorithm:
    * https://www.geeksforgeeks.org/tomohiko-sakamotos-algorithm-finding-day-week/
    */
  public static int getDiaDeSemana(int year, int month, int day) {
    // Array with leading number of days values
    int t[] = { 0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4 };

    // If month is less than 3 reduce year by 1
    if (month < 3)
      year -= 1;

    return (year + year / 4 - year / 100 + year / 400 +
            t[month - 1] + day) % 7;
  }

  public int getDiaDeSemana() {
    return Fecha.getDiaDeSemana(year_, month_, day_);
  }


  // Setters

  public void setAnio(int year) {
    if (isAnioValido(year))
      year_ = year;
  }

  public void setMes(int month) {
    if (isMesValido(month))
      month_ = month;
  }

  public void setDia(int day) {
    if (isDiaValido(year_, month_, day))
      day_ = day;
  }


  // Operators

  /**
    * Change the date of this instance to the next valid day.
    * EDGE CASES:
    *   - Last day of month (must change month)
    *   - Last day of year (must change month and year)
    *   - Change to day 29 on leap years, next month on non leap years.
    */
  public Fecha siguienteDia() {
    try {
      setDia(day_ + 1);

      // If day > 1, wrap to next month and set day to the 1st
    } catch (IllegalArgumentException e1) {
      setDia(1); // First day of next month
      siguienteMes();
    }

    return this;
  }

  /**
    * Change the date of this instance to the previous valid day.
    * EDGE CASES:
    *   - First day of month (must change month)
    *   - First day of january (must change month and year)
    *   - First day of march (change to 29 on leap years, 28 on non leap years)
    */
  public Fecha anteriorDia() {
    try {
      setDia(day_ - 1);

      // If day < 1, wrap to previous month and set the day as the last of the
      // month
    } catch (IllegalArgumentException e1) {
      anteriorMes();
      setDia(getDaysInMonth(year_, month_));
    }

    return this;
  }

  /**
    * Change the date of this instance to the next month, capping the day if
    * necessary.
    * EDGE CASES:
    *   - Last day of month (must cap day. Example: 31 january -> 28 february on
    *   non leap years. 31 may -> 30 june)
    *   - December (last month of year, must change year)
    */
  public Fecha siguienteMes() {
    try {
      setMes(month_ + 1);

      // If month > 12 (December), wrap to January and add 1 year
    } catch (IllegalArgumentException e) {
      setMes(1);
      setAnio(year_ + 1);
    }

    try {
      isDiaValido(year_, month_, day_);

      // If after changing month the date isn't valid (31 in a 30-day month, for
      // example) set the day to the closest max day (30, 29 or 28)
    } catch (IllegalArgumentException e) {
      setDia(Fecha.getDaysInMonth(year_, month_));
    }

    return this;
  }

  /**
    * Change the date of this instance to the previous month, capping the day if
    * necessary.
    * EDGE CASES:
    *   - Last day of month (must cap day. Example: 31 march -> 28 february on
    *   non leap years. 31 may -> 30 april)
    *   - January (first month of year, must change year)
    */
  public Fecha anteriorMes() {
    try {
      setMes(month_ - 1);

      // If month < 1 (January), wrap to December and substract 1 year
    } catch (IllegalArgumentException e) {
      setMes(12);
      setAnio(year_ - 1);
    }

    try {
      isDiaValido(year_, month_, day_);

      // If after changing month the date isn't valid (31 in a 30-day month, for
      // example) set the day to the closest max day (30, 29 or 28)
    } catch (IllegalArgumentException e) {
      setDia(Fecha.getDaysInMonth(year_, month_));
    }

    return this;
  }

  /**
    * Change the date of this instance to the next year.
    * EDGE CASES:
    *   - Change from leap year to non leap year on 29 february
    */
  public Fecha siguienteAnio() {
    setAnio(year_ + 1);

    try {
      Fecha.isDiaValido(year_, month_, day_);

      // This exception is only thrown when changing from leap to non leap
      // year on 29 feb
    } catch (IllegalArgumentException e) {
      setDia(28);
    }

    return this;
  }

  /**
    * Change the date of this instance to the previous year.
    * EDGE CASES:
    *   - Change from leap year to non leap year on 29 february
    */
  public Fecha anteriorAnio() {
    setAnio(year_ - 1);

    try {
      Fecha.isDiaValido(year_, month_, day_);

      // This exception is only thrown when changing from leap to non leap
      // year on 29 feb
    } catch (IllegalArgumentException e) {
      setDia(28);
    }

    return this;
  }

  /**
    * Comparing objects of class Fecha: year -> month -> day.
    */
  @Override
  public int compareTo(Fecha rhs) {
    int result = Integer.compare(year_, rhs.year_);
    if (result == 0) {
      result = Integer.compare(month_, rhs.month_);
      if (result == 0) {
        result = Integer.compare(day_, rhs.day_);
      }
    }

    return result;
  }


  // Date validation methods

  /**
    * Tests if the given date is a valid date (returning true). If not, throws
    * IllegalArgumentException.
    */
  public static boolean esFechaValida(int year, int month, int day) {
    try {
      Fecha.isAnioValido(year);
      Fecha.isMesValido(month);
      Fecha.isDiaValido(year, month, day);
      return true;

    } catch (IllegalArgumentException | IllegalStateException e) {
      // Rethrowing exception, just for changing the message
      throw new IllegalArgumentException("día, mes o año no válido");
    }
  }

  /**
    * Test if the year is between the min and max valid year. NOTE: This
    * function throws an exception of type IllegalStateException, not
    * IllegalArgumentException as the other methods.
    */
  private static boolean isAnioValido(int year) {
    if (year >= MIN_YEAR && year <= MAX_YEAR)
      return true;
    else
      throw new IllegalStateException("año no válido!");
  }

  /**
    * Test if the month is between the min and max valid day.
    */
  private static boolean isMesValido(int month) {
    if (month >= 1 && month <= MAX_MONTH)
      return true;
    else
      throw new IllegalArgumentException("mes no válido!");
  }

  /**
    * Test if the day is between the min and max valid day. Month and Year
    * parameters are necessary to calculate the number of days that the given
    * month has.
    */
  private static boolean isDiaValido(int year, int month, int day) {

    if (day >= 1 && day <= Fecha.getDaysInMonth(year, month))
      return true;
    else
      throw new IllegalArgumentException("dia no válido!");
  }

  /**
    * Returns true or false whether the year is leap or non leap.
    */
  public static boolean esBisiesto(int year) {
    if ((year % 400) == 0)
      return true;

    if ((year % 4) == 0 && (year % 100) != 0)
      return true;

    return false;
  }


  // Helper methods

  /**
    * Returns the number of days that the month has (31, 30, 29 or 28)
    */
  private static int getDaysInMonth(int year, int month) {
    // Formula to get the days that the given month has
    return (month == 2) ? (28 + (Fecha.esBisiesto(year) ? 1 : 0))
           : 31 - (((month - 1) % 7) % 2);
  }


  // Transform methods

  /**
    * Transform the date to string.
    * FORMAT: $(DayOfWeek) $(Day) de $(Month_String) de $(Year)
    */
  public String toString() {
    return String.format("%s, %d de %s de %d", DAYS_OF_WEEK[getDiaDeSemana()],
                         day_, MONTHS[month_], year_);
  }
}
