package computergraphics.datastructures;

import java.util.ArrayList;
import java.util.List;

import computergraphics.math.Vector3;

public class TriangleMesh implements ITriangleMesh{
	
	private List<ITriangle> triangles = new ArrayList<ITriangle>();
	private List<IVertex> vertices = new ArrayList<IVertex>();

	 /**
	   * Add a new triangle to the mesh with the vertex indices a, b, c. The index
	   * of the first vertex is 0.
	   * 
	   * @param t
	   *          Container object to represent a triangle.
	   */
	@Override
	public void addTriangle(int vertexIndex1, int vertexIndex2, int vertexIndex3) {
		ITriangle triangle = new Triangle(vertexIndex1, vertexIndex2, vertexIndex3);
		calcTriangleNormal(triangle);
		triangles.add(triangle);
	}

	 /**
	   * Add a new vertex to the vertex list. The new vertex is appended to the end
	   * of the list.
	   * 
	   * @param v
	   *          Vertex to be added.
	   * 
	   * @return Index of the vertex in the vertex list.
	   */
	@Override
	public int addVertex(IVertex v) {
		vertices.add(v);
		return vertices.indexOf(v);
	}
	
	  /**
	   * Add a new vertex (given by position) to the vertex list. The new vertex is
	   * appended to the end of the list.
	   * 
	   * @param position
	   *          Position for the vertex to be added.
	   * 
	   * @return Index of the vertex in the vertex list.
	   */
	@Override
	public int addVertex(Vector3 position) {
		IVertex vertex = new Vertex(position);
		vertices.add(vertex);
		return vertices.indexOf(vertex);
	}

	  /**
	   * Getter.
	   * 
	   * @return Number of triangles in the mesh.
	   */
	@Override
	public int getNumberOfTriangles() {
		return triangles.size();
	}
	
	  /**
	   * Getter.
	   * 
	   * @return Number of vertices in the triangle mesh.
	   */
	@Override
	public int getNumberOfVertices() {
		return vertices.size();
	}
	
	  /**
	   * Getter
	   * 
	   * @param index
	   *          Index of the vertex to be accessed.
	   * @return Vertex at the given index.
	   */
	@Override
	public IVertex getVertex(int index) {
		return vertices.get(index);
	}
	
	  /**
	   * Return the facet at the given index.
	   * 
	   * @param triangleIndex
	   *          Index of the facet.
	   * @return Facet at the index, null if the index is invalid.
	   */
	@Override
	public ITriangle getTriangle(int triangleIndex) {
		return triangles.get(triangleIndex);
	}
	
	  /**
	   * Clear mesh - remove all triangles and vertices.
	   */
	@Override
	public void clear() {
		vertices.clear();
		triangles.clear();
		
	}
	
	  /**
	   * Set the filename of the used texture.
	   */
	@Override
	public void setTextureFilename(String textureFilename) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * calculate the normalized normal-vector of a given triangle
	 * @param triangle
	 */
	private void calcTriangleNormal(ITriangle triangle) {
		Vector3 vertexA = vertices.get(triangle.getVertexIndexA()).getPosition();
		Vector3 vertexB = vertices.get(triangle.getVertexIndexB()).getPosition();
		Vector3 vertexC = vertices.get(triangle.getVertexIndexC()).getPosition();
		
		Vector3 vectorAB = vertexB.subtract(vertexA);
		Vector3 vectorAC = vertexC.subtract(vertexA);
		
		Vector3 normalVector = vectorAB.cross(vectorAC).getNormalized();
		
		triangle.setNormal(normalVector);
	}

}
