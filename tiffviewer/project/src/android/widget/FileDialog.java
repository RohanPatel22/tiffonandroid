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

package android.widget;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.tiffviewer.R;

import android.app.ListActivity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

public abstract class FileDialog extends ListActivity {

	private final static String LOG_TAG = "FileDialog";
	
    /**
     * text we use for the parent directory
     */
    private final static String PARENT_DIR = "..";
    
    /**
     * Currently displayed files
     */
    private final List<String> mCurrentFiles = new ArrayList<String>();
    
    /**
     * Currently displayed directory
     */
    private File mCurrentDir = null;

    public void onPostFileAccessIntent( final File file)
    {
    	
    }
    
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);

        // go to the root directory
        try {
        	showDirectory("/");
        } catch (NullPointerException e) {
            showDirectory("/");
        }
       getListView().setSelector(getResources().getDrawable(R.drawable.bk_listitem_focus));
    }

    protected void onListItemClick(final ListView l, final View v, final int position, final long id) {
        if (position == 0 && PARENT_DIR.equals(this.mCurrentFiles.get(0))) {
            showDirectory(this.mCurrentDir.getParent());
        } else {
            final File file = new File(this.mCurrentFiles.get(position));

            if (file.isDirectory()) {
                showDirectory(file.getAbsolutePath());
            } else {
            	onPostFileAccessIntent(file);
            }
        }
    }

    /**
     * Show the contents of a given directory as a selectable list
     * 
     * @param path	the directory to display
     */
    private void showDirectory(final String path) {
        // we clear any old content and add an entry to get up one level
        this.mCurrentFiles.clear();
        this.mCurrentDir = new File(path);
        if (this.mCurrentDir.getParentFile() != null) {
            this.mCurrentFiles.add(PARENT_DIR);
        }

        // get all directories and files in the given path
        final File[] files = this.mCurrentDir.listFiles();
        //if( null == files )
        	//return;
        final Set<String> sorted = new TreeSet<String>();

        if ( null != files ){
        for (final File file : files) {
            final String name = file.getAbsolutePath();

            if (file.isDirectory()) {
                sorted.add(name);
            } else {
                final String extension = name.indexOf('.') > 0 ? name.substring(name.lastIndexOf('.') + 1) : "";

                if (null == getAcceptedFileTypes() || getAcceptedFileTypes().contains(extension.toLowerCase())) {
                    sorted.add(name);
                }
            }
        }
        this.mCurrentFiles.addAll(sorted);
        }

        // display these images
        final Context context = this;

        ArrayAdapter<String> filenamesAdapter = new ArrayAdapter<String>(this, getTextView(), this.mCurrentFiles) {

            public View getView(final int position, final View convertView, final ViewGroup parent) {
                return new IconifiedTextLayout(context, getItem(position), position);
            }
        };

        setListAdapter(filenamesAdapter);
    }

    // new layout displaying a text and an associated image 
    class IconifiedTextLayout extends LinearLayout {

        public IconifiedTextLayout(final Context context, final String path, final int position) {
            super(context);

            setOrientation(HORIZONTAL);

            // determine icon to display
            final ImageView imageView = new ImageView(context);
            final File file = new File(path);

            if (position == 0 && PARENT_DIR.equals(path)) {
                imageView.setImageResource(getParentFolderImage());
            } else {
                if (file.isDirectory()) {
                	if( file.getName().equalsIgnoreCase("system") || file.getName().equalsIgnoreCase("data"))
                		imageView.setImageResource(getInternalStorageImage());
                	else if (file.getName().equalsIgnoreCase("sdcard"))
                		imageView.setImageResource(getExternalStorageImage());
                	else
                    imageView.setImageResource(getFolderImage());
                } else {
                    imageView.setImageResource(getFileImage());
                }
            }
            imageView.setPadding(0, 1, 5, 0);

            // create view for the directory name
            final TextView textView = new TextView(context);

            textView.setText(file.getName());
            Resources resources = getBaseContext().getResources();
            textView.setTextColor(resources.getColor(R.color.tab_tag_text_focus));
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(24);
            addView(imageView, new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            addView(textView, new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT));
        }
    }

    // methods to be implemented by subclasses
    /**
     * Get the list of file extensions which are accepted by the file dialog
     * 
     * @return	null to accept all files, or a list of lowercase file extensions
     */
    public abstract List<String> getAcceptedFileTypes();

    /**
     * Get the TextView resource used for the TextView inside the list
     * 
     * @return	layout resource id
     */
    public abstract int getTextView();

    /**
     * Get the image used for navigating to the parent folder
     * 
     * @return	image resource id
     */
    public abstract int getParentFolderImage();

    /**
     * Get the image denoting a folder
     * 
     * @return	image resource id
     */
    public abstract int getFolderImage();

    /**
     * Get the image denoting a file to select
     * 
     * @return	image resource id
     */
    public abstract int getFileImage();
    
    public abstract int getExternalStorageImage();
    
    public abstract int getInternalStorageImage();
}