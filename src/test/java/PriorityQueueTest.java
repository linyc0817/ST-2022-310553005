import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.xml.crypto.Data;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class PriorityQueueTest {

    static Stream<Arguments> streamProvider() {
        return Stream.of(
                arguments(new int[]{5, 4, 2, 3}, new int[]{2, 3, 4, 5}),
//                arguments(new int[]{-3,-1,-2,5}, new int[]{-3,-2,-1,5}),
                arguments(new int[]{1,1,1,1}, new int[]{-3,-2,-1,5}),
                arguments(new int[]{3,-2,-5,-1,2}, new int[]{-5,-2,-1,2,3}),
                arguments(new int[]{-3,1,11,0,9,3}, new int[]{-3,0,1,3,9,11}),
                arguments(new int[]{3,7,2,-1,-2}, new int[]{-2,-1,2,3,7})
        );
    }

    @ParameterizedTest(name="#{index}- Test with Argument={0},{1}")
    @MethodSource("streamProvider")
//    @DisplayName("Parameterization Priority Queue Pool Test")
    public void PriorityQueue_RunTest(int[] random_array, int[] correct_array){
        PriorityQueue<Integer> test = new PriorityQueue<Integer>();
        int index = 0;
//        Integer s;
        int[] result = new int[random_array.length];

        // Get priority queue
        for(index=0;index<random_array.length;index++){
            test.add(random_array[index]);
        }

        for(index=0;index<random_array.length;index++){
            result[index] = test.poll();
        }

//        System.out.println ( "Priority queue values after poll: "+ test);
//        System.out.println ( "GET result: "+ Arrays.toString(result));

        assertArrayEquals(correct_array,result);
    }

    @Test
    public void whenExceptionThrown_theInitialCapacityIsLessThanOne(){
        Exception exception = assertThrows(IllegalArgumentException.class, ()->{
            PriorityQueue<Integer> test = new PriorityQueue<>(0);
        });
    }

    @Test
    public void whenExceptionThrown_noElementCanRemove(){
        Exception exception = assertThrows(NoSuchElementException.class, ()->{
            PriorityQueue<Integer> test = new PriorityQueue<>();
            test.remove();
        });
    }

    @Test
    public void whenExceptionThrown_queueNull(){
        Exception exception = assertThrows(NullPointerException.class, ()->{
            PriorityQueue<Integer> test = new PriorityQueue<>();
            test.add(null);
        });
    }

}
