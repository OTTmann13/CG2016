package computergraphics.datastructures;

import java.util.ArrayList;
import java.util.List;

import computergraphics.math.Vector3;

public class TriangleMesh implements ITriangleMesh{
	
	private List<ITriangle> triangles = new ArrayList<ITriangle>();
	private List<IVertex> vertices = new ArrayList<IVertex>();

	@Override
	public void addTriangle(int vertexIndex1, int vertexIndex2, int vertexIndex3) {
		ITriangle triangle = new Triangle(vertexIndex1, vertexIndex2, vertexIndex3);
		triangles.add(triangle);
	}

	@Override
	public int addVertex(IVertex v) {
		vertices.add(v);
		return vertices.indexOf(v);
	}

	@Override
	public int addVertex(Vector3 position) {
		IVertex vertex = new Vertex(position, position);
		vertices.add(vertex);
		return vertices.indexOf(vertex);
	}

	@Override
	public int getNumberOfTriangles() {
		return triangles.size();
	}

	@Override
	public int getNumberOfVertices() {
		return vertices.size();
	}

	@Override
	public IVertex getVertex(int index) {
		return vertices.get(index);
	}

	@Override
	public ITriangle getTriangle(int triangleIndex) {
		return triangles.get(triangleIndex);
	}

	@Override
	public void clear() {
		vertices.clear();
		triangles.clear();
		
	}

	@Override
	public void setTextureFilename(String textureFilename) {
		// TODO Auto-generated method stub
		
	}

}
