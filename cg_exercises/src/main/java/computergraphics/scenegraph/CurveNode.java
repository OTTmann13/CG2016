package computergraphics.scenegraph;

import com.jogamp.opengl.GL2;

import computergraphics.applications.Aufgabe5;
import computergraphics.datastructures.Curve;
import computergraphics.datastructures.HermitCurve;
import computergraphics.datastructures.polygon.Polygon;
import computergraphics.math.Vector3;

public class CurveNode extends Node{
	private Curve curve;
	private int resolution;
	private double tangentT = 0.0;
	private Polygon polygon;
	
	public CurveNode(Curve curve, int resolution, Polygon polygon) {
		this.curve = curve;
		this.resolution = resolution;
		this.polygon = polygon;
	}
	
	@Override
	public void drawGl(GL2 gl) {
		gl.glBegin(GL2.GL_LINES);
		if(curve.getClass().equals(HermitCurve.class)) {
			for(int j = 0; j < polygon.getNumberOfVertecies() - 1; j++) {
				for(int i = 0; i < resolution; i++) {				
					Vector3 start = curve.getSplineCurveValue((1.0 / resolution) * i, j);
					Vector3 end = curve.getSplineCurveValue((1.0 / resolution) * (i + 1), j);
					
					gl.glVertex3d(start.get(0), start.get(1), start.get(2));
					gl.glVertex3d(end.get(0), end.get(1), end.get(2));
				}
			}
		}else{
			for(int i = 0; i < resolution; i++) {
								Vector3 start = curve.getCurveValue((1.0 / resolution) * i);
								Vector3 end = curve.getCurveValue((1.0 / resolution) * (i + 1));
								
								
								gl.glVertex3d(start.get(0), start.get(1), start.get(2));
								gl.glVertex3d(end.get(0), end.get(1), end.get(2));
							}
		}
		
//		Vector3 tangent = curve.getTangent(tangentT);
//		Vector3 start = curve.getCurveValue(tangentT);
//		gl.glVertex3d(start.get(0) - tangent.get(0), start.get(1) - tangent.get(1), start.get(2) - tangent.get(2));
//		gl.glVertex3d(start.get(0) + tangent.get(0), start.get(1) + tangent.get(1), start.get(2) + tangent.get(2));
		gl.glEnd();
		
	}
	
	public void setTangentT(double t) {
		this.tangentT = t;
	}

}
