package project.scode.com.menuselectdemo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2018/8/11.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    View layout_container,layout_temp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_menu, null);
        layout_container = view.findViewById(R.id.layout_container);
        layout_temp = view.findViewById(R.id.layout_temp);
        layout_container.setBackgroundColor(getResources().getColor(getColor()));
        layout_temp.setOnClickListener(this);
        return view;
    }

    abstract int getColor();


    @Override
    public void onClick(View view) {
        ((MainActivity) getActivity()).hideAnim();
    }

    public void showTemp(){
        if (layout_temp.getVisibility()==View.GONE)
        layout_temp.setVisibility(View.VISIBLE);
    }

    public void hideTemp(){
        if (layout_temp.getVisibility()==View.VISIBLE)
            layout_temp.setVisibility(View.GONE);
    }

}
