package lesson6;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class Test_MyArray2 {
    MyArray2 myArray2;
    @Before
    public void init(){
        myArray2 = new MyArray2();
    }

    @Test
    @DisplayName("Есть 1, нет 4")
    public void checkedOne(){
        int[] testArr = {2,1,3,2};
        Assert.assertTrue("Нет ни единиц, ни четверок", myArray2.checkedArray(testArr));
    }
    @Test
    @DisplayName("Есть 4, нет 1")
    public void checkedFour(){
        int[] testArr = {2,4,3,2};
        Assert.assertTrue("Нет ни единиц, ни четверок", myArray2.checkedArray(testArr));
    }
    @Test
    @DisplayName("Нет ни единиц, ни четверок")
    public void noOneNoFour(){
        int[] testArr = {2,2,3,2};
        Assert.assertTrue("Нет ни единиц, ни четверок", myArray2.checkedArray(testArr));
    }
    @Test
    @DisplayName("Передан пустой массив")
    public void nullPointerArray(){
        int[] testArr = null;
        Assert.assertThrows("Передан пустой массив", NullPointerException.class, () -> myArray2.checkedArray(testArr));
    }

}
