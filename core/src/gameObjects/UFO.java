package gameObjects;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;

public class UFO extends SpaceObject {
	
	private float height, dv;
	Random rand = new Random();
	
	public UFO(float x, float y) {
		position = new Vector2(x, y);
		velocity = new Vector2();
		edges = 8;
		vertices = new float[edges][2];
		r = 100;
		height = (float) (r * 0.5);
	}
	
	public void update(float delta) {
		move(delta);
		position.add(velocity);
		wrap();
		setVertices();
	}
	
	private void move(float delta) {
		velocity.setZero(); // Wipes the current velocity vector
		velocity.x = delta * 200;
	}
	
	private void setVertices() {
		vertices[0][0] = position.x - r/2;
		vertices[0][1] = position.y;
		
		vertices[5][0] = position.x + r/2;
		vertices[5][1] = position.y;
		
		vertices[1][0] = position.x - r/5;
		vertices[1][1] = position.y + height/3;
		
		vertices[4][0] = position.x + r/5;
		vertices[4][1] = position.y + height/3;
		
		vertices[7][0] = position.x - r/5;
		vertices[7][1] = position.y - height/3;
		
		vertices[6][0] = position.x + r/5;
		vertices[6][1] = position.y - height/3;
		
		vertices[2][0] = position.x - r/8;
		vertices[2][1] = (float) (position.y + 2.1*height/3);
		
		vertices[3][0] = position.x + r/8;
		vertices[3][1] = (float) (position.y + 2.1*height/3);
	}
}