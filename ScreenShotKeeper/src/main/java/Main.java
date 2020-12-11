import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String ACCESS_TOKEN = "KEHM_EXAMPLE_TOKEN_E9inO";//Enter your access token after registering at Dropbox
        // Create Dropbox client
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);



        for (; ; ) {


//            //Creating and saving screenshot
//            try {
//                BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
//                ImageIO.write(image, "png", new File("/" + nameOfTheFileWithExtension));
//                System.out.println(image.getWidth() + "x" + image.getHeight());
//                System.out.println("A full screen shot saved!");
//            } catch (AWTException ex) {
//                System.err.println(ex);
//            } catch (IOException x) {
//                System.err.println(x);
//            }

            try {
                //Creating screenshot and sending it to buffer
                BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(image, "png", baos);


//Creating thread and giving DbxClientV2 client and buffer with the file for uploading to Dropbox
                FromRAMtoDropBoxUploader thread2 = new FromRAMtoDropBoxUploader(client, baos);
                thread2.start();
                thread2.sleep(5000);

            } catch (AWTException awt) {
                System.out.println("Что-то пошло не так на этапе создания скриншота");
                awt.printStackTrace();
            } catch (IOException ioe) {
                System.out.println("Что-то пошло не так на этапе отправки скриншота в буфер");
                ioe.printStackTrace();
            } catch (InterruptedException e) {
                System.out.println("Что-то пошло не так с таймером");
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Ой.");
                e.printStackTrace();
            }

        } //end of eternal for-cycle

    }
}


