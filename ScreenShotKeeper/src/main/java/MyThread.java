import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;

import java.io.FileInputStream;
import java.io.InputStream;

public class MyThread extends Thread {
    private DbxClientV2 client;
    private String nameOfTheFileWithExtension;

    public MyThread(DbxClientV2 client, String nameOfTheFileWithExtension) {
        this.client = client;
        this.nameOfTheFileWithExtension = nameOfTheFileWithExtension;
    }

    @Override
    public void run() {

        // Upload the file with the name from the Builder to Dropbox
        try (InputStream in = new FileInputStream("/" + nameOfTheFileWithExtension)) {
            FileMetadata metadata = client.files().uploadBuilder("/" + nameOfTheFileWithExtension).uploadAndFinish(in);

        } catch (Exception e) {
            e.printStackTrace();
        }
        for (; ; ) {



        }
    }
}


