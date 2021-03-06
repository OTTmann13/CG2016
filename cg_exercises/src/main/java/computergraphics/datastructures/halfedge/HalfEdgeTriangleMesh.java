package computergraphics.datastructures.halfedge;

import java.util.ArrayList;
import java.util.List;

import computergraphics.datastructures.ITriangle;
import computergraphics.datastructures.ITriangleMesh;
import computergraphics.datastructures.IVertex;
import computergraphics.math.Vector3;

public class HalfEdgeTriangleMesh implements ITriangleMesh{
	
	List<HalfEdge> halfEdges = new ArrayList<HalfEdge>();
	List<HalfEdgeTriangle> halfEdgeTriangles = new ArrayList<HalfEdgeTriangle>();
	List<HalfEdgeVertex> halfEdgeVertices = new ArrayList<HalfEdgeVertex>();
	
	 /**
	   * Add a new triangle to the mesh with the vertex indices a, b, c. The index
	   * of the first vertex is 0.
	   * 
	   * @param t
	   *          Container object to represent a triangle.
	   */
	@Override
	public void addTriangle(int vertexIndex1, int vertexIndex2, int vertexIndex3) {
		HalfEdgeTriangle halfEdgeTriangle = new HalfEdgeTriangle();	
		
		//Create 3 edges forming the triangle
		HalfEdge halfEdge1 = new HalfEdge();
		HalfEdge halfEdge2 = new HalfEdge();
		HalfEdge halfEdge3 = new HalfEdge();
		//Set information for the half-edges
		//Information:
		//appropriate triangle
		//next half-edge
		halfEdge1.setFacet(halfEdgeTriangle);
		halfEdge1.setStartVertex(halfEdgeVertices.get(vertexIndex1));
		halfEdge1.setNext(halfEdge2);
		halfEdges.add(halfEdge1);
		
		halfEdge2.setFacet(halfEdgeTriangle);
		halfEdge2.setStartVertex(halfEdgeVertices.get(vertexIndex2));	
		halfEdge2.setNext(halfEdge3);
		halfEdges.add(halfEdge2);
		
		halfEdge3.setFacet(halfEdgeTriangle);
		halfEdge3.setStartVertex(halfEdgeVertices.get(vertexIndex3));	
		halfEdge3.setNext(halfEdge1);
		halfEdges.add(halfEdge3);
		
		//Set informations for the triangle
		//Information:
		//one half-edge of the triangle
		halfEdgeTriangle.setHalfEdge(halfEdge1);	
		halfEdgeTriangles.add(halfEdgeTriangle);
		
		//Calculate and set the triangle-normal
		calculateTriangleNormal(halfEdgeTriangle);
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
		HalfEdgeVertex halfEdgeVertex = (HalfEdgeVertex) v;
		halfEdgeVertices.add(halfEdgeVertex);
		return halfEdgeVertices.indexOf(v);
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
		HalfEdgeVertex halfEdgeVertex = new HalfEdgeVertex(position);
		halfEdgeVertices.add(halfEdgeVertex);
		return halfEdgeVertices.indexOf(halfEdgeVertex);
	}

	  /**
	   * Getter.
	   * 
	   * @return Number of triangles in the mesh.
	   */
	@Override
	public int getNumberOfTriangles() {
		return halfEdgeTriangles.size();
	}

	  /**
	   * Getter.
	   * 
	   * @return Number of vertices in the triangle mesh.
	   */
	@Override
	public int getNumberOfVertices() {
		return halfEdgeVertices.size();
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
		return halfEdgeVertices.get(index);
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
		return halfEdgeTriangles.get(triangleIndex);
	}

	  /**
	   * Clear mesh - remove all triangles and vertices.
	   */
	@Override
	public void clear() {
		halfEdges.clear();
		halfEdgeTriangles.clear();
		halfEdgeVertices.clear();
	}

	  /**
	   * Set the filename of the used texture.
	   */
	@Override
	public void setTextureFilename(String textureFilename) {
		// TODO Auto-generated method stub
		
	}
	
	private void calculateTriangleNormal(HalfEdgeTriangle triangle) {
		//Set currentEdge to one of the edges exists in the triangle
		HalfEdge currentEdge = triangle.getHalfEdge();
		Vector3 vertexA = currentEdge.getStartVertex().getPosition();
		
		//Set currentEdge to the next edge in the triangle
		currentEdge = currentEdge.getNext();
		Vector3 vertexB = currentEdge.getStartVertex().getPosition();
		
		//Set currentEdge to the next edge in the triangle
		currentEdge = currentEdge.getNext();
		Vector3 vertexC = currentEdge.getStartVertex().getPosition();
		
		//Calculate two vectors of the triangle
		Vector3 vectorAB = vertexB.subtract(vertexA);
		Vector3 vectorAC = vertexC.subtract(vertexA);
		
		//Calculate the normal and normalize it
		Vector3 normalVector = vectorAB.cross(vectorAC).getNormalized();
		
		triangle.setNormal(normalVector);
	}
	
	public void calculateVertexNormal() {
		Vector3 summTriangleNormal = new Vector3(0,0,0);
		for(HalfEdgeVertex vertex : halfEdgeVertices) {
			//Get the start edge
			HalfEdge startEdge = vertex.getHalfEdge();
			
			//Get the normal of the first triangle
			summTriangleNormal = startEdge.getFacet().getNormal();
			
			//Check if the edge is a margin-edge
			if(vertex.getHalfEdge().getOpposite() != null) {
				//Set current edge of the next edge
				HalfEdge currentEdge = vertex.getHalfEdge().getOpposite().getNext();
				
				//Check if the edge is a margin-edge
				if(currentEdge.getOpposite() != null) {
					//Search all adjacent triangles and add all normals
					while(startEdge != currentEdge) {
						summTriangleNormal.add(currentEdge.getFacet().getNormal());
						currentEdge = currentEdge.getOpposite().getNext();
					}
					
					//Normalize the sum of all triangle normals
					Vector3 normilizedVertexVector =  summTriangleNormal.getNormalized();
					
					//Set the vector to a vertex
					vertex.setNormal(normilizedVertexVector);
				}
			}
		}
	}
	
	public void setAdditionalInformations() {
		//Add a half-edge to every vertex
		//Search all half-edges and set the edge to a vertex
		for(HalfEdge e :  halfEdges) {
			HalfEdgeVertex vertex = e.getStartVertex();
			if(vertex.getHalfEdge() == null) {
				vertex.setHalfEgde(e);
			}
		}
		
		for(HalfEdge halfEdge : halfEdges) {
			//get v1 and v2 of a half-edge
			HalfEdgeVertex v1 = halfEdge.getStartVertex();
			HalfEdgeVertex v2 = halfEdge.getNext().getStartVertex();
			
			//search all half-edges for an edge leading v2 -> v1
			for(HalfEdge oppositeEdge : halfEdges) {
				if(oppositeEdge.getStartVertex().equals(v2) && oppositeEdge.getNext().getStartVertex().equals(v1)) {
					halfEdge.setOpposite(oppositeEdge);
				}
			}
		}
	}
	
	public List<HalfEdge> getHalfEdges() {
		return halfEdges;
	}

}
