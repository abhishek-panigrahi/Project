package testCases;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class FunctionLibrary {


		/**
		 * 
		 * 
		 * 	
		 * @param driver
		 * @param screenShotName
		 * @return
		 * @throws IOException
		 */
		public  static String captureScreenshot(WebDriver driver, String screenShotName) throws IOException {
		String dest = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy h-m-s");
			Date date = new Date();
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			dest = System.getProperty("user.dir") + "\\Reports\\" + screenShotName + dateFormat.format(date)
			+ ".png";
			File destination = new File(dest);
			FileUtils.copyFile(source, destination);
		} catch (Exception e) {
			e.getMessage();
			System.out.println(e.getMessage());
		}
		return dest;
	}

}
