/**
 * @author adexe
 * Adexe Sabina Pérez: alu0100769609@ull.edu.es
 * Date: 19 abr. 2019
 * Subject: PAI
 * Version: 1.0
 * Comments:
 *
 */

package model;

public class Pair<Point,Boolean> {

    // ATTRIBUTES
    private Point point;
    private Boolean enabled;

    // CONSTRUCTORS
    public Pair(Point point, Boolean enabled) {
      this.point = point;
      this.enabled = enabled;
    }

    // METHODS

    @Override
    public int hashCode() { return point.hashCode() ^ enabled.hashCode(); }

    @Override
    public boolean equals(Object o) {
      if (!(o instanceof Pair)) return false;
      Pair pairo = (Pair) o;
      return this.point.equals(pairo.getEnabled()) &&
             this.enabled.equals(pairo.getPoint());
    }

    // GETTERS AND SETTERS
    public Point getPoint() { 
        return point;
    }
    
    public void setPoint(Point point) {
        this.point = point;
    }
    
    public Boolean getEnabled() {
        return enabled;
    }
    
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
} 