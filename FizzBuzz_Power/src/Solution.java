import java.util.HashSet;
import java.util.Set;

class Solution {
    public void solution(int N) {
        // Precompute powers of 2 up to 1000
        Set<Integer> powersOfTwo = new HashSet<>();
        int power = 1;
        while (power <= 1000) {
            powersOfTwo.add(power);
            power *= 2;
        }

        // iterate and print the results
        for (int i = 1; i <= N; i++) {
            if (powersOfTwo.contains(i)) {
                System.out.print("POWER");
            } else {
                System.out.print(i);
            }
        }

        // add a newline after printing the solution
        System.out.println();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // Test cases
        sol.solution(7);  // POWERPOWER3POWER567
        sol.solution(16); // POWERPOWER3POWER567POWER9101112131415POWER
    }
}
