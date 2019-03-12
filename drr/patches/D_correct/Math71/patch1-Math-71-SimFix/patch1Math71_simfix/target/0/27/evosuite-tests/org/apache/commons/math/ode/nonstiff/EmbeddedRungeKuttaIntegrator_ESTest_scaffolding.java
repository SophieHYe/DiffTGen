/**
 * Scaffolding file used to store all the setups needed to run 
 * tests automatically generated by EvoSuite
 * Tue Mar 12 00:15:53 GMT 2019
 */

package org.apache.commons.math.ode.nonstiff;

import org.evosuite.runtime.annotation.EvoSuiteClassExclude;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.junit.AfterClass;
import org.evosuite.runtime.sandbox.Sandbox;
import org.evosuite.runtime.sandbox.Sandbox.SandboxMode;

@EvoSuiteClassExclude
public class EmbeddedRungeKuttaIntegrator_ESTest_scaffolding {

  @org.junit.Rule 
  public org.junit.rules.Timeout globalTimeout = new org.junit.rules.Timeout(4000); 

  private static final java.util.Properties defaultProperties = (java.util.Properties) java.lang.System.getProperties().clone(); 

  private org.evosuite.runtime.thread.ThreadStopper threadStopper =  new org.evosuite.runtime.thread.ThreadStopper (org.evosuite.runtime.thread.KillSwitchHandler.getInstance(), 3000);

  @BeforeClass 
  public static void initEvoSuiteFramework() { 
    org.evosuite.runtime.RuntimeSettings.className = "org.apache.commons.math.ode.nonstiff.EmbeddedRungeKuttaIntegrator"; 
    org.evosuite.runtime.GuiSupport.initialize(); 
    org.evosuite.runtime.RuntimeSettings.maxNumberOfThreads = 100; 
    org.evosuite.runtime.RuntimeSettings.maxNumberOfIterationsPerLoop = 10000; 
    org.evosuite.runtime.RuntimeSettings.mockSystemIn = true; 
    org.evosuite.runtime.RuntimeSettings.sandboxMode = org.evosuite.runtime.sandbox.Sandbox.SandboxMode.RECOMMENDED; 
    org.evosuite.runtime.sandbox.Sandbox.initializeSecurityManagerForSUT(); 
    org.evosuite.runtime.classhandling.JDKClassResetter.init(); 
    initializeClasses();
    org.evosuite.runtime.Runtime.getInstance().resetRuntime(); 
  } 

  @AfterClass 
  public static void clearEvoSuiteFramework(){ 
    Sandbox.resetDefaultSecurityManager(); 
    java.lang.System.setProperties((java.util.Properties) defaultProperties.clone()); 
  } 

  @Before 
  public void initTestCase(){ 
    threadStopper.storeCurrentThreads();
    threadStopper.startRecordingTime();
    org.evosuite.runtime.jvm.ShutdownHookHandler.getInstance().initHandler(); 
    org.evosuite.runtime.sandbox.Sandbox.goingToExecuteSUTCode(); 
    setSystemProperties(); 
    org.evosuite.runtime.GuiSupport.setHeadless(); 
    org.evosuite.runtime.Runtime.getInstance().resetRuntime(); 
    org.evosuite.runtime.agent.InstrumentingAgent.activate(); 
  } 

  @After 
  public void doneWithTestCase(){ 
    threadStopper.killAndJoinClientThreads();
    org.evosuite.runtime.jvm.ShutdownHookHandler.getInstance().safeExecuteAddedHooks(); 
    org.evosuite.runtime.classhandling.JDKClassResetter.reset(); 
    resetClasses(); 
    org.evosuite.runtime.sandbox.Sandbox.doneWithExecutingSUTCode(); 
    org.evosuite.runtime.agent.InstrumentingAgent.deactivate(); 
    org.evosuite.runtime.GuiSupport.restoreHeadlessMode(); 
  } 

