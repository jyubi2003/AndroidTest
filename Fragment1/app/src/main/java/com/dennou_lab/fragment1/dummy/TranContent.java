package com.dennou_lab.fragment1.dummy;

import android.app.AlertDialog;

import com.nifty.cloud.mb.core.DoneCallback;
import com.nifty.cloud.mb.core.FindCallback;
import com.nifty.cloud.mb.core.NCMB;
import com.nifty.cloud.mb.core.NCMBException;
import com.nifty.cloud.mb.core.NCMBObject;
import com.nifty.cloud.mb.core.NCMBQuery;

import java.util.ArrayList;
import java.util.Date;
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

        NCMBQuery<NCMBObject> query = new NCMBQuery<>("TestClass");
        //query.whereEqualTo("message", "Hello, NCMB!");
        query.findInBackground(new FindCallback<NCMBObject>() {
            @Override
            public void done(List<NCMBObject> result, NCMBException e) {
                clrItem();
                if (!result.isEmpty()) {
                    for (int i=0; i<result.size(); i++){
                        addItem(new TranItem(result.get(i)));
                    }
                } else {
                    addItem(new TranItem(new NCMBObject("TestApp1")));
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
        TestClass.saveInBackground(new DoneCallback() {
            @Override
            public void done(NCMBException e) {
                if(e != null){
                    //保存成功
                }else {
                    //保存失敗
                }
            }
        });
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
        public String objectId;
        public String Remarks;
        public String Application;
        public String Customer;
        public String message;
        public Date TranDateTime;
        public String DebitAccount;
        public String Unit;
        public String Amount;
        public String Tax;
        public String CreditAccount;
        public Date createDate;
        public Date updateDate;
        public String acl;

        public TranItem(NCMBObject content) {
            this.id = content.getString("objectId");
            this.content = content.getString("content");
            this.content = content.getString("objectId");
            this.Remarks = content.getString("Remarks");
            this.Application = content.getString("Application");
            this.Customer = content.getString("Customer");
            this.message = content.getString("message");
            this.TranDateTime = content.getDate("TranDateTime");
            this.DebitAccount = content.getString("DebitAccount");
            this.Unit = content.getString("Unit");
            this.Amount = content.getString("Amount");
            this.Tax = content.getString("Tax");
            this.CreditAccount = content.getString("CreditAccount");
            this.createDate = content.getCreateDate();
            this.updateDate = content.getUpdateDate();
            this.acl = content.getString("acl");
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
