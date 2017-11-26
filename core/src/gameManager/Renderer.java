package gameManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import main.Main;

public class Renderer {
	
	private World world;
	private OrthographicCamera cam;
	private ShapeRenderer sr;
	
	public Renderer(World world) {
		this.world = world;
		int w = Main.getWidth();
		int h = Main.getHeight();
		cam = new OrthographicCamera(w, h);
		cam.translate(w / 2, h / 2);
		cam.update();
		sr = new ShapeRenderer();
		sr.setProjectionMatrix(cam.combined);
	}

	public void render() {
		drawBackground();
		drawAsteroids();
		drawMissiles();
		drawUFOs();
		drawRockets();
	}
	
	private void drawRockets() {
		int numRockets = world.getNumRockets();
		
		for(int i = 0; i < numRockets; i++) {
			float vertices[][] = world.getRocket(i).getVertices();
			int edges = world.getRocket(i).getEdges();
			float[] polygon = polygonArray(vertices, edges);
			
			Gdx.gl.glLineWidth(3);
			sr.begin(ShapeType.Line);
			sr.setColor(0, 0, 0, 1);
			sr.polygon(polygon);
			sr.end();
		}
	}
	
	private void drawAsteroids() {
		int numAsteroids = world.getNumRockets();
		
		for(int i = 0; i < numAsteroids; i++) {
			float vertices[][] = world.getAsteroid(i).getVertices();
			int edges = world.getAsteroid(i).getEdges();
			float[] polygon = polygonArray(vertices, edges);
			
			Gdx.gl.glLineWidth(3);
			sr.begin(ShapeType.Line);
			sr.setColor(0, 0, 0, 1);
			sr.polygon(polygon);
			sr.end();
		}
	}
	
	private void drawMissiles() {
		int numMissiles = world.getNumMissiles();
		
		for(int i = 0; i < numMissiles; i++) {
			float vertices[][] = world.getMissile(i).getVertices();
			Gdx.gl.glLineWidth(3);
			sr.begin(ShapeType.Line);
			sr.setColor(0, 0, 0, 1);
			sr.line(vertices[0][0], vertices[0][1],
					vertices[1][0], vertices[1][1]);
			sr.end();
		}
	}
	
	private void drawUFOs() {
		int numUFOs = world.getNumUFOs();
		
		for(int i = 0; i < numUFOs; i++) {
			float vertices[][] = world.getUFO(i).getVertices();
			int edges = world.getUFO(i).getEdges();
			float[] polygon = polygonArray(vertices, edges);
			
			Gdx.gl.glLineWidth(3);
			sr.begin(ShapeType.Line);
			sr.setColor(0, 0, 0, 1);
			sr.polygon(polygon);
			sr.end();
			
			sr.begin(ShapeType.Line);
			sr.line(vertices[0][0], vertices[0][1], vertices[5][0], vertices[5][1]);
			sr.line(vertices[1][0], vertices[1][1], vertices[4][0], vertices[4][1]);
			sr.end();
		}
	}
	
	private float[] polygonArray(float[][] vertices, int edges) {
		float[] polygon = new float[edges * 2];
		
		for(int i = 0; i < edges; i ++) {
			polygon[i*2] = vertices[i][0];
			polygon[(i*2)+1] = vertices[i][1];
		}
		
		return polygon;	
	}

	private void drawBackground() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
}
