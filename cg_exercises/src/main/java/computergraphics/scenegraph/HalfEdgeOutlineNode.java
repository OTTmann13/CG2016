package computergraphics.scenegraph;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;

import computergraphics.datastructures.ITriangleMesh;
import computergraphics.datastructures.halfedge.HalfEdge;
import computergraphics.datastructures.halfedge.HalfEdgeVertex;

/*
 * Node to draw the outline of a given trianglemesh
 */
public class HalfEdgeOutlineNode extends Node{
	
	private ITriangleMesh mesh;
	private HalfEdge startEdge;
	
	public HalfEdgeOutlineNode(ITriangleMesh mesh) {
		this.mesh = mesh;
		
	}

	@Override
	public void drawGl(GL2 gl) {
		gl.glLineWidth(5);
		gl.glBegin(GL2.GL_LINES);
		
		for(HalfEdge e : mesh.getHalfEdges()) {
			if(e.getOpposite() == null) {
				
				HalfEdgeVertex vertexA = e.getStartVertex();
				HalfEdgeVertex vertexB = e.getNext().getStartVertex();	
				
				gl.glVertex3d(vertexA.getPosition().get(0), vertexA.getPosition().get(1), vertexA.getPosition().get(2));
				gl.glVertex3d(vertexB.getPosition().get(0), vertexB.getPosition().get(1), vertexB.getPosition().get(2));
				
			}
		}
		gl.glEnd();
		gl.glLineWidth(1);
	}
}
