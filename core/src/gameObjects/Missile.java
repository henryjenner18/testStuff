package gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Missile extends SpaceObject {
	
	private int l;
	private Vector2 rocketVel;
	private float timeLeft;
	private Rocket rocket;
	
	public Missile(Rocket rocket) {
		this.rocket = rocket;
		setTimeLeft(2);
		
		heading = rocket.getHeading();
		float radians = (float) Math.toRadians(heading);
		float x = rocket.getX() + MathUtils.cos(radians) * rocket.getHeight() / 2;
		float y = rocket.getY() + MathUtils.sin(radians) * rocket.getHeight() / 2;
		
		position = new Vector2(x, y);
		velocity = new Vector2();
		rocketVel = new Vector2(rocket.getVelocity());
		vertices = new float[2][2];
		edges = vertices.length;
		l = 30;
	}
	
	public void update(float delta) {
		timeLeft -= delta;
		if(timeLeft <= 0) {
			setTimeLeft(0);
		}
		move(delta);
		position.add(velocity);
		wrap();
		setVertices();
	}
	
	private void setVertices() {
		float radians = (float) Math.toRadians(heading);
		
		vertices[0][0] = position.x;
		vertices[0][1] = position.y;
		vertices[1][0] = position.x + MathUtils.cos(radians) * l;
		vertices[1][1] = position.y + MathUtils.sin(radians) * l;
	}
	
	private void move(float delta) {
		velocity.setZero(); // Wipes the current velocity vector
		float radians = (float) Math.toRadians(heading);
		
		velocity.x = MathUtils.cos(radians) * delta * 800;
		velocity.y = MathUtils.sin(radians) * delta * 800;
		
		velocity.add(rocketVel);
	}
	
	public void setTimeLeft(int i) {
		timeLeft = i;
	}
	
	public float getTimeLeft() {
		return timeLeft;
	}
}
