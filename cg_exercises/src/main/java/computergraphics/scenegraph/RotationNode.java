package computergraphics.scenegraph;

import com.jogamp.opengl.GL2;

import computergraphics.math.Vector3;

public class RotationNode extends Node{
	
	private final Vector3 rotate = new Vector3(1.0, 1.0, 1.0);
	private Double angle = 90.0;
	
	public RotationNode(Double angle, Vector3 rotate) {
		this.rotate.copy(rotate);
		this.angle = angle;
	}

	@Override
	public void drawGl(GL2 gl) {
		gl.glPushMatrix();
		
		gl.glRotated(angle, rotate.get(0), 
				rotate.get(1), rotate.get(2));
		
		for(int childrenIndex = 0; childrenIndex < getNumberOfChildren(); childrenIndex++) {
			System.out.println("rotate: "+childrenIndex);
			getChildNode(childrenIndex).drawGl(gl);
		}
		
		gl.glPopMatrix();
		
	}

}
