package bookstore.test;

import bookstore.utils.FilterString;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FilterStringTest {

    @Test
    void filterString() {
        Boolean adfsdfs = FilterString.filterString("(a'df\"sdfs)");
        System.out.println(adfsdfs);
    }
}