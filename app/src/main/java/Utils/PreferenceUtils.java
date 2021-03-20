package Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
    public class PreferenceUtils {
        private SharedPreferences sharedPreferences;

        public PreferenceUtils(){

        }

        public static boolean saveEmail(String email, Context context) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor prefsEditor = prefs.edit();
            prefsEditor.putString(Utils.COL_EMAIL, email);
            prefsEditor.apply();
            return true;
        }

        public static String getEmail(Context context) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            return prefs.getString(Utils.COL_EMAIL, null);
        }

        public static boolean savePassword(String password, Context context) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor prefsEditor = prefs.edit();
            prefsEditor.putString(Utils.COL_PASSWORD, password);
            prefsEditor.apply();
            return true;
        }



    }
