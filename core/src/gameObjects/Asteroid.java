package gameObjects;

import java.util.Arrays;
import java.util.Random;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Asteroid extends SpaceObject {
	
	private float[] angles;
	private float[] radii;
	private int v, dr;
	private double area;
	private float rotation;
	Random rand = new Random();
	
	public Asteroid(float x, float y, int r, int v, int hg) {
		this.r = r;		
		setProperties();
		
		position = new Vector2(x, y);	
		velocity = new Vector2();
		this.v = v;
		heading = hg;
		rotation = 0;
		dr = rand.nextInt(21) - 20;
		
		angles = new float[edges];
		radii = new float[edges];
		vertices = new float[edges][2];

		generateAngles();
		generateRadii();
	}
	
	public void update(float delta) {
		move(delta);
		position.add(velocity);
		rotate(delta);
		wrap();
		setVertices();
	}
	
	private void rotate(float delta) {
		rotation += dr * delta;
	}
	
	private void move(float delta) {
		velocity.setZero(); // Wipes the current velocity vector
		float radians = (float) Math.toRadians(heading);
		
		velocity.x = MathUtils.cos(radians) * delta * v;
		velocity.y = MathUtils.sin(radians) * delta * v;
	}
	
	private void setProperties() {
		area = Math.PI * Math.pow(r, 2);
		
		if(r < 30) { edges = 12; }
		if(r >= 30) { edges = 14; }
		if(r >= 40) { edges = 15; }
		if(r >= 50) { edges = 17; }
		if(r >= 60) { edges = 19; }
		if(r >= 70) { edges = 20; }
		if(r >= 90) { edges = 22; }
	}

	private void setVertices() {
		float radians;
		
		for(int i = 0; i < edges; i ++) {
			radians = (float) Math.toRadians(angles[i] + rotation);
			
			// x-coordinate
			vertices[i][0] = position.x + MathUtils.cos(radians) * radii[i];
			
			// y-coordinate
			vertices[i][1] = position.y + MathUtils.sin(radians) * radii[i];
		}
	}

	private void generateRadii() {
		int diff = edges / 2;
		double maxRadius = r + diff;
		double minRadius = r - diff;

		for(int i = 0; i < edges; i++) {
			double r = (rand.nextInt((int) ((maxRadius - minRadius) + 1)) + minRadius);
			
			radii[i] = (float) r;
		}
	}
	
	private void generateAngles() {
		float a = 360 / edges; // Regular difference between each angle
		
		for(int i = 0; i < edges; i++) {
			int da = rand.nextInt(11) - 5; // Generate number between -5, 5
			angles[i] = (a * i) + da; // Add change to offset angles a little
		}
		
		Arrays.sort(angles); // Order the angles ascending
	}
	
	public double getArea() {
		return area;
	}
	
	public int getV() {
		return v;
	}
	
	public double getAvgRadius() {
		return r;
	}
}

