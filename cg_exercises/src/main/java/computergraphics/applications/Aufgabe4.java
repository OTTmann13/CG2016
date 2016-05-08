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
	private static ITriangleMesh mesh = new TriangleMesh();
	
	private final int ISOWERT = 0;
	private final double CUBERESOLUTION = 0.1;
	private final double MATRIX_INTERVAL_MIN = -2;
	private final double MATRIX_INTERVAL_MAX = 2;
	
	private int cubeNumber = 0;
	private int triangleNumber = 0;
	
	
	public Aufgabe4(int timerInterval) {
		super(timerInterval);
		

		createMatrix(MATRIX_INTERVAL_MIN, MATRIX_INTERVAL_MAX, CUBERESOLUTION);

		
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
		
//		for(List<Vector3> points : pointsList) {
//			SquareNode square = new SquareNode(points);
//			squareColor.addChild(square);
//			for(Vector3 point : points) {
//				SphereNode shpere = new SphereNode(0.05, 20);
//				TranslationNode translateSphere = new TranslationNode(point);
//				translateSphere.addChild(shpere);
//				shpereColor.addChild(translateSphere);
//			}
//		}
		
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
		
		ArrayList<Integer> edgeList = new ArrayList<Integer>();
		while(indexBegin <= indexEnd) {
			int edge = faces[indexBegin];
			if(edge != -1) {
				edgeList.add(edge);
				
				Vector3 vertex1 = points.get(edgeMap.get(edge).get(0));
				Vector3 vertex2 = points.get(edgeMap.get(edge).get(1));
				
				double v1 = values.get(points.indexOf(vertex1));
				double v2 = values.get(points.indexOf(vertex2));
				double t = (ISOWERT - v1) / (v2 - v1);
				Vector3 tmp = vertex1.multiply((1 - t));
				Vector3 tmp2 = vertex2.multiply(t);
				Vector3 p = tmp.add(tmp2); 
				
//				Vector3 p = vertex1.add(vertex2).multiply(0.5);
				mesh.addVertex(p);
			}
			indexBegin++;
		}
		
		for(int i = 0; i < edgeList.size(); i += 3) {
			mesh.addTriangle(triangleNumber * 3, (triangleNumber * 3) + 1, (triangleNumber * 3) + 2);
			triangleNumber++;
		}
	}
	
	private void ceateCube(double x, double y, double z, double t) {
		List<Vector3> points = new ArrayList<Vector3>();
		List<Double> values = new ArrayList<Double>();
		
		Vector3 vector1 = new Vector3(x, y, z);
		Vector3 vector2 = new Vector3(x + t, y, z);
		Vector3 vector3 = new Vector3(x + t, y + t, z);
		Vector3 vector4 = new Vector3(x, y + t, z);
		Vector3 vector5 = new Vector3(x, y, z + t);
		Vector3 vector6 = new Vector3(x + t, y, z + t);
		Vector3 vector7 = new Vector3(x + t, y + t, z + t);
		Vector3 vector8 = new Vector3(x, y + t, z + t);
		
		points.add(vector1);
		points.add(vector2);
		points.add(vector3);
		points.add(vector4);
		points.add(vector5);
		points.add(vector6);
		points.add(vector7);
		points.add(vector8);
		
		double r = 1;
		double rr = 4;
		
//		double value1 = kugel(vector1.get(0), vector1.get(1), vector1.get(2), r);
//		double value2 = kugel(vector2.get(0), vector2.get(1), vector2.get(2), r);
//		double value3 = kugel(vector3.get(0), vector3.get(1), vector3.get(2), r);
//		double value4 = kugel(vector4.get(0), vector4.get(1), vector4.get(2), r);
//		double value5 = kugel(vector5.get(0), vector5.get(1), vector5.get(2), r);
//		double value6 = kugel(vector6.get(0), vector6.get(1), vector6.get(2), r);
//		double value7 = kugel(vector7.get(0), vector7.get(1), vector7.get(2), r);
//		double value8 = kugel(vector8.get(0), vector8.get(1), vector8.get(2), r);
		
		double value1 = torus(vector1.get(0), vector1.get(1), vector1.get(2), r, rr);
		double value2 = torus(vector2.get(0), vector2.get(1), vector2.get(2), r, rr);
		double value3 = torus(vector3.get(0), vector3.get(1), vector3.get(2), r, rr);
		double value4 = torus(vector4.get(0), vector4.get(1), vector4.get(2), r, rr);
		double value5 = torus(vector5.get(0), vector5.get(1), vector5.get(2), r, rr);
		double value6 = torus(vector6.get(0), vector6.get(1), vector6.get(2), r, rr);
		double value7 = torus(vector7.get(0), vector7.get(1), vector7.get(2), r ,rr);
		double value8 = torus(vector8.get(0), vector8.get(1), vector8.get(2), r, rr);
		
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
	
	private double kugel(double x, double y, double z, double radius) {
		return Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2) - Math.pow(radius, 2);
	}
	
	private double torus(double x, double y, double z, double rI, double rA) {
		return Math.pow((Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2) - Math.pow(rI, 2)),2) - Math.pow(4*rA, 2) * (Math.pow(x, 2) + Math.pow(y, 2));
	}
	
	private double quad(double x, double y, double z, double r) {
		return x * y * z - r;
	}
	
	private void createMatrix(double begin, double end, double step) {
		
		for(double x = begin; x < end; x += step) {
			for(double y = begin; y < end; y += step) {
				for(double z = begin; z < end; z += step) {
					ceateCube(x, y, z, step);
				}
			}
		}
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
