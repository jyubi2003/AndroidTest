package com.dennou_lab.fragment1;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import java.util.*;
import com.nifty.cloud.mb.*;
import android.app.AlertDialog;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TextFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class TextFragment extends Fragment {

    Activity mParent;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TextFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TextFragment newInstance() {
        TextFragment fragment = new TextFragment();
        return fragment;
    }
    public TextFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // HTML タグ付き文字列の作成
        String html = "<font color=#808080><b><big>購入入力</big></b><sup><small>&reg;</small></sup></font>";
        // fromHtml() の引数にタグ付き文字列を渡す
        CharSequence source = Html.fromHtml(html);
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_text, container, false);
        // inflateしたレイアウト内のTextView要素をid指定で参照する
        TextView textView = (TextView)view.findViewById(R.id.textStaticKounyu);
        // fromHtml() の戻り値を setText() に引数として渡し TextView に設定
        textView.setText(source);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mParent = activity;
    }

}

