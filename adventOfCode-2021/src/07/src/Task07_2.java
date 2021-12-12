import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Task07_2 {
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(new File("input07_2.txt"))) {
			Task07_2 task = new Task07_2(sc.nextLine());
			System.out.println(task.calcFuelForBestPositionForCrabs());
//			System.out.println(sc.nextLine());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private List<Long> in = new ArrayList<Long>();

	public Task07_2(String inputStr) {
		StringTokenizer st = new StringTokenizer(inputStr, ",");
		while (st.hasMoreElements()) {
			in.add(Long.parseLong(st.nextToken()));
		}
	}

	public long calcFuelForBestPositionForCrabs() {
		long min = in.stream().reduce(Long::min).get();
		long max = in.stream().reduce(Long::max).get();

		long best_pos = min;
		long best_score = Long.MAX_VALUE;
		for (long pos = min; pos <= max; ++pos) {
			final long ipos = pos;
			long score = in.stream().reduce(0L, (a, b) -> {
				long n = Math.abs(ipos - b);
				return a + (n * (n + 1)) / 2;
			});
			if (score < best_score) {
				best_pos = pos;
				best_score = score;
			}
		}

		return best_score;
	}
}
