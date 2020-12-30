//package congressional_data.domain;
//
//import io.quarkus.test.junit.QuarkusTest;
//import lombok.ToString;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.mock;
//
//@QuarkusTest
//class CongressionalBillTest {
//
//    @DisplayName("isBipartisan() should return true when bill is sponsored and cosponsored by different parties")
//    @Test
//    public void t1() {
//
//        Bill bill = new Bill();
//        Sponsors sponsors = new Sponsors();
//        Cosponsors cosponsors = new Cosponsors();
//
//        //set up sponsor
//        Item sponsorItem = new Item();
//        sponsorItem.setParty(Party.D);
//        List<Item> sponsorItems = new ArrayList<>();
//        sponsorItems.add(sponsorItem);
////        sponsors.setItems(sponsorItems);
//
//        //set up cosponsor
//        Item cosponsorItem = new Item();
//        cosponsorItem.setParty(Party.R);
//        List<Item> cosponsorItems = new ArrayList<>();
//        cosponsorItems.add(cosponsorItem);
//        cosponsors.setItems(cosponsorItems);
//
//        // setup bill
//        bill.setSponsors(sponsors);
//        bill.setCosponsors(cosponsors);
//
//        CongressionalBill congressionalBill = new CongressionalBill();
//        congressionalBill.setBill(bill);
//        boolean bipartisan = congressionalBill.isBipartisan();
//        assertTrue(bipartisan);
//    }
//
//    @DisplayName("isBipartisan() should return false when bill is sponsored and cosponsored by the same party")
//    @Test
//    public void t2() {
//
//        Bill bill = new Bill();
//        Sponsors sponsors = new Sponsors();
//        Cosponsors cosponsors = new Cosponsors();
//
//        //set up sponsor
//        Item sponsorItem = new Item();
//        sponsorItem.setParty(Party.D);
//        List<Item> sponsorItems = new ArrayList<>();
//        sponsorItems.add(sponsorItem);
//        sponsors.setItems(sponsorItems);
//
//        //set up cosponsor
//        Item cosponsorItem = new Item();
//        cosponsorItem.setParty(Party.D);
//        List<Item> cosponsorItems = new ArrayList<>();
//        cosponsorItems.add(cosponsorItem);
//        cosponsors.setItems(cosponsorItems);
//
//        // setup bill sponsors and cosponsors
//        bill.setSponsors(sponsors);
//        bill.setCosponsors(cosponsors);
//
//        CongressionalBill congressionalBill = new CongressionalBill();
//        congressionalBill.setBill(bill);
//        boolean bipartisan = congressionalBill.isBipartisan();
//        assertFalse(bipartisan);
//    }
//
//}