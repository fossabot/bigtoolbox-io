package io.github.incplusplus.bigtoolbox.io.filesys;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.CONTINUE;

public class FileVisitor implements java.nio.file.FileVisitor<Path>
{
	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException
	{
		System.out.format("Visiting directory: %s%n", dir);
		return CONTINUE;
	}
	
	// Print information about
	// each type of file.
	@Override
	public FileVisitResult visitFile(Path file,
	                                 BasicFileAttributes attr) {
		if (attr.isSymbolicLink()) {
			System.out.format("Symbolic link: %s ", file);
		} else if (attr.isRegularFile()) {
			System.out.format("Regular file: %s ", file);
		} else {
			System.out.format("Other: %s ", file);
		}
		System.out.println("(" + attr.size() + "bytes)");
		return CONTINUE;
	}
	
	// Print each directory visited.
	@Override
	public FileVisitResult postVisitDirectory(Path dir,
	                                          IOException exc) {
		System.out.format("Leaving directory: %s%n", dir);
		return CONTINUE;
	}
	
	// If there is some error accessing
	// the file, let the user know.
	// If you don't override this method
	// and an error occurs, an IOException
	// is thrown.
	@Override
	public FileVisitResult visitFileFailed(Path file,
	                                       IOException exc) {
		System.err.println(exc);
		return CONTINUE;
	}
}
