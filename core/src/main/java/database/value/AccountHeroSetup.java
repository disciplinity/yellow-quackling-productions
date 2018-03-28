package database.value;

import lombok.ToString;


// TODO: For the test purposes. Move to tests
@ToString
public class AccountHeroSetup {
    String usr;
    int accId;
    public AccountHeroSetup(String usr, int accId) {
        this.usr = usr;
        this.accId = accId;
    }
}
