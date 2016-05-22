package computergraphics.scenegraph;

import com.jogamp.opengl.GL2;

import computergraphics.datastructures.polygon.Polygon;

public class PolygonNode extends Node{
	private Polygon polygon;
	
	public PolygonNode(Polygon polygon) {
		this.polygon = polygon;
	}
	
	@Override
	public void drawGl(GL2 gl) {
		gl.glBegin(GL2.GL_LINES);
		gl.glColor3d(0.0, 0.0, 0.0);
		for(int i = 0; i < polygon.getNumberOfVertecies(); i++) {
			gl.glVertex3d(polygon.getVertex(i).getPosition().get(0), polygon.getVertex(i).getPosition().get(1), polygon.getVertex(i).getPosition().get(2));
			if(i+1 >= polygon.getNumberOfVertecies() && polygon.getClosedState()) {
				gl.glVertex3d(polygon.getVertex(0).getPosition().get(0), polygon.getVertex(0).getPosition().get(1), polygon.getVertex(0).getPosition().get(2));
			}else if(i+1 < polygon.getNumberOfVertecies()){
				gl.glVertex3d(polygon.getVertex(i+1).getPosition().get(0), polygon.getVertex(i+1).getPosition().get(1), polygon.getVertex(i+1).getPosition().get(2));
			}
		}
		
		gl.glEnd();
		
	}

}
