package phase2.Data;

import java.io.*;

/**
 * The type Write file.
 */
public class WriteFile  {

    private File path;
    private boolean appendFile = true;

    /**
     * Instantiates a new Write file.
     *
     * @param filePath the file path
     */
// if not specified info is appended to file
    public WriteFile(File filePath){
        this.path = filePath;
    }

    /**
     * Instantiates a new Write file.
     *
     * @param filePath   the file path
     * @param appendFile the append file
     */
//User can choose to overwrite file with new info
    //*wipes file then writes it*
    public WriteFile(File filePath, boolean appendFile){
        this.path = filePath;
        this.appendFile = appendFile;
    }

    /**
     * Write to file.
     *
     * @param outgoingText the outgoing text
     * @throws IOException the io exception
     */
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
