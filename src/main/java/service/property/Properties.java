package service.property;

import model.constats.Constants;
import org.json.JSONObject;
import service.DataUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

public class Properties {
    public static final String LIST_CANDIDATES_NAME = "listCandidates";
    private static File file = createResourceSubFolder();
    private static java.util.Properties props = new java.util.Properties();

    private Properties() {
    }

    private static File createResourceSubFolder() {
        File fileProperties;
        File fileDir = new File(Constants.PATH_DIR);
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }

        fileProperties = new File(fileDir.getAbsolutePath() + "\\" + Constants.PROPERTIES_NAME);
        if (!fileProperties.exists()) {
            try {
                fileProperties.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return fileProperties;
    }

    public static String getProperties(String key) throws IOException {
        String result = null;
        props.load(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));

        if (props.containsKey(key)) {
            result = props.getProperty(key);
        }
        return result;
    }

    public static void saveProperties(String key, String value) throws IOException {
        props.setProperty(key, value);
        props.store(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8), "Properties");
    }

    public static void saveProperties(String key, List<String> list) throws IOException {
        String line = JSONObject.valueToString(list);
        props.setProperty(key, line);
        props.store(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8), "Properties");

    }

    public static File getFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            return file;
        }
        return null;
    }

    public static List<String> getSettingForListCandidates() throws IOException {
        Object obj = getProperties(LIST_CANDIDATES_NAME);
        List<String> list = Collections.emptyList();
        if (obj != null) {
            list = DataUtils.toList(obj);
        }
        return list;

    }
}
