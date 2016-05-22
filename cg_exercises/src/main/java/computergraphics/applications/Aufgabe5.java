package computergraphics.applications;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.plaf.synth.SynthDesktopIconUI;

import computergraphics.datastructures.polygon.Polygon;
import computergraphics.framework.AbstractCGFrame;
import computergraphics.math.Vector3;
import computergraphics.scenegraph.ColorNode;
import computergraphics.scenegraph.PolygonNode;
import computergraphics.scenegraph.ShaderNode;
import computergraphics.scenegraph.ShaderNode.ShaderType;
import computergraphics.scenegraph.SphereNode;
import computergraphics.scenegraph.TranslationNode;

public class Aufgabe5 extends AbstractCGFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 685837369852550184L;
	private Polygon polygon = new Polygon();
	
	private final int RESOLUTION = 7;
	
	public Aufgabe5(int timerInterval) {
		super(timerInterval);
		
		readPolygon("square.polygon");
		
		for(int i = 0; i < RESOLUTION; i++) {
			polygon.split();
			polygon.avgerage();
		}
		
		
		PolygonNode pn = new PolygonNode(polygon);
		getRoot().addChild(pn);
		
		ShaderNode shader = new ShaderNode(ShaderType.PHONG);
		getRoot().addChild(shader);
		
		//Add commented code to show the nodes (not recommended with a higher iteration)
//		for(int i = 0; i < polygon.getNumberOfVertecies(); i++) {
//			ColorNode c = new ColorNode(1.0, 0.0, 0.0);
//			shader.addChild(c);
//			SphereNode sphere = new SphereNode(0.01, 30);
//			TranslationNode translateSphere = new TranslationNode(polygon.getVertex(i).getPosition());
//			translateSphere.addChild(sphere);
//			c.addChild(translateSphere);
//		}
	}
	
	
	/**
	 * read a text file and create a polygon with given data
	 * @param fileName name of the text file example: example.polygon
	 */
	public void readPolygon(String fileName) {
		FileReader fr;
		try {
			//Read file
			fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			
			//Check for open or close polygon
			String line = br.readLine();
			if(line.equals("CLOSED")) {
				polygon.setClosed(true);
			}else if(line.equals("OPEN")) {
				polygon.setClosed(false);
			}
			
			//read all points for the polygon
			line = br.readLine();
			while(line != null) {
				String[] points = line.split("\\s+");
				
				double x = Float.parseFloat(points[0].replace(",", "."));
				double y = Float.parseFloat(points[1].replace(",", "."));
				double z = Float.parseFloat(points[2].replace(",", "."));
				
				polygon.addVertex(x, y, z);
				line = br.readLine();
			}
		} catch (Exception e) {
			System.err.println(e);
		}	
	}
	
	@Override
	protected void timerTick() {
		// TODO Auto-generated method stub
		
	}
		
	public static void main(String[] args) {
		Aufgabe5 a5 = new Aufgabe5(1000);
	}


}
