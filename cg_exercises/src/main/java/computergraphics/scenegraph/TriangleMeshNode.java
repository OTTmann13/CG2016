package computergraphics.scenegraph;

import com.jogamp.opengl.GL2;

import computergraphics.datastructures.ITriangleMesh;

public class TriangleMeshNode extends Node{
	
	private final ITriangleMesh mesh;
	
	public TriangleMeshNode(ITriangleMesh mesh) {
		this.mesh = mesh;
	}

	@Override
	public void drawGl(GL2 gl) {
		gl.glBegin(GL2.GL_TRIANGLES);
		gl.glColor3d(1, 0, 0);
			for(int i = 0; i < mesh.getNumberOfVertices(); i++) {
				System.out.println("draw triangle " + i);
				System.out.println("A: " + mesh.getTriangle(i).getVertexIndexA() + ", B: " + mesh.getTriangle(i).getVertexIndexB() + ", C: " + mesh.getTriangle(i).getVertexIndexC());
				gl.glVertex3d(mesh.getVertex(i).getPosition().get(0), mesh.getVertex(i).getPosition().get(1), mesh.getVertex(i).getPosition().get(2));
			}
		
		gl.glEnd();
		
	}

}
