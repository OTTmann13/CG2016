package computergraphics.applications;



import computergraphics.datastructures.ITriangleMesh;
import computergraphics.datastructures.ObjIO;
import computergraphics.datastructures.halfedge.HalfEdgeTriangleMesh;
import computergraphics.framework.AbstractCGFrame;
import computergraphics.scenegraph.ColorNode;
import computergraphics.scenegraph.HalfEdgeTriangleNode;
import computergraphics.scenegraph.ShaderNode;
import computergraphics.scenegraph.ShaderNode.ShaderType;

public class Aufgabe3 extends AbstractCGFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Aufgabe3(int timerInterval) {
		super(timerInterval);
		ITriangleMesh mesh = new HalfEdgeTriangleMesh();
		
		ObjIO obj = new ObjIO();
		obj.read("hemisphere", mesh);
		
		ShaderNode shader = new ShaderNode(ShaderType.PHONG);
		getRoot().addChild(shader);
		
		ColorNode color = new ColorNode(1.0, 0.0, 0.0);
		shader.addChild(color);
		
		HalfEdgeTriangleNode triangleNode = new HalfEdgeTriangleNode(mesh);
		color.addChild(triangleNode);
	}

	@Override
	protected void timerTick() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		new Aufgabe3(1000);
	}

}
