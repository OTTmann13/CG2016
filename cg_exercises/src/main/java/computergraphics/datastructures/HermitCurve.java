package computergraphics.datastructures;

import computergraphics.datastructures.polygon.Polygon;
import computergraphics.math.Vector3;

public class HermitCurve extends Curve{
	private final Polygon controlPolygon;

	public HermitCurve(Polygon controlPolygon) {
		super(controlPolygon);
		this.controlPolygon = controlPolygon;
	}
	
	
	@Override
	public Vector3 getCurveValue(double t) {
		double h0 = Math.pow((1 - t), 2) * (1 + 2 * t);
		double h1 = t * Math.pow((1 - t), 2);
		double h2 = -Math.pow(t, 2) * (1 - t);
		double h3 = (3 - 2 * t) * Math.pow(t, 2);
		
		Vector3 p0 = controlPolygon.getVertex(0).getPosition();
		Vector3 m0 = controlPolygon.getVertex(1).getPosition();
		Vector3 m1 = controlPolygon.getVertex(2).getPosition();
		Vector3 p1 = controlPolygon.getVertex(3).getPosition();
		
		Vector3 v0 = p0.multiply(h0);
		Vector3 v1 = m0.multiply(h1);
		Vector3 v2 = m1.multiply(h2);
		Vector3 v3 = p1.multiply(h3);
		return v0.add(v1).add(v2).add(v3);
	}
	
	public Vector3 getSplineCurveValue(double t, int i) {
		double h0 = Math.pow((1 - t), 2) * (1 + 2 * t);
		double h1 = t * Math.pow((1 - t), 2);
		double h2 = -Math.pow(t, 2) * (1 - t);
		double h3 = (3 - 2 * t) * Math.pow(t, 2);
		
		Vector3 p0 = controlPolygon.getVertex(i).getPosition();
		Vector3 m0 = getSplineTangent(i);
		Vector3 p1 = controlPolygon.getVertex(i + 1).getPosition();
		Vector3 m1 = getSplineTangent(i + 1);
		
		
		Vector3 v0 = p0.multiply(h0);
		Vector3 v1 = m0.multiply(h1);
		Vector3 v2 = m1.multiply(h2);
		Vector3 v3 = p1.multiply(h3);
		return v0.add(v1).add(v2).add(v3);
	}


	@Override
	public Vector3 getTangent(double t) {
		return null;
	}
	
	public Vector3 getSplineTangent(int i) {
		if(i == 0) {
			return controlPolygon.getVertex(i + 1).getPosition();
		}else if(i == controlPolygon.getNumberOfVertecies() - 1) {
			return controlPolygon.getVertex(i - 2).getPosition();
		}else {
			Vector3 c1 = controlPolygon.getVertex(i + 1).getPosition();
			Vector3 c2 = controlPolygon.getVertex(i - 1).getPosition();
			
			return (c1.subtract(c2)).multiply(c1.subtract(c2).getNorm());
		}
	}
	

}
