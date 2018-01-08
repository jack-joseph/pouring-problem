/**
 * Author: Jacob Joseph
 * Date: Jan 7, 2018
 *
 * This class creates a "game board" of sorts for the water pouring logic
 * problem. It uses the Container class to provide "buckets" for the AP to
 * fill and pour water between. It is important to be careful with your 
 * parameters when using this class, as it does not validate input in any way.
 *
 * https://en.wikipedia.org/wiki/Water_pouring_puzzle
 * 
 * This is free, unencumbered software released into the public domain.
 */

public class Game {
	private int size;				// how many buckets used
	private int[] targets;			// target values of each bucket
	private Container[] buckets;	// array of buckets

	/**
	 * Default ctor. Sets size to 3, targets, to 4, 4, 0, and bucket 
	 * sizes to 8, 5, and 3.
	 */
	public Game() {
		size = 3;

		targets = new int[size];
			targets[0] = 4;
			targets[1] = 4;
			targets[2] = 0;

		buckets = new Container[size];
			buckets[0] = new Container(8);
			buckets[1] = new Container(5);
			buckets[2] = new Container(3);

		fill(0);
	}

	/**
	 * Ctor. Allows for custom number of buckets, target values, and
	 * bucket volumes. DOES NOT protect against invalid input (e.g. 
	 * more target values than number of buckets, negative volumes, etc.)
	 *
	 * @param size    - size of the bucket array
	 * @param targets - array of target values
	 * @param volumes - array of the bucket sizes
	 */
	public Game(int size, int[] targets, int[] volumes) {
		this.size = size;
		this.targets = targets;
		buckets = new Container[size];

		for (int i = 0; i < size; i++) {
			buckets[i] = new Container(volumes[i]);
		}

		fill(0);
	}

	/**
	 * Returns true if the game is over (all bucket volumes match their
	 * targets). Returns false otherwise.
	 *
	 * @return true if the game is over, false otherwise
	 */
	public boolean isOver() {
		boolean isOver = true;

		for (int i = 0; i < size; i++) {
			if (buckets[i].getVolume() != targets[i]) return false;
		}

		return isOver;
	}

	/**
	 * Pours the entire contents of one bucket into another. Buckets are
	 * specified by their index in the buckets[] array.
	 *
	 * @param pourIndex - index of the bucket that is pouring its water
	 * @param fillIndex - index of the bucket filling up with water
	 */
	public void pour(int pourIndex, int fillIndex) {
		buckets[pourIndex].pour(buckets[fillIndex]);
	}

	/**
	 * Fills a bucket up to its max capacity.
	 */
	public void fill(int index) {
		buckets[index].fill();
	}

	/**
	 * Returns the bucket's capacity at a given index
	 *
	 * @param index - the bucket's index
	 * @return the bucket's capacity at index
	 */
	public int getCapacity(int index) {
		return buckets[index].getCapacity();
	}
	
	/**
	 * Returns the capacity of each bucket
	 *
	 * @return each bucket's capacity in an int array
	 */
	public int[] getCapacity() {
		int[] capacities = new int[size];

		for (int i = 0; i < size; i++) {
			capacities[i] = buckets[i].getCapacity();
		}

		return capacities;
	}

	/**
	 * Returns an int array of the current bucket volumes.
	 * 
	 * @return an int array of the current bucket volumes
	 */
	public int[] getCurrentVolume() {
		int[] currentVolume = new int[size];

		for (int i = 0; i < size; i++) {
			currentVolume[i] = buckets[i].getVolume();
		}

		return currentVolume;
	}

	/**
	 * Returns the current bucket volume at a given index.
	 * 
	 * @param index - the index of the bucket
	 * @return the current bucket volume at a given index
	 */
	public int getCurrentVolume(int index) {
		return buckets[index].getVolume();
	}

	/**
	 * Resets the game to its starting state: all buckets empty
	 * except the first one, which is filled.
	 */
	public void reset() {
		for (int i = 0; i < size; i++) {
				while (!buckets[i].isEmpty()) {
					buckets[i].pop();
				}
			}

		fill(0);
	}

	/**
	 * Sets each target to the target values passed in by array.
	 * DOES NOT protect against invalid input (e.g. more targets
	 * than there are buckets).
	 *
	 * @param targets - array of target values to set for the buckets
	 */
	public void setTarget(int[] targets) {
		for (int i = 0; i < targets.length; i++) {
			this.targets[i] = targets[i];
		}
	}

	/**
	 * Sets the target value for a bucket at a given index. DOES NOT check
	 * whether the index exists, or if the target is invalid (negative, etc.).
	 *
	 * @param target - new target value
	 * @param index  - bucket index
	 */
	public void setTarget(int target, int index) {
		targets[index] = target;
	}

	/**
	 * Returns an array of the current target values.
	 *
	 * @return an int array of the current target values
	 */
	public int[] getTarget() {
		int[] newTargets = targets;
		return newTargets;
	}

	/**
	 * Returns the target at a given index.
	 *
	 * @param index - the bucket index
	 * @return the target value at a given index
	 */
	public int getTarget(int index) {
		return targets[index];
	}

	/**
	 * Returns how many buckets are being used for the game
	 *
	 * @return how many buckets are being used
	 */
	public int getSize() { return size; }
}
