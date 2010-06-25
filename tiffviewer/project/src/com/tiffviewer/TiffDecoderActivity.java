/* libtiffdecoder A tiff decoder run on android system. Copyright (C) 2009 figofuture
 *
 * This library is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser General Public 
 * License as published by the Free Software Foundation; either version 2.1 of the License, or (at your option) any later 
 * version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this library; if not, write to the Free 
 * Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA 
 * 
 * */

package com.tiffviewer;

import java.io.File;

//import com.tiffdecoder.TiffDecoder;

//import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Matrix;
import android.util.Log;
//import android.view.ViewGroup.LayoutParams;
//import android.widget.Gallery;
//import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LoadTiffFileDialog;
//import android.widget.TextView;
import android.net.Uri;
import android.os.Bundle;


public class TiffDecoderActivity extends LoadTiffFileDialog
{
	Bitmap mBitmap;
	LinearLayout mLinearLayout;
	private static final String LOG_TAG = "TiffDecoderActivity";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        /*mLinearLayout = new LinearLayout(this);
        ImageView image = new ImageView(this);
        nativeTiffOpen("/sdcard/test.tif");
        //int length = nativeTiffGetLength();
        Log.i(LOG_TAG,"width = " + nativeTiffGetWidth() + " height = " + nativeTiffGetHeight() );
        long begin = System.currentTimeMillis();
        int[] pixels = nativeTiffGetBytes();
        long end = System.currentTimeMillis();
		  Log.i(LOG_TAG,"the cost time is " + (end - begin) );
        mBitmap = Bitmap.createBitmap(pixels, nativeTiffGetWidth(), nativeTiffGetHeight(),Bitmap.Config.ARGB_8888);
        image.setImageBitmap(mBitmap);
        image.setAdjustViewBounds(true); // set the ImageView bounds to match the Drawable's dimensions
        image.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        // Add the ImageView to the layout and set the layout as the content view
        mLinearLayout.addView(image);

		setContentView(mLinearLayout);
		nativeTiffClose();*/
    }
    
    public void onPostFileAccessIntent( final File file )
    {
    	Intent imageIntent = new Intent(this,TiffImageViewer.class);
    	Uri data = Uri.fromFile(file);
    	imageIntent.setData(data);
    	startActivity(imageIntent);
    }
}
