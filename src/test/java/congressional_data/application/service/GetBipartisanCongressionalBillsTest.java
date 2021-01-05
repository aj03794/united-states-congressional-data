//package congressional_data.application.service;
//
//import congressional_data.application.ports.out.LoadCongressionalBillsPort;
//import congressional_data.domain.CongressionalBill;
//import io.quarkus.test.junit.QuarkusTest;
//import io.quarkus.test.junit.mockito.InjectMock;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import javax.inject.Inject;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@QuarkusTest
//class GetBipartisanCongressionalBillsTest {
//
//    @Inject
//    GetBipartisanCongressionalBills getBipartisanCongressionalBills;
//
//    @InjectMock
//    LoadCongressionalBillsPort loadCongressionalBillsPort;
//
//    @DisplayName("Should return that there are 2 Bipartisan bills")
//    @Test
//    public void t1() {
//
//        List<CongressionalBill> congressionalBills = new ArrayList<>();
//        CongressionalBill congressionalBillOne = Mockito.mock(CongressionalBill.class);
//        CongressionalBill congressionalBillTwo = Mockito.mock(CongressionalBill.class);
//        CongressionalBill congressionalBillThree = Mockito.mock(CongressionalBill.class);
//
//        congressionalBills.add(congressionalBillOne);
//        congressionalBills.add(congressionalBillTwo);
//        congressionalBills.add(congressionalBillThree);
//
//        Mockito.when(congressionalBillOne.isBipartisan()).thenReturn(true);
//        Mockito.when(congressionalBillTwo.isBipartisan()).thenReturn(false);
//        Mockito.when(congressionalBillThree.isBipartisan()).thenReturn(true);
//
//
//        Mockito.when(loadCongressionalBillsPort.getAllCongressionalBills()).thenReturn(congressionalBills);
//
//        int expected = 2;
//
//        int actual = getBipartisanCongressionalBills.execute();
//
//        assertEquals(expected, actual);
//    }
//
//    @DisplayName("Should return that there are 0 Bipartisan bills")
//    @Test
//    public void t2() {
//
//        List<CongressionalBill> congressionalBills = new ArrayList<>();
//        CongressionalBill congressionalBillOne = Mockito.mock(CongressionalBill.class);
//        CongressionalBill congressionalBillTwo = Mockito.mock(CongressionalBill.class);
//        CongressionalBill congressionalBillThree = Mockito.mock(CongressionalBill.class);
//
//        congressionalBills.add(congressionalBillOne);
//        congressionalBills.add(congressionalBillTwo);
//        congressionalBills.add(congressionalBillThree);
//
//        Mockito.when(congressionalBillOne.isBipartisan()).thenReturn(false);
//        Mockito.when(congressionalBillTwo.isBipartisan()).thenReturn(false);
//        Mockito.when(congressionalBillThree.isBipartisan()).thenReturn(false);
//
//        Mockito.when(loadCongressionalBillsPort.getAllCongressionalBills(List.of("115", "116")).thenReturn(congressionalBills);
//
//        int expected = 0;
//
//        int actual = getBipartisanCongressionalBills.execute();
//
//        assertEquals(expected, actual);
//    }
//
//}