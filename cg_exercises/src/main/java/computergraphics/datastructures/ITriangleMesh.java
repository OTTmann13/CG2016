/**
 * Prof. Philipp Jenke
 * Hochschule für Angewandte Wissenschaften (HAW), Hamburg
 * 
 * Base framework for "WP Computergrafik".
 */
package computergraphics.datastructures;

import java.util.List;

import computergraphics.datastructures.halfedge.HalfEdge;
import computergraphics.math.Vector3;

/**
 * This interface describes the valid operations for a triangle mesh structure.
 * 
 * @author Philipp Jenke
 */
public interface ITriangleMesh {

  /**
   * Add a new triangle to the mesh with the vertex indices a, b, c. The index
   * of the first vertex is 0.
   * 
   * @param t
   *          Container object to represent a triangle.
   */
  public void addTriangle(int vertexIndex1, int vertexIndex2, int vertexIndex3);

  /**
   * Add a new vertex to the vertex list. The new vertex is appended to the end
   * of the list.
   * 
   * @param v
   *          Vertex to be added.
   * 
   * @return Index of the vertex in the vertex list.
   */
  public int addVertex(IVertex v);

  /**
   * Add a new vertex (given by position) to the vertex list. The new vertex is
   * appended to the end of the list.
   * 
   * @param position
   *          Position for the vertex to be added.
   * 
   * @return Index of the vertex in the vertex list.
   */
  public int addVertex(Vector3 position);

  /**
   * Getter.
   * 
   * @return Number of triangles in the mesh.
   */
  public int getNumberOfTriangles();

  /**
   * Getter.
   * 
   * @return Number of vertices in the triangle mesh.
   */
  public int getNumberOfVertices();

  /**
   * Getter
   * 
   * @param index
   *          Index of the vertex to be accessed.
   * @return Vertex at the given index.
   */
  public IVertex getVertex(int index);

  /**
   * Return the facet at the given index.
   * 
   * @param triangleIndex
   *          Index of the facet.
   * @return Facet at the index, null if the index is invalid.
   */
  public ITriangle getTriangle(int triangleIndex);

  /**
   * Clear mesh - remove all triangles and vertices.
   */
  public void clear();

  /**
   * Set the filename of the used texture.
   */
  public void setTextureFilename(String textureFilename);
  
  public List<HalfEdge> getHalfEdges();
}
