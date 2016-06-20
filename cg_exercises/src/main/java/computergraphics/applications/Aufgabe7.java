package computergraphics.applications;

import javax.swing.JFrame;

import computergraphics.framework.AbstractCGFrame;
import computergraphics.framework.Camera;
import computergraphics.math.Vector3;
import computergraphics.projects.raytracing.ImageViewer;
import computergraphics.projects.raytracing.Raytracer;
import computergraphics.scenegraph.Node;
import computergraphics.scenegraph.PlainNode;
import computergraphics.scenegraph.RootNode;
import computergraphics.scenegraph.SphereNode;

public class Aufgabe7 extends AbstractCGFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Aufgabe7(int timerInterval) {
		super(timerInterval);
		
		Node root = new RootNode();
		
		Node sphere1 = new SphereNode(1, new Vector3(-2.5, 0, 2.5));
		sphere1.setColor(new Vector3(1, 0, 0));
		
		Node sphere2 = new SphereNode(1, new Vector3(0, 1, 0));
		sphere2.setColor(new Vector3(0, 1, 0));
		sphere2.setReflection(0.8);

		
		Node sphere3 = new SphereNode(1, new Vector3(2.5, 0, -3));
		sphere3.setColor(new Vector3(0, 0, 1));
		sphere3.setReflection(0.0);

		Node plain = new PlainNode(new Vector3(0, -1, 0), new Vector3(-0.5, 0.0, -1), new Vector3(1, 0, -0.5));

		root.addChild(sphere1);
		root.addChild(sphere2);
		root.addChild(sphere3);
		root.addChild(plain);

		
		
		Camera c = new Camera();
		c.setEye(new Vector3(7, 5, 10));
//		c.setEye(new Vector3(-3, 7, 10));
		c.setRef(new Vector3(0, 0, 1));
		
		Raytracer tracer = new Raytracer(c, root);
		ImageViewer viewer = new ImageViewer(tracer.render(800, 640));
		viewer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	protected void timerTick() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		new Aufgabe7(1000);
	}

}
