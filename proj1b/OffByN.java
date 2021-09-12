public class OffByN implements CharacterComparator {
    private int diff;

    public OffByN(int N) {
        diff = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        if ((Character.isLowerCase(x) || Character.isUpperCase(x)) &&
                (Character.isLowerCase(y) || Character.isUpperCase(y))) {
            return Math.abs(x - y) == diff;
        } else {
            return false;
        }
    }
}
