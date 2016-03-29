/**
 * Prof. Philipp Jenke
 * Hochschule für Angewandte Wissenschaften (HAW), Hamburg
 * 
 * Base framework for "WP Computergrafik".
 */

package computergraphics.applications;

import java.util.Random;

import com.sun.java.swing.plaf.windows.WindowsInternalFrameTitlePane.ScalableIconUIResource;

import computergraphics.framework.AbstractCGFrame;
import computergraphics.math.Vector3;
import computergraphics.scenegraph.CuboidNode;
import computergraphics.scenegraph.GroundNode;
import computergraphics.scenegraph.HelicopterNode;
import computergraphics.scenegraph.RootNode;
import computergraphics.scenegraph.RotationNode;
import computergraphics.scenegraph.ScaleNode;
import computergraphics.scenegraph.ShaderNode;
import computergraphics.scenegraph.ShaderNode.ShaderType;
import computergraphics.scenegraph.SingleTriangleNode;
import computergraphics.scenegraph.SphereNode;
import computergraphics.scenegraph.TranslationNode;
import computergraphics.scenegraph.TreeNode;
import sun.reflect.generics.tree.Tree;

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
    
    // Shader node does the lighting computation
    ShaderNode shaderNode = new ShaderNode(ShaderType.PHONG);
    getRoot().addChild(shaderNode);
    
    RotationNode rotateGround = new RotationNode(270.0, new Vector3(1.0, 0.0, 0.0));
    shaderNode.addChild(rotateGround);
    
    GroundNode ground = new GroundNode();
    rotateGround.addChild(ground);
    
    RotationNode rotationHeli = new RotationNode(90.0, new Vector3(1.0, 0.0, 0.0));
    shaderNode.addChild(rotationHeli);
    
    ScaleNode scaleHeli = new ScaleNode(new Vector3(0.1, 0.1, 0.1));
    shaderNode.addChild(scaleHeli);
    
    TranslationNode translateHeli = new TranslationNode(new Vector3(0.0, 5.0, 0.0));
    scaleHeli.addChild(translateHeli);
    
    HelicopterNode heliNode = new HelicopterNode(6);
    translateHeli.addChild(heliNode);
    
    TranslationNode translateTree = new TranslationNode(new Vector3(-0.5, 0.0, -0.5));
    shaderNode.addChild(translateTree);
    
    ScaleNode scaleTree = new ScaleNode(new Vector3(0.1, 0.1, 0.1));
    translateTree.addChild(scaleTree);
    
    Random rand = new Random();
    for(int i = 0; i < 50; i++) {
    	TranslationNode spreadTrees = new TranslationNode
    			(new Vector3(rand.nextInt(13 - -3 + 1) -3.0, 1.0, rand.nextInt(13 - -3 + 1) -3.0));
    	spreadTrees.addChild(new TreeNode());
    	scaleTree.addChild(spreadTrees);
    }
    
    
    
    
//    //Rotate
//    RotationNode rotationNode = new RotationNode(90.0, new Vector3(1.0, 1.0, 1.0));
//    getRoot().addChild(rotationNode);
//
//    //Translate
//    TranslationNode translationNode = new TranslationNode(new Vector3(0.0, 0.0, 0.0));
//    rotationNode.addChild(translationNode);
//    
//    TreeNode treeNode = new TreeNode();
//    translationNode.addChild(treeNode);
//    
//    // Simple triangle
//    SingleTriangleNode triangleNode = new SingleTriangleNode();
//    translationNode.addChild(triangleNode);
//    
//    //Cuboid
//    CuboidNode cuboidNode = new CuboidNode(2, 2, 2);
//    translationNode.addChild(cuboidNode);
//
//    // Sphere
//    SphereNode sphereNode = new SphereNode(0.75, 20);
//    translationNode.addChild(sphereNode);
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
