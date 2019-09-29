import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Date;

class FileAttributesFormatted {

    String getCreatedTimeFile(File file) {
        BasicFileAttributes attrs;
        try {
            attrs = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
            FileTime time = attrs.creationTime();
            String pattern = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            return simpleDateFormat.format(new Date(time.toMillis()));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    String formatDate(Long millis) {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date(millis));
    }

    String formatSize(Long size) {
        if (size > 1024 * 1024) {
            return getFileSizeMegaBytes(size);
        } else if (size > 1024 && size < 1024 * 1024) {
            return getFileSizeKiloBytes(size);
        } else {
            return getFileSizeBytes(size);
        }
    }

    private static String getFileSizeMegaBytes(Long size) {
        return size / (1024 * 1024) + " mb";
    }

    private static String getFileSizeKiloBytes(Long size) {
        return size / 1024 + "  kb";
    }

    private static String getFileSizeBytes(Long size) {
        return size + " bytes";
    }
}
