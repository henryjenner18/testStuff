package gameObjects;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import main.Main;

public class Rocket extends SpaceObject {
	
	private int height, dh, terminalVel;
	private boolean thrusting, left, right;
	
	public Rocket() {
		position = new Vector2();
		velocity = new Vector2();
		vertices = new float[4][2];
		height = 90;
		r = height / 2;
		edges = vertices.length;
		dh = 4;
		terminalVel = 10;
		reset();
	}
	
	public void update(float delta) {
		if(thrusting) {
			thrust(delta);
		}
		
		terminalVelCheck();
		position.add(velocity);
		
		if(left) heading += dh;
		if(right) heading -= dh;
		
		wrap();
		setVertices();
	}
	
	private void thrust(float delta) {
		Vector2 force = new Vector2();
		float radians = (float) Math.toRadians(heading);
		
		force.x = MathUtils.cos(radians) * delta * 10;
		force.y = MathUtils.sin(radians) * delta * 10;
		
		velocity.add(force);	
	}
	
	private void terminalVelCheck() {
		double resultantVel = Math.sqrt(Math.pow(velocity.x, 2) + Math.pow(velocity.y, 2)); // Pythagoras' theorem
		
		if(resultantVel > terminalVel) {
			velocity.x = (float) ((velocity.x / resultantVel) * terminalVel); // Reduce x and y components
			velocity.y = (float) ((velocity.y / resultantVel) * terminalVel);
		}
	}

	private void reset() {
		position.x = Main.getWidth() / 2;
		position.y = Main.getHeight() / 2;
		velocity.x = 0;
		velocity.y = 0;
		heading = 90;
	}
	
	private void setVertices() {
		float radians = (float) Math.toRadians(heading);
		
		vertices[0][0] = position.x + MathUtils.cos(radians) * height / 2;
		vertices[0][1] = position.y + MathUtils.sin(radians) * height / 2;
		
		vertices[1][0] = position.x + MathUtils.cos(radians + 3 * MathUtils.PI / 4) * height / 3;
		vertices[1][1] = position.y + MathUtils.sin(radians + 3 * MathUtils.PI / 4) * height / 3;
		
		vertices[2][0] = position.x - MathUtils.cos(radians) * (height) / 6;
		vertices[2][1] = position.y - MathUtils.sin(radians) * (height) / 6;
				
		vertices[3][0] = position.x + MathUtils.cos(radians - 3 * MathUtils.PI / 4) * height / 3;
		vertices[3][1] = position.y + MathUtils.sin(radians - 3 * MathUtils.PI / 4) * height / 3;
	}
	
	public void setThrusting() {
		thrusting = !thrusting;
	}
	
	public void setLeft() {
		left = !left;
	}
	
	public void setRight() {
		right = !right;
	}
	
	public int getHeight() {
		return height;
	}	
}
