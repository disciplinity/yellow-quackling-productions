package database.value;

import lombok.ToString;


// TODO: For the test purposes
@ToString
public class AccountHeroSetup {
    String usr;
    int accId;
    public AccountHeroSetup(String usr, int accId) {
        this.usr = usr;
        this.accId = accId;
    }
}
