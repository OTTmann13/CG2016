package computergraphics.applications;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import computergraphics.datastructures.ITriangleMesh;
import computergraphics.datastructures.TriangleMesh;
import computergraphics.framework.AbstractCGFrame;
import computergraphics.math.Vector3;
import computergraphics.scenegraph.ColorNode;
import computergraphics.scenegraph.ShaderNode;
import computergraphics.scenegraph.SphereNode;
import computergraphics.scenegraph.SquareNode;
import computergraphics.scenegraph.TranslationNode;
import computergraphics.scenegraph.TriangleMeshNode;

public class Aufgabe4 extends AbstractCGFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5627500506044803004L;
	private static int faces[];
	private static List<List<Vector3>> pointsList = new ArrayList<>();
	private static List<List<Double>> valuesList = new ArrayList<>();
	private static Map<Integer, List<Integer>> edgeMap = new HashMap<Integer, List<Integer>>();
	
	private final int ISOWERT = 6;
	private static ITriangleMesh mesh = new TriangleMesh();
	private int cubeNumber = 0;
	
	public Aufgabe4(int timerInterval) {
		super(timerInterval);
		
		ceateCube(0, 0.5);
		ceateCube(0.5, 1);
		ceateCube(0, 1);
		ceateCube(1.0, 1.5);
		ceateCube(0, 1.5);
		while(cubeNumber < pointsList.size()) {
			createTriangles(pointsList.get(cubeNumber), valuesList.get(cubeNumber));
			cubeNumber++;
		}
		
		ColorNode squareColor = new ColorNode(0, 0, 1);
		getRoot().addChild(squareColor);
		
		
		
		ShaderNode shader = new ShaderNode(ShaderNode.ShaderType.PHONG);
		getRoot().addChild(shader);
		
		ColorNode shpereColor = new ColorNode(0, 0, 1);
		shader.addChild(shpereColor);
		
		for(List<Vector3> points : pointsList) {
			SquareNode square = new SquareNode(points);
			squareColor.addChild(square);
			for(Vector3 point : points) {
				SphereNode shpere = new SphereNode(0.05, 20);
				TranslationNode translateSphere = new TranslationNode(point);
				translateSphere.addChild(shpere);
				shpereColor.addChild(translateSphere);
			}
		}
//		
		ColorNode triangleColor = new ColorNode(1, 0, 0);
		shader.addChild(triangleColor);
		
		TriangleMeshNode tmn = new TriangleMeshNode(mesh);
		triangleColor.addChild(tmn);
	}
	
	@Override
	protected void timerTick() {
		 
	}
	
	private void createTriangles(List<Vector3> points, List<Double> values){
		int caseIndex = 0;
		
		for(int i = 0; i < points.size(); i++) {
			
			int b = (values.get(i) > ISOWERT)? 1 : 0;
			
			switch (i) {
			case 0: caseIndex += b * 1;
					break;
			case 1: caseIndex += b * 2;
					break;
			case 2: caseIndex += b * 4;
					break;
			case 3: caseIndex += b * 8;
					break;
			case 4: caseIndex += b * 16;
					break;
			case 5: caseIndex += b * 32;
					break;
			case 6: caseIndex += b * 64;
					break;
			case 7: caseIndex += b * 128;
					break;
			default:
					break;
			}
		}
		
		int indexBegin = caseIndex * 15;
		int indexEnd = (caseIndex + 1) * 15 - 1;
		
		List<Integer> edges = new ArrayList<Integer>();
		while(indexBegin <= indexEnd) {
			int edge = faces[indexBegin];
			if(edge != -1) {
				edges.add(edge);
				Vector3 vertex1 = points.get(edgeMap.get(edge).get(0));
				Vector3 vertex2 = points.get(edgeMap.get(edge).get(1));
				System.out.println(points.indexOf(vertex1));
				double v1 = values.get(points.indexOf(vertex1));
				double v2 = values.get(points.indexOf(vertex2));
				double t = (ISOWERT - v1) / (v2 - v1);
				Vector3 p = vertex1.multiply((1 - t)).add(vertex2.multiply(t)); 
	
//				Vector3 newVertex = vertex1.add(vertex2).multiply(0.5);
				mesh.addVertex(p);
			}
			indexBegin++;
		}
		
		for(int i = 0; i < edges.size(); i += 3) {
			mesh.addTriangle(cubeNumber, cubeNumber + 1, cubeNumber + 2);
		}
	}
	
	private void ceateCube(double max, double min) {
		List<Vector3> points = new ArrayList<Vector3>();
		List<Double> values = new ArrayList<Double>();
		
		Vector3 vector1 = new Vector3(min, min, min);
		Vector3 vector2 = new Vector3(max, min, min);
		Vector3 vector3 = new Vector3(max, max, min);
		Vector3 vector4 = new Vector3(min, max, min);
		Vector3 vector5 = new Vector3(min, min, max);
		Vector3 vector6 = new Vector3(max, min, max);
		Vector3 vector7 = new Vector3(max, max, max);
		Vector3 vector8 = new Vector3(min, max, max);
		
		points.add(vector1);
		points.add(vector2);
		points.add(vector3);
		points.add(vector4);
		points.add(vector5);
		points.add(vector6);
		points.add(vector7);
		points.add(vector8);
		
		double value1 = 5;
		double value2 = 3;
		double value3 = 2;
		double value4 = 7;
		double value5 = 0;
		double value6 = 0;
		double value7 = 0;
		double value8 = 0;
		
		values.add(value1);
		values.add(value2);
		values.add(value3);
		values.add(value4);
		values.add(value5);
		values.add(value6);
		values.add(value7);
		values.add(value8);
		
		pointsList.add(points);
		valuesList.add(values);
	}
	
	
	public static void main(String[] args) {
		Faces f = new Faces();
		faces = f.getFaces();
		
		edgeMap.put(0, Arrays.asList(0, 1));
		edgeMap.put(1, Arrays.asList(1, 2));
		edgeMap.put(2, Arrays.asList(2, 3));
		edgeMap.put(3, Arrays.asList(0, 3));
		edgeMap.put(4, Arrays.asList(4, 5));
		edgeMap.put(5, Arrays.asList(5, 6));
		edgeMap.put(6, Arrays.asList(6, 7));
		edgeMap.put(7, Arrays.asList(4, 7));
		edgeMap.put(8, Arrays.asList(0, 4));
		edgeMap.put(9, Arrays.asList(1, 5));
		edgeMap.put(10, Arrays.asList(3, 7));
		edgeMap.put(11, Arrays.asList(2, 6));
		
		
		
		Aufgabe4 a4 = new Aufgabe4(1000);
	}


	
}
