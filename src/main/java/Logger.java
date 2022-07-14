public class Logger {
    private static Logger logger = null;
    private int lineNumber = 0;

    private Logger() {}

    public static Logger getInstance()
    {
        if (logger == null)
        {
            logger = new Logger();
        }
        return logger;
    }
    public void log (String str)
    {
        lineNumber++;
        System.out.println(lineNumber + " - " + str);
    }
}
