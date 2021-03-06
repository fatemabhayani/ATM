package phase2.Data;

import java.io.*;

/**
 * The type Write file.
 */
class WriteFile  {

    private final File path;
    private boolean appendFile = true;

    /**
     * Instantiates a new Write file.
     * if not specified info is appended to file
     * @param filePath the file path
     */
    public WriteFile(File filePath){
        this.path = filePath;
    }
    /**
     * Instantiates a new Write file.
     *
     * @param filePath   the file path
     * @param appendFile whether the data is appended or overwritten
     */
    public WriteFile(File filePath, boolean appendFile){
        this.path = filePath;
        this.appendFile = appendFile;
    }

    /**
     * Write to file.
     *
     * @param outgoingText the outgoing text
     */
    public void writeToFile( String outgoingText ) {
        try {
            FileWriter fw = new FileWriter(this.path, appendFile);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(outgoingText);
            pw.close();
        }
        catch(IOException ioException) { ioException.printStackTrace();}
    }
}
