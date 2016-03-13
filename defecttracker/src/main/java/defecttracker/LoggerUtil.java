package defecttracker;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerUtil {

	public static Logger getLogger(String file) {
		Logger logger = null;
		// setup logging
		try {
			logger = Logger.getLogger(DbUtils.class.getName());
			FileHandler handler = new FileHandler(file);
			logger.addHandler(handler);
			logger.setLevel(Level.INFO);
			handler.setLevel(Level.INFO);

		} catch (SecurityException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return logger;
	}
}
