package gameManager;

import java.util.ArrayList;

import gameObjects.SpaceObject;

public class CollisionDetector {
	
	private World world;
	
	public CollisionDetector(World world) {
		this.world = world;
	}

	public void manage() {
		checkForCollisions(world.getRockets(), world.getAsteroids());
	}
	
	public void checkForCollisions(ArrayList<?> obj1, ArrayList<?> obj2) {
		
		for(int i = 0; i < obj1.size(); i++) {
			if(obj1.get(i) instanceof SpaceObject) {

				((SpaceObject) obj1.get(i)).linearEquation();
				float[][] obj1Vertices = ((SpaceObject) obj1.get(i)).getVertices();
				float[] obj1Gradients = ((SpaceObject) obj1.get(i)).getGradients();
				float[] obj1Yintercepts = ((SpaceObject) obj1.get(i)).getYintercepts();
				int obj1Edges = ((SpaceObject) obj1.get(i)).getEdges();
				
				for(int e = 0; e < obj1Edges; e++) {
					
					for(int a = 0; a < obj2.size(); a++) {
						if(obj2.get(a) instanceof SpaceObject) {
							
							((SpaceObject) obj2.get(a)).linearEquation();
							float[][] obj2Vertices = ((SpaceObject) obj2.get(a)).getVertices();
							float[] obj2Gradients = ((SpaceObject) obj2.get(a)).getGradients();
							float[] obj2Yintercepts = ((SpaceObject) obj2.get(a)).getYintercepts();
							int obj2Edges = ((SpaceObject) obj2.get(a)).getEdges();
							
							for(int q = 0; q < obj2Edges; q++) {
								
								// Check for infinity gradient
							}
						}
					}
				}	
			}			
		}
	}
}
