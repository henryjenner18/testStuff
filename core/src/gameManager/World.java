package gameManager;

import java.util.ArrayList;
import java.util.Random;

import gameObjects.Asteroid;
import gameObjects.Missile;
import gameObjects.Rocket;
import gameObjects.UFO;
import main.Main;

public class World {
	
	private static ArrayList<Rocket> rockets;
	private static ArrayList<Asteroid> asteroids;
	private static ArrayList<Missile> missiles;
	private static ArrayList<UFO> ufos;
	Random rand = new Random();
	
	public World() {
		rockets = new ArrayList<Rocket>();
		asteroids = new ArrayList<Asteroid>();
		missiles = new ArrayList<Missile>();
		ufos = new ArrayList<UFO>();
		spawnRocket();
	}

	public void update(float delta) {
		if(asteroids.size() == 0) {
			for(int i = 0; i < 3; i++) {
				newAsteroid();
			}
		}
		
		if(ufos.size() == 0) {
			//spawnUFO();
		}
		
		for(int i = 0; i < rockets.size(); i++) {
			rockets.get(i).update(delta);
		}
		
		for(int i = 0; i < asteroids.size(); i++) {
			asteroids.get(i).update(delta);
		}
		
		for(int i = 0; i < missiles.size(); i++) {
			missiles.get(i).update(delta);
		}
		
		for(int i = 0; i < ufos.size(); i++) {
			ufos.get(i).update(delta);
		}
	}
	
	private void newAsteroid() {
		int f = 5; // Frame size, max hori/vert distance they can be away from the edges
		float x = 0, y = 0; // Initialise x and y
		float w = Main.getWidth(), h = Main.getHeight();
		boolean acceptable = false;
		
		while(acceptable == false) { // While acceptable coordinates haven't been found
			x = rand.nextInt((int) (w + 1));
			y = rand.nextInt((int) (h + 1));
			
			if(x >= f && x <= w - f && y >= f && y <= h - f) { // Check to see if not in frame
				acceptable = false;
			} else {
				acceptable = true;
			}
		}
		int r = rand.nextInt(21) + 90;
		int v = rand.nextInt(101) + 150;
		int hg = rand.nextInt(361);
		spawnAsteroid(x, y, r, v, hg);
	}
	
	public void spawnAsteroid(float x, float y, int r, int v, int hg) {
		if(r >= 15) {
			Asteroid asteroid = new Asteroid(x, y, r, v, hg);
			asteroids.add(asteroid);
		}	
	}
	
	public void spawnMissile() {
		Missile missile = new Missile(rockets.get(0));
		missiles.add(missile);
	}
	
	public void spawnUFO() {
		float x = rand.nextInt(2201);
		float y = rand.nextInt(1251);
		UFO ufo = new UFO(x, y);
		ufos.add(ufo);
	}
	
	private void spawnRocket() {
		Rocket rocket = new Rocket();
		rockets.add(rocket);
	}
	
	public void removeMissile(int i) {
		missiles.remove(i);
	}
	
	public ArrayList<Rocket> getRockets() {
		return rockets;
	}
	
	public Rocket getRocket(int i) {
		return rockets.get(i);
	}
	
	public int getNumRockets() {
		return rockets.size();
	}
	
	public ArrayList<Asteroid> getAsteroids() {
		return asteroids;
	}
	
	public Asteroid getAsteroid(int i) {
		return asteroids.get(i);
	}
	
	public int getNumAsteroids() {
		return asteroids.size();
	}
	
	public Missile getMissile(int i) {
		return missiles.get(i);
	}
	
	public int getNumMissiles() {
		return missiles.size();
	}
	
	public UFO getUFO(int i) {
		return ufos.get(i);
	}
	
	public int getNumUFOs() {
		return ufos.size();
	}
}
