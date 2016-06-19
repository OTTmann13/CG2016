/**
 * Prof. Philipp Jenke
 * Hochschule für Angewandte Wissenschaften (HAW), Hamburg
 * 
 * Base framework for "WP Computergrafik".
 */
package computergraphics.scenegraph;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;

import computergraphics.datastructures.IntersectionResult;
import computergraphics.datastructures.Ray3D;
import computergraphics.math.Vector3;

/**
 * Geometry of a simple sphere.
 * 
 * @author Philipp Jenke
 */
public class SphereNode extends Node {

	/**
	 * Sphere radius.
	 */
	private double radius;

	/**
	 * Resolution (in one dimension) of the mesh.
	 */
	private int resolution;

	
	private Vector3 center = new Vector3();
	/**
	 * Constructor.
	 */
	public SphereNode(double radius, int resolution) {
		this.radius = radius;
		this.resolution = resolution;
	}
	
	public SphereNode(double radius, Vector3 center) {
		this.radius = radius;
		this.center.copy(center);
	}
	
	public IntersectionResult findIntersection(Node object, Ray3D ray) {
		
		double p = (ray.getPoint().multiply(ray.getDirection()) * 2) - (center.multiply(ray.getDirection()) * 2) / ray.getDirection().multiply(ray.getDirection());
		double q = ((ray.getPoint().multiply(ray.getPoint()) - (2 * ray.getPoint().multiply(center)) + center.multiply(center)) - Math.pow(radius, 2)) / (ray.getDirection().multiply(ray.getDirection()));
		double lambda = Math.min((p/-2) + Math.sqrt(Math.pow(p, 2)/4 - q),(p/-2) - Math.sqrt(Math.pow(p, 2)/4 - q));

		if (Double.isNaN(lambda) || lambda <= 0) {
			return null;
		}
		
		IntersectionResult result = new IntersectionResult();
		result.point = ray.getPoint().add(ray.getDirection().multiply(lambda));
		result.object = this;
		result.normal = center.subtract(result.point).getNormalized();
		
		return result;
	}

	@Override
	public void drawGl(GL2 gl) {
		//gl.glColor3d(0.0, 1.0, 0.0);
		GLU glu = new GLU();
		GLUquadric earth = glu.gluNewQuadric();
		glu.gluQuadricDrawStyle(earth, GLU.GLU_FILL);
		glu.gluQuadricNormals(earth, GLU.GLU_SMOOTH);
		glu.gluQuadricOrientation(earth, GLU.GLU_OUTSIDE);
		final int slices = resolution;
		final int stacks = resolution;
		glu.gluSphere(earth, radius, slices, stacks);
	}
	
	public Vector3 getCenter() {
		return center;
	}


	public void setCenter(Vector3 center) {
		this.center = center;
	}

}
