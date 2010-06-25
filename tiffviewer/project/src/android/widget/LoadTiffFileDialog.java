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

import java.util.Arrays;
import java.util.List;

import com.tiffviewer.R;

public class LoadTiffFileDialog extends FileDialog {

	@Override
	public List<String> getAcceptedFileTypes() {
		final String[] types = {"tif","tiff"};
		return Arrays.asList(types);
	}

	@Override
	public int getExternalStorageImage() {
		return com.tiffviewer.R.drawable.media_flash_sd_mmc;
	}

	@Override
	public int getFileImage() {
		// TODO Auto-generated method stub
		return R.drawable.image_tiff;
	}

	@Override
	public int getFolderImage() {
		return R.drawable.folder;
	}

	@Override
	public int getInternalStorageImage() {
		return com.tiffviewer.R.drawable.drive_harddisk;
	}

	@Override
	public int getParentFolderImage() {
		return R.drawable.parent;
	}

	@Override
	public int getTextView() {
		return R.layout.listactivities_textview;
	}

}