  public void setSystemProperties() {
 
    java.lang.System.setProperties((java.util.Properties) defaultProperties.clone()); 
    java.lang.System.setProperty("java.vm.vendor", "Oracle Corporation"); 
    java.lang.System.setProperty("java.specification.version", "1.8"); 
            java.lang.System.setProperty("java.home", "/usr/lib/jvm/java-8-openjdk-amd64/jre"); 
    java.lang.System.setProperty("user.dir", "/home/wasp/Desktop/ICSE18/DiffTGen/drr/patches/D_correct/Math71/patch1-Math-71-SimFix/patch1Math71_simfix/target/0/27"); 
    java.lang.System.setProperty("java.io.tmpdir", "/tmp"); 
    java.lang.System.setProperty("awt.toolkit", "sun.awt.X11.XToolkit"); 
    java.lang.System.setProperty("file.encoding", "UTF-8"); 
    java.lang.System.setProperty("file.separator", "/"); 
        java.lang.System.setProperty("java.awt.graphicsenv", "sun.awt.X11GraphicsEnvironment"); 
    java.lang.System.setProperty("java.awt.headless", "true"); 
    java.lang.System.setProperty("java.awt.printerjob", "sun.print.PSPrinterJob"); 
    java.lang.System.setProperty("java.class.path", "/tmp/EvoSuite_pathingJar7154309555684530727.jar"); 
    java.lang.System.setProperty("java.class.version", "52.0"); 
        java.lang.System.setProperty("java.endorsed.dirs", "/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/endorsed"); 
    java.lang.System.setProperty("java.ext.dirs", "/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/ext:/usr/java/packages/lib/ext"); 
    java.lang.System.setProperty("java.library.path", "lib"); 
    java.lang.System.setProperty("java.runtime.name", "OpenJDK Runtime Environment"); 
    java.lang.System.setProperty("java.runtime.version", "1.8.0_191-8u191-b12-2ubuntu0.16.04.1-b12"); 
    java.lang.System.setProperty("java.specification.name", "Java Platform API Specification"); 
    java.lang.System.setProperty("java.specification.vendor", "Oracle Corporation"); 
        java.lang.System.setProperty("java.vendor", "Oracle Corporation"); 
    java.lang.System.setProperty("java.vendor.url", "http://java.oracle.com/"); 
    java.lang.System.setProperty("java.version", "1.8.0_191"); 
    java.lang.System.setProperty("java.vm.info", "mixed mode"); 
    java.lang.System.setProperty("java.vm.name", "OpenJDK 64-Bit Server VM"); 
    java.lang.System.setProperty("java.vm.specification.name", "Java Virtual Machine Specification"); 
    java.lang.System.setProperty("java.vm.specification.vendor", "Oracle Corporation"); 
    java.lang.System.setProperty("java.vm.specification.version", "1.8"); 
    java.lang.System.setProperty("java.vm.version", "25.191-b12"); 
    java.lang.System.setProperty("line.separator", "\n"); 
    java.lang.System.setProperty("os.arch", "amd64"); 
    java.lang.System.setProperty("os.name", "Linux"); 
    java.lang.System.setProperty("os.version", "4.15.0-43-generic"); 
    java.lang.System.setProperty("path.separator", ":"); 
    java.lang.System.setProperty("user.country", "US"); 
    java.lang.System.setProperty("user.home", "/home/wasp"); 
    java.lang.System.setProperty("user.language", "en"); 
    java.lang.System.setProperty("user.name", "wasp"); 
    java.lang.System.setProperty("user.timezone", "Europe/Stockholm"); 
  }

