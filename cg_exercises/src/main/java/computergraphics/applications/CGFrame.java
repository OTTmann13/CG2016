/**
 * Prof. Philipp Jenke
 * Hochschule für Angewandte Wissenschaften (HAW), Hamburg
 * 
 * Base framework for "WP Computergrafik".
 */

package computergraphics.applications;

import computergraphics.framework.AbstractCGFrame;
import computergraphics.math.Vector3;
import computergraphics.scenegraph.CuboidNode;
import computergraphics.scenegraph.RotationNode;
import computergraphics.scenegraph.ScaleNode;
import computergraphics.scenegraph.ShaderNode;
import computergraphics.scenegraph.ShaderNode.ShaderType;
import computergraphics.scenegraph.SingleTriangleNode;
import computergraphics.scenegraph.SphereNode;
import computergraphics.scenegraph.TranslationNode;

/**
 * Application for the first exercise.
 * 
 * @author Philipp Jenke
 */
public class CGFrame extends AbstractCGFrame {

  /**
   * 
   */
  private static final long serialVersionUID = 4257130065274995543L;

  /**
   * Constructor.
   */
  public CGFrame(int timerInverval) {
    super(timerInverval);
    
    //Rotate
    RotationNode rotationNode = new RotationNode(90.0, new Vector3(1.0, 1.0, 1.0));
    getRoot().addChild(rotationNode);

    //Translate
    TranslationNode translationNode = new TranslationNode(new Vector3(0.0, 0.0, 0.0));
    rotationNode.addChild(translationNode);
    
    // Shader node does the lighting computation
    ShaderNode shaderNode = new ShaderNode(ShaderType.PHONG);
    translationNode.addChild(shaderNode);

    // Simple triangle
    SingleTriangleNode triangleNode = new SingleTriangleNode();
    translationNode.addChild(triangleNode);
    
    //Cuboid
    CuboidNode cuboidNode = new CuboidNode(2, 2, 2);
    translationNode.addChild(cuboidNode);

    // Sphere
    SphereNode sphereNode = new SphereNode(0.25, 20);
    translationNode.addChild(sphereNode);
  }

  /*
   * (nicht-Javadoc)
   * 
   * @see computergrafik.framework.ComputergrafikFrame#timerTick()
   */
  @Override
  protected void timerTick() {
    System.out.println("Tick");
  }

  public void keyPressed(int keyCode) {
    System.out.println("Key pressed: " + (char) keyCode);
  }

  /**
   * Program entry point.
   */
  public static void main(String[] args) {
    // The timer ticks every 1000 ms.
    new CGFrame(1000);
  }
}
