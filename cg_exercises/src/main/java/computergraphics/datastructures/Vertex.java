package computergraphics.datastructures;

import computergraphics.math.Vector3;

public class Vertex implements IVertex{
	
	private final Vector3 position = new Vector3(0,0,0);
	private final Vector3 normal = new Vector3(1,0,0);
	
	public Vertex(Vector3 position, Vector3 normal) {
		this.position.copy(position);
		this.normal.copy(normal);
	}

	@Override
	public Vector3 getPosition() {
		return position;
	}

	@Override
	public Vector3 getNormal() {
		return normal;
	}

}
