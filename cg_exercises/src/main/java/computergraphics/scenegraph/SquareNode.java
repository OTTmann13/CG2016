package computergraphics.scenegraph;

import java.util.List;

import com.jogamp.opengl.GL2;

import computergraphics.math.Vector3;

public class SquareNode extends Node{
	private List<Vector3> points;
	private int displayList;
	private boolean objectCreated = false;
	
	public SquareNode(List<Vector3> points) {
		this.points = points;
	}

	@Override
	public void drawGl(GL2 gl) {
		
		if(!objectCreated) {
			displayList = gl.glGenLists(1);
			gl.glNewList(displayList, GL2.GL_COMPILE);
			gl.glBegin(GL2.GL_LINES);
			
			gl.glVertex3d(points.get(0).get(0), points.get(0).get(1), points.get(0).get(2));
			gl.glVertex3d(points.get(1).get(0), points.get(1).get(1), points.get(1).get(2));
			
			gl.glVertex3d(points.get(0).get(0), points.get(0).get(1), points.get(0).get(2));
			gl.glVertex3d(points.get(4).get(0), points.get(4).get(1), points.get(4).get(2));
			
			gl.glVertex3d(points.get(0).get(0), points.get(0).get(1), points.get(0).get(2));
			gl.glVertex3d(points.get(3).get(0), points.get(3).get(1), points.get(3).get(2));
			
			gl.glVertex3d(points.get(1).get(0), points.get(1).get(1), points.get(1).get(2));
			gl.glVertex3d(points.get(2).get(0), points.get(2).get(1), points.get(2).get(2));
			
			gl.glVertex3d(points.get(1).get(0), points.get(1).get(1), points.get(1).get(2));
			gl.glVertex3d(points.get(5).get(0), points.get(5).get(1), points.get(5).get(2));
			
			gl.glVertex3d(points.get(2).get(0), points.get(2).get(1), points.get(2).get(2));
			gl.glVertex3d(points.get(3).get(0), points.get(3).get(1), points.get(3).get(2));
			
			gl.glVertex3d(points.get(2).get(0), points.get(2).get(1), points.get(2).get(2));
			gl.glVertex3d(points.get(6).get(0), points.get(6).get(1), points.get(6).get(2));
			
			gl.glVertex3d(points.get(3).get(0), points.get(3).get(1), points.get(3).get(2));
			gl.glVertex3d(points.get(7).get(0), points.get(7).get(1), points.get(7).get(2));
			
			gl.glVertex3d(points.get(4).get(0), points.get(4).get(1), points.get(4).get(2));
			gl.glVertex3d(points.get(5).get(0), points.get(5).get(1), points.get(5).get(2));
			
			gl.glVertex3d(points.get(4).get(0), points.get(4).get(1), points.get(4).get(2));
			gl.glVertex3d(points.get(7).get(0), points.get(7).get(1), points.get(7).get(2));
			
			gl.glVertex3d(points.get(5).get(0), points.get(5).get(1), points.get(5).get(2));
			gl.glVertex3d(points.get(6).get(0), points.get(6).get(1), points.get(6).get(2));
			
			gl.glVertex3d(points.get(6).get(0), points.get(6).get(1), points.get(6).get(2));
			gl.glVertex3d(points.get(7).get(0), points.get(7).get(1), points.get(7).get(2));
			
			gl.glEnd();
			gl.glEndList();
			objectCreated = true;
		}else{
			gl.glCallList(displayList);
		}
	}

}
