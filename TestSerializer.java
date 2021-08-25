package CountSort;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestSerializer {
    public static void main(String[] args) {

        //test with random capacity 100 times
        System.out.print("Test 1: \n");
        boolean[] expectedBooleans = new boolean[100];
        Arrays.fill(expectedBooleans, true);

        boolean[] result = new boolean[100];
        for (int i = 0; i < 100; i++) {
            Random random = new Random();
            int array_length = random.nextInt(200) + 1;

            short[] shortsBeforeSer = new short[array_length];
            HashSet<Short> shorts = new HashSet<>();
            int chek = 0;

            while (chek < array_length) {
                short nextShort = (short) (random.nextInt(999)+1);
                if (!shorts.contains(nextShort)){
                    shorts.add(nextShort);
                    shortsBeforeSer[chek]=nextShort;
                    chek+=1;
                }
            }

            String s = ToStringSerializer.serializeShortsToString(shortsBeforeSer);
            short[] shortsAfterDes = ToStringSerializer.deserializeStringToShorts(s);
            result[i] = Arrays.equals(shortsBeforeSer, shortsAfterDes);
        }

        System.out.printf("Все 100 случаев массивы до сериализации и после равны - %b\n", Arrays.equals(expectedBooleans, result));



//        // Test with array capacity = 1
        System.out.print("Test 2: \n");
        Random random = new Random();
        short[] shortsBeforeSer = new short[1];
        shortsBeforeSer[0] = (short) (random.nextInt(999) + 1);
        String charsResult = ToStringSerializer.serializeShortsToString(shortsBeforeSer);
        short[] shortsAfterDes = ToStringSerializer.deserializeStringToShorts(charsResult);
        System.out.printf("Массивы до сериализации и после равны - %b\n", Arrays.equals(shortsBeforeSer, shortsAfterDes));



//        test with max array capacity = 200
        System.out.print("Test 3: \n");
        
        short[] shortsBeforeSerTest3 = new short[200];
        HashSet<Short> shorts = new HashSet<>();
        int chek = 0;

        while (chek < 200) {
            short nextShort = (short) (random.nextInt(999)+1);
            if (!shorts.contains(nextShort)){
                shorts.add(nextShort);
                shortsBeforeSerTest3[chek]=nextShort;
                chek+=1;
            }
        }
        String charsResultTest3 = ToStringSerializer.serializeShortsToString(shortsBeforeSerTest3);

        System.out.printf("%d - длинна массива чисел, %d - длинна строки\n", shortsBeforeSerTest3.length, charsResultTest3.length());

        short[] shortsAfterDesTest3 = ToStringSerializer.deserializeStringToShorts(charsResultTest3);
        System.out.printf("Массивы до сериализации и после равны - %b\n", Arrays.equals(shortsBeforeSerTest3, shortsAfterDesTest3));

    }
}