  private static void initializeClasses() {
    org.evosuite.runtime.classhandling.ClassStateSupport.initializeClasses(EmbeddedRungeKuttaIntegrator_ESTest_scaffolding.class.getClassLoader() ,
      "org.apache.commons.math.ode.DerivativeException",
      "org.apache.commons.math.ode.nonstiff.AdamsBashforthIntegrator",
      "org.apache.commons.math.ode.nonstiff.ClassicalRungeKuttaIntegrator",
      "org.apache.commons.math.MathException",
      "org.apache.commons.math.ode.sampling.StepHandler",
      "org.apache.commons.math.analysis.polynomials.PolynomialFunctionLagrangeForm",
      "org.apache.commons.math.ode.nonstiff.GillIntegrator",
      "org.apache.commons.math.util.OpenIntToDoubleHashMap",
      "org.apache.commons.math.linear.FieldMatrixChangingVisitor",
      "org.apache.commons.math.analysis.UnivariateRealFunction",
      "org.apache.commons.math.ode.sampling.DummyStepInterpolator",
      "org.apache.commons.math.ode.events.EventException",
      "org.apache.commons.math.ode.nonstiff.AdamsNordsieckTransformer",
      "org.apache.commons.math.linear.DefaultRealMatrixChangingVisitor",
      "org.apache.commons.math.ode.MultistepIntegrator",
      "org.apache.commons.math.ode.nonstiff.GraggBulirschStoerStepInterpolator",
      "org.apache.commons.math.ode.nonstiff.AdamsIntegrator",
      "org.apache.commons.math.analysis.ComposableFunction$2",
      "org.apache.commons.math.analysis.ComposableFunction$1",
      "org.apache.commons.math.analysis.ComposableFunction$4",
      "org.apache.commons.math.analysis.ComposableFunction$3",
      "org.apache.commons.math.analysis.ComposableFunction$6",
      "org.apache.commons.math.analysis.polynomials.PolynomialFunction",
      "org.apache.commons.math.analysis.ComposableFunction$5",
      "org.apache.commons.math.util.CompositeFormat",
      "org.apache.commons.math.DuplicateSampleAbscissaException",
      "org.apache.commons.math.analysis.ComposableFunction$17",
      "org.apache.commons.math.analysis.ComposableFunction$16",
      "org.apache.commons.math.analysis.ComposableFunction$19",
      "org.apache.commons.math.analysis.ComposableFunction$18",
      "org.apache.commons.math.ode.FirstOrderDifferentialEquations",
      "org.apache.commons.math.MathRuntimeException",
      "org.apache.commons.math.analysis.ComposableFunction$13",
      "org.apache.commons.math.ode.SecondOrderDifferentialEquations",
      "org.apache.commons.math.linear.ArrayRealVector",
      "org.apache.commons.math.analysis.ComposableFunction$12",
      "org.apache.commons.math.analysis.ComposableFunction$15",
      "org.apache.commons.math.ArgumentOutsideDomainException",
      "org.apache.commons.math.MathRuntimeException$1",
      "org.apache.commons.math.analysis.ComposableFunction$14",
      "org.apache.commons.math.MathRuntimeException$2",
      "org.apache.commons.math.MathRuntimeException$3",
      "org.apache.commons.math.ode.AbstractIntegrator$EndTimeChecker",
      "org.apache.commons.math.MathRuntimeException$4",
      "org.apache.commons.math.analysis.ComposableFunction$11",
      "org.apache.commons.math.MathRuntimeException$5",
      "org.apache.commons.math.analysis.ComposableFunction$10",
      "org.apache.commons.math.MathRuntimeException$6",
      "org.apache.commons.math.MathRuntimeException$7",
      "org.apache.commons.math.MathRuntimeException$8",
      "org.apache.commons.math.ode.nonstiff.EulerStepInterpolator",
      "org.apache.commons.math.MathRuntimeException$9",
      "org.apache.commons.math.ode.nonstiff.HighamHall54Integrator",
      "org.apache.commons.math.ode.events.CombinedEventsManager",
      "org.apache.commons.math.ode.FirstOrderIntegrator",
      "org.apache.commons.math.ode.nonstiff.MidpointStepInterpolator",
      "org.apache.commons.math.linear.DecompositionSolver",
      "org.apache.commons.math.ode.sampling.DummyStepHandler$LazyHolder",
      "org.apache.commons.math.linear.RealVectorFormat",
      "org.apache.commons.math.analysis.ComposableFunction$28",
      "org.apache.commons.math.ode.MultistepIntegrator$CountingDifferentialEquations",
      "org.apache.commons.math.analysis.ComposableFunction$27",
      "org.apache.commons.math.analysis.ComposableFunction$29",
      "org.apache.commons.math.analysis.ComposableFunction$24",
      "org.apache.commons.math.ode.sampling.NordsieckStepInterpolator",
      "org.apache.commons.math.analysis.ComposableFunction$23",
      "org.apache.commons.math.analysis.ComposableFunction$26",
      "org.apache.commons.math.FieldElement",
      "org.apache.commons.math.ode.nonstiff.ThreeEighthesStepInterpolator",
      "org.apache.commons.math.analysis.ComposableFunction$25",
      "org.apache.commons.math.ode.nonstiff.ClassicalRungeKuttaStepInterpolator",
      "org.apache.commons.math.linear.RealMatrixPreservingVisitor",
      "org.apache.commons.math.analysis.ComposableFunction$8",
      "org.apache.commons.math.analysis.ComposableFunction$20",
      "org.apache.commons.math.analysis.ComposableFunction$7",
      "org.apache.commons.math.analysis.ComposableFunction$22",
      "org.apache.commons.math.ode.sampling.AbstractStepInterpolator",
      "org.apache.commons.math.analysis.ComposableFunction$9",
      "org.apache.commons.math.analysis.ComposableFunction$21",
      "org.apache.commons.math.ode.FirstOrderConverter",
      "org.apache.commons.math.ode.nonstiff.DormandPrince54StepInterpolator",
      "org.apache.commons.math.linear.SparseRealMatrix",
      "org.apache.commons.math.linear.MatrixIndexException",
      "org.apache.commons.math.FunctionEvaluationException",
      "org.apache.commons.math.linear.AbstractRealMatrix",
      "org.apache.commons.math.ode.nonstiff.HighamHall54StepInterpolator",
      "org.apache.commons.math.linear.BlockRealMatrix",
      "org.apache.commons.math.linear.OpenMapRealVector",
      "org.apache.commons.math.linear.AbstractRealVector",
      "org.apache.commons.math.linear.InvalidMatrixException",
      "org.apache.commons.math.analysis.ComposableFunction$35",
      "org.apache.commons.math.linear.RealVector",
      "org.apache.commons.math.analysis.ComposableFunction$34",
      "org.apache.commons.math.linear.RealMatrixChangingVisitor",
      "org.apache.commons.math.analysis.ComposableFunction$36",
      "org.apache.commons.math.analysis.ComposableFunction$31",
      "org.apache.commons.math.analysis.ComposableFunction$30",
      "org.apache.commons.math.analysis.ComposableFunction$33",
      "org.apache.commons.math.analysis.ComposableFunction$32",
      "org.apache.commons.math.linear.OpenMapRealMatrix",
      "org.apache.commons.math.ode.nonstiff.EulerIntegrator",
      "org.apache.commons.math.ode.nonstiff.RungeKuttaIntegrator",
      "org.apache.commons.math.ode.nonstiff.DormandPrince54Integrator",
      "org.apache.commons.math.ode.nonstiff.DormandPrince853Integrator",
      "org.apache.commons.math.ConvergenceException",
      "org.apache.commons.math.ode.AbstractIntegrator",
      "org.apache.commons.math.ode.sampling.DummyStepHandler",
      "org.apache.commons.math.analysis.DifferentiableUnivariateRealFunction",
      "org.apache.commons.math.linear.RealMatrix",
      "org.apache.commons.math.ode.nonstiff.DormandPrince853StepInterpolator",
      "org.apache.commons.math.ode.events.EventHandler",
      "org.apache.commons.math.ode.nonstiff.RungeKuttaStepInterpolator",
      "org.apache.commons.math.analysis.MultivariateRealFunction",
      "org.apache.commons.math.ode.sampling.StepNormalizer",
      "org.apache.commons.math.linear.RealVector$Entry",
      "org.apache.commons.math.linear.SparseRealVector",
      "org.apache.commons.math.ode.sampling.StepInterpolator",
      "org.apache.commons.math.ode.nonstiff.GillStepInterpolator",
      "org.apache.commons.math.ode.nonstiff.ThreeEighthesIntegrator",
      "org.apache.commons.math.analysis.BivariateRealFunction",
      "org.apache.commons.math.MathRuntimeException$10",
      "org.apache.commons.math.ode.nonstiff.GraggBulirschStoerIntegrator",
      "org.apache.commons.math.ode.nonstiff.AdaptiveStepsizeIntegrator",
      "org.apache.commons.math.ode.nonstiff.EmbeddedRungeKuttaIntegrator",
      "org.apache.commons.math.analysis.ComposableFunction",
      "org.apache.commons.math.linear.RealMatrixImpl",
      "org.apache.commons.math.ode.nonstiff.MidpointIntegrator",
      "org.apache.commons.math.MaxEvaluationsExceededException",
      "org.apache.commons.math.linear.AnyMatrix",
      "org.apache.commons.math.analysis.polynomials.PolynomialSplineFunction",
      "org.apache.commons.math.linear.Array2DRowRealMatrix",
      "org.apache.commons.math.ode.jacobians.FirstOrderIntegratorWithJacobians$EventHandlerWrapper",
      "org.apache.commons.math.ode.events.EventState",
      "org.apache.commons.math.ode.nonstiff.AdamsMoultonIntegrator",
      "org.apache.commons.math.linear.NonSquareMatrixException",
      "org.apache.commons.math.ode.ODEIntegrator",
      "org.apache.commons.math.linear.MatrixVisitorException",
      "org.apache.commons.math.ode.sampling.DummyStepHandler$1",
      "org.apache.commons.math.linear.DefaultRealMatrixPreservingVisitor",
      "org.apache.commons.math.analysis.polynomials.PolynomialFunctionNewtonForm",
      "org.apache.commons.math.ode.IntegratorException",
      "org.apache.commons.math.linear.FieldMatrix",
      "org.apache.commons.math.ode.sampling.FixedStepHandler"
    );
  } 

