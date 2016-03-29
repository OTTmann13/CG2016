package computergraphics.scenegraph;

import javax.print.attribute.ResolutionSyntax;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;

public class HelicopterNode extends Node{
	private int resolution = 30;
	private int radius = 1;
	private GLU glu;
	private int rotation = 0;
	private int rotationSpeed;
	
	public HelicopterNode(int rotationSpeed) {
		this.rotationSpeed = rotationSpeed;
	}

	@Override
	public void drawGl(GL2 gl) {
		glu = new GLU();
		
		//Cockpit
		gl.glPushMatrix();
			gl.glColor3d(1.0, 0.0, 0.0);
			GLU glu = new GLU();
			GLUquadric earth = glu.gluNewQuadric();
			glu.gluQuadricDrawStyle(earth, GLU.GLU_FILL);
			glu.gluQuadricNormals(earth, GLU.GLU_SMOOTH);
			glu.gluQuadricOrientation(earth, GLU.GLU_OUTSIDE);
			final int slices = resolution;
			final int stacks = resolution;
			glu.gluSphere(earth, radius, slices, stacks);
		gl.glPopMatrix();
		
		//Rotorbolt
		gl.glPushMatrix();
			final float rotorboltRadius = 0.1f;
	        final float rotorboltHeight = 0.1f;
	        final int rotorboltSlices = 16;
	        final int rotorboltStacks = 16;
	        GLUquadric rotorbolt = glu.gluNewQuadric();
	        gl.glColor3d(0.0, 0.0, 0.0);
	        gl.glRotated(270, 1, 0, 0);
	        gl.glTranslatef(0, 0, radius);
	        glu.gluDisk(rotorbolt, 0, rotorboltRadius, rotorboltSlices, 2);
	        glu.gluCylinder(rotorbolt, rotorboltRadius, rotorboltRadius, rotorboltHeight, rotorboltSlices, rotorboltStacks);
	        gl.glTranslatef(0, 0, rotorboltHeight);
	        glu.gluDisk(rotorbolt, 0, rotorboltRadius, rotorboltSlices, 2);
	        glu.gluDeleteQuadric(rotorbolt);
        gl.glPopMatrix();
        
        //Rotorblade1
        gl.glPushMatrix();
	        final float rotorblade1Radius = 0.1f;
	        final float rotorblade1Height = 3.0f;
	        final int rotorblade1Slices = 16;
	        final int rotorblade1Stacks = 16;
	        GLUquadric rotorblade1 = glu.gluNewQuadric();
	        gl.glColor3d(0.41, 0.41, 0.41);
	        gl.glRotated(90, 0, 1, 0);
	        gl.glRotated(rotation, 0, 1, 0);
	        rotation = (rotation + rotationSpeed) % 360;
	        gl.glTranslatef(0, radius + rotorboltHeight, -rotorblade1Height/2);
	        glu.gluDisk(rotorblade1, 0, rotorblade1Radius, rotorblade1Slices, 2);
	        glu.gluCylinder(rotorblade1, rotorblade1Radius, rotorblade1Radius, rotorblade1Height, rotorblade1Slices, rotorblade1Stacks);
	        gl.glTranslatef(0, 0, rotorblade1Height);
	        glu.gluDisk(rotorblade1, 0, rotorblade1Radius, rotorblade1Slices, 2);
	        glu.gluDeleteQuadric(rotorblade1);
        gl.glPopMatrix();
        
        //Heck
        gl.glPushMatrix();
        	final float heckRadiusBase = 0.5f;
	        final float heckRadiusTop = 0.2f;
	        final float heckHeight = 2.0f;
	        final int heckSlices = 16;
	        final int heckStacks = 16;
	        GLUquadric heck = glu.gluNewQuadric();
	        gl.glColor3d(1.0, 0.0, 0.0);
	        gl.glRotated(90, 0, 1, 0);
	        gl.glTranslated(0, 0, radius - 0.2);
	        glu.gluDisk(heck, 0, heckRadiusBase, heckSlices, 2);
	        glu.gluCylinder(heck, heckRadiusBase, heckRadiusTop, heckHeight, heckSlices, heckStacks);
	        gl.glTranslatef(0, 0, heckHeight);
	        glu.gluDisk(heck, 0, heckRadiusTop, heckSlices, 2);
	        glu.gluDeleteQuadric(heck);
        gl.glPopMatrix();
        
        //Heckrotorbolt
        gl.glPushMatrix();
	        final float heckrotorboltRadius = 0.1f;
	        final float heckrotorboltHeight = 0.1f;
	        final int heckrotorboltSlices = 16;
	        final int heckrotorboltStacks = 16;
	        GLUquadric heckrotorbolt = glu.gluNewQuadric();
	        gl.glColor3d(0.0, 0.0, 0.0);
	        gl.glTranslated((radius + heckHeight) - 0.3, 0, 0.2);
	        gl.glRotated(90, 0, 0, 1);
	        glu.gluDisk(heckrotorbolt, 0, heckrotorboltRadius, heckrotorboltSlices, 2);
	        glu.gluCylinder(heckrotorbolt, heckrotorboltRadius, heckrotorboltRadius, heckrotorboltHeight, heckrotorboltSlices, heckrotorboltStacks);
	        gl.glTranslatef(0, 0, heckrotorboltHeight);
	        glu.gluDisk(heckrotorbolt, 0, heckrotorboltRadius, heckrotorboltSlices, 2);
	        glu.gluDeleteQuadric(heckrotorbolt);
        gl.glPopMatrix();
        
      //Heckrotor
        gl.glPushMatrix();
	        final float heckrotorRadius = 0.05f;
	        final float heckrotorHeight = 1.0f;
	        final int heckrotorSlices = 16;
	        final int heckrotorStacks = 16;
	        GLUquadric heckrotor = glu.gluNewQuadric();
	        gl.glColor3d(0.41, 0.41, 0.41);
	        gl.glRotated(90, 0, 1, 0);  
	        gl.glTranslated(-0.35, 0, (radius + heckHeight) - 0.3);
	        gl.glRotated(rotation, 1, 0, 0);
	        gl.glTranslated(0, 0, - heckrotorHeight / 2);
	        glu.gluDisk(heckrotor, 0, heckrotorRadius, heckrotorSlices, 2);
	        glu.gluCylinder(heckrotor, heckrotorRadius, heckrotorRadius, heckrotorHeight, heckrotorSlices, heckrotorStacks);
	        gl.glTranslatef(0, 0, heckrotorHeight);
	        glu.gluDisk(heckrotor, 0, heckrotorRadius, heckrotorSlices, 2);
	        glu.gluDeleteQuadric(heckrotor);
        gl.glPopMatrix();
        	
	}

}
