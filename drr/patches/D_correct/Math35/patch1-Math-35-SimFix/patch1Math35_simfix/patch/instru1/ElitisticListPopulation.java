/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.math3.genetics;

import java.util.Collections;
import java.util.List;

import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import myprinter.FieldPrinter;

/**
 * Population of chromosomes which uses elitism (certain percentage of the best
 * chromosomes is directly copied to the next generation).
 *
 * @version $Id$
 * @since 2.0
 */
public class ElitisticListPopulation extends ListPopulation {

    public static int eid_ElitisticListPopulation_ListLTEMPChromosomeRTEMP_int_double_7au3e = 0;
	public static int eid_ElitisticListPopulation_int_double_7au3e = 0;
	public static int eid_nextGeneration_7au3e = 0;
	public static int eid_setElitismRate_double_7au3e = 0;
	public static int eid_getElitismRate_7au3e = 0;
	public static Map oref_map = new HashMap();

	public static void addToORefMap(String msig, Object obj) {
		List l = (List) oref_map.get(msig);
		if (l == null) {
			l = new ArrayList();
			oref_map.put(msig, l);
		}
		l.add(obj);
	}

	public static void clearORefMap() {
		oref_map.clear();
		eid_ElitisticListPopulation_ListLTEMPChromosomeRTEMP_int_double_7au3e = 0;
		eid_ElitisticListPopulation_int_double_7au3e = 0;
		eid_nextGeneration_7au3e = 0;
		eid_setElitismRate_double_7au3e = 0;
		eid_getElitismRate_7au3e = 0;
	}

	/** percentage of chromosomes copied to the next generation */
    private double elitismRate = 0.9;

    /**
     * Creates a new ElitisticListPopulation instance.
     *
     * @param chromosomes list of chromosomes in the population
     * @param populationLimit maximal size of the population
     * @param elitismRate how many best chromosomes will be directly transferred to the
     *                    next generation [in %]
     * @throws OutOfRangeException if the elitism rate is outside the [0, 1] range
     */
    public void ElitisticListPopulation_7au3e(final List<Chromosome> chromosomes,
                                   final int populationLimit,
                                   final double elitismRate) {
        super(chromosomes, populationLimit);
        if(elitismRate<0||elitismRate>1){
        	throw new OutOfRangeException(LocalizedFormats.ELITISM_RATE,elitismRate,0,1);
        	}
        this.elitismRate = elitismRate;
    }

    /**
     * Creates a new ListPopulation instance and initializes its inner
     * chromosome list.
     *
     * @param populationLimit maximal size of the population
     * @param elitismRate how many best chromosomes will be directly transferred to the
     *                    next generation [in %]
     * @throws OutOfRangeException if the elitism rate is outside the [0, 1] range
     */
    public ElitisticListPopulation(final int populationLimit, final double elitismRate) {
        super(populationLimit);
        if(elitismRate<0||elitismRate>1){
        	throw new OutOfRangeException(LocalizedFormats.ELITISM_RATE,elitismRate,0,1);
        	}
        this.elitismRate = elitismRate;
    }

    /**
     * Start the population for the next generation. The <code>{@link #elitismRate}</code>
     * percents of the best chromosomes are directly copied to the next generation.
     *
     * @return the beginnings of the next generation.
     */
    public Population nextGeneration() {
        // initialize a new generation with the same parameters
        ElitisticListPopulation nextGeneration = new ElitisticListPopulation(this.getPopulationLimit(), this.getElitismRate());

        List<Chromosome> oldChromosomes = this.getChromosomes();
        Collections.sort(oldChromosomes);

        // index of the last "not good enough" chromosome
        int boundIndex = (int) FastMath.ceil((1.0 - this.getElitismRate()) * oldChromosomes.size());
        for (int i=boundIndex; i<oldChromosomes.size(); i++) {
            nextGeneration.addChromosome(oldChromosomes.get(i));
        }
        return nextGeneration;
    }

    /**
     * Sets the elitism rate, i.e. how many best chromosomes will be directly
     * transferred to the next generation [in %].
     *
     * @param elitismRate how many best chromosomes will be directly transferred to the
     *                    next generation [in %]
     * @throws OutOfRangeException if the elitism rate is outside the [0, 1] range
     */
    public void setElitismRate(final double elitismRate) {
        if (elitismRate < 0 || elitismRate > 1) {
            throw new OutOfRangeException(LocalizedFormats.ELITISM_RATE, elitismRate, 0, 1);
        }
        this.elitismRate = elitismRate;
    }

    /**
     * Access the elitism rate.
     * @return the elitism rate
     */
    public double getElitismRate() {
        return this.elitismRate;
    }

	/**
	 * Creates a new ElitisticListPopulation instance.
	 * @param chromosomes  list of chromosomes in the population
	 * @param populationLimit  maximal size of the population
	 * @param elitismRate  how many best chromosomes will be directly transferred to the next generation [in %]
	 * @throws OutOfRangeException  if the elitism rate is outside the [0, 1] range
	 */
	public ElitisticListPopulation(final List<Chromosome> chromosomes,
			final int populationLimit, final double elitismRate) {
		Object o_7au3e = null;
		String c_7au3e = "org.apache.commons.math3.genetics.ElitisticListPopulation";
		String msig_7au3e = "ElitisticListPopulation(List<Chromosome>$int$double)"
				+ eid_ElitisticListPopulation_ListLTEMPChromosomeRTEMP_int_double_7au3e;
		try {
			ElitisticListPopulation_7au3e(chromosomes, populationLimit,
					elitismRate);
			addToORefMap(msig_7au3e, null);
			addToORefMap(msig_7au3e, this);
			addToORefMap(msig_7au3e, null);
			addToORefMap(msig_7au3e, null);
			addToORefMap(msig_7au3e, null);
		} catch (Throwable t7au3e) {
			addToORefMap(msig_7au3e, t7au3e);
			throw t7au3e;
		} finally {
			eid_ElitisticListPopulation_ListLTEMPChromosomeRTEMP_int_double_7au3e++;
		}
	}

}
