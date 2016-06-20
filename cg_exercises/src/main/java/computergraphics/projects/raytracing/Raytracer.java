package computergraphics.projects.raytracing;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import computergraphics.datastructures.IntersectionResult;
import computergraphics.datastructures.Ray3D;
import computergraphics.framework.Camera;
import computergraphics.math.Vector3;
import computergraphics.scenegraph.LightSource;
import computergraphics.scenegraph.Node;
import computergraphics.scenegraph.PlainNode;
import computergraphics.scenegraph.SphereNode;

/**
 * Creates a raytraced image of the current scene.
 */
public class Raytracer {

  /**
   * Reference to the current camera.
   */
  private final Camera camera;
  private List<Node> childNodes = new ArrayList<Node>();
  private LightSource light = new LightSource(new Vector3(5, 5, 10),new Vector3(1, 1, 1));
  
  /**
   * Constructor.
   * 
   * @param camera
   *          Scene camera.
   * @param rootNode
   *          Root node of the scenegraph.
   */
  public Raytracer(Camera camera, Node rootNode) {
    this.camera = camera;
    
    this.childNodes = rootNode.getAllChildNodes();
  }

  /**
   * Creates a raytraced image for the current view with the provided
   * resolution. The opening angle in x-direction is grabbed from the camera,
   * the opening angle in y-direction is computed accordingly.
   * 
   * @param resolutionX
   *          X-Resolution of the created image.
   * 
   * @param resolutionX
   *          Y-Resolution of the created image.
   */
  public Image render(int resolutionX, int resolutionY) {
    BufferedImage image = new BufferedImage(resolutionX, resolutionY, BufferedImage.TYPE_INT_RGB);

    Vector3 viewDirection = camera.getRef().subtract(camera.getEye()).getNormalized();
    Vector3 xDirection = viewDirection.cross(camera.getUp()).getNormalized();
    Vector3 yDirection = viewDirection.cross(xDirection).getNormalized();
    double openingAngleYScale = Math.sin(camera.getOpeningAngle() * Math.PI / 180.0);
    double openingAngleXScale = openingAngleYScale * (double) resolutionX / (double) resolutionY;

    for (int i = 0; i < resolutionX; i++) {
      double alpha = (double) i / (double) (resolutionX + 1) - 0.5;
      for (int j = 0; j < resolutionY; j++) {
        double beta = (double) j / (double) (resolutionY + 1) - 0.5;
        Vector3 rayDirection = viewDirection.add(xDirection.multiply(alpha * openingAngleXScale))
            .add(yDirection.multiply(beta * openingAngleYScale)).getNormalized();
        Ray3D ray = new Ray3D(camera.getEye(), rayDirection);

        Vector3 color = trace(ray, 0);

        // Adjust color boundaries
        for (int index = 0; index < 3; index++) {
          color.set(index, Math.max(0, Math.min(1, color.get(index))));
        }

        image.setRGB(i, j,
            new Color((int) (255 * color.get(0)), (int) (255 * color.get(1)), (int) (255 * color.get(2))).getRGB());
      }
    }

    return image;
  }

  /**
   * Compute a color from tracing the ray into the scene.
   * 
   * @param ray
   *          Ray which needs to be traced.
   * @param recursion
   *          Current recursion depth. Initial recursion depth of the rays
   *          through the image plane is 0. This parameter is used to abort the
   *          recursion.
   * 
   * @return Color in RGB. All values are in [0,1];
   */
  private Vector3 trace(Ray3D ray, int recursion) {

	//recursvion terminator
	if (recursion > 2) {
		
		return new Vector3(0, 0, 0);
	}
	
	IntersectionResult intersection = null;
	
	//current closest object distance
	double current_min_distance = Double.MAX_VALUE;
	
	for (Node node: childNodes) {
		
		if (node.getClass().equals(SphereNode.class) || node.getClass().equals(PlainNode.class)) {
		
			IntersectionResult result = node.findIntersection(node, ray);
		
			if (result != null) {
		
				double distance = ray.getPoint().subtract(result.point).getSqrNorm(); 
				
				//found closer object
				if (distance < current_min_distance) {
					
					intersection = result;
					current_min_distance = distance;
				}			
			}
		}
	}
		
	if (intersection != null) {
		
		boolean object_in_shadow = checkShadow(intersection);
		
		Vector3 color = intersection.object.getColor();
		
		if(!object_in_shadow) {
			color = getLightningColor(intersection, ray, color);
			
		}else{
			color = new Vector3(0, 0, 0);
//			color = color.multiply(0.1);
		}		
		
		if (intersection.object.getReflection() > 0) {
			color = color.multiply(1 - intersection.object.getReflection());
			
			Vector3 N = intersection.normal;
			Vector3 L = ray.getDirection();
			
			//2(LN)N - L
			Vector3 ideal_reflection = L.subtract(N.multiply(2 * L.multiply(N)));
			
			Vector3 reflected_color = trace(new Ray3D(intersection.point,ideal_reflection), recursion + 1);
			
			reflected_color = reflected_color.multiply(intersection.object.getReflection());
			
			color = color.add(reflected_color);
		}
		
		return color;
	}
	
    return new Vector3(0, 0, 0);
  }
  
  private boolean checkShadow(IntersectionResult intersection) {
	  
	  for (Node n: childNodes) {
		  
		  if (!n.equals(intersection.object)) {
			  
			  IntersectionResult intersection_with_blocking_object = n.findIntersection(intersection.object, new Ray3D(intersection.point, light.getPosition().subtract(intersection.point).getNormalized()));
			  
			  if (intersection_with_blocking_object != null) {
					
				  return true;
			  }
		  }
	  }
	  return false;
  }
  
  
  private Vector3 getLightningColor(IntersectionResult intersection, Ray3D ray, Vector3 objectColor) {
	  int m = 20;
	
	  Vector3 N = intersection.normal;
	  Vector3 Vs = ray.getDirection().getNormalized();
	  Vector3 L = intersection.point.subtract(light.getPosition()).getNormalized();
	  
	  double NL = N.multiply(L);
	  Vector3 R = L.subtract(N.multiply(2*NL));
	  double RVs = R.multiply(Vs.multiply(-1));
	  
	  //initilize with black
	  Vector3 colorDiff = new Vector3(0, 0, 0); 
	  Vector3 colorSpec = new Vector3(0, 0, 0);
	  
	  if (NL > 0) {
		  
		  colorDiff = objectColor.multiply(NL);
	  }
	  
	  if (RVs > 0) {
		  
		  colorSpec = (new Vector3(1, 1, 1)).multiply(Math.pow(RVs, m)); //.multiply(new Vector3(1,1,1));
	  }
	  
	  return colorDiff.add(colorSpec);
  }
  

  

}