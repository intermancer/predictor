package com.intermancer.predictor.gene;

import java.util.ArrayList;
import java.util.List;

import com.intermancer.predictor.data.ConsumeResponse;
import com.intermancer.predictor.data.Quantum;
import com.intermancer.predictor.mutation.MutationCommand;
import com.intermancer.predictor.mutation.MutationContext;

public abstract class AbstractGene implements Gene {
        
        public class GeneOffsetMC implements MutationCommand {
                
                private int index;
                
                public GeneOffsetMC(int index) {
                        this.index = index;
                }

                public void execute(MutationContext context) {
                        getOffsets()[index] = context.getMutationAssistant().getNewInt(getOffsets()[index], context); 
                }
                
                public int getIndex() {
                        return index;
                }

        }

        private Double[] values;

        public abstract int[] getOffsets();
        public abstract ConsumeResponse consumeInternal(Quantum quantum);
        
        public void init() {
        }

        @Override
        public int getHungryCycles() {
                return 0;
        }

        @Override
        public List<MutationCommand> assembleMutationCommandList() {
                List<MutationCommand> commandList = new ArrayList<MutationCommand>();
                for(int i = 0; i < getOffsets().length; i++) {
                        commandList.add(new GeneOffsetMC(i));
                }
                return commandList;
        }

        @Override
        public boolean equals(Object obj) {
                boolean matches = true;
                if(this.getClass().equals(obj.getClass())) {
                        AbstractGene gene = (AbstractGene) obj;
                        for(int i = 0; i < getOffsets().length; i++) {
                                if(getOffsets()[i] != gene.getOffsets()[i]) {
                                        matches = false;
                                        break;
                                }
                        }
                        return matches;
                }
                return false;
        }
        
        /**
         * The standard pattern for overriding hashCode() should be to
         * additively modify the value returned by super.hashCode().  This
         * implementation, therefore, takes into consideration offsets.
         * @return A value taking into account the offsets.
         */
        @Override
        public int hashCode() {
                int hashValue = 0;
                for(int offset : getOffsets()) {
                        hashValue += Math.abs(offset);
                }
                return 0;
        }

        @Override
        public ConsumeResponse consume(Quantum quantum) {
                int[] offsets = getOffsets();
                values = new Double[offsets.length];
                for(int i = 0; i < offsets.length; i++) {
                        values[i] = quantum.getChannel(offsets[i]).getValue();
                }
                return consumeInternal(quantum);
        }

        public Double[] getValues() {
                return values;
        }
        
        public double getIndexedValue(int index) {
                return getValues()[getOffsets()[index]];
        }

}