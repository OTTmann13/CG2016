package computergraphics.scenegraph;

import com.jogamp.opengl.GL2;

public class GroundNode extends Node{

	@Override
	public void drawGl(GL2 gl) {
		gl.glBegin(GL2.GL_QUADS);
			gl.glColor3f(0, 1, 0);
			gl.glNormal3f(0, 0, 1);
			gl.glVertex3f(1, 1, 0);
			
			gl.glColor3f(0, 1, 0);
			gl.glNormal3f(0, 0, 1);
			gl.glVertex3f(1, -1, 0);
			
			gl.glColor3f(0, 1, 0);
			gl.glNormal3f(0, 0, 1);
			gl.glVertex3f(-1, -1, 0);

			gl.glColor3f(0, 1, 0);
			gl.glNormal3f(0, 0, 1);
			gl.glVertex3f(-1, 1, 0);
		gl.glEnd();
		
	}

}