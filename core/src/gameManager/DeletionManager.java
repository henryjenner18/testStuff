package gameManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DeletionManager {
	
	private World world;
	
	public DeletionManager(World world) {
		this.world = world;
	}

	public void manage() {
		removeMissiles();
	}
	
	private void removeMissiles() {
		ArrayList<Integer> removeMissiles = new ArrayList<Integer>();
		
		int numMissiles = world.getNumMissiles();
		
		for(int i = 0; i < numMissiles; i++) {
			if(world.getMissile(i).getTimeLeft() == 0) {
				removeMissiles.add(i);
			}
		}
		
		Set<Integer> noDuplicates = new HashSet<Integer>();
		noDuplicates.addAll(removeMissiles);
		removeMissiles.clear();
		removeMissiles.addAll(noDuplicates);
		Collections.sort(removeMissiles);
		Collections.reverse(removeMissiles);
		
		for(int i = 0; i < removeMissiles.size(); i++) {
			world.removeMissile(removeMissiles.get(i));
		}
	}

}
