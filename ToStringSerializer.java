package CountSort;

public class ToStringSerializer {

    static String serializeShortsToString(Short[] shorts) {
        StringBuilder bitsArray = arrayToBitsArray(shorts);
        return bitsArrayToChars(bitsArray).toString();
    }

    static Short[] deserializeStringToShorts(String chars) {
        StringBuilder bits = getBitArrayFromCharArray(chars);
        return getShortArrayFromBitsArray(bits);
    }

    private static StringBuilder arrayToBitsArray(Short[] shorts) {
        StringBuilder bits = new StringBuilder();

        for (short b : shorts) {
            StringBuilder stringBuilder1 = new StringBuilder(Integer.toBinaryString(b));
            if (stringBuilder1.length() < 10) {
                int length = stringBuilder1.length();
                for (int j = 0; j < 10 - length; j++) {
                    stringBuilder1.insert(0, "0");
                }
            }
            bits.append(stringBuilder1);
        }
        return bits;
    }

    public static StringBuilder bitsArrayToChars(StringBuilder bits) {
        StringBuilder charResult = new StringBuilder();
        for (int i = 0; i < bits.length(); i += 16) {
            char c;
            if (i + 16 >= bits.length()) {
                c = (char) (Integer.parseInt(bits.substring(i), 2));
            } else {
                c = (char) (Integer.parseInt(bits.substring(i, i + 16), 2));
            }
            charResult.append(c);
        }
        return charResult;
    }

    private static StringBuilder getBitArrayFromCharArray(String chars) {
        StringBuilder charResult = new StringBuilder(chars);
        StringBuilder bitArray = new StringBuilder();

        for (int i = 0; i < charResult.length(); i++) {
            int a = charResult.charAt(i);
            StringBuilder charToBits = new StringBuilder(Integer.toBinaryString(a));
            if (charToBits.length() < 16) {
                if (i == charResult.length() - 1 && (bitArray.length() + charToBits.length()) % 10 == 0) {
                    bitArray.append(charToBits);
                    break;
                }
                if (i == charResult.length() - 1) {
                    boolean stop = false;
                    while (!stop) {
                        charToBits.insert(0, "0");
                        stop = (bitArray.length() + charToBits.length()) % 10 == 0;
                    }
                    bitArray.append(charToBits);
                    break;
                }
                int length = charToBits.length();
                for (int j = 0; j < 16 - length; j++) {
                    charToBits.insert(0, "0");
                }
            }
            bitArray.append(charToBits);
        }
        return bitArray;
    }

    public static Short[] getShortArrayFromBitsArray(StringBuilder bits) {

        Short[] result = new Short[bits.length() / 10];

        for (int i = 0, j = 0; i < bits.length(); i += 10, j++) {
            if (i + 10 >= bits.length()) {
                result[j] = Short.parseShort(bits.substring(i), 2);
            } else
                result[j] = Short.parseShort(bits.substring(i, i + 10), 2);

        }
        return result;
    }

}
