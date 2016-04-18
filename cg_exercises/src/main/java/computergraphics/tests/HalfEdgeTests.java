package computergraphics.tests;

import static org.junit.Assert.*;
import org.junit.Test;

import computergraphics.datastructures.ITriangleMesh;
import computergraphics.datastructures.IVertex;
import computergraphics.datastructures.halfedge.HalfEdge;
import computergraphics.datastructures.halfedge.HalfEdgeTriangleMesh;
import computergraphics.datastructures.halfedge.HalfEdgeVertex;
import computergraphics.math.Vector3;

import org.junit.Before;
import org.junit.After;

public class HalfEdgeTests {
	
	private ITriangleMesh mesh;
	
	private final Vector3 VERTEX_A_POSITION = new Vector3(1.0, 1.0, 0.0);
	private final Vector3 VERTEX_B_POSITION = new Vector3(1.0, 2.0, 0.0);
	private final Vector3 VERTEX_C_POSITION = new Vector3(2.0, 2.0, 0.0);
	private final Vector3 VERTEX_D_POSITION = new Vector3(2.0, 1.0, 0.0);
	
	
	@Before
	public void setUp() {
		mesh = new HalfEdgeTriangleMesh();
		
		mesh.addVertex(VERTEX_A_POSITION);
		mesh.addVertex(VERTEX_B_POSITION);
		mesh.addVertex(VERTEX_C_POSITION);
		mesh.addVertex(VERTEX_D_POSITION);
		
		mesh.addTriangle(0, 1, 2);
		mesh.addTriangle(0, 2, 3);
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void vertexTest() {
		assertEquals(mesh.getNumberOfVertices(), 4);
		
		IVertex v = new HalfEdgeVertex(VERTEX_A_POSITION);
		assertEquals(mesh.getVertex(0), v);
	}
	
	@Test
	public void triangleTest() {
		assertEquals(mesh.getNumberOfTriangles(), 2);
	}
	
	@Test
	public void clearMeshTest() {		
		mesh.clear();
		
		assertEquals(mesh.getNumberOfTriangles(), 0);
		assertEquals(mesh.getNumberOfVertices(), 0);
	}
	
	@Test
	public void halfEdgeTest() {
		HalfEdge edgeA = mesh.getHalfEdges().get(3);
		HalfEdge edgeB = mesh.getHalfEdges().get(4);
		HalfEdge edgeC = mesh.getHalfEdges().get(2);
		
		assertEquals(edgeA.getNext(), edgeB);
		assertEquals(edgeB.getNext(), edgeC);
		assertEquals(edgeC.getNext(), edgeA);
	}
	
	@Test
	public void oppositeTest() {
	}

}
