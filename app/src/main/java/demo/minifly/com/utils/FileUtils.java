package demo.minifly.com.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

public class FileUtils {
	private String SDPATH = null;
	
	public FileUtils() {
		// 初始化当前SD卡的路径
		SDPATH = Environment.getExternalStorageDirectory()+"/";
	}
	
	// 得到当前SD卡的路径
	public String getSDPATH() {
		return SDPATH;
	}
	
	// 在SD卡上创建新文件
	public File createSDFile(String fileName) throws Exception {
		File file = new File(SDPATH + fileName);
		if (!file.exists()) {
			file.createNewFile();
		}
		
		return file;
	}
	
	// SD卡上创建一个文件夹
	public File createSDDir(String dirName) {
		File dir = new File(SDPATH + dirName);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return dir;
	}

	// 判断SD卡上文件或文件夹是否存在
	public boolean isFileExist(String fileName) {
		File file = new File(SDPATH + fileName);
		return file.exists();
	}
	
	// 得到文件
	public File getFile(String fileName) {
		return new File(SDPATH + fileName);
	}	

	public File getApkPath() {
		File file = new File(getSDPATH() + "wangcang/apks/");
		if (file.exists()) {
			return file;
		}
		else {
			file.mkdirs();
			return file;
		}
	}

	/**
	 * 录音文件夹
	 */
	public File getRecordPath(){File file = new File(getSDPATH() + "wangcang/record");
		if (file.exists()) {
			return file;
		}
		else {
			file.mkdirs();
			return file;
		}
	}

	public File getRecordFile(String name){
		File file = new File(getSDPATH() + "wangcang/record/" + name);
		if (file.exists()) {
			return file;
		}
		else {
			file.mkdirs();
			return file;
		}
	}

	public File getApkFile(String name) {
		File file = new File(getSDPATH() + "wangcang/apks/" + name);
		if (file.exists()) {
			return file;
		}
		else {
			file.mkdirs();
			return file;
		}
	}

	public File getImagesFile(String fileName) {
		return new File(getSDPATH() + "wangcang/images/" + fileName);
	}

	public File getImagesTempFile(String fileName) {
		return new File(getSDPATH() + "wangcang/tempImages/" + fileName);
	}

	// 删除由于压缩上传图片产生的临时文件
	public void deleteTempImage() {
		try {
			File dir = new File(getSDPATH() + "wangcang/tempImages/");
			if (dir.exists()) {
				File[] files = dir.listFiles();
				for (int i = 0; i < files.length; i++) {
					files[i].delete();
				}
			}
		}
		catch (Exception e) {

		}
	}

	public boolean copyFile(String srcFileName, String destFileName, boolean overlay) {
		File srcFile = new File(srcFileName);

		// 判断源文件是否存在
		if (!srcFile.exists()) {
			return false;
		} else if (!srcFile.isFile()) {
			return false;
		}

		// 判断目标文件是否存在
		File destFile = new File(destFileName);
		if (destFile.exists()) {
			// 如果目标文件存在并允许覆盖
			if (overlay) {
				// 删除已经存在的目标文件，无论目标文件是目录还是单个文件
				new File(destFileName).delete();
			}
		} else {
			// 如果目标文件所在目录不存在，则创建目录
			if (!destFile.getParentFile().exists()) {
				// 目标文件所在目录不存在
				if (!destFile.getParentFile().mkdirs()) {
					// 复制文件失败：创建目标文件所在目录失败
					return false;
				}
			}
		}

		// 复制文件
		int byteread = 0; // 读取的字节数
		InputStream in = null;
		OutputStream out = null;

		try {
			in = new FileInputStream(srcFile);
			out = new FileOutputStream(destFile);
			byte[] buffer = new byte[1024];

			while ((byteread = in.read(buffer)) != -1) {
				out.write(buffer, 0, byteread);
			}
			return true;
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		} finally {
			try {
				if (out != null)
					out.close();
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void nioTransferCopy(File source, File target) {
		FileChannel in = null;
		FileChannel out = null;
		FileInputStream inStream = null;
		FileOutputStream outStream = null;
		try {
			inStream = new FileInputStream(source);
			outStream = new FileOutputStream(target);
			in = inStream.getChannel();
			out = outStream.getChannel();
			in.transferTo(0, in.size(), out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inStream.close();
				in.close();
				outStream.close();
				out.close();
			}
			catch (Exception e) {

			}
		}
	}

	// 删除小于1000字节的文件
	public void check_cache() {
		try {
			File filesDir = new File(getSDPATH() + "wangcang/imgcache/");
			File files[] = filesDir.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].length() < 1000) {
					files[i].delete();
				}
			}
		}
		catch (Exception e) {
			
		}
	}
	
