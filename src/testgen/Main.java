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
	List<MethodToBeInstrumented> oracle_med_instru_list = OracleParser.parse(oracles);
	if (modList != null && !modList.isEmpty() &&
	    oracle_med_instru_list != null && !oracle_med_instru_list.isEmpty()) {
	    Main m = new Main();
	    m.testgen(bugid, repairtool, modList, oracle_med_instru_list, trials, timeout, outputdpath);
	}
    }

   
    private boolean writeTestCaseToFile(TestCase tc, String projectRootPath) {
	String tc_full_name = tc.getTestCaseFullName();
	String rslt_tc_ctnt = tc.getTestCaseContent();
	if (rslt_tc_ctnt == null) { return false; }
	//Write to file
	String tc_name = null;
	int last_dot_index = tc_full_name.lastIndexOf(".");
	if (last_dot_index == -1) { tc_name = tc_full_name; }
	else { tc_name = tc_full_name.substring(last_dot_index+1); }
	String rslt_tc_fpath = projectRootPath + "/testcase/" + tc_name + ".java";
	File rslt_tc_f = new File(rslt_tc_fpath);
	try { FileUtils.writeStringToFile(rslt_tc_f, rslt_tc_ctnt, (String) null); }
	catch (Throwable t) {
	    t.printStackTrace(); System.err.println(t);
	    return false;
	}
	return true;
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
			List<MethodToBeInstrumented> oracle_med_instru_list,
			int trials, int timeout, String output_root_dpath) {

	Timer timer = Global.timer;
	timer.start();

	System.out.println("Initializing...");
	boolean status0 = InitialParams.init(bugid, repair_tool, modList, oracle_med_instru_list, trials, timeout, output_root_dpath);
	if (!status0) {
	    System.err.println("Initialization Failure.");
	    return;
	}
	System.out.println("Initializing Done.");


	System.out.println("Creating Instrumented Files...");
	boolean status1 = CreateInstrumentedFiles.createInstrumentedFiles(bugid, repair_tool, modList, oracle_med_instru_list, output_root_dpath);
	if (!status1) {
	    System.err.println("Create Instrumentation Files Failure.");
	    return;
	}
	System.out.println("Creating Instrumented Files Done.");


	System.out.println("Compiling Instrumented Files...");	
	boolean status2 = CompileInstrumentedFiles.compile(bugid, repair_tool, modList, oracle_med_instru_list, output_root_dpath);
	if (!status2) {
	    System.err.println("Compiling Instrumented Files Failure.");
	    return;
	}
	System.out.println("Compiling Instrumented Files Done.");

	System.out.println("Creating Test Target(s)...");
	List<TestTarget> tt_list = CreateTestTargets.create(modList,output_root_dpath);
	System.out.println("Creating Test Target(s) Done.");
	
	
	System.out.println("Compiling Test Target(s)...");
	boolean status3 = CompileTestTargets.compile(modList, oracle_med_instru_list, output_root_dpath);
	if (!status3) {
	    System.err.println("Compiling Target Programs Failure.");
	    return;
	}
	System.out.println("Compiling Test Target(s) Done.");


	System.out.println("Generating Test Case(s)...");
	boolean overfitting_break = Global.stopifoverfittingfound;
	TestCaseGenerator tcgen = new TestCaseGenerator();
	TestCase regression_tc = null, repair_tc = null, defective_tc = null;
	for (int i=0; i<tt_list.size(); i++) {
	    TestTarget tt = tt_list.get(i);
	    System.out.println("Working on Test Target No."+i+" for Test Case Generation.");
	    List<TestCase> tc_list = tcgen.generateTestCases(i+"", tt);
	    for (TestCase tc : tc_list) {
		if (regression_tc == null && tc.isRegressionIndicative()) {
		    regression_tc = tc;
		}
		if (repair_tc == null && tc.isRepairIndicative()) {
		    repair_tc = tc;
		}
		if (defective_tc == null && tc.isDefectiveIndicative()) {
		    defective_tc = tc;
		}
	    }
	    if ((overfitting_break && (regression_tc!=null || defective_tc!=null)) ||
	        (regression_tc!=null && repair_tc!=null && defective_tc!=null)) {
		if (!timer.isReset()) {
		    timer.end();
		    System.out.println("Total execution time: " + timer.getDurationInMillis());
		    timer.reset();
		}
		break;
	    }
	}

	if (!timer.isReset()) {
	    timer.end();
	    System.out.println("Total execution time: " + timer.getDurationInMillis());
	    timer.reset();
	}

	if (regression_tc==null && repair_tc==null && defective_tc==null) {
	    System.out.println("Found Nothing.");
	    return;
	}

	if (regression_tc != null) {
	    boolean write_tc = writeTestCaseToFile(regression_tc, projectRootPath);
	    if (!write_tc) { System.out.println("Write Regression Test Case Failure."); }
	}
	if (repair_tc != null) {
	    boolean write_tc = writeTestCaseToFile(repair_tc, projectRootPath);
	    if (!write_tc) { System.out.println("Write Repair Test Case Failure."); }
	}
	if (defective_tc != null) {
	    boolean write_tc = writeTestCaseToFile(defective_tc, projectRootPath);
	    if (!write_tc) { System.out.println("Write Defective Test Case Failure."); }
	}
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
