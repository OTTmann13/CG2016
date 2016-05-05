package computergraphics.scenegraph;

import com.jogamp.opengl.GL2;

import computergraphics.datastructures.ITriangleMesh;
import computergraphics.datastructures.IVertex;
import computergraphics.datastructures.halfedge.HalfEdge;
import computergraphics.datastructures.halfedge.HalfEdgeTriangle;

/*
 * Node to draw a given trianglemesh using half-edge datastructure.
 * The displayed object using vertex normals
 */
public class HalfEdgeTriangleNode extends Node{
	ITriangleMesh mesh;
	private boolean objectCreated = false;
	private int displayList;
	private boolean drawVertexNormale = false;
	
	public HalfEdgeTriangleNode(ITriangleMesh mesh, boolean drawVertexNormale) {
		this.mesh = mesh;
		this.drawVertexNormale = drawVertexNormale;
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
					
					if(drawVertexNormale){
						gl.glNormal3d(vertexA.getNormal().get(0), vertexA.getNormal().get(1), vertexA.getNormal().get(2));
					}else{
						gl.glNormal3d(triangle.getNormal().get(0), triangle.getNormal().get(1), triangle.getNormal().get(2));
					}
					
					gl.glVertex3d(vertexA.getPosition().get(0), vertexA.getPosition().get(1), vertexA.getPosition().get(2));
					
					if(drawVertexNormale) gl.glNormal3d(vertexB.getNormal().get(0), vertexB.getNormal().get(1), vertexB.getNormal().get(2));
					gl.glVertex3d(vertexB.getPosition().get(0), vertexB.getPosition().get(1), vertexB.getPosition().get(2));
					
					if(drawVertexNormale) gl.glNormal3d(vertexC.getNormal().get(0), vertexC.getNormal().get(1), vertexC.getNormal().get(2));
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
