package computergraphics.applications;

import computergraphics.datastructures.ITriangleMesh;
import computergraphics.datastructures.ObjIO;
import computergraphics.datastructures.TriangleMesh;
import computergraphics.framework.AbstractCGFrame;
import computergraphics.math.Vector3;
import computergraphics.scenegraph.CubeNode;
import computergraphics.scenegraph.ScaleNode;
import computergraphics.scenegraph.SingleTriangleNode;
import computergraphics.scenegraph.TriangleMeshNode;

public class Aufgabe2 extends AbstractCGFrame{

	public Aufgabe2(int timerInterval) {
		super(timerInterval);
	    ITriangleMesh mesh = new TriangleMesh();
	    
	    ObjIO obj = new ObjIO();
	    obj.read("cube", mesh);
	    
//	    SingleTriangleNode tria = new SingleTriangleNode();
//	    getRoot().addChild(tria);
	    
//	    ScaleNode scale = new ScaleNode(new Vector3(0.5, 0.5, 0.5));
//	    getRoot().addChild(scale);
	    
	    TriangleMeshNode drawMesh = new TriangleMeshNode(mesh);
	    getRoot().addChild(drawMesh);
	    
	    CubeNode cube = new CubeNode();
	    getRoot().addChild(cube);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8973187627133197454L;

	@Override
	protected void timerTick() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		new Aufgabe2(1000);
	}

}
