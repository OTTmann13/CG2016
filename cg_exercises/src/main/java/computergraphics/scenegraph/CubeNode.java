package computergraphics.scenegraph;

import com.jogamp.opengl.GL2;

public class CubeNode extends Node{

	@Override
	public void drawGl(GL2 gl) {
		gl.glBegin(GL2.GL_TRIANGLES);
		gl.glVertex3d(-0.5, -0.5, -0.5);
		gl.glVertex3d(-0.5, -0.5, -0.5);
		gl.glVertex3d(0.5, 0.5, -0.5);
		gl.glVertex3d(-0.5, 0.5, -0.5);
		gl.glVertex3d(-0.5, -0.5, 0.5);
		gl.glVertex3d(0.5, -0.5, 0.5);
		gl.glVertex3d(0.5, 0.5, 0.5);
		gl.glVertex3d(-0.5, 0.5, 0.5);
		gl.glEnd();
		
		
	}

}
