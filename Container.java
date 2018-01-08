/**
 * Author: Jacob Joseph
 * Date: Jan 7, 2018
 *
 * This class creates the buckets for the water pouring logic
 * problem. The buckets use a stack of 1s to simulate pouring and filling.
 * This means that a specific value cannot be pushed, you just call push()
 * to increase the volume of the stack by one. It is important to be careful
 * with your parameters when using this class, as it does not validate 
 * input in any way.
 *
 * https://en.wikipedia.org/wiki/Water_pouring_puzzle
 * 
 * This is free, unencumbered software released into the public domain.
 */

public class Container {
	private int capacity;	// capacity of the bucket
	private int volume;		// current volume of the bucket
	private int[] stack;	// stack is implemented as an int array

	/**
	 * Default ctor. Sets the capacity to 10, volume to zero, and inititalizes
	 * the stack. 
	 */
	public Container() {
		capacity = 10;
		volume = 0;
		stack = new int[capacity];
	}

	/**
	 * Ctor. Takes an int as a parameter and sets it to the capacity
	 * of the bucket. DOES NOT protect against invalid input, such as a
	 * negative capacity.
	 *
	 * @param capacity - capacity of the bucket
	 */
	public Container(int capacity) {
		this.capacity = capacity;
		volume = 0;
		stack = new int[capacity];
	}

	/**
	 * pushes a '1' onto the stack. Returns true if the push was successful, 
	 * false otherwise (full stack).
	 *
	 * @return true if successful push, false otherwise (full stack)
	 */
	public boolean push() {
		if (!isFull()) {
			stack[volume] = 1;
			volume++;

			return true;
		} else {
			return false;
		}
		
	}

	/**
	 * Pops the top value (will always be 1)off the stack off and returns it.
	 * If the stack is empty, returns -1.
	 *
	 * @return 1 if the pop was successful, -1 otherwise (empty stack)
	 */
	public int pop() {
		if (!isEmpty()) {
			int temp = stack[volume - 1];

			stack[volume - 1] = 0;
			volume--;

			return temp;
		} else {
			return -1;
		}
	}

	/**
	 * Pours the contents of this bucket into another. Will
	 * pour until this bucket is empty, or the other is full.
	 *
	 * @param other - the bucket to be filled
	 */
	public void pour(Container other) {
		if (this == other) return;

		while (!other.isFull() && !isEmpty()) {
			pop();
			other.push();
		}
	}

	/**
	 * Fills this bucket to capacity.
	 */
	public void fill() {
		while (!isFull()) {
			push();
		}
	}

	/**
	 * Returns true if the bucket is empty, false otherwise.
	 *
	 * @return true if the bucket is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return volume == 0;
	}

	/**
	 * Returns true if the bucket is full, false otherwise.
	 *
	 * @return true if the bucket is full, false otherwise.
	 */
	public boolean isFull() {
		return volume == capacity;
	}

	/**
	 * Returns the current volume
	 *
	 * @return the current volume
	 */
	public int getVolume() {
		return volume;
	}

	/**
	 * Returns the bucket's capacity
	 *
	 * @return the bucket's capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Takes in a parameter and sets it to the new capacity. 
	 * DOES NOT protect against invalid input (e.g. if the new
	 * capacity is smaller than the current volume).
	 *
	 * @param capacity - the new capacity
	 */
	public void resize(int newCapacity) {
		int[] newStack = new int[newCapacity];

		for (int i = 0; i < volume; i++) {
			newStack[i] = stack[i];
		}

		capacity = newCapacity;
		stack = newStack;
	}
}