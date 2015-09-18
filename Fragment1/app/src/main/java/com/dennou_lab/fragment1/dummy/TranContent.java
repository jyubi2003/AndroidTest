package com.dennou_lab.fragment1.dummy;

import android.app.AlertDialog;

import com.nifty.cloud.mb.FindCallback;
import com.nifty.cloud.mb.NCMB;
import com.nifty.cloud.mb.NCMBException;
import com.nifty.cloud.mb.NCMBObject;
import com.nifty.cloud.mb.NCMBQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.content.Context;
/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class TranContent {

    /**
     * An array of TranData items.
     */
    public static List<TranItem> ITEMS = new ArrayList<TranItem>();

    /**
     * A map of TranData items, by ID.
     */
    public static Map<String, TranItem> ITEM_MAP = new HashMap<String, TranItem>();

    /**
     * constructor
     */
    public TranContent(){
    }

    /**
     * Read Content
     */
    public void ReadContent(Context context){

        NCMB.initialize(context, "2d2329a32f2b834527eb0d2a301032f2a27df4dd6814f95cb7e966a6f832020c", "cbf48ac77f3edfff2319c4185d7def9e11aa62d38b6bd5c38570d114bf25afb9");

        NCMBQuery<NCMBObject> query = NCMBQuery.getQuery("TestClass");
        //query.whereEqualTo("message", "Hello, NCMB!");
        query.findInBackground(new FindCallback<NCMBObject>() {
            @Override
            public void done(List<NCMBObject> result, NCMBException e) {
                clrItem();
                if (!result.isEmpty()) {
                    for (int i=0; i<result.size(); i++){
                        addItem(new TranItem("item", result.get(i).toString()));
                    }
                } else {
                    addItem(new TranItem("item", "取引はありません"));
                }
            }
        });
    }

    /**
     * add item
     */
    private static void addItem(TranItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * clear item
     */
    private static void clrItem() {
        ITEMS.clear();
        ITEM_MAP.clear();
    }

    /**
     * post data
     */
    private void PostData(){
        NCMBObject TestClass = new NCMBObject("TestClass");
        TestClass.put("message", "Hello, NCMB!");
        TestClass.saveInBackground();
    }

    /*
    private void dispMessage(NCMBObject message){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mParent);

        alertDialogBuilder.setTitle("データ取得");
        alertDialogBuilder.setMessage(message.getString("message"));
        alertDialogBuilder.show();
    }
    */

    /**
     * A dummy item representing a piece of content.
     */
    public static class TranItem {
        public String id;
        public String content;

        public TranItem(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
