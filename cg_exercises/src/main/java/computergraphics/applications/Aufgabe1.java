package computergraphics.applications;

import java.util.Random;

import computergraphics.framework.AbstractCGFrame;
import computergraphics.math.Vector3;
import computergraphics.scenegraph.ColorNode;
import computergraphics.scenegraph.CuboidNode;
import computergraphics.scenegraph.CylinderNode;
import computergraphics.scenegraph.RotationNode;
import computergraphics.scenegraph.ScaleNode;
import computergraphics.scenegraph.ShaderNode;
import computergraphics.scenegraph.SphereNode;
import computergraphics.scenegraph.TranslationNode;

public class Aufgabe1 extends AbstractCGFrame{
	private TranslationNode helicopterTranslation;
	private RotationNode helicopterRotation;
	private RotationNode rotorSpin;
	private RotationNode rearrotorSpin;
	private TranslationNode treeTranslation;
	private ShaderNode shader;
	
	private Random rand =  new Random();
	
	private final int RESOLUTION = 30;
	private final int MAXTREENUMBER = 50;
	
	private double rotationAngle = 0.0;
	private double helicopterMovementAngle = 0.0;
	
	//Helicopter
	private final double HELICOPTERHEIGHTPOSITION = 2.0;
	private final double HELICOPTERDEPTHPOSITION = 3.0;
	private final double HELICOPTERWIDTHPOSITION = 0.0;
	
	private final double COCKPITRADIUS = 1.0;
	private final double ROTORBOLTRADIUS = 0.1;
	private final double ROTORBOLTHEIGHT = 0.1;
	private final double ROTORBOLTANGLE = 90.0;
	
	private final double ROTORWIDTH = 5.0;
	private final double ROTORHEIGHT = 0.1;
	private final double ROTORDEPTH = 0.2;
	
	//rear
	private final double REARWIDTH = 3.5;
	private final double REARHEIGHT = 0.5;
	private final double REARDEPTH = 0.5;
	
	//rearrotor
	private final double REARROTORWIDTH = 1.0;
	private final double REARROTORHEIGHT = 0.1;
	private final double REARROTORDEPTH = 0.1;
	
	//treetop
	private final double TREETOPRADIUS = 0.5;
	
	//treetrunk
	private final double TREETRUNKHEIGHT = 1.0;
	private final double TREETRUNKRADIUS = 0.2;
	
	//ground
	private final double GROUNDWIDTH = 5.0;
	private final double GROUNDDEPTH = 5.0;
	private final double GROUNDHEIGHT = 0.1;

	
	//Colors
	private final double[] BLACK = {0.0, 0.0, 0.0};
	private final double[] GREY = {0.38, 0.38, 0.38};
	private final double[] RED = {0.8, 0.0, 0.0};
	private final double[] GREEN = {0.13, 0.55, 0.13};
	private final double[] BROWN = {0.55, 0.35, 0.17};
	/**
	 * 
	 */
	private static final long serialVersionUID = -88200910435904402L;
	
