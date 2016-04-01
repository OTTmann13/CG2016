package computergraphics.applications;

import com.sun.prism.GraphicsPipeline.ShaderType;

import computergraphics.framework.AbstractCGFrame;
import computergraphics.math.Vector3;
import computergraphics.scenegraph.ColorNode;
import computergraphics.scenegraph.CylinderNode;
import computergraphics.scenegraph.ShaderNode;
import computergraphics.scenegraph.SingleTriangleNode;
import computergraphics.scenegraph.SphereNode;
import computergraphics.scenegraph.TranslationNode;
import javafx.scene.transform.Translate;

public class HelicopterScene extends AbstractCGFrame{
	private SphereNode sphere;
	private ShaderNode shader;
	private SingleTriangleNode triangle = new SingleTriangleNode();
	private CylinderNode cylinder;
	private double sphereMovment = 0.0;
	/**
	 * 
	 */
	private static final long serialVersionUID = -88200910435904402L;
	
	public HelicopterScene(int timerInterval) {
		super(timerInterval);
		
		shader = new ShaderNode();
		getRoot().addChild(shader);
		
		cylinder = new CylinderNode(1, 1, 2, 30, true);
		
		sphere = new SphereNode(2, 30);
		
		ColorNode color = new ColorNode(1.0, 0.0, 0.0);
		shader.addChild(color);
		color.addChild(sphere);
	}

	@Override
	protected void timerTick() {
	}
	
	public static void main(String[] args) {
		new HelicopterScene(1000);
	}

}
