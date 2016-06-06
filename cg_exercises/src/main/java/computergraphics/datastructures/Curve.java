package computergraphics.datastructures;

import computergraphics.datastructures.polygon.Polygon;
import computergraphics.math.Vector3;

public abstract class Curve {
	private final Polygon controlPolygon; 
	
	
	public Curve(Polygon controlPolygon) {
		this.controlPolygon = controlPolygon;
	}
	
	public abstract Vector3 getCurveValue(double t);
	
	public abstract Vector3 getTangent(double t);
	
	public abstract Vector3 getSplineCurveValue(double t, int i);
	
	public abstract Vector3 getSplineTangent(int i);
}
