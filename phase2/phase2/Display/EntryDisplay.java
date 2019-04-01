package phase2.Display;

import phase2.Data.DataReader;

public class EntryDisplay {

    public static void main(String[] args) {
        DataReader d = new DataReader();
        d.readAll();

        ATM.main(null);
    }
}

