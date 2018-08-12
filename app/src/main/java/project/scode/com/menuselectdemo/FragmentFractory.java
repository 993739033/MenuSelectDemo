package project.scode.com.menuselectdemo;


import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/8/11.
 */

public class FragmentFractory {
    public static HashMap<String, BaseFragment> fragments = new HashMap<>();
    public static Fragment getMeunFragment(String menuName){
        BaseFragment fragment = null;
        if (fragments.containsKey(menuName)){
            fragment = fragments.get(menuName);
        }else{
            switch (menuName){
                case "ml":
                    fragment = new MenuFragment();
                    fragments.put(menuName, fragment);
                    break;
                case "cd":
                    fragment = new CDFragment();
                    fragments.put(menuName, fragment);
                    break;
                case "fl":
                    fragment = new CategoryFragment();
                    fragments.put(menuName, fragment);
                    break;
            }
        }
            return fragment;
    }
}
