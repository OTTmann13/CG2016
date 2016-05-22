package computergraphics.datastructures.polygon;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import computergraphics.math.Vector3;

public class Polygon {
	private List<Vertex> vertecies = new ArrayList<Vertex>();
	
	private boolean closed;
	
	/**
	 * instert a new vertex at the given index
	 * @param index wich the new vertex get added
	 * @param position for the new vertex
	 */
	public void insertAt(int index, Vector3 position) {
		Vertex vertex = new Vertex(position);
		vertecies.add(index, vertex);
	}
	
	/**
	 * insert nodes in the polygon between each pair of vertecies
	 */
	public void split() {
		if(closed){
			//create a copy of the vertecies-list to iterate with out modifyed objects
			List<Vertex> tmp = new ArrayList<Vertex>();
			
			for(Vertex v : vertecies) {
				Vertex vTmp = new Vertex(v);
				tmp.add(vTmp);
			}
			
			Vector3 vector1 = new Vector3();
			Vector3 vector2 = new Vector3();
			
			//collect the current and next vector from the copyed list
			for(int i = 0; i < tmp.size(); i++) {
				vector1.copy(tmp.get(i).getPosition());
				if(i + 1 >= tmp.size()) {
					vector2.copy(tmp.get(0).getPosition());
				} else {
					vector2.copy(tmp.get(i + 1).getPosition());
				}
				
				//create a new vector between those two vertecies
				Vector3 newVector = vector1.add(vector2).multiply(0.5);
				
				//add the new vertex to the vertecies-list
				insertAt((i * 2) + 1, newVector);
			}
		} else {
			System.err.println("Polygon must be closed!");
		}
	}
	
	/**
	 * calculate the average between the current vertex and the next vertex and set the new postion to the current vertex
	 * reapet for all vertecies
	 * the last node has the first vertex for next
	 */
	public void avgerage() {
		if(closed) {
			//create a copy of the vertecies-list to iterate with out modifyed objects
			List<Vertex> tmp = new ArrayList<Vertex>();
			
			for(Vertex v : vertecies) {
				Vertex vTmp = new Vertex(v);
				tmp.add(vTmp);
			}
			Vector3 vector1 = new Vector3();
			Vector3 vector2 = new Vector3();
			
			//collect the current and next vector from the copyed list
			for(int i = 0; i < tmp.size(); i++) {
				vector1.copy(tmp.get(i).getPosition());
				if(i + 1 >= tmp.size()) {
					vector2.copy(tmp.get(0).getPosition());
				} else {
					vector2.copy(tmp.get(i + 1).getPosition());
				}
				
				//calculate the average between those two vertecies
				Vector3 newVector = new Vector3(vector1.multiply(0.5).add(vector2.multiply(0.5)));
				
				//set the new position
				vertecies.get(i).setPosition(newVector);
			}
		} else {
			System.err.println("Polygon must be closed!");
		}
	}
	
	public void setClosed(boolean closed) {
		this.closed = closed;
	}
	
	public void addVertex(double x, double y, double z) {
		Vertex vertex = new Vertex(x, y, z);
		vertecies.add(vertex);
	}
	
	public void addVertex(Vector3 position) {
		Vertex vertex = new Vertex(position);
		vertecies.add(vertex);
	}
	
	public Vertex getVertex(int i) {
		return vertecies.get(i);
	}
	
	public int getNumberOfVertecies() {
		return vertecies.size();
	}
	
	public boolean getClosedState() {
		return closed;
	}
}
