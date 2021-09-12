public class Palindrome {
    /**
     * return a Deque where the characters appear in
     * the same order as in the String
     * eg. "persiflage" --> ["p","e","s","i","f","l","a","g",l"e"]
     */
    public Deque<Character> wordToDeque(String word) {
        ArrayDeque<Character> AD = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            AD.addLast(word.charAt(i));
        }
        return AD;
    }

    public boolean isPalindrome(String word) {
        Palindrome palindrome = new Palindrome();
        Deque<Character> wordList = palindrome.wordToDeque(word);
        return isPalindromeHelper(wordList);

    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Palindrome palindrome = new Palindrome();
        Deque<Character> wordList = palindrome.wordToDeque(word);
        return isPalindromeHelper(wordList, cc);

    }

    private boolean isPalindromeHelper(Deque<Character> wordList) {
        if (wordList.isEmpty() || wordList.size() == 1) {
            return true;
        }
        if (wordList.removeFirst() == wordList.removeLast()) {
            return isPalindromeHelper(wordList);
        } else {
            return false;
        }

    }

    private boolean isPalindromeHelper(Deque<Character> wordList, CharacterComparator cc) {
        if (wordList.isEmpty() || wordList.size() == 1) {
            return true;
        }
        if (cc.equalChars(wordList.removeFirst(), wordList.removeLast())){
            return isPalindromeHelper(wordList, cc);
        } else {
            return false;
        }

    }
}
