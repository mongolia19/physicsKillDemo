package net.xsmile.fv;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class MyFloatViewActivity extends Activity {
    /** Called when the activity is first created. */
	
	

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        	
        	Toast.makeText(getApplicationContext(), "flat", Toast.LENGTH_SHORT).show();
        	requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        	setContentView(R.layout.layout_gallery);
        	
        
   	       //delete();
   	        
   	  
            Integer[] images =new Integer[5] ;
            images[0]=R.drawable.joe;
            images[1]=R.drawable.bernuli;
            images[2]=R.drawable.einstein;
            images[3]=R.drawable.newton;
            images[4]=R.drawable.galio;
          
            ImageAdapter adapter = new ImageAdapter(this, images);
            adapter.createReflectedImages();//创建倒影效果
            GalleryFlow galleryFlow =(GalleryFlow) ((Gallery) this.findViewById(R.id.gallery));
            galleryFlow.setFadingEdgeLength(0);
            galleryFlow.setSpacing(-90); //图片之间的间
            galleryFlow.setAdapter(adapter);
            
            galleryFlow.setOnItemSelectedListener( new OnItemSelectedListener(){

				@Override
				public void onItemSelected(AdapterView parent, View view,  int position, long id) {
					// TODO Auto-generated method stub
					 //wupinname.setText(mgr.findExhibitById(Integer.valueOf(position), 1,0).name);
					
				}

				@Override
				public void onNothingSelected(AdapterView parent) {
					// TODO Auto-generated method stub
					
				}
            	
            }); 
           
            galleryFlow.setOnItemClickListener(new OnItemClickListener() {
                @SuppressWarnings("rawtypes")
				public void onItemClick(AdapterView parent, View view,
                        int position, long id) {
            
					
				
                   Intent intent=new Intent(MyFloatViewActivity.this,DuelActivity.class);
   	               Bundle bundle=new Bundle();
   	              
   	             
   	              
   	               bundle.putString("name", "kill");
   	               bundle.putString("PersonNum", String.valueOf(position));
   	               intent.putExtras(bundle);
   	               startActivity(intent);
                
                    //Toast.makeText(getApplicationContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
                }
               
            });

            
        
    
    }
    
  
    
    private void createView(){
    	
    	
    }
    
    @Override
    public void onDestroy(){
    	super.onDestroy();
    	//在程序退出(Activity销毁）时销毁悬浮窗口
    	
    }
    
   
    
}