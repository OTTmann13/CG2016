package computergraphics.scenegraph;

import com.jogamp.opengl.GL2;

import computergraphics.datastructures.ITriangle;
import computergraphics.datastructures.ITriangleMesh;
import computergraphics.datastructures.IVertex;
import computergraphics.datastructures.halfedge.HalfEdge;
import computergraphics.datastructures.halfedge.HalfEdgeTriangle;

/*
 * Node to draw a given trianglemesh using half-edge datastructure.
 * The displayed object using triangle normals
 */
public class TriangleNormalHalfEdgeTriangleNode extends Node {
	ITriangleMesh mesh;
	private boolean objectCreated = false;
	private int displayList;
	
	public TriangleNormalHalfEdgeTriangleNode(ITriangleMesh mesh) {
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
					
					HalfEdgeTriangle triangle = (HalfEdgeTriangle)mesh.getTriangle(i);
					
					HalfEdge currentEdge = triangle.getHalfEdge();
					IVertex vertexA = currentEdge.getStartVertex();
					
					currentEdge = currentEdge.getNext();
					IVertex vertexB = currentEdge.getStartVertex();
					
					currentEdge = currentEdge.getNext();
					IVertex vertexC = currentEdge.getStartVertex();
					
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
