package computergraphics.scenegraph;

import java.security.InvalidParameterException;

import com.jogamp.opengl.GL2;

import computergraphics.datastructures.IntersectionResult;
import computergraphics.datastructures.Ray3D;
import computergraphics.math.Vector3;

public class PlainNode extends Node{
	
	private final Vector3 normal = new Vector3();
	private final Vector3 point = new Vector3();
	
	private final Vector3 tU;
	private final Vector3 tV;
	
	public PlainNode(Vector3 point, Vector3 normal) {
		this.point.copy(point);
		this.normal.copy(normal);
		
		// Ab hier werden die UV-Parameter berechnet. Dazu werden zwei Werte frei gewaehlt, der dritte wird dann berechnet
		double free_choosen_1 = point.get(0) - 1;
		double free_choosen_2 = point.get(1) - 1;
		
		// Fallunterscheidung, falls die Normale 0-Werte enthält. Zunächst wird versucht nach Z aufzulösen, dann nach Y, dann nach X
		if (normal.get(2) != 0)
		{
			tU = new Vector3(free_choosen_1,free_choosen_2,(0 - normal.get(0) * free_choosen_1 - normal.get(1) * free_choosen_2) / normal.get(2));
		}
		else if (normal.get(1) != 0)
		{
			tU = new Vector3(free_choosen_1,(0 - normal.get(0) * free_choosen_1 - normal.get(2) * free_choosen_2) / normal.get(1),free_choosen_2);
		}
		else if (normal.get(0) != 0)
		{
			tU = new Vector3((0 - normal.get(1) * free_choosen_1 - normal.get(2) * free_choosen_2) / normal.get(0),free_choosen_1,free_choosen_2);
		}
		else
		{
			// Fehlermeldung, wenn alle Werte der Normale = 0 sind, dann können U und V nicht berechnet werden (und es ist auch keine gültige Normale)
			throw new InvalidParameterException("Invalid normal given: " + normal);
		}
		
		// Normen von tU und tV
		tU.normalize();
		// tV aus Kreuzprodukt von tU und Normalen berechnen
		tV = normal.cross(tU).getNormalized();
	}
	

	public IntersectionResult findIntersection(Node object, Ray3D ray) {
		
		//Calculate lambda
		double lambda = (this.normal.multiply(this.point) - this.normal.multiply(ray.getPoint())) / this.normal.multiply(ray.getDirection());
		
		//check if the ray is parallel to the object
		if(Double.isInfinite(lambda)) {
			return null;
		}
		
		//Calculate the intersection point
		Vector3 point = ray.getPoint().add(ray.getDirection().multiply(lambda));
		
		IntersectionResult result = new IntersectionResult();
		result.point = point;
		result.object = this;
		result.normal = this.normal.getNormalized();
		return result;
	}
	
	public Vector3 getChessboardColor(Vector3 point)
	{
		double u = point.multiply(tU);
		double v = point.multiply(tV);
		if (u < 0) u = -u + 1;
		if (v < 0) v = -v + 1;
		if ((int)u % 2 == (int)v % 2)
		{
			return new Vector3(1, 1, 1);
		}
		else
		{
			return new Vector3(.2, .2, .2);
		}
	}

	@Override
	public void drawGl(GL2 gl) {
		// TODO Auto-generated method stub
		
	}

}
