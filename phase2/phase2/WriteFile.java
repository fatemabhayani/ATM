package phase2;

import phase2.Transactions.Transaction;

import java.io.*;

public class WriteFile  {

    private File path;
    private boolean appendFile = true;

    // if not specified info is appended to file
    public WriteFile(File filePath){
        this.path = filePath;
    }
    //User can choose to overwrite file with new info
    //*wipes file then writes it*
    public WriteFile(File filePath, boolean appendFile){
        this.path = filePath;
        this.appendFile = appendFile;
    }

    public void writeToFile( String outgoingText ) throws IOException {
        try {
            FileWriter fw = new FileWriter(this.path, appendFile);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(outgoingText);
            pw.println("hi");
            pw.close();
        }
        catch(IOException ioException) { ioException.printStackTrace();}
    }
}
