package computergraphics.datastructures;

import computergraphics.math.Vector3;

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
  
  /**
   * Set the normal-vector of this triangle
   * @param normal
   */
  public void setNormal(Vector3 normal);
  
  /**
   * 
   * @return the normal-vector
   */
  public Vector3 getNormal();

}
