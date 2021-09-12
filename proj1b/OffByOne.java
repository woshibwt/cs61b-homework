public class OffByOne implements CharacterComparator {


    @Override
    public boolean equalChars(char x, char y) {
        if ((Character.isLowerCase(x) || Character.isUpperCase(x)) &&
                (Character.isLowerCase(y) || Character.isUpperCase(y))){
            return Math.abs(x - y) == 1;
        }
        else {
            return false;
        }
    }
}
