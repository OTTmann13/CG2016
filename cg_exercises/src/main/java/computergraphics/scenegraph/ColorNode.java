package computergraphics.scenegraph;

import com.jogamp.opengl.GL2;

public class ColorNode extends Node{
	private double red;
	private double green;
	private double blue;
	
	public ColorNode(double red, double green, double blue){
		this.red = red;
		this.green = green;
		this.blue = blue;
		
	}

	@Override
	public void drawGl(GL2 gl) {
		gl.glPushMatrix();
			gl.glColor3d(red, green, blue);
			
			for(int childrenIndex = 0; childrenIndex < getNumberOfChildren(); childrenIndex++) {
				getChildNode(childrenIndex).drawGl(gl);
			}
		gl.glPopMatrix();
		
	}

}
