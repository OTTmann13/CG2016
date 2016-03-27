package computergraphics.datastructures;

/**
 * Parent interface for all triangles.
 * 
 * @author Philipp Jenke
 */
public interface ITriangle {

  /**
   * Return the index of the first vertex.
   */
  public int getVertexIndexA();

  /**
   * Return the index of the second vertex.
   */
  public int getVertexIndexB();

  /**
   * Return the index of the third vertex.
   */
  public int getVertexIndexC();

  /**
   * Return the index vertex. Valid values for i are 0,1,2.
   */
  public int getVertexIndex(int i);

}
