/**
 * Prof. Philipp Jenke
 * Hochschule für Angewandte Wissenschaften (HAW), Hamburg
 * 
 * Base framework for "WP Computergrafik".
 */
package computergraphics.math;

/**
 * Implementation of a 3-dimensional matrix.
 * 
 * @author Philipp Jenke
 * 
 */
public class Matrix3 {

  /**
   * Entries of the matrix
   */
  private final Vector3[] rows = { new Vector3(), new Vector3(), new Vector3() };

  /**
   * Constructor.
   */
  public Matrix3() {
  }

  /**
   * Constructor with initial values.
   */
  public Matrix3(Vector3 row1, Vector3 row2, Vector3 row3) {
    rows[MathHelpers.INDEX_0] = row1;
    rows[MathHelpers.INDEX_1] = row2;
    rows[MathHelpers.INDEX_2] = row3;
  }

  /**
   * Konstruktor mit Initialisierung.
   */
  public Matrix3(double v00, double v01, double v02, double v10, double v11, double v12, double v20, double v21,
      double v22) {
    rows[0] = new Vector3(v00, v01, v02);
    rows[1] = new Vector3(v10, v11, v12);
    rows[2] = new Vector3(v20, v21, v22);
  }

  /**
   * Setter.
   */
  public void setRow(final int index, Vector3 row) {
    rows[index] = row;
  }

  /**
   * Getter.
   */
  public Vector3 getRow(final int index) {
    return rows[index];
  }

  /**
   * Multiply vector from the right. Return result.
   */
  public Vector3 multiply(final Vector3 other) {
    Vector3 result = new Vector3();
    for (int i = 0; i < MathHelpers.DIMENSION_3; i++) {
      result.set(i, getRow(i).multiply(other));
    }

    return result;
  }

  /**
   * Getter - convenient e.g. to pass it to OpenGL.
   */
  public double[] data() {
    double[] data = { rows[0].get(0), rows[0].get(1), rows[0].get(2), rows[1].get(0), rows[1].get(1), rows[1].get(2),
        rows[2].get(0), rows[2].get(1), rows[2].get(2) };
    return data;
  }

  /**
   * Create a rotation axis.
   * 
   * @param axis
   *          Rotation vector.
   * @param angle
   *          Rotation angle.
   */
  public static Matrix3 getRotationMatrix(Vector3 axis, double angle) {
    double s = Math.sin(angle);
    double c = Math.cos(angle);
    double t = 1.0 - c;

    return new Matrix3(
        new Vector3(t * axis.get(0) * axis.get(0) + c, t * axis.get(0) * axis.get(1) + s * axis.get(2),
            t * axis.get(0) * axis.get(2) - s * axis.get(1)),
        new Vector3(t * axis.get(0) * axis.get(1) - s * axis.get(2), t * axis.get(1) * axis.get(1) + c,
            t * axis.get(1) * axis.get(2) + s * axis.get(0)),
        new Vector3(t * axis.get(0) * axis.get(2) + s * axis.get(1), t * axis.get(2) * axis.get(2) - s * axis.get(0),
            t * axis.get(2) * axis.get(2) + c));
  }

  /**
   * Liefert die transponierte Matrix zurück.
   */
  public Matrix3 getTransposed() {
    return new Matrix3(get(0, 0), get(1, 0), get(2, 0), get(0, 1), get(1, 1), get(2, 1), get(0, 2), get(1, 2),
        get(2, 2));
  }

  /**
   * Zugriff auf den Wert an der Stelle (rowIndex, columnIndex).
   */
  public double get(int rowIndex, int columnIndex) {
    return rows[rowIndex].get(columnIndex);
  }

  /**
   * Berechnet die Inverse der Matrix und liefert diese zurück. Wirft eine
   * Exception, falls die Matrix nicht invertierbar ist.
   */
  public Matrix3 getInverse() {
    double det = getDeteterminant();
    if (Math.abs(det) < 1e-5) {
      throw new IllegalArgumentException("Matrix cannot be inverted, determinant is (close to) 0.");
    }
    double a = get(0, 0);
    double b = get(0, 1);
    double c = get(0, 2);
    double d = get(1, 0);
    double e = get(1, 1);
    double f = get(1, 2);
    double g = get(2, 0);
    double h = get(2, 1);
    double i = get(2, 2);
    Matrix3 inverse = new Matrix3(e * i - f * h, c * h - b * i, b * f - c * e, f * g - d * i, a * i - c * g,
        c * d - a * f, d * h - e * g, b * g - a * h, a * e - b * d).multiply(1.0 / det);
    return inverse;
  }

  /**
   * Liefert die Determinante der Matrix.
   */
  public double getDeteterminant() {
    return get(0, 0) * get(1, 1) * get(2, 2) + get(0, 1) * get(1, 2) * get(2, 0) + get(0, 2) * get(1, 0) * get(2, 1)
        - get(0, 2) * get(1, 1) * get(2, 0) - get(0, 1) * get(1, 0) * get(2, 2) - get(0, 0) * get(1, 2) * get(2, 1);
  }

  /**
   * Multipliziert die Matrix mit dem Skalar factor und liefert neue Matrix mit
   * dem Ergebnis zurück.
   */
  public Matrix3 multiply(double factor) {
    Matrix3 result = new Matrix3();
    for (int row = 0; row < 3; row++) {
      for (int column = 0; column < 3; column++) {
        result.set(row, column, get(row, column) * factor);
      }
    }
    return result;
  }

  /**
   * Setzt den Wert an der Stelle (rowIndex, columnIndex).
   */
  public void set(int rowIndex, int columnIndex, double d) {
    rows[rowIndex].set(columnIndex, d);
  }
}