  private static void resetClasses() {
    org.evosuite.runtime.classhandling.ClassResetter.getInstance().setClassLoader(EmbeddedRungeKuttaIntegrator_ESTest_scaffolding.class.getClassLoader()); 

    org.evosuite.runtime.classhandling.ClassStateSupport.resetClasses(
      "org.apache.commons.math.ode.nonstiff.HighamHall54Integrator",
      "org.apache.commons.math.ode.sampling.AbstractStepInterpolator",
      "org.apache.commons.math.ode.nonstiff.RungeKuttaStepInterpolator",
      "org.apache.commons.math.ode.nonstiff.HighamHall54StepInterpolator",
      "org.apache.commons.math.ode.nonstiff.DormandPrince853Integrator",
      "org.apache.commons.math.ode.nonstiff.DormandPrince853StepInterpolator",
      "org.apache.commons.math.ode.nonstiff.DormandPrince54Integrator",
      "org.apache.commons.math.ode.nonstiff.DormandPrince54StepInterpolator",
      "org.apache.commons.math.ode.sampling.DummyStepHandler$LazyHolder",
      "org.apache.commons.math.MathException",
      "org.apache.commons.math.ode.IntegratorException",
      "org.apache.commons.math.ode.DerivativeException",
      "org.apache.commons.math.ConvergenceException",
      "org.apache.commons.math.MaxEvaluationsExceededException"
    );
  }
}
