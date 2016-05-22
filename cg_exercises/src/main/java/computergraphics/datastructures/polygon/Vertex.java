package computergraphics.datastructures.polygon;

import computergraphics.math.Vector3;

public class Vertex {
	private Vector3 position;
	
	public Vertex(double x, double y, double z) {
		this.position = new Vector3(x, y, z);
	}
	
	public Vertex(Vector3 position) {
		this.position = position;
	}
	
	public Vertex(Vertex other) {
		this.position = other.getPosition();
	}
	
	public void setPosition(double x, double y, double z) {
		position = new Vector3(x, y, z);
	}
	
	public void setPosition(Vector3 position) {
		this.position = position;
	}
	
	public Vector3 getPosition() {
		return position;
	}
	
	public String toString() {
		return "position: " + position;
	}
}
