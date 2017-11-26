package gameHelpers;

import java.util.Random;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

import gameManager.World;
import gameObjects.Rocket;

public class InputHandler implements InputProcessor {
	
	private World world;
	private Rocket rocket;
	
	public InputHandler(World world, Rocket rocket) {
		this.world = world;
		this.rocket = rocket;
	}

	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.UP) {
			rocket.setThrusting();
			
		} else if(keycode == Keys.LEFT) {
			rocket.setLeft();
			
		} else if(keycode == Keys.RIGHT) {
			rocket.setRight();
			
		} else if(keycode == Keys.SPACE) {
			world.spawnMissile();
		}
		
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Keys.UP) {
			rocket.setThrusting();
			
		} else if(keycode == Keys.LEFT) {
			rocket.setLeft();
			
		} else if(keycode == Keys.RIGHT) {
			rocket.setRight();
		}
		
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
