package computergraphics.datastructures;

import computergraphics.math.Vector3;

/**
 * Parent interface for all vertex implementations.
 * 
 * @author Philipp Jenke
 */
public interface IVertex {
  /**
   * Return position of the vertex.
   */
  public Vector3 getPosition();

  /**
   * Return normal at the vertex. Null, if no normal is available.
   */
  public Vector3 getNormal();
  
  /**
   * 
   * @return String representation of the vertex
   */
  public String toString();
}
