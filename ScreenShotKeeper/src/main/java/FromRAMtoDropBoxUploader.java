import com.dropbox.core.v2.DbxClientV2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FromRAMtoDropBoxUploader extends Thread {
    private DbxClientV2 client;
    private ByteArrayOutputStream baos;

    public DbxClientV2 getClient() {
        return client;
    }

    public void setClient(DbxClientV2 client) {
        this.client = client;
    }

    public ByteArrayOutputStream getBaos() {
        return baos;
    }

    public void setBaos(ByteArrayOutputStream baos) {
        this.baos = baos;
    }

    public FromRAMtoDropBoxUploader(DbxClientV2 client, ByteArrayOutputStream baos) {
        this.client = client;
        this.baos = baos;
    }

    @Override
    public void run() {
        try {
            //Making up the name of the screenshot file с помощью класса SimpleDateFormat
            String nameOfTheFileWithExtension = makeUpNameOfFileDayMonthY_Hms();
            //создать массив байтов и записать туда то, что  в потоке, с помощью метода toByteArray класса BAOS
            byte[] imageInbyte = baos.toByteArray();
            //передать массив imageInByte в конструктор объекта ByteArrayInputStream и всё это присвоить переменной
            // InputStream
            InputStream inputStream = new ByteArrayInputStream(imageInbyte);

            // Upload the file (from inputStream) with the name from the Builder to Dropbox
            client.files().uploadBuilder("/" + nameOfTheFileWithExtension).uploadAndFinish(inputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Making up the name of the screenshot file
     *
     * @return A name of the file in dayMonthYear_HourMinutesSeconds format with .png extension
     */
    public String makeUpNameOfFileDayMonthY_Hms() {
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMYYYY_HHmmss");
        Date now = new Date();
        return formatter.format(now) + ".png";
    }

}
