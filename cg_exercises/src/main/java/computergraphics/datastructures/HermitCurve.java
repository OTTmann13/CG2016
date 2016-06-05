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
		double h2 = Math.pow(-t, 2) * (1 - t);
		double h3 = (3 - 2 * t) * Math.pow(t, 2);
		
		Vector3 c0 = controlPolygon.getVertex(0).getPosition();
		Vector3 c1 = controlPolygon.getVertex(1).getPosition();
		Vector3 c2 = controlPolygon.getVertex(2).getPosition();
		Vector3 c3 = controlPolygon.getVertex(3).getPosition();
		
		
		Vector3 a0 = c0.multiply(h0);
		Vector3 a1 = c1.multiply(h1);
		Vector3 a2 = c2.multiply(h2);
		Vector3 a3 = c3.multiply(h3);
		return a0.add(a1).add(a2).add(a3);
	}


	@Override
	public Vector3 getTangent(double t) {
//		Vector3 p1 = controlPolygon.getVertex(t + 1).getPosition();
//		Vector3 p2 = controlPolygon.getVertex(t - 1).getPosition();
//		return p1.subtract(p2); p1.subtract(p2);
		return null;
	}
	

}
