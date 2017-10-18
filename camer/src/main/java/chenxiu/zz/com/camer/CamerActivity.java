package chenxiu.zz.com.camer;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chenxiu on 2016/8/25.
 */
public abstract class CamerActivity extends Activity {

    public static String selectPath = "";


    public static ArrayList<String> mSlectPaths = new ArrayList<>();

    public static ArrayList<String> mImagesPaths = new ArrayList<>();

    public static final String KEY_FILE_PATH = "file_path";

    public static final String KEY_IMAGE_PATH = "IMAGE_path";

    public static ArrayList<Activity> mActivitys = new ArrayList<>();


    public static void addActivity(Activity activity){
        mActivitys.add(activity);
    }

    public static void clearActivity(){
        for(Activity activity : mActivitys){
            activity.finish();
        }
        mActivitys.clear();
    }
}
