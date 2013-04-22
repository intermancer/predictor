package com.intermancer.predictor.mutation;

/**
 * A repository for resources for use in mutation.
 * 
 * @author johnfryar
 * 
 */
public interface MutationContext {

  /**
   * Returns a MutationAssistant for use in mutation.
   * 
   * @return configured MutationAssistant
   */
  MutationAssistant getMutationAssistant();

	/**
	 * Returns a GeneFactory for use in mutation.
	 * 
	 * @return configured GeneFactory
	 */
	GeneFactory getGeneFactory();
	
	/**
	 * Returns a ChromosomeFactory for use in mutation.
	 * 
	 * @return configured ChromosomeFactory
	 */
	ChromosomeFactory getChromosomeFactory();

}
