/**
* Praktikum CG, SS 2016
* Gruppe: Steffen Schweneker (steffen.schweneker@haw-hamburg.de),
* Jan Spijker (jan.spijker@haw-hamburg.de)
* Aufgabe: Aufgabenblatt 1, Aufgabe 1.3
*/

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
