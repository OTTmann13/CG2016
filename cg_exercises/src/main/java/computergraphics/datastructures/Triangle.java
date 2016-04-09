package computergraphics.datastructures;

import computergraphics.math.Vector3;

public class Triangle implements ITriangle{
	
	private int vertexIndexA;
	private int vertexIndexB;
	private int vertexIndexC;
	
	private final Vector3 normal = new Vector3();
	
	public Triangle(int vertexIndexA, int vertexIndexB, int vertexIndexC) {
		this.vertexIndexA = vertexIndexA;
		this.vertexIndexB = vertexIndexB;
		this.vertexIndexC = vertexIndexC;
	}

	@Override
	public int getVertexIndexA() {
		return vertexIndexA;
	}

	@Override
	public int getVertexIndexB() {
		return vertexIndexB;
	}

	@Override
	public int getVertexIndexC() {
		return vertexIndexC;
	}

	@Override
	public int getVertexIndex(int i) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void setNormal(Vector3 normal) {
		this.normal.copy(normal);
	}
	
	public Vector3 getNormal() {
		return normal;
	}

}
