package org.xiangbalao.baidu;




import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import baidu.GeometryDemo;

import com.baidu.mapapi.SDKInitializer;


public class MainActivity extends Activity {
	
	/**
	 * 构造广播监听类，监听 SDK key 验证以及网络异常广播
	 */
	public class SDKReceiver extends BroadcastReceiver {
		private static final String LTAG = "BAIDUTEST";

		public void onReceive(Context context, Intent intent) {
			String s = intent.getAction();
			Log.d(LTAG, "action: " + s);
//			TextView text = (TextView) findViewById(R.id.text_Info);
//			text.setTextColor(Color.RED);
		if (s.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)) {
//				text.setText("key 验证出错! 请在 AndroidManifest.xml 文件中检查 key 设置");
		
			Log.d(LTAG,  "key 验证出错! 请在 AndroidManifest.xml 文件中检查 key 设置");
		
		} else if (s
            .equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK)) {
//			    text.setText("key 验证成功! 功能可以正常使用");
//			    text.setTextColor(Color.YELLOW);
		}
			else if (s
				.equals(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR)) {
//				text.setText("网络出错");
			}
		}
	}

	private SDKReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
    	// 注册 SDK 广播监听者
		IntentFilter iFilter = new IntentFilter();
		iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK);
		iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
		iFilter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
		mReceiver = new SDKReceiver();
		registerReceiver(mReceiver, iFilter);
        
        findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				Intent mIntent =new Intent(MainActivity.this, GeometryDemo.class);
				
				startActivity(mIntent);
				
			}
		});
    }


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
