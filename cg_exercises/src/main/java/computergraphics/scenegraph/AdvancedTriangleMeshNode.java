package computergraphics.scenegraph;

import com.jogamp.opengl.GL2;

import computergraphics.datastructures.ITriangle;
import computergraphics.datastructures.ITriangleMesh;
import computergraphics.datastructures.IVertex;

/*
 * Node to draw a given trianglemesh using displaylists
 */
public class AdvancedTriangleMeshNode extends Node{
	private final ITriangleMesh mesh;
	private int displayList;
	private boolean objectCreated = false;
	
	public AdvancedTriangleMeshNode(ITriangleMesh mesh) {
		this.mesh = mesh;
	}

	@Override
	public void drawGl(GL2 gl) {
		//make sure the displaylist is only created once
		if(!objectCreated) {
			displayList = gl.glGenLists(1);
			//create a new displaylist
			gl.glNewList(displayList, GL2.GL_COMPILE);
			
			//creating the model
			gl.glBegin(GL2.GL_TRIANGLES);
			//gl.glColor3d(0, 1, 0);
				for(int i = 0; i < mesh.getNumberOfTriangles(); i++) {
					
					ITriangle triangle = mesh.getTriangle(i);
					IVertex vertexA = mesh.getVertex(triangle.getVertexIndexA());
					IVertex vertexB = mesh.getVertex(triangle.getVertexIndexB());
					IVertex vertexC = mesh.getVertex(triangle.getVertexIndexC());
					
					gl.glNormal3d(triangle.getNormal().get(0), triangle.getNormal().get(1), triangle.getNormal().get(2));
					gl.glVertex3d(vertexA.getPosition().get(0), vertexA.getPosition().get(1), vertexA.getPosition().get(2));
					gl.glVertex3d(vertexB.getPosition().get(0), vertexB.getPosition().get(1), vertexB.getPosition().get(2));
					gl.glVertex3d(vertexC.getPosition().get(0), vertexC.getPosition().get(1), vertexC.getPosition().get(2));

				}
			
			gl.glEnd();
			gl.glEndList();
			objectCreated = true;
		}else{
			//call the displaylist
			gl.glCallList(displayList);
		}
	}
}
