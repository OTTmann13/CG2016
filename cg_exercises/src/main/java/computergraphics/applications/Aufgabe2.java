package computergraphics.applications;

import computergraphics.datastructures.ITriangleMesh;
import computergraphics.datastructures.ObjIO;
import computergraphics.datastructures.TriangleMesh;
import computergraphics.framework.AbstractCGFrame;
import computergraphics.scenegraph.AdvancedTriangleMeshNode;
import computergraphics.scenegraph.ShaderNode;
import computergraphics.scenegraph.TriangleMeshNode;

public class Aufgabe2 extends AbstractCGFrame{

	public Aufgabe2(int timerInterval) {
		super(timerInterval);
	    ITriangleMesh mesh = new TriangleMesh();
	    
	    ObjIO obj = new ObjIO();
	    obj.read("cow", mesh);
	    
	    ShaderNode shader = new ShaderNode();
	    getRoot().addChild(shader);
	    
//	    TriangleMeshNode drawMesh = new TriangleMeshNode(mesh);
//	    shader.addChild(drawMesh);
	    
	    AdvancedTriangleMeshNode drawMeshWithDisplayList = new AdvancedTriangleMeshNode(mesh);
	    shader.addChild(drawMeshWithDisplayList);
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
