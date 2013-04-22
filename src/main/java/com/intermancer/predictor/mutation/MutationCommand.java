package com.intermancer.predictor.mutation;

/**
 * MutationCommands are executable commands that will affect the structure or
 * settings of an Organism, Chromosome, or Gene.
 * 
 * @author johnfryar
 * 
 */
public interface MutationCommand {

	/**
	 * Perform the particular mutation.
	 * 
	 * @param context
	 *            A repository for resources for use in mutation.
	 */
	void execute(MutationContext context);

}