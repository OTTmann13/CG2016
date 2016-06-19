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
		
		Node sp1 = new SphereNode(1, new Vector3(2.5,0,0));
		sp1.setColor(new Vector3(1,0,0));
//		sp1.setGlossiness(5);
//		sp1.setReflection(1);
		
		Node sp2 = new SphereNode(1, new Vector3(0,0,0));
		sp2.setColor(new Vector3(1,0,0));
		sp2.setGlossiness(5);
		sp2.setReflection(1);

		
		Node sp3 = new SphereNode(1, new Vector3(-2.5,0,0));
		sp3.setColor(new Vector3(0,1,1));
		sp3.setGlossiness(30);
		sp3.setReflection(0.5);
	
		Node e1 = new PlainNode(new Vector3(0,-1,0), new Vector3(0,-1,0));


		root.addChild(sp1);
		root.addChild(sp2);
		root.addChild(sp3);
		root.addChild(e1);

		
		
		Camera c = new Camera();
		c.setEye(new Vector3(7,5,10));
		c.setRef(new Vector3(0,0,1));
		
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
