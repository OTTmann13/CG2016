package computergraphics.scenegraph;

import com.jogamp.opengl.GL2;

import computergraphics.datastructures.ITriangle;
import computergraphics.datastructures.ITriangleMesh;
import computergraphics.datastructures.IVertex;

/*
 * Node to draw a trianglemesh
 */
public class TriangleMeshNode extends Node{
	
	private final ITriangleMesh mesh;
	
	public TriangleMeshNode(ITriangleMesh mesh) {
		this.mesh = mesh;
	}

	@Override
	public void drawGl(GL2 gl) {
		gl.glBegin(GL2.GL_TRIANGLES);
		//gl.glColor3d(1, 0, 0);
			for(int i = 0; i < mesh.getNumberOfTriangles(); i++) {
					
				ITriangle triangle = mesh.getTriangle(i);
				IVertex vertexA = mesh.getVertex(triangle.getVertexIndexA());
				IVertex vertexB = mesh.getVertex(triangle.getVertexIndexB());
				IVertex vertexC = mesh.getVertex(triangle.getVertexIndexC());
				
//				System.out.println("A: " + triangle.getVertexIndexA());
//				System.out.println("B: " + triangle.getVertexIndexB());
//				System.out.println("C: " + triangle.getVertexIndexC());
				
				gl.glNormal3d(triangle.getNormal().get(0), triangle.getNormal().get(1), triangle.getNormal().get(2));
				gl.glVertex3d(vertexA.getPosition().get(0), vertexA.getPosition().get(1), vertexA.getPosition().get(2));
				gl.glVertex3d(vertexB.getPosition().get(0), vertexB.getPosition().get(1), vertexB.getPosition().get(2));
				gl.glVertex3d(vertexC.getPosition().get(0), vertexC.getPosition().get(1), vertexC.getPosition().get(2));

			}
			
		gl.glEnd();
	}

}