	public Aufgabe1(int timerInterval) {
		super(timerInterval);
		
		shader = new ShaderNode();
		getRoot().addChild(shader);
		
		//-----------------Helicopter--------------------------
		ScaleNode helicopterScale = new ScaleNode(new Vector3(0.5, 0.5, 0.5));
		shader.addChild(helicopterScale);
		
		helicopterRotation = new RotationNode(helicopterMovementAngle, new Vector3(0.0, -1.0, 0.0));
		helicopterScale.addChild(helicopterRotation);
		
		helicopterTranslation = new TranslationNode(new Vector3(HELICOPTERWIDTHPOSITION, HELICOPTERHEIGHTPOSITION, HELICOPTERDEPTHPOSITION));
		helicopterRotation.addChild(helicopterTranslation);
		
		//Cockpit
		ColorNode cockpitColor = new ColorNode(RED[0], RED[1], RED[2]);
		helicopterTranslation.addChild(cockpitColor);
		SphereNode cockpit = new SphereNode(COCKPITRADIUS, RESOLUTION);
		cockpitColor.addChild(cockpit);
		
		//Rotorbolt
		ColorNode rotorBoltColor = new ColorNode(BLACK[0], BLACK[1], BLACK[2]);
		helicopterTranslation.addChild(rotorBoltColor);
		
		CylinderNode rotorBolt = new CylinderNode(ROTORBOLTRADIUS, ROTORBOLTRADIUS, ROTORBOLTHEIGHT, RESOLUTION, true);
		TranslationNode rotorBoltTranslation = new TranslationNode(new Vector3(0.0, COCKPITRADIUS + ROTORBOLTHEIGHT, 0.0));
		rotorBoltColor.addChild(rotorBoltTranslation);
				
		RotationNode rotorBoltRotation = new RotationNode(ROTORBOLTANGLE, new Vector3(1.0, 0.0, 0.0));
		rotorBoltTranslation.addChild(rotorBoltRotation);
		rotorBoltRotation.addChild(rotorBolt);
		
		//Mainrotor
		ColorNode rotorColor = new ColorNode(GREY[0], GREY[1], GREY[2]);
		helicopterTranslation.addChild(rotorColor);
		
		CuboidNode rotor1 = new CuboidNode(ROTORWIDTH, ROTORHEIGHT, ROTORDEPTH);
		CuboidNode rotor2 = new CuboidNode(ROTORWIDTH, ROTORHEIGHT, ROTORDEPTH);
		TranslationNode rotorTranslation = new TranslationNode(new Vector3(0.0, COCKPITRADIUS + ROTORBOLTHEIGHT, 0.0));
		rotorColor.addChild(rotorTranslation);
		
		rotorSpin = new RotationNode(rotationAngle, new Vector3(0.0, 1.0, 0.0));
		rotorTranslation.addChild(rotorSpin);
		rotorSpin.addChild(rotor1);
		
		RotationNode rotorRotation = new RotationNode(90.0, new Vector3(0.0, 1.0, 0.0));
 		rotorSpin.addChild(rotorRotation);
		rotorRotation.addChild(rotor2);
		
		//Rear
		ColorNode rearColor = new ColorNode(RED[0], RED[1], RED[2]);
		helicopterTranslation.addChild(rearColor);
		
		CuboidNode rear = new CuboidNode(REARWIDTH, REARHEIGHT, REARDEPTH);
		TranslationNode rearTranslation = new TranslationNode(new Vector3(REARWIDTH / 2, 0.5, 0.0));
		rearColor.addChild(rearTranslation);
		rearTranslation.addChild(rear);
		
		//Rearrotor
		ColorNode rearrotorColor = new ColorNode(GREY[0], GREY[1], GREY[2]);
		helicopterTranslation.addChild(rearrotorColor);
		
		CuboidNode rearrotor1 = new CuboidNode(REARROTORWIDTH, REARROTORHEIGHT, REARROTORDEPTH);
		CuboidNode rearrotor2 = new CuboidNode(REARROTORWIDTH, REARROTORHEIGHT, REARROTORDEPTH);
		TranslationNode rearrotorTranslation = new TranslationNode(new Vector3(REARWIDTH - 0.2, 0.5, REARDEPTH / 2 + REARROTORDEPTH));
		rearrotorColor.addChild(rearrotorTranslation);
		
		rearrotorSpin = new RotationNode(rotationAngle, new Vector3(0.0, 0.0, 1.0));
		rearrotorTranslation.addChild(rearrotorSpin);
		rearrotorSpin.addChild(rearrotor1);
		
		RotationNode rearrotorRotation = new RotationNode(90.0, new Vector3(0.0, 0.0, 1.0));
		rearrotorSpin.addChild(rearrotorRotation);
		rearrotorRotation.addChild(rearrotor2);
		
		//----------------Tree--------------------
		for(int currentTreeNumber = 0; currentTreeNumber < MAXTREENUMBER; currentTreeNumber++) {
			treeTranslation = new TranslationNode(new Vector3(rand.nextInt((int)GROUNDWIDTH) - GROUNDWIDTH/2 + 0.3, 0.0, rand.nextInt((int) GROUNDDEPTH) - GROUNDDEPTH/2 + 0.3));
			shader.addChild(treeTranslation);
			
			//treetop
			ColorNode treetopColor = new ColorNode(GREEN[0], GREEN[1], GREEN[2]);
			treeTranslation.addChild(treetopColor);
			
			TranslationNode treetopTranslation = new TranslationNode(new Vector3(0.0, 0.0, 0.0));
			treetopColor.addChild(treetopTranslation);
			SphereNode treetop = new SphereNode(TREETOPRADIUS, RESOLUTION);
			treetopTranslation.addChild(treetop);
			
			//treetrunk
			ColorNode treetrunkColor = new ColorNode(BROWN[0], BROWN[1], BROWN[2]);
			treeTranslation.addChild(treetrunkColor);
			
			RotationNode treetrunkRotation = new RotationNode(90.0, new Vector3(1.0, 0.0, 0.0));
			treetrunkColor.addChild(treetrunkRotation);
			CylinderNode treetrunk = new CylinderNode(TREETRUNKRADIUS, TREETRUNKRADIUS, TREETRUNKHEIGHT, RESOLUTION, true);
			treetrunkRotation.addChild(treetrunk);
		}
		
		//---------------Ground-----------------
		ColorNode groundColor = new ColorNode(GREEN[0], GREEN[1], GREEN[2]);
		shader.addChild(groundColor);
		
		TranslationNode groundTranslation = new TranslationNode(new Vector3(0.0, -TREETRUNKHEIGHT, 0.0));
		groundColor.addChild(groundTranslation);
		CuboidNode ground = new CuboidNode(GROUNDWIDTH, GROUNDHEIGHT, GROUNDDEPTH);
		groundTranslation.addChild(ground);
	}

	@Override
	protected void timerTick() {
		//Rotormovment
		rotationAngle += 30;
		rotorSpin.setAngle(rotationAngle);
		rearrotorSpin.setAngle(rotationAngle);
		
		//Helicoptermovment
		helicopterMovementAngle += 5;
		helicopterRotation.setAngle(helicopterMovementAngle);
	}
	
	public static void main(String[] args) {
		new Aufgabe1(100);
	}

}
