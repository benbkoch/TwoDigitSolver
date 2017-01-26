import java.util.ArrayList;
import java.util.List;

public class Main {

	private static int[] input = { 97, 99, 41, 21, 35, 57, 47, 87, 7 };

	public static void main(String[] args) {
		

		List<List<int[]>> allGroups = new ArrayList<List<int[]>>();

		for (int i = 1; i < 9; i++) {
			allGroups.add(getSubsets(input, i));
		}
				
		for(int i = 0; i < allGroups.size(); i++) {
			for(int j = 0; j < allGroups.size(); j++) {
				for(int[] groupA : allGroups.get(i)) {
					for(int[] groupB : allGroups.get(j)) {
						if(contains(groupA, groupB)) {
							continue;
						}
						if(sum(groupA) == sum(groupB)) {
							print(groupA, groupB);
							//return;
							System.out.println("__________");
						}
					}
				}
			}
		}

		
	}

	private static boolean contains(int[] groupA, int[] groupB) {
		for(int i = 0; i < groupA.length; i++) {
			for(int j = 0; j < groupB.length; j++) {
				if(groupA[i] == groupB[j]) {
					return true;
				}
			}
		}
		return false;
	}

	private static void print(int[] groupA, int[] groupB) {
		System.out.print("[");
		for(int i = 0; i < groupA.length; i++) {
			System.out.print(groupA[i] + ",");
		}
		System.out.println("]");
		System.out.print("[");
		for(int i = 0; i < groupB.length; i++) {
			System.out.print(groupB[i] + ",");
		}
		System.out.println("]");
		
	}

	private static int sum(int[] group) {
		int sum = 0;
		for(int i = 0; i < group.length; i++) {
			sum += group[i];
		}
		return sum;
	}

	static int[] getSubset(int[] input, int[] subset) {
		int[] result = new int[subset.length];
		for (int i = 0; i < subset.length; i++)
			result[i] = input[subset[i]];
		return result;
	}

	static List<int[]> getSubsets(int[] input, int k) {

		List<int[]> subsets = new ArrayList<>();

		int[] s = new int[k]; // here we'll keep indices
								// pointing to elements in input array

		if (k <= input.length) {
			// first index sequence: 0, 1, 2, ...
			for (int i = 0; (s[i] = i) < k - 1; i++)
				;
			subsets.add(getSubset(input, s));
			for (;;) {
				int i;
				// find position of item that can be incremented
				for (i = k - 1; i >= 0 && s[i] == input.length - k + i; i--)
					;
				if (i < 0) {
					break;
				} else {
					s[i]++; // increment this item
					for (++i; i < k; i++) { // fill up remaining items
						s[i] = s[i - 1] + 1;
					}
					subsets.add(getSubset(input, s));
				}
			}
		}

		return subsets;
	}

}
