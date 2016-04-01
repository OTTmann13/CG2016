package computergraphics.scenegraph;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;

public class CylinderNode extends Node{
	private GLU glu;
	private double base;
	private double top;
	private double height;
	private int resolution;
	private boolean filled;
	
	public CylinderNode(double base, double top, double height, int resolution, boolean filled){
		this.base = base;
		this.top = top;
		this.height = height;
		this.resolution = resolution;
		this.filled = filled;
	}

	@Override
	public void drawGl(GL2 gl) {
		glu = new GLU();
		GLUquadric quad = glu.gluNewQuadric();
		glu.gluQuadricDrawStyle(quad, GLU.GLU_FILL);
		final int slices = resolution;
		final int stacks = resolution;
		glu.gluCylinder(quad, base, top, height, slices, stacks);
		
		if(filled) {
			gl.glRotated(180, 1, 0, 0);
			glu.gluDisk(quad, 0, base, slices, 2);
			gl.glRotated(180, 1, 0, 0);
			gl.glTranslated(0, 0, height);
			glu.gluDisk(quad, 0, top, slices, 2);
		}
		
		
	}

}
