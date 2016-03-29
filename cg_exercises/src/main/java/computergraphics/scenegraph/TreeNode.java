package computergraphics.scenegraph;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLU.*;
import com.jogamp.opengl.glu.GLUquadric;

public class TreeNode extends Node{
	private int resolution = 30;
	private int radius = 1;
	private GLU glu;

	@Override
	public void drawGl(GL2 gl) {
		glu = new GLU();
		//Treestump
		gl.glPushMatrix();
			final float treestumpRadius = 0.5f;
	        final float treestumpHeight = 1.0f;
	        final int treestumpSlices = 16;
	        final int treestumpStacks = 16;
	        GLUquadric treestump = glu.gluNewQuadric();
	        gl.glColor3d(0.55, 0.27, 0.07);
	        gl.glTranslatef(0, 0, 0);
	        gl.glRotatef(90, 1, 0, 0);
	        glu.gluDisk(treestump, 0, treestumpRadius, treestumpSlices, 2);
	        glu.gluCylinder(treestump, treestumpRadius, treestumpRadius, treestumpHeight, treestumpSlices, treestumpStacks);
	        gl.glTranslatef(0, 0, treestumpHeight);
	        glu.gluDisk(treestump, 0, treestumpRadius, treestumpSlices, 2);
	        glu.gluDeleteQuadric(treestump);
        gl.glPopMatrix();
		
        //Treetop
      	gl.glPushMatrix();
      		gl.glColor3d(0.0, 1.0, 0.0);
      		gl.glTranslatef(0, treestumpHeight - 0.3f, 0);
    		GLU glu = new GLU();
     		GLUquadric earth = glu.gluNewQuadric();
      		glu.gluQuadricDrawStyle(earth, GLU.GLU_FILL);
      		glu.gluQuadricNormals(earth, GLU.GLU_SMOOTH);
      		glu.gluQuadricOrientation(earth, GLU.GLU_OUTSIDE);
      		final int slices = resolution;
      		final int stacks = resolution;
      		glu.gluSphere(earth, radius, slices, stacks);
      	gl.glPopMatrix();
		
	}

}
