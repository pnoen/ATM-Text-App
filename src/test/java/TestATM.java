
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Date;

public class TestATM {

    @Test
    void testConstructor() {
        ATM test = new ATM("Hello", "test_cards", 100000);
    }

    @Test
    void testFileReader() throws ParseException {

        ATM valid_atm = new ATM("01/01/2021", "cards.csv", 199934690);
        ATM invalid_atm = new ATM("01/01/2021", "fakecards.csv", 199934690);

        //Assertions.assertThrows(FileNotFoundException.class, ()-> invalid_atm.readCSV());
        //assertEquals("Could not load the database", valid_atm.readCSV());
    }

    @Test
    void testBalance() throws ParseException{
        ATM valid_atm = new ATM("01/01/2021", "cards.csv", 10);
        valid_atm.readCSV();
        Admin admin = new Admin(valid_atm, 99999, 9999);
        assertEquals(10, valid_atm.getBalanceATM());

    }

    @Test
    void testCheckValid() throws ParseException{
        ATM valid_atm = new ATM("01/01/2021", "cards.csv", 10);
        valid_atm.readCSV();
        assertTrue(valid_atm.checkValid(00006));
        assertFalse(valid_atm.checkValid(0055));
    }

    @Test
    void testWithdraw() throws ParseException{
        ATM valid_atm = new ATM("01/01/2021", "cards.csv", 10000);
        valid_atm.readCSV();
        valid_atm.checkValidID(9);
        Card card = valid_atm.getCurrCard();
        valid_atm.withdraw(1000);
        assertTrue(valid_atm.getBalanceATM() == 9000);
        assertTrue(card.getCurrBalance() == 9100);


    }

    @Test
    void testDeposit() throws ParseException{
        ATM valid_atm = new ATM("01/01/2021", "cards.csv", 10000);
        valid_atm.readCSV();
        valid_atm.checkValidID(9);
        Card card = valid_atm.getCurrCard();
        valid_atm.deposit(1000);
        assertTrue(valid_atm.getBalanceATM() == 11000);
        assertTrue(card.getCurrBalance() == 11100);


    }

}


