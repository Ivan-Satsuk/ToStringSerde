package CountSort;

import java.util.*;

public class ToStringSerializer {
    final int ARRAY_LENGTH;

    short[] shorts;

    ToStringSerializer(int ARRAY_LENGTH) {

        this.ARRAY_LENGTH = ARRAY_LENGTH;
        shorts = new short[ARRAY_LENGTH];

        for (int i = 0; i < shorts.length; i++) {
            shorts[i] = (short) (i * 2 + 1);
            int a = Integer.parseInt(Integer.toBinaryString(shorts[i]));
        }
    }

    public short[] getShorts() {
        return shorts;
    }

    private StringBuilder arrayToBitsArray() {
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

    public StringBuilder bitsArrayToChars() {
        StringBuilder bits = arrayToBitsArray();
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

    private StringBuilder getBitArrayFromCharArray() {
        StringBuilder bits = arrayToBitsArray();
        StringBuilder charResult = bitsArrayToChars();
        StringBuilder bitArrayFromCharArray = new StringBuilder();

        for (int i = 0; i < charResult.length(); i++) {
            int a = charResult.charAt(i);
            StringBuilder bitsFFromChar = new StringBuilder(Integer.toBinaryString(a));
            if (bitsFFromChar.length() < 16) {
                if (i == charResult.length() - 1) {
                    int length = bits.length() - bitArrayFromCharArray.length() - bitsFFromChar.length();
                    for (int j = 0; j < length; j++) {
                        bitsFFromChar.insert(0, "0");
                    }
                    bitArrayFromCharArray.append(bitsFFromChar);
                    break;
                }
                int length = bitsFFromChar.length();
                for (int j = 0; j < 16 - length; j++) {
                    bitsFFromChar.insert(0, "0");
                }
            }
            bitArrayFromCharArray.append(bitsFFromChar);
        }
        return bitArrayFromCharArray;
    }

    public short[] getShortArrayFromBitsArray() {
        StringBuilder bitArrayFromCharArray = getBitArrayFromCharArray();
        short[] result = new short[ARRAY_LENGTH];
        for (int i = 0, j = 0; i < bitArrayFromCharArray.length(); i += 10, j++) {
            if (i + 10 >= bitArrayFromCharArray.length()) {
                result[j] = Short.parseShort(bitArrayFromCharArray.substring(i), 2);
            } else
                result[j] = Short.parseShort(bitArrayFromCharArray.substring(i, i + 10), 2);
        }
        return result;
    }

}
