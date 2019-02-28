package testgen;

import util.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;

import CommandLine.CmdLineCheck;
import CommandLine.CmdParse;
import org.apache.commons.cli.CommandLine;


public class Main
{
	public static String projectRootPath;
   
    public static void main(String[] args) {

    	/**
    	 * parse the args to command line object
    	 */
    	CommandLine 	cmd_line = CmdParse.cmdParse(args);
	if (cmd_line == null) { 
		return; 
	}
	/**
	 * validate the parameters
	 */
	CmdLineCheck.verifyEmpty(cmd_line);
	
	Global.bugid = cmd_line.getOptionValue("bugid");
	String bugid = Global.bugid;
    Global.repairtool = cmd_line.getOptionValue("repairtool");;
    String repairtool = Global.repairtool;
    Global.difftgendpath = cmd_line.getOptionValue("difftgendpath");
    Global.evosuitejpath = cmd_line.getOptionValue("evosuitejpath");
    Global.inputfpath = cmd_line.getOptionValue("inputfpath");
    String inputfpath=Global.inputfpath;
    Global.outputdpath = cmd_line.getOptionValue("outputdpath");
    String outputdpath=Global.outputdpath;
    Global.dependjpath = cmd_line.getOptionValue("dependjpath");
    Global.oracleinputfpath = cmd_line.getOptionValue("oracleinputfpath");
    String oracleinputfpath = Global.oracleinputfpath;   
    int trials = Global.evosuitetrials;
    int timeout = Global.evosuitetimeout;
    Global.testID = bugid + "_" + repairtool.toLowerCase();
    String projectRootPath=outputdpath + "/" + Global.testID;

	/**
	 * Read content from inputfpath and oracleinputfpath.
	 */
	List<String> inputDeltas = ReadInputs.readLine(inputfpath);
	List<String> oracles = ReadInputs.readLine(oracleinputfpath);
	

	List<Modification> modList = SynDeltaParser.parse(inputDeltas);
	List<MethodToBeInstrumented> oracleMedInstruList = OracleParser.parse(oracles);
	if (modList != null && !modList.isEmpty() &&
	    oracleMedInstruList != null && !oracleMedInstruList.isEmpty()) {
	    Main m = new Main();
	    m.testgen(bugid, repairtool, modList, oracleMedInstruList, trials, timeout, outputdpath);
	}
    }

   
    private boolean compileTestCases(String projectRootPath) {

	String difftgendpath = Global.difftgendpath;
	String dependjpath = Global.dependjpath;
	String libdpath = difftgendpath + "/lib";
	String compilepath =
	    ":"+projectRootPath+"/bug/instru1/build/classes:" //Instrumented Files First
	    +dependjpath+":"
	    +libdpath+"/myprinter.jar:"
	    +libdpath+"/commons-lang3-3.5.jar:"
	    +libdpath+"/junit-4.11.jar:"
	    +libdpath+"/evosuite-1.0.2.jar:"
	    +libdpath+"/servlet.jar";

	String tc_dpath = projectRootPath+"/testcase";
	String tc_build_dpath = tc_dpath+"/build";
	String tc_build_classes_dpath = tc_build_dpath+"/classes";
	File tc_dir = new File(tc_dpath);
	File tc_build_dir = new File(tc_build_dpath);
	File tc_build_classes_dir = new File(tc_build_classes_dpath);
	if (!tc_build_dir.exists()) { tc_build_dir.mkdir(); }
	if (!tc_build_classes_dir.exists()) { tc_build_classes_dir.mkdir(); }

	CompileResult comp_rslt = CompileExecutor.compile(tc_dir, compilepath, tc_dpath, tc_build_classes_dpath);
	if (comp_rslt.getExitValue() != 0) {
	    System.err.println("Failed Compiling the Test Cases.");
	    String[] comp_cmds = comp_rslt.getCompileCommands();
	    for (String comp_cmd : comp_cmds) {
		System.err.print(comp_cmd + " ");
	    }
	    System.err.println();
	    return false;
	}

	return true;
    }
    
    public void testgen(String bugid, String repair_tool, List<Modification> modList,
			List<MethodToBeInstrumented> oracleMedInstruList,
			int trials, int timeout, String outputPath) {

	Timer timer = Global.timer;
	timer.start();

	System.out.println("Initializing...");
	boolean status0 = InitialParams.init(bugid, repair_tool, modList, oracleMedInstruList, trials, timeout, outputPath);
	if (!status0) {
	    System.err.println("Initialization Failure.");
	    return;
	}
	System.out.println("Initializing Done.");


	System.out.println("Creating Instrumented Files...");
	boolean status1 = CreateInstrumentedFiles.createInstrumentedFiles(bugid, repair_tool, modList, oracleMedInstruList, outputPath);
	if (!status1) {
	    System.err.println("Create Instrumentation Files Failure.");
	    return;
	}
	System.out.println("Creating Instrumented Files Done.");


	System.out.println("Compiling Instrumented Files...");	
	boolean status2 = CompileInstrumentedFiles.compile(bugid, repair_tool, modList, oracleMedInstruList, outputPath);
	if (!status2) {
	    System.err.println("Compiling Instrumented Files Failure.");
	    return;
	}
	System.out.println("Compiling Instrumented Files Done.");

	System.out.println("Creating Test Target(s)...");
	List<TestTarget> testTargetList = CreateTestTargets.create(modList,outputPath);
	System.out.println("Creating Test Target(s) Done.");
	
	
	System.out.println("Compiling Test Target(s)...");
	boolean status3 = CompileTestTargets.compile(modList, oracleMedInstruList, outputPath);
	if (!status3) {
	    System.err.println("Compiling Target Programs Failure.");
	    return;
	}
	System.out.println("Compiling Test Target(s) Done.");


	System.out.println("Generating Test Case(s)...");
	AutomaticTestsGeneration.generateTest(timer, testTargetList, projectRootPath);
	System.out.println("Generating Test Case(s) Done");

	
	System.out.println("Compiling Test Case(s)...");
	boolean status4 = compileTestCases(projectRootPath);
	if (!status4) {
	    System.err.println("Compiling Test Cases Failure.");
	    return;
	}
	System.out.println("Compiling Test Case(s) Done.");
    }
}
