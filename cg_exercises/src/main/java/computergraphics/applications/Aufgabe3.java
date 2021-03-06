package computergraphics.applications;



import computergraphics.datastructures.ITriangleMesh;
import computergraphics.datastructures.ObjIO;
import computergraphics.datastructures.halfedge.HalfEdgeTriangleMesh;
import computergraphics.framework.AbstractCGFrame;
import computergraphics.scenegraph.ColorNode;
import computergraphics.scenegraph.HalfEdgeOutlineNode;
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
		obj.read("cow", mesh);
		
		mesh.setAdditionalInformations();
		mesh.calculateVertexNormal();
		
		ShaderNode shader = new ShaderNode(ShaderType.PHONG);
		getRoot().addChild(shader);
		
		ColorNode color = new ColorNode(0.0, 0.0, 1.0);
		shader.addChild(color);
		
		ColorNode outlineColor = new ColorNode(1.0, 0.0, 0.0);
		getRoot().addChild(outlineColor);
		
		HalfEdgeOutlineNode outline = new HalfEdgeOutlineNode(mesh);
		outlineColor.addChild(outline);
		
		HalfEdgeTriangleNode triangleNode = new HalfEdgeTriangleNode(mesh, false);
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
