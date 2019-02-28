package testgen;

import java.io.File;
import java.util.List;

public class CompileInstrumentedFiles {
	public static File projectFile;
	public static String compilepath;

	public static boolean compile(String bugid, String repairTool, List<Modification> modList,
			List<MethodToBeInstrumented> oracleMedInstrulList, String outputPath) {

		String testid = bugid + "_" + repairTool.toLowerCase();
		String projectPath = outputPath + "/" + testid;
		projectFile = new File(projectPath);

		/**
		 * create build path
		 */
		createBuildPath(projectPath, "instru0", "bug");
		createBuildPath(projectPath, "instru0", "patch");
		createBuildPath(projectPath, "instru0", "fix");
		createBuildPath(projectPath, "instru1", "bug");
		createBuildPath(projectPath, "instru1", "patch");

		/**
		 * get the dependency jar files for compiling
		 */

		compilepath = getCompileDependency(Global.difftgendpath, Global.dependjpath);

		/*
		 * compile each component
		 */
		compileComponent(projectPath, "instru0", "bug");
		compileComponent(projectPath, "instru0", "patch");
		compileComponent(projectPath, "instru0", "fix");
		compileComponent(projectPath, "instru1", "bug");
		compileComponent(projectPath, "instru1", "patch");
		
		/**
		 * if no exception is thrown, return true
		 */
		return true;
	}

	private static void compileComponent(String projectPath, String instru, String type) {
		String srcdpath = projectPath + "/" + type + "/" + instru;
		String desdpath = projectPath + "/" + type + "/" + instru + "/build/classes";
		CompileResult compileResult = CompileExecutor.compile(projectFile, compilepath, srcdpath, desdpath);
		if (compileResult.getExitValue() != 0) {
			System.err.println("Failed Compiling Faulty Program's Output Instrumented Files.");
			String[] compileCmds = compileResult.getCompileCommands();
			for (String compileCmd : compileCmds) {
				System.err.print(compileCmd + " ");
			}
			throw new RuntimeException(compileCmds + ": fail to be compiled!");
		}

	}

	private static String getCompileDependency(String difftgendpath, String dependjpath) {
		String libdpath = difftgendpath + "/lib";
		String compilepath = ":" + dependjpath + ":" + libdpath + "/myprinter.jar:" + libdpath
				+ "/commons-lang3-3.5.jar:" + libdpath + "/junit-4.11.jar:" + libdpath + "/evosuite-1.0.2.jar:"
				+ libdpath + "/servlet.jar";
		return compilepath;
	}

	public static void createBuildPath(String projectPath, String instruID, String type) {
		String build_dpath = projectPath + "/" + type + "/" + instruID + "/build";
		File build_dir = new File(build_dpath);
		if (!build_dir.exists()) {
			build_dir.mkdir();
			(new File(build_dpath + "/classes")).mkdir();
		}
	}

}
