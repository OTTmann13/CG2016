package computergraphics.datastructures;

public class Triangle implements ITriangle{
	
	private int vertexIndexA;
	private int vertexIndexB;
	private int vertexIndexC;
	
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

}
