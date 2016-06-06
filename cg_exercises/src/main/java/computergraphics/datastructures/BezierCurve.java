package computergraphics.datastructures;

import computergraphics.datastructures.polygon.Polygon;
import computergraphics.math.MathHelpers;
import computergraphics.math.Vector3;

public class BezierCurve extends Curve{
	private final Polygon controlPolygon;
	private final double h = 0.0001;

	public BezierCurve(Polygon controlPolygon) {
		super(controlPolygon);
		this.controlPolygon = controlPolygon;
	}
	
	@Override
	public Vector3 getCurveValue(double t) {
		int numberOfControlPoints = controlPolygon.getNumberOfVertecies();
		int curveDeg = numberOfControlPoints -1;
		Vector3 sumVector = new Vector3();
		
		for(int i = 0; i < numberOfControlPoints; i++) {
			Vector3 controlPointPosition = controlPolygon.getVertex(i).getPosition();
			
			double nodeValue = MathHelpers.over(curveDeg, i) * Math.pow(t, i) * Math.pow((1 - t), (curveDeg - i));
			
			sumVector = sumVector.add(controlPointPosition.multiply(nodeValue));
		}
		return sumVector;
	}
	
	@Override
	public Vector3 getTangent(double t) {
		return getCurveValue(t + h).subtract(getCurveValue(t)).multiply((1/h));
	}
	
	@Override
	public Vector3 getSplineCurveValue(double t, int i) {
		return null;
	}
	
	@Override
	public Vector3 getSplineTangent(int i) {
		return null;
	}

}
