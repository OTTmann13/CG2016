package computergraphics.applications;

import computergraphics.datastructures.ITriangleMesh;
import computergraphics.datastructures.ObjIO;
import computergraphics.datastructures.TriangleMesh;
import computergraphics.framework.AbstractCGFrame;
import computergraphics.math.Vector3;
import computergraphics.scenegraph.AdvancedTriangleMeshNode;
import computergraphics.scenegraph.ScaleNode;
import computergraphics.scenegraph.ShaderNode;
import computergraphics.scenegraph.TranslationNode;
import computergraphics.scenegraph.TriangleMeshNode;

public class Aufgabe2 extends AbstractCGFrame{

	public Aufgabe2(int timerInterval) {
		super(timerInterval);
	    ITriangleMesh mesh = new TriangleMesh();
	    
	    ObjIO obj = new ObjIO();
	    obj.read("Sonic", mesh);
	    
	    ShaderNode shader = new ShaderNode();
	    getRoot().addChild(shader);
	    
//	    TriangleMeshNode drawMesh = new TriangleMeshNode(mesh);
//	    shader.addChild(drawMesh);
	    
	    ScaleNode scale = new ScaleNode(new Vector3(0.1, 0.1, 0.1));
	    shader.addChild(scale);
	    
	    TranslationNode translate = new TranslationNode(new Vector3(0.0, -13.0, 0.0));
	    scale.addChild(translate);
	    
	    AdvancedTriangleMeshNode drawMeshWithDisplayList = new AdvancedTriangleMeshNode(mesh);
	    translate.addChild(drawMeshWithDisplayList);
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
