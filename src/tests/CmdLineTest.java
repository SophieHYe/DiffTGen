package tests;
import static org.junit.Assert.assertEquals;

import org.apache.commons.cli.CommandLine;
import org.junit.Test;

import CommandLine.CmdParse;

public class CmdLineTest {
	
	@Test
	public void paramParseSucc() {
		String cmdString = "run.py -bugid bug1 -repairtool repairtool";
		String[] cmdlist = cmdString.split(" ");
		CommandLine 	cmdLine = CmdParse.cmdParse(cmdlist);
		assert(cmdLine.hasOption("bugid"));
		assertEquals("bug1",cmdLine.getOptionValue("bugid"));
	}

}
