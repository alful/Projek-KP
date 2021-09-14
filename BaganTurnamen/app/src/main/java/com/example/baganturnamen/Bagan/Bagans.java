package com.example.baganturnamen.Bagan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.example.baganturnamen.Peserta;
import com.example.baganturnamen.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import de.blox.treeview.BaseTreeAdapter;
import de.blox.treeview.TreeNode;
import de.blox.treeview.TreeView;

public class Bagans extends AppCompatActivity {

    DatabaseReference databaseReference;
    ArrayAdapter arrayAdapter;
    ArrayList<String> arrayTampil=new ArrayList<>();
    ArrayList<String> arrayTampil1=new ArrayList<>();
    ArrayList<String> ALNama = new ArrayList<String>();
    ArrayList<Peserta> ALPeserta = new ArrayList<Peserta>();
    Button ssa;
    TreeView treeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bagans);
        ssa=findViewById(R.id.ssa);
        treeView = findViewById(R.id.idTreeView);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Peserta");
        Log.d("TAG", "onCreate: "+databaseReference);
        HashMap <String,String> datas=new HashMap<String, String>();

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                databaseReference = FirebaseDatabase.getInstance().getReference().child("Peserta").child(snapshot.getKey());
                String nama= (String)snapshot.child("nama").getValue();
                Log.d("TAG", "onChildAdded: "+nama);
                Long umur= (Long) snapshot.child("umur").getValue();
                String id=snapshot.getKey();
                String umr= Long.toString(umur);
                Log.d("TAG", "onChildAdded: "+umur);
                Peserta peserta = snapshot.getValue(Peserta.class);

                datas.put(id,nama);
                ALPeserta.add(peserta);
                ALNama.add(nama);
                Log.d("TAG", "onChildAddedss: "+datas+ALPeserta+ALNama);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ssa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get a random entry from the HashMap.
                Object[] crunchifyKeys = datas.keySet().toArray();
                Object key = crunchifyKeys[new Random().nextInt(crunchifyKeys.length)];
                System.out.println("************ Random Value ************ \n" + key + " :: " + datas.get(key));

                List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(datas.entrySet());

                // Bonus Crunchify Tips: How to Shuffle a List??
                // Each time you get a different order...
                System.out.println("\n************ Now Let's start shuffling list ************");
                Collections.shuffle(list);
                for (Map.Entry<String, String> entry : list) {
                    System.out.println(entry.getKey() + " :: " + entry.getValue());
                }

            }
        });

        BaseTreeAdapter<Viewholder> adapter = new BaseTreeAdapter<Viewholder>(this, R.layout.tree_view_node){
            @NonNull
            @Override
            public Viewholder onCreateViewHolder(View view){
                return new Viewholder(view);
            }

            @Override
            public void onBindViewHolder(Viewholder viewHolder, Object data, int position){
//                Peserta peserta = ALPeserta.get(30);
                viewHolder.textView.setText(data.toString());
//                viewHolder.textView.setText(peserta.getNama());
                viewHolder.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("TAG", "onClick: textview"+data+position);
                    }
                });
            }
        };

        treeView.setAdapter(adapter);

        String SData="Mahes";
        TreeNode Troot = new TreeNode("");
        TreeNode Troot2 = new TreeNode("");
        TreeNode Troot3 = new TreeNode("");
        TreeNode Troot4 = new TreeNode("");
        for(int x=0; x<ALNama.size(); x++){
            String SDatas = null;
            Troot = new TreeNode(ALNama.get(x));  //15 - 30
            Troot2 = new TreeNode(ALNama.get(x+1));

            if(x%2==0){
                Troot.addChild(Troot2);
            }
            else{
                Troot3.addChild(Troot4);
            }
        }
//        Troot.addChild(yy);//51
//        tp.addChild(to);

//        TreeNode root = new TreeNode("");
//        TreeNode DSA = new TreeNode("");
//        TreeNode aaa = new TreeNode("");
//        TreeNode bbb = new TreeNode("");
//        TreeNode ccc = new TreeNode("ccc");
//        TreeNode ddd = new TreeNode("ddd");
//        TreeNode eee = new TreeNode("eee");
//        TreeNode fff = new TreeNode("fff");
//        TreeNode ggg = new TreeNode("ggg");
//        TreeNode hhh = new TreeNode("hhh");
//        TreeNode iii = new TreeNode("");
//        TreeNode jjj = new TreeNode("jjj");
//        TreeNode ooo = new TreeNode("ooo");
//        TreeNode ppp = new TreeNode("");
//        TreeNode rrr = new TreeNode("rrr");
//        TreeNode mmm = new TreeNode("mmm");
//        TreeNode zzz = new TreeNode("");
//        TreeNode ttt = new TreeNode("ttt");
//        TreeNode qqq = new TreeNode("qqq");
//        TreeNode vvv = new TreeNode("");
//        TreeNode uuu = new TreeNode("uuu");
//        TreeNode kkk = new TreeNode("kkk");
//        TreeNode www = new TreeNode("www");
//        TreeNode wow = new TreeNode("wow");
//        TreeNode wu = new TreeNode("wu");
//        TreeNode cc = new TreeNode("cc");
//        TreeNode cf = new TreeNode("");
//        TreeNode cr = new TreeNode("");
//        TreeNode ct = new TreeNode("");
//        TreeNode fg = new TreeNode("");
//        TreeNode rt = new TreeNode("rt");
//        TreeNode yy = new TreeNode("");
//        TreeNode to = new TreeNode("");
//        TreeNode tp = new TreeNode("");
//
//
//        tp.addChild(yy);//51
//        tp.addChild(to);
//
//        yy.addChild(root);//41
//        yy.addChild(cr);
//
//        to.addChild(ct);//31
//        to.addChild(fg);
//
//        root.addChild(DSA);//22
//        root.addChild(aaa);
//
//        cr.addChild(bbb);//23
//        cr.addChild(iii);
//
//        ct.addChild(zzz);//22
//        ct.addChild(ppp);
//
//        fg.addChild(cf);//21
//        fg.addChild(vvv);
//
//        DSA.addChild(fff);//1
//        DSA.addChild(ggg);
//
//        aaa.addChild(ccc);//2
//        aaa.addChild(ddd);
//
//        bbb.addChild(eee);//3
//        bbb.addChild(hhh);
//
//        iii.addChild(jjj);//4
//        iii.addChild(ooo);
//
//        zzz.addChild(qqq);//5
//        zzz.addChild(ttt);
//
//        ppp.addChild(rrr);//6
//        ppp.addChild(mmm);
//
//        www.addChild(kkk);//7
//        www.addChild(uuu);
//
//        vvv.addChild(wow);//8
//        vvv.addChild(wu);
//
//        cf.addChild(cc);//9
//        cf.addChild(rt);

//        adapter.setRootNode(tp);

        adapter.setRootNode(Troot);


    }
}
