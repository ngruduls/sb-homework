class Solution {
    public String solution(String S) {
        // build a 'map' with character(digit) counts
        int[] digitCounts = new int[10];
        for (char ch : S.toCharArray()) {
            digitCounts[ch - '0']++;
        }

        // if all are zeroes, can return 0 immediately
        if (digitCounts[0] == S.length()) {
            return "0";
        }

        // choose odd number for center, and starting with largest even number for left side
        StringBuilder leftHalf = new StringBuilder();
        char centerDigit = 0;

        for (int digit = 9; digit >= 0; digit--) {
            if (digitCounts[digit] % 2 == 1 && centerDigit == 0) {
                centerDigit = (char) (digit + '0');
            }
            // append first half of even digits
            for (int i = 0; i < digitCounts[digit] / 2; i++) {
                leftHalf.append((char) (digit + '0'));
            }
        }

        // remove leading zeroes
        while (leftHalf.length() > 0 && leftHalf.charAt(0) == '0') {
            leftHalf.deleteCharAt(0);
        }

        // build the final palindrome constructing with left + center + reverse(left)
        String leftHalfStr = leftHalf.toString();
        String rightHalfStr = new StringBuilder(leftHalfStr).reverse().toString();

        StringBuilder palindrome = new StringBuilder();
        palindrome.append(leftHalfStr);
        if (centerDigit != 0) {
            palindrome.append(centerDigit);
        }
        palindrome.append(rightHalfStr);

        String result = palindrome.toString();
        return result.isEmpty() ? "0" : result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.solution("39878"));
        System.out.println(solution.solution("00900"));
        System.out.println(solution.solution("0000"));
        System.out.println(solution.solution("54321"));
    }
}
