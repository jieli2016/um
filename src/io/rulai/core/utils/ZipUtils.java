package io.rulai.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtils {

	public static void main(String[] args) throws Exception {
		boolean ok = zip("/tmp/test", "/tmp/t1.zip", true, 3);
		System.out.println("ok = " + ok);

		ok = zip("/tmp/test", "/tmp/t2.zip", false, 9);
		System.out.println("ok = " + ok);

		ok = zip("/tmp/test/", "/tmp/t3.zip", true, 1);
		System.out.println("ok = " + ok);

		ok = zip("/tmp/test/", "/tmp/t4.zip", false, 2);
		System.out.println("ok = " + ok);

		ok = zip("/tmp/test/test1/2.txt", "/tmp/t5.zip", false, 2);
		System.out.println("ok = " + ok);

		ok = zip("/tmp/test/test2", "/tmp/t6.zip", false, 2);
		System.out.println("ok = " + ok);

		ok = zip("/tmp/test/test2", "/tmp/t7.zip", true, 2);
		System.out.println("ok = " + ok);
		
		ok = unzipTo("/tmp/t1.zip", "/tmp/o1/");
		ok = unzipTo("/tmp/t2.zip", "/tmp/o2/");
		ok = unzipTo("/tmp/t3.zip", "/tmp/o3/");
		ok = unzipTo("/tmp/t4.zip", "/tmp/o4/");
		ok = unzipTo("/tmp/t5.zip", "/tmp/o5/");
		ok = unzipTo("/tmp/t6.zip", "/tmp/o6/");
		ok = unzipTo("/tmp/t7.zip", "/tmp/o7/");
	}

	public static boolean unzipTo(String zipFile, String targetPath)
			throws IOException {
		ZipInputStream zis = null;
		boolean failed = false;
		try {
			zis = new ZipInputStream(new FileInputStream(zipFile));
			ZipEntry entry;

			File file = new File(targetPath);
			if (!file.exists()) {
				file.mkdirs();
			}
			
			while ((entry = zis.getNextEntry()) != null) {
				File entryFile = new File(targetPath, entry.getName());
				if (entry.isDirectory()) {
					if (entryFile.exists()) {
						System.out.println("directory already exists: "
								+ entryFile);
						return false;
					} else {
						entryFile.mkdirs();
					}
				} else {
					// Make sure all folders exists (they should, but the safer,
					// the better ;-))
					if (entryFile.getParentFile() != null
							&& !entryFile.getParentFile().exists()) {
						entryFile.getParentFile().mkdirs();
					}

					// Create file on disk...
					if (!entryFile.exists()) {
						entryFile.createNewFile();
					}

					// and rewrite data from stream
					OutputStream os = null;
					try {
						os = new FileOutputStream(entryFile);
						int n = 0;
						byte[] buffer = new byte[1024 * 4];
						while ((n = zis.read(buffer)) > 0) {
							os.write(buffer, 0, n);
						}
					} catch (Exception e) {
						failed = true;
					} finally {
						if (os != null) {
							try {
								os.close();
							} catch (Exception e) {
								failed = true;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			failed = true;
		} finally {
			if (zis != null) {
				try {
					zis.close();
				} catch (Exception e) {
					failed = true;
				}
			}
		}
		return !failed;
	}

	public static boolean zip(String targetPath, String zipFile,
			boolean includingTargetRootDirectoryIfTargetIsDirectory, int level) {
		boolean failed = false;
		ZipOutputStream zos = null;
		try {
			File file = new File(targetPath);
			if (!file.exists()) {
				return false;
			}

			File ofile = new File(zipFile);
			File ofileParent = ofile.getParentFile();
			if (!ofileParent.exists()) {
				ofileParent.mkdirs();
			}

			FileOutputStream fos = new FileOutputStream(zipFile);
			zos = new ZipOutputStream(fos);
			zos.setLevel(level);

			String basePath = null;
			if (file.isDirectory()) {
				targetPath = file.getAbsolutePath();
				if (includingTargetRootDirectoryIfTargetIsDirectory) {
					File parent = file.getParentFile();
					basePath = parent == null ? "" + File.separatorChar
							: parent.getAbsolutePath();
				} else {
					basePath = file.getAbsolutePath();
				}
			} else {
				File parent = file.getParentFile();
				basePath = parent == null ? ("" + File.separatorChar) : parent
						.getAbsolutePath();
			}

			addEntry(zos, targetPath, basePath);
		} catch (Exception e) {
			failed = true;
		} finally {
			if (zos != null) {
				try {
					zos.close();
				} catch (Exception e) {
					failed = true;
				}
			}
		}

		return !failed;
	}

	private static void addEntry(ZipOutputStream zos, String filePath,
			String basePath) throws IOException {
		File f = new File(filePath);
		if (f.exists()) {
			if (f.isDirectory()) {
				addFolder(zos, filePath, basePath);
			} else {
				addFile(zos, filePath, basePath);
			}
		} else {
			throw new IOException("file not exists " + filePath);
		}
	}

	private static void addFile(ZipOutputStream zos, String filePath,
			String basePath) throws IOException {
		FileInputStream in = null;
		try {
			// extract the relative name for entry purpose
			String entryName = filePath.substring(basePath.length() + 1);
			ZipEntry ze = new ZipEntry(entryName);
			zos.putNextEntry(ze);
			in = new FileInputStream(filePath);
			int len;
			byte buffer[] = new byte[1024 * 4];
			while ((len = in.read(buffer)) > 0) {
				zos.write(buffer, 0, len);
			}
			zos.closeEntry();
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				throw e;
			}
		}
	}

	private static void addFolder(ZipOutputStream zos, String filePath,
			String basePath) throws IOException {
		try {
			File f = new File(filePath);
			if (!filePath.equalsIgnoreCase(basePath)) {
				String entryName = filePath.substring(basePath.length() + 1)
						+ File.separatorChar;
				ZipEntry ze = new ZipEntry(entryName);
				zos.putNextEntry(ze);
			}
			File f2[] = f.listFiles();
			for (int i = 0; i < f2.length; i++) {
				addEntry(zos, f2[i].getAbsolutePath(), basePath);
			}
		} catch (IOException e) {
			throw e;
		}
	}
}