	// 传入一个大小，判断在sdcard上是否放得下
	@TargetApi(18)
	@SuppressLint("NewApi")
	public boolean isAvailableSize(String size, Context context) {
		boolean flag = true;
		
		try {
			File file = new File(SDPATH);
			StatFs statfs = new StatFs(file.getPath());
			long nBlocSize = 0;
			long nAvailaBlock = 0;
			int version = android.os.Build.VERSION.SDK_INT;
			Log.d("dongjie", "当前API:" + version);
			if (version < 18) {
				nBlocSize = statfs.getBlockSize();
				nAvailaBlock = statfs.getAvailableBlocks();
			}
			else {
				nBlocSize = statfs.getBlockSizeLong();
				nAvailaBlock = statfs.getAvailableBlocksLong();
			}
			
			long leftSize = nBlocSize * nAvailaBlock / 1024 / 1024;  // mb
			Log.d("dongjie", "剩余空间：" + leftSize);
			size = size.replace("MB", "").replace("mb", "").replace("Mb", "").replace("mB", "");
			if (size.contains(".")) {
				size = size.substring(0, size.indexOf("."));
			}
			int s = (Integer.parseInt(size) + 1) * 2;
			
			flag = leftSize - s > 0;
		}
		catch (Exception e) {
			Log.d("dongjie", e.toString());
		}
		
		return flag;
	}
	
	// 递归删除文件夹里面的东西
	public void deleteDirs(File file) {
		if(file.isFile()) {
		   file.delete();
		}
		else {
			File[] files = file.listFiles();
		    for(File f : files) {
			    deleteDirs(f); //递归删除每一个文件
			    f.delete(); //删除该文件夹
		    }
		}
	}
	
	// 清除缓存，但不删除文件夹
	public void cleanCache() {
		try {
			File file = new File("/mnt/sdcard/toomao/header/");
			File[] fs = file.listFiles();
			for (int i = 0; i < fs.length; i++) {
				fs[i].delete();
			}
			
			file = new File("/mnt/sdcard/toomao/imgcache/");
			fs = file.listFiles();
			for (int i = 0; i < fs.length; i++) {
				fs[i].delete();
			}
			
			file = new File("/mnt/sdcard/toomao/cameraimage/");
			fs = file.listFiles();
			for (int i = 0; i < fs.length; i++) {
				fs[i].delete();
			}
		}
		catch (Exception e) {
			
		}
	}

	public void saveImageToGallery(Context context, Bitmap bmp) {
		// 首先保存图片
		File appDir = new File(getSDPATH() + "toomao/images");
		if (!appDir.exists()) {
			appDir.mkdirs();
		}
		String fileName = System.currentTimeMillis() + ".jpg";
		File file = new File(appDir, fileName);
		try {
			FileOutputStream fos = new FileOutputStream(file);
			bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 其次把文件插入到系统图库
		try {
			MediaStore.Images.Media.insertImage(context.getContentResolver(),
					file.getAbsolutePath(), fileName, null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String path = getSDPATH() + "toomao/images/" + fileName;
		// 最后通知图库更新
		context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + path)));
	}
}