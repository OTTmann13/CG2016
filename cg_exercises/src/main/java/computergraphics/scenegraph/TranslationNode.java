/**
* Praktikum CG, SS 2016
* Gruppe: Steffen Schweneker (steffen.schweneker@haw-hamburg.de),
* Jan Spijker (jan.spijker@haw-hamburg.de)
* Aufgabe: Aufgabenblatt 1, Aufgabe 1.2
*/

package computergraphics.scenegraph;

import com.jogamp.opengl.GL2;

import computergraphics.math.Vector3;

public class TranslationNode extends Node{
	
	private final Vector3 translate = new Vector3(1, 1, 1);
	
	public TranslationNode(Vector3 translate) {
		this.translate.copy(translate);
	}

	@Override
	public void drawGl(GL2 gl) {
		gl.glPushMatrix();
			gl.glTranslatef((float)translate.get(0), (float)translate.get(1),
					(float)translate.get(2));
			
			for(int childrenIndex = 0; childrenIndex < getNumberOfChildren(); childrenIndex++) {
				getChildNode(childrenIndex).drawGl(gl);
			}
		gl.glPopMatrix();
	}
	
	public void setTranslation(Vector3 translate) {
		this.translate.copy(translate);
	}

}
