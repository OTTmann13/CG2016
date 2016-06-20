package computergraphics.scenegraph;

import com.jogamp.opengl.GL2;

import computergraphics.datastructures.IntersectionResult;
import computergraphics.datastructures.Ray3D;
import computergraphics.math.Vector3;

public class PlainNode extends Node{
	
	private Vector3 normal = new Vector3();
	private Vector3 point = new Vector3();
	
	private final Vector3 spanVector1;
	private final Vector3 spanVector2;
	
	public PlainNode(Vector3 point, Vector3 spanVector1, Vector3 spanVector2) {
		this.point = point;
		this.spanVector1 = spanVector1;
		this.spanVector2 = spanVector2;
		this.normal = calcNormal();
	}
	
	private Vector3 calcNormal() {
		return spanVector1.cross(spanVector2).getNormalized();
		
	}

	public IntersectionResult findIntersection(Node object, Ray3D ray) {
		
		//Calculate lambda
		double lambda = (this.normal.multiply(this.point) - this.normal.multiply(ray.getPoint())) / this.normal.multiply(ray.getDirection());
		
		//check if the ray is parallel to the object
		if(Double.isNaN(lambda) || lambda < 0) {
			return null;
		}
		
		//Calculate the intersection point
		Vector3 point = ray.getPoint().add(ray.getDirection().multiply(lambda));
		
		IntersectionResult result = new IntersectionResult();
		result.point = point;
		result.object = this;
		result.normal = this.normal;
		return result;
	}

	@Override
	public void drawGl(GL2 gl) {
		// TODO Auto-generated method stub
		
	}

}
