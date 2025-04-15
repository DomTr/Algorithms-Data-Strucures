package locks;

import java.util.Random;

public class Backoff {
	int MIN_DELAY;
	int MAX_DELAY;
	int limit;
	Random random;
	public Backoff(int MIN_DELAY, int MAX_DELAY) {
		this.MIN_DELAY = MIN_DELAY;
		this.MAX_DELAY = MAX_DELAY;
		this.limit = MAX_DELAY;
		random = new Random();
	}
	public void backoff() throws InterruptedException {
		int delay = random.nextInt(limit);
		if (limit < MAX_DELAY) {
			limit = 2 * limit;
		}
		Thread.sleep(delay);
	}
}